import subprocess
import xml.etree.ElementTree as ET
import threading
import os
import shutil
import make_report_resume
import mujava_coverage
import utils
from pit_mutants_histogram import pit_mutants_histogram

from enum import Enum


class EpatestingMethod(Enum):
    ONLY_TESTGEN = 1
    ONLY_METRICS = 2
    BOTH = 3
    BOTH_WITHOUT_MUJAVA = 4
    ONLY_METRICS_WITHOUT_MUJAVA = 5
    ONLY_PIT_MUTANTS_HISTOGRAM = 6
    ONLY_TEST_SUITE_LOC_AND_EXCEPTION = 7
    
class BugType(Enum):
    ALL = 1
    ERRPROT = 2

class AssertType(Enum):
    ASSERT = 1
    NO_ASSERT = 2
    NO_ASSERT_EXCEPTION = 3


def run_evosuite(evosuite_jar_path, projectCP, class_name, criterion, epa_path, inferred_epa_xml_path, stopping_condition, search_budget, test_dir='test', report_dir='report'):
    #is_JDBCResultSet = "JDBCResultSet" in class_name
    #extra_parameters = "-Dassertions=\"false\" -Dminimize=\"false\"" if is_JDBCResultSet else ""
    extra_parameters = "-Dminimize=\"true\""
    command = 'java -jar {} -projectCP {} -class {} -criterion {} -mem=\"1048\" -Dstopping_condition={} -Dsearch_budget={} -Djunit_allow_restricted_libraries=true -Dp_functional_mocking=\"0.0\" -Dp_reflection_on_private=\"0.0\" -Duse_separate_classloader=\"false\" -Dwrite_covered_goals_file=\"false\" -Dwrite_all_goals_file=\"false\" -Dtest_archive=\"true\" -Dprint_missed_goals=\"true\" -Dtest_dir={} -Dreport_dir={} -Depa_xml_path={} -Dno_runtime_dependency=\"true\" -Dshow_progress=\"false\" -Dtimeout="4000" -Doutput_variables=\"TARGET_CLASS,criterion,Coverage,Total_Goals,Covered_Goals,Generations,Total_Time\" -Dassertions=\"true\" -Dcoverage=\"true\" -Djunit_check_timeout="600" -Dassertion_timeout="600" -Dinferred_epa_xml_path={} {} > {}gen_out.txt 2> {}gen_err.txt'.format(evosuite_jar_path, projectCP, class_name, criterion, stopping_condition, search_budget, test_dir, report_dir, epa_path, inferred_epa_xml_path, extra_parameters, test_dir, test_dir)
    utils.print_command(command)
    try:
        subprocess.check_output(command, shell=True)
    except:
        print("Error al correr evosuite en la generacion de test con el comando '{}'".format(command))
    
def run_randoop(projectCP, class_name, randoop_jar_path, testdir, search_budget):
    def remove_randoop_error_test(testdir):
        for test in os.listdir(testdir):
            if "ErrorTest" in test:
                test_file = os.path.join(testdir, test)
                os.unlink(test_file)
            
            """if not test_file.endswith(".java"):
                continue
            if not test[-1:].isdigit(): # Randoop generates a test file without tests.
                continue
            utils.rename_class(test_file, "RegressionTest", new_class_name)"""
        
    utils.make_dirs_if_not_exist(testdir)   
    sep = os.path.pathsep
    err_file = os.path.join(testdir, "err.txt")
    out_file = os.path.join(testdir, "out.txt")
    package = class_name.split(".")[0:-1]
    packages_dir = utils.get_package_dir(package)
    command = 'java -classpath {}{}{} randoop.main.Main gentests --testclass={} --time-limit={} --junit-package-name={} --npe-on-non-null-input=expected --junit-output-dir={} > {} 2> {}'.format(projectCP, sep, randoop_jar_path, class_name, search_budget, packages_dir.replace(os.path.sep, ".")[:-1], testdir, out_file, err_file)
    utils.print_command(command)
    try:
        subprocess.check_output(command, shell=True)
    except:
        print("Error al correr randoop con el comando '{}'".format(command))
    testdir_full = os.path.join(testdir, packages_dir)
    remove_randoop_error_test(testdir_full)
    #change_class_name(testdir_full, class_name.split(".")[-1]+"_ESTest")

def workaround_test(test_dir, class_name, file_name, add_fails, assert_type):
    packages = class_name.split(".")[0:-1]
    packages_dir = utils.get_package_dir(packages)
    java_file = os.path.join(test_dir, packages_dir, file_name)
    utils.replace_assert_catch_in_test(java_file, assert_type)
    if(add_fails):
        utils.add_fails_in_test(java_file)

def measure_evosuite(evosuite_jar_path, projectCP, testCP, class_name, epa_path, report_dir, criterion):
    utils.make_dirs_if_not_exist(report_dir)
    err_file = os.path.join(report_dir, criterion.replace(":","_") + "_err.txt")
    out_file = os.path.join(report_dir, criterion.replace(":","_") + "_out.txt")
    sep = os.path.pathsep
    command = 'java -jar {} -projectCP {}{}{} -class {} -Depa_xml_path={} -criterion {} -Dwrite_covered_goals_file=\"true\" -Dwrite_all_goals_file=\"true\" -Dreport_dir={} -measureCoverage > {} 2> {}'.format(evosuite_jar_path, projectCP, sep, testCP, class_name, epa_path, criterion, report_dir, out_file, err_file)
    utils.print_command(command)
    try:
        subprocess.check_output(command, shell=True)
    except:
        print("Error al correr evosuite en la medicion de cobertura con el comando '{}'".format(command))

def setup_subjects(results_dir_name, original_code_dir, instrumented_code_dir, mining_code_dir, name, evosuite_classes, class_name):
    bin_original_code_dir = get_subject_original_bin_dir(results_dir_name, name)
    bin_instrumented_code_dir = get_subject_instrumented_bin_dir(results_dir_name, name)
    bin_mining_code_dir = get_subject_mining_bin_dir(results_dir_name, name)
    
    if not exist_subject(bin_original_code_dir, class_name):
        utils.compile_workdir(original_code_dir, bin_original_code_dir, evosuite_classes)
    if not exist_subject(bin_instrumented_code_dir, class_name):
        utils.compile_workdir(instrumented_code_dir, bin_instrumented_code_dir, evosuite_classes)
    if not exist_subject(bin_mining_code_dir, class_name):
        utils.compile_workdir(mining_code_dir, bin_mining_code_dir, evosuite_classes)
    
def get_subject_original_bin_dir(results_dir_name, subject):
    return os.path.join(get_subject_dir(results_dir_name, subject), "bin", "original")

def get_subject_instrumented_bin_dir(results_dir_name, subject):
    return os.path.join(get_subject_dir(results_dir_name, subject), "bin", "instrumented")

def get_subject_mining_bin_dir(results_dir_name, subject):
    return os.path.join(get_subject_dir(results_dir_name, subject), "bin", "mining")

def get_subject_dir(results_dir_name, subject):
    return os.path.join(results_dir_name, "subjects", subject)

def exist_subject(bin_code_dir, class_name):
    package_dir = utils.get_package_dir(class_name.split(".")[0:-1])
    if os.path.exists(os.path.join(bin_code_dir, package_dir)):
        return True
    return False

def edit_pit_pom(file_path, targetClasses, targetTests, output_file):

    def find_by_subtag(node, subtag):
        for child in node:
            if subtag in child.tag:
                return child

    def find_pit_plugin(plugins):
        for plugin in plugins:
            for child in plugin:
                if "groupId" in child.tag and child.text == "org.pitest":
                    return plugin

    tree = ET.parse(file_path)
    root = tree.getroot()
    build = find_by_subtag(root, "build")
    plugins = find_by_subtag(build, "plugins")
    pitest_plugin = find_pit_plugin(plugins)
    configuration = find_by_subtag(pitest_plugin, "configuration")
    # Changes the targetClasses
    configuration[0][0].text = targetClasses
    # Changes the targetTests
    configuration[1][0].text = targetTests
    """test_dir_sub = os.path.join(test_dir, package)
    for test in os.listdir(test_dir_sub):
        test_file = os.path.join(test_dir_sub, test)
        if not test_file.endswith(".java"):
            continue
        # ErrorTest files are generated by randoop. Contains error test. That fails in PIT
        if "ErrorTest" in test_file:
            continue
        test_file_name = test.replace(".java","")
        # Randoop generates a test file that call all test files (RegresionTest.java)
        if not test_file_name[-1:].isdigit():
            configuration[1][0].text = "{}{}".format(package.replace(os.path.sep, "."),test_file_name)
            break"""
            
    ET.register_namespace('', "http://maven.apache.org/POM/4.0.0")
    ET.register_namespace('xsi', "http://www.w3.org/2001/XMLSchema-instance")
    tree.write(output_file, default_namespace="")


def run_pitest(workdir):
    command = "mvn clean install org.pitest:pitest-maven:mutationCoverage > {}out.txt 2> {}err.txt".format(workdir, workdir)
    utils.print_command(command, workdir)
    try:
        subprocess.check_output(command, cwd=workdir, shell=True)
    except:
        print("Error al correr pitest con el comando '{}'".format(command))


def generate_pitest_workdir(pitest_dir):
    # To generate the pitest workdir we need the following hierachy:
    # pom.xml
    # src/main/java/ < source code we want to test
    # src/test/java/ < testsuite
    command_mkdir_home = "mkdir {}".format(pitest_dir)
    utils.print_command(command_mkdir_home)
    if not os.path.exists(pitest_dir):
        os.makedirs(pitest_dir)
    pitest_dir_src = os.path.join(pitest_dir, "src");
    command_mkdir_src = "mkdir {}".format(pitest_dir_src)
    utils.print_command(command_mkdir_src)
    if not os.path.exists(pitest_dir_src):
        os.makedirs(pitest_dir_src)

    pitest_dir_src_main = os.path.join(pitest_dir, "src", "main");
    command_mkdir_src_main = "mkdir {}".format(pitest_dir_src_main)
    utils.print_command(command_mkdir_src_main)
    if not os.path.exists(pitest_dir_src_main):
        os.makedirs(pitest_dir_src_main)

    pitest_dir_src_main_java = os.path.join(pitest_dir, "src", "main", "java")
    command_mkdir_src_main_java = "mkdir {}".format(pitest_dir_src_main_java)
    utils.print_command(command_mkdir_src_main_java)
    if not os.path.exists(pitest_dir_src_main_java):
        os.makedirs(pitest_dir_src_main_java)
    
    pitest_dir_src_test = os.path.join(pitest_dir, "src", "test")
    command_mkdir_src_test = "mkdir {}".format(pitest_dir_src_test)
    utils.print_command(command_mkdir_src_test)
    if not os.path.exists(pitest_dir_src_test):
        os.makedirs(pitest_dir_src_test)
        
    pitest_dir_src_test_java = os.path.join(pitest_dir, "src", "test", "java")        
    command_mkdir_src_test_java = "mkdir {}".format(pitest_dir_src_test_java)
    utils.print_command(command_mkdir_src_test_java)
    if not os.path.exists(pitest_dir_src_test_java):
        os.makedirs(pitest_dir_src_test_java)


def pitest_measure(pitest_dir, targetClasses, targetTests, class_dir, test_dir):
    generate_pitest_workdir(pitest_dir)
    edit_pit_pom('pit_pom.xml', targetClasses, targetTests, os.path.join(pitest_dir, "pom.xml"))

    pitest_dir_src_main_java = os.path.join(pitest_dir, "src", "main", "java")
    command_copy_source = 'cp -r {}/* {}'.format(class_dir, pitest_dir_src_main_java)
    utils.print_command(command_copy_source)
    # Si existe el directorio lo elimino (sino tira error shutil.copytree)
    if os.path.exists(pitest_dir_src_main_java):
        shutil.rmtree(pitest_dir_src_main_java)
    shutil.copytree(class_dir, pitest_dir_src_main_java)
    
    pitest_dir_src_test_java = os.path.join(pitest_dir, "src", "test", "java")
    command_copy_test = 'cp -r {}/* {}'.format(test_dir, pitest_dir_src_test_java)
    utils.print_command(command_copy_test)
    if os.path.exists(pitest_dir_src_test_java):
        shutil.rmtree(pitest_dir_src_test_java)
    shutil.copytree(test_dir, pitest_dir_src_test_java)

    run_pitest(os.path.join(pitest_dir, ""))

def get_mutation_csv_pit(pitest_dir):
    pit_reports_dir = os.path.join(pitest_dir, "target", "pit-reports")
    for date_dir in os.listdir(pit_reports_dir):
        mutations_csv = os.path.join(pit_reports_dir, date_dir, "mutations.csv")
        if os.path.exists(mutations_csv):
            return mutations_csv
    
def mujava_measure(bug_type, name, criterion, subdir_mutants, error_prot_list, ignore_mutants_list, bin_original_code_dir, generated_test_dir, class_name, junit_jar, hamcrest_jar, generated_report_mujava):
    mujava = mujava_coverage.MuJava(bug_type, name, criterion, subdir_mutants, error_prot_list, ignore_mutants_list, bin_original_code_dir, generated_test_dir, class_name, junit_jar, hamcrest_jar, generated_report_mujava)
    mujava.compute_mutation_score()


def copy_csv(file_path, file_name, all_report_dir):
    dest = os.path.join(all_report_dir, "{}.csv".format(file_name))
    command = 'cp {} {}'.format(file_path, dest)
    utils.print_command(command)
    shutil.copyfile(file_path, dest)


def copy_pitest_csv(name, workdir, all_report_dir):
    command = utils.find_and_save_command("*.csv", "sources.txt")
    utils.print_command(command, workdir)
    
    utils.lock_if_windows()
    subprocess.check_output(command, cwd=workdir, shell=True)
    utils.release_if_windows()

    with open(os.path.join(workdir, "sources.txt")) as file:
        for line in file:
            file_path = os.path.join(workdir, line[2:-1])
            if 'mutations' in line:
                copy_csv(file_path, '{}_mutations'.format(name), all_report_dir)
            elif 'jacoco' in line:
                copy_csv(file_path, '{}_jacoco'.format(name), all_report_dir)

def cp_testsuite_if_exists_in_other_results(curr_bug_type, subdir_testgen, generated_test_report_evosuite_dir, class_name, criterion):
    other_bug_type = BugType.ALL.name.lower() if(curr_bug_type.upper() == BugType.ERRPROT.name) else BugType.ERRPROT.name.lower()
    other_generated_test_dir = subdir_testgen.replace(curr_bug_type, other_bug_type)
    other_generated_test_report_evosuite_dir = generated_test_report_evosuite_dir.replace(curr_bug_type, other_bug_type)
    other_full_test_dir = os.path.join(other_generated_test_dir, "test", utils.get_package_dir(class_name.split(".")[0:-1]))
    testsuite_exists = check_if_exists_testgendir_in_other_bug_type(other_generated_test_report_evosuite_dir, other_full_test_dir, criterion)
    if(testsuite_exists):
        if os.path.exists(subdir_testgen):
            shutil.rmtree(subdir_testgen)
        shutil.copytree(other_generated_test_dir, subdir_testgen)
        print("TEST ALREADY GENERATED! coping from {}".format(other_generated_test_dir))
        #if other_bug_type == ERRPROT, then i need to move original test file (with asserts)
        if(other_bug_type.upper() == BugType.ERRPROT.name):
            curr_full_test_dir = other_full_test_dir.replace(other_bug_type, curr_bug_type)
            for test_file_name in os.listdir(curr_full_test_dir):
                if not test_file_name.endswith(".original"):
                    test_file_path = os.path.join(curr_full_test_dir, test_file_name)
                    os.unlink(test_file_path)
            for test_file_name in os.listdir(curr_full_test_dir):
                if test_file_name.endswith(".original"):
                    #test_file_path = test_file_path.replace(other_bug_type, curr_bug_type)
                    #os.unlink(test_file_path)
                    test_file_path = os.path.join(curr_full_test_dir, test_file_name)
                    shutil.move(test_file_path, test_file_path.replace(".original",""))
    return testsuite_exists

    
def check_if_exists_testgendir_in_other_bug_type(generated_test_report_evosuite_dir, test_dir, criterion):
    exists = os.path.exists(test_dir)
    if not exists:
        return False
    if not "randoop".upper() in criterion.upper():
        exists = exists and os.path.exists(os.path.join(generated_test_report_evosuite_dir, "statistics.csv"))
    exists_java_file = False
    for test_file_name in os.listdir(test_dir):
        if "test" in test_file_name.lower() and test_file_name.endswith(".java"):
            exists_java_file = True
            break
    exists = exists and exists_java_file
    return exists


# Para obtener TS LOC
def get_file_path_jncss(class_name, test_dir, results_dir_name, bug_type, stopping_condition, search_budget, criterion, runid, javancss_jar_path):
    package = class_name.split(".")[0:-1]
    package_dir = utils.get_package_dir(package)
    only_class_name = class_name.split(".")[-1]
    test_suite_file_path = os.path.join(test_dir, package_dir, only_class_name +"_ESTest.java")
    
    result_jncss_temp = os.path.join(results_dir_name, "javancss_temp", "{}_{}_{}_{}_{}".format(bug_type, stopping_condition, search_budget, class_name, criterion))
    utils.make_dirs_if_not_exist(result_jncss_temp)
    
    result_jncss_temp = os.path.join(result_jncss_temp, "{}.txt".format(runid))
    command = "java -jar {} {} > {}".format(javancss_jar_path, test_suite_file_path, result_jncss_temp)
    utils.print_command(command)
    subprocess.check_output(command, shell=True)
    return result_jncss_temp

lock = threading.Lock()
class RunTestEPA(threading.Thread):

    def __init__(self, name, junit_jar, instrumented_code_dir, mining_code_dir, original_code_dir, evosuite_classes, evosuite_jar_path, evosuite_runtime_jar_path, class_name, epa_path, criterion, bug_type, stopping_condition, search_budget, runid, method, results_dir_name, subdir_mutants, error_prot_list, ignore_mutants_list, hamcrest_jar_path, randoop_jar_path, javancss_jar_path):
        threading.Thread.__init__(self)

        self.subdir_testgen = os.path.join(results_dir_name, "testgen", name, bug_type, stopping_condition, search_budget, criterion.replace(':', '_').lower(), "{}".format(runid))
        utils.make_dirs_if_not_exist(self.subdir_testgen)
        self.subdir_metrics = os.path.join(results_dir_name, "metrics", name, bug_type, stopping_condition, search_budget, criterion.replace(':', '_').lower(), "{}".format(runid))
        self.generated_test_report_evosuite_dir = os.path.join(self.subdir_testgen, 'report_evosuite_generated_test')
        self.subdir_mutants = subdir_mutants
        self.resume_csv = os.path.join(self.subdir_metrics, 'resume.csv')
        self.inferred_epa_xml = os.path.join(self.subdir_metrics, 'inferred_epa.xml')

        self.name = name
        self.junit_jar = junit_jar
        self.instrumented_code_dir = instrumented_code_dir
        self.mining_code_dir = mining_code_dir
        self.original_code_dir = original_code_dir
        self.evosuite_classes = evosuite_classes
        self.evosuite_jar_path = evosuite_jar_path
        self.evosuite_runtime_jar_path = evosuite_runtime_jar_path
        self.class_name = class_name
        self.epa_path = epa_path
        self.criterion = criterion
        self.bug_type = bug_type
        self.generated_test_dir = os.path.join(self.subdir_testgen, 'test')
        self.generated_report_evosuite_dir = os.path.join(self.subdir_metrics, 'report_evosuite')
        self.generated_report_pitest_dir = os.path.join(self.subdir_metrics, 'report_pitest')
        self.generated_pitest_killer_test = os.path.join(self.generated_report_pitest_dir, 'killer_test')
        self.generated_report_mujava = os.path.join(self.subdir_metrics, 'report_mujava')
        self.stopping_condition = stopping_condition
        self.search_budget = search_budget
        self.runid = runid

        self.home_dir = os.path.dirname(os.path.abspath(__file__))
        self.bin_original_code_dir = get_subject_original_bin_dir(results_dir_name, name)
        self.bin_instrumented_code_dir = get_subject_instrumented_bin_dir(results_dir_name, name)
        self.bin_mining_code_dir = get_subject_mining_bin_dir(results_dir_name, name)
        self.results_dir_name = results_dir_name
        self.method = method
        self.assert_type = AssertType.ASSERT.name # default
        
        self.error_prot_list = error_prot_list
        self.ignore_mutants_list = ignore_mutants_list
        self.hamcrest_jar_path = hamcrest_jar_path
        self.randoop_jar_path = randoop_jar_path
        self.javancss_jar_path = javancss_jar_path

    def run(self):
        if self.method in [EpatestingMethod.ONLY_TESTGEN.value, EpatestingMethod.BOTH.value, EpatestingMethod.BOTH_WITHOUT_MUJAVA.value]:
            print('GENERATING TESTS')
            code_dir = self.instrumented_code_dir if "epa".upper() in self.criterion.upper() else self.original_code_dir
            if "mining".upper() in self.criterion.upper():
                code_dir = self.mining_code_dir
            
            bin_code_dir = self.bin_instrumented_code_dir if "epa".upper() in self.criterion.upper() else self.bin_original_code_dir
            if "mining".upper() in self.criterion.upper():
                bin_code_dir = self.bin_mining_code_dir
            
            # if exists testsuite in other bug_type, copy it!
            testsuite_exists = False
            curr_bug_type = self.bug_type
            try:
                lock.acquire()
                testsuite_exists = cp_testsuite_if_exists_in_other_results(curr_bug_type, self.subdir_testgen, self.generated_test_report_evosuite_dir, self.class_name, self.criterion)
            except:
                testsuite_exists = False
                print("error copying from other bug_type folder to {}".format(self.subdir_testgen))
            finally:
                lock.release()

            if(not testsuite_exists):
                if self.criterion == "randoop":
                    run_randoop(projectCP=bin_code_dir, class_name=self.class_name, randoop_jar_path=self.randoop_jar_path, testdir=self.generated_test_dir, search_budget=self.search_budget)
                else:
                    run_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=bin_code_dir, class_name=self.class_name, criterion=self.criterion, epa_path=self.epa_path, inferred_epa_xml_path=self.inferred_epa_xml, test_dir=self.generated_test_dir, stopping_condition=self.stopping_condition, search_budget=self.search_budget, report_dir=self.generated_test_report_evosuite_dir)

            add_fails= False
            if(self.bug_type.upper() == BugType.ERRPROT.name):
                # If is run in errprot mode, then always remove asserts and specific exceptions
                self.assert_type = AssertType.NO_ASSERT_EXCEPTION.name
                #if("JDBCResultSet" in self.name):
                    #add_fails= True;
            if self.assert_type.upper() in [AssertType.NO_ASSERT.name, AssertType.NO_ASSERT_EXCEPTION.name]:
                if "randoop".upper() in self.criterion.upper():
                    test_dir = self.generated_test_dir
                    packages_dir = utils.get_package_dir(self.class_name.split(".")[:-1])
                    test_dir_sub = os.path.join(test_dir, packages_dir)
                    for test_file_name in os.listdir(test_dir_sub):
                        test_file = os.path.join(test_dir_sub, test_file_name)
                        if not test_file.endswith(".java"):
                            continue
                        # ErrorTest files are generated by randoop. Contains error test. That fails in PIT
                        if "ErrorTest" in test_file:
                            continue
                        workaround_test(self.generated_test_dir, self.class_name, test_file_name, add_fails, self.assert_type)
                else:
                    test_file_name = self.class_name.split(".")[-1]+"_ESTest.java"
                    workaround_test(self.generated_test_dir, self.class_name, test_file_name, add_fails, self.assert_type)

            utils.compile_test_workdir(self.generated_test_dir, code_dir, self.junit_jar, self.evosuite_classes, self.evosuite_runtime_jar_path)

        criterion = get_alternative_criterion_names(self.criterion)
        
        if self.method in [EpatestingMethod.ONLY_METRICS.value, EpatestingMethod.BOTH.value, EpatestingMethod.BOTH_WITHOUT_MUJAVA.value, EpatestingMethod.ONLY_METRICS_WITHOUT_MUJAVA.value]:
            print('GENERATING METRICS')
            if not os.path.exists(self.subdir_testgen):
                print("not found testgen folder ! '{}'".format(self.subdir_testgen))
                exit(1)
            
            measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="epatransition")
            measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="epaexception")
            measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="epaadjacentedges")
            #ONLY to get exception goals in metrics folder
            #measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="line:branch:exception:epatransition:epaexception")
            #measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="line:branch:exception:epatransition")
            #measure_evosuite(evosuite_jar_path=self.evosuite_jar_path, projectCP=self.bin_instrumented_code_dir, testCP=self.generated_test_dir, class_name=self.class_name, epa_path=self.epa_path, report_dir=self.generated_report_evosuite_dir, criterion="line:branch:exception:epaadjacentedges")

            # Run Pitest to measure
            
            targetTests = "{}_ESTest".format(self.class_name)
            if "randoop".upper() in self.criterion.upper():
                targetTests = "{}.RegressionTest".format(utils.get_package_name_from_qualifiedname(self.class_name))
            pitest_measure(self.generated_report_pitest_dir, self.class_name, targetTests, self.original_code_dir.replace("mining","original"), self.generated_test_dir)
            #pitest_measure(self.generated_report_pitest_dir, self.class_name, self.original_code_dir, self.generated_test_dir, utils.get_package_dir(self.class_name.split(".")[0:-1]))
            
            if self.method in [EpatestingMethod.ONLY_METRICS.value, EpatestingMethod.BOTH.value]:
                mujava_measure(self.bug_type, self.name, self.criterion, self.subdir_mutants, self.error_prot_list, self.ignore_mutants_list, self.bin_original_code_dir.replace("mining","original"), self.generated_test_dir, self.class_name, self.junit_jar, self.hamcrest_jar_path, self.generated_report_mujava)

            # Resume the reports generated
            all_report_dir = os.path.join(self.subdir_metrics, 'all_reports')
            command_mkdir_report = 'mkdir {}'.format(all_report_dir)
            utils.print_command(command_mkdir_report)
            if not os.path.exists(all_report_dir):
                os.makedirs(all_report_dir)

            copy_pitest_csv(self.name, self.generated_report_pitest_dir, all_report_dir)
            
            
            
            statistics_csv = os.path.join(self.generated_report_evosuite_dir, "statistics.csv")
            copy_csv(statistics_csv, 'epacoverage_{}'.format(self.name), all_report_dir)
            
            statistics_testgen_csv = ""
            if not self.criterion == "randoop":
                try:
                    statistics_testgen_csv = os.path.join(self.generated_test_report_evosuite_dir, "statistics.csv")
                    copy_csv(statistics_testgen_csv, 'statistics_testgen_{}'.format(self.name), all_report_dir)
                except:
                    print("statistics_testgen_csv (generated by Evosuite) not found")
            mujava_csv = os.path.join(self.generated_report_mujava, "mujava_report.csv")
            if os.path.exists(mujava_csv):
                copy_csv(mujava_csv, 'mujava_{}'.format(self.name), all_report_dir)
            else:
                print("Does not exists mujava file {}".format(mujava_csv))
            
            epacoverage_csv = os.path.join(all_report_dir, "epacoverage_{}.csv".format(self.name))
            if self.criterion != "randoop":
                statistics_testgen_csv = os.path.join(all_report_dir, "statistics_testgen_{}.csv".format(self.name))
            jacoco_csv = os.path.join(all_report_dir, "{}_jacoco.csv".format(self.name))
            mutations_csv = os.path.join(all_report_dir, "{}_mutations.csv".format(self.name))
            
            #if self.bug_type.upper() == BugType.ALL.name:
            pit_mutants_histogram(self.criterion, self.search_budget, self.stopping_condition, mutations_csv, self.generated_test_dir, self.generated_pitest_killer_test, self.runid)
            # For test suite LOC
            result_jncss_temp = get_file_path_jncss(self.class_name, self.generated_test_dir, self.results_dir_name, self.bug_type, self.stopping_condition, self.search_budget, criterion, self.runid, self.javancss_jar_path)
            # For covered exceptions goals
            testgen_log_file_path = os.path.join(self.subdir_testgen, "testgen_out.txt")
            
            make_report_resume.resume(self.class_name, epacoverage_csv, statistics_testgen_csv, jacoco_csv, mutations_csv, self.resume_csv, self.runid, self.stopping_condition, self.search_budget, criterion, self.bug_type, mujava_csv, result_jncss_temp, testgen_log_file_path)
        
        if self.method in [EpatestingMethod.ONLY_PIT_MUTANTS_HISTOGRAM.value]:
            mutations_csv = get_mutation_csv_pit(self.generated_report_pitest_dir)
            pit_mutants_histogram(self.criterion, self.search_budget, self.stopping_condition, mutations_csv, self.generated_test_dir, self.generated_pitest_killer_test, self.runid)
        
        # Hack (for old executions)
        if self.method in [EpatestingMethod.ONLY_TEST_SUITE_LOC_AND_EXCEPTION.value]:
            #Para TS LOC
            result_jncss_temp = get_file_path_jncss(self.class_name, self.generated_test_dir, self.results_dir_name, self.bug_type, self.stopping_condition, self.search_budget, criterion, self.runid, self.javancss_jar_path)
            # Para obtener exceptions
            ####################
            testgen_log_file_path = os.path.join(self.subdir_testgen, "testgen_out.txt")
            # Este archivo tiene la cantidad de goals cubiertos para cada criterio (-measureCoverage)
            all_report_dir = os.path.join(self.subdir_metrics, 'all_reports')
            epacoverage_csv = os.path.join(all_report_dir, "epacoverage_{}.csv".format(self.name))
            # Este archivo tiene la suma de goals cubiertos (incluyendo criterio exception)
            statistics_testgen_csv = os.path.join(all_report_dir, "statistics_testgen_{}.csv".format(self.name))
            
            utils.make_dirs_if_not_exist(self.subdir_metrics)
            make_report_resume.resume_test_suite_loc_and_exceptions(self.class_name, self.resume_csv, self.runid, self.stopping_condition, self.search_budget, criterion, self.bug_type, result_jncss_temp, testgen_log_file_path, epacoverage_csv, statistics_testgen_csv)
        

def get_alternative_criterion_names(criterion):
    #if (criterion == "line:branch"):
    #   criterion = "evosuite_default"
    #if (criterion == "epatransition"):
    #    criterion = "evosuite_epaalone"
    #if (criterion == "line:branch:epatransition"):
    #    criterion = "evosuite_epamixed"
    return criterion.replace(":","_")