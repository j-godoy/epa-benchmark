import argparse
import configparser
import mujava_coverage
from run_test_epa import RunTestEPA
import run_test_epa
from make_report_resume import merge_final_results
import os
import time
import utils
import pit_mutants_histogram
import multiprocessing

class Subject:

    def __init__(self, name, instrumented_code_dir, mining_code_dir, original_code_dir, class_name, epa_path, mutants_dir, subdir_mutants, error_prot_list, all_mutants_list, ignore_mutants_list, extra_classpath):
        self.name = name
        self.instrumented_code_dir = instrumented_code_dir
        self.mining_code_dir = mining_code_dir
        self.original_code_dir = original_code_dir
        self.class_name = class_name
        self.epa_path = epa_path
        self.mutants_dir = mutants_dir
        self.subdir_mutants = subdir_mutants
        self.error_prot_list = error_prot_list
        self.all_mutants_list = all_mutants_list
        self.ignore_mutants_list = ignore_mutants_list
        self.extra_classpath = extra_classpath


class EPAConfig:

    def read_config_file(self, config_file):
        def replace_path_and_classpath(path):
            return replace_paths_separator(replace_classpath_separator(path))

        def replace_classpath_separator(classpath):
            if len(classpath) == 0:
                return classpath
            all_classpath = classpath.split(",")
            classpath = ""
            for path in all_classpath:
                classpath += setupdir(path) + os.path.pathsep
            return classpath

        def replace_paths_separator(path):
            path = path.replace("\\", os.path.sep)
            return path.replace("/", os.path.sep)

        def setupdir(path):
            path = replace_paths_separator(path.strip())
            user_home_dir = os.path.expanduser('~')
            curr_dir = os.path.join(user_home_dir, path)
            if os.path.exists(curr_dir):
                return curr_dir
            elif not os.path.exists(path):
                print("The path '{}' does not exists.".format(path))
                exit(1)
            return path
    
        config = configparser.ConfigParser()
        config.read(config_file)

        # Reads the configuration values that will be used in each run
        self.junit_jar = setupdir(config['DEFAULT']['JUnitJAR'])
        self.evosuite_classes = setupdir(config['DEFAULT']['EvoSuiteClasses'])
        self.evosuite_jar_path = setupdir(config['DEFAULT']['EvoSuiteJARPath'])
        self.evosuite_runtime_jar_path = setupdir(config['DEFAULT']['EvoSuiteRuntimeJARPath'])
        self.results_dir_name = setupdir(config['DEFAULT']['ResultsDirName'])
        self.randoop_jar_path = setupdir(config['DEFAULT']['RandoopJARPath'])
        self.javancss_jar_path = setupdir(config['DEFAULT']['JavaNCSSJARPath'])
        self.hamcrest_jar_path = setupdir(config['DEFAULT']['HamcrestJarPath'])
        workers = config['DEFAULT']['Workers']
        if workers == "ALL":
            self.workers = multiprocessing.cpu_count()
        else:
            self.workers = int(workers)


        # Reads each section witch defines a run
        # tests_to_run = []
        # runid = 0
        self.subjects = {}

        for section in config.sections():
            name = config[section]['Name']
            instrumented_code_dir = setupdir(config[section]['InstrumentedCodeDir'])
            mining_code_dir = setupdir(config[section]['MiningCodeDir'])
            original_code_dir = setupdir(config[section]['OriginalCodeDir'])
            class_name = config[section]['ClassName']
            epa_path = setupdir(config[section]['EPAPath'])
            mutants_dir = setupdir(config[section]['MutantsDir'])
            error_prot_list = setupdir(config[section]['ErrorProtList'])
            all_mutants_list = setupdir(config[section]['AllMutantsList'])
            try: # Que sea opcional tener la lista de mutantes a ignorar
                ignore_mutants_list = setupdir(config[section]['IgnoreMutantsList'])
            except:
                ignore_mutants_list = ""
            try: # Que sea opcional tener el classpath extra
                extra_classpath = config[section]['ExtraClasspath']
            except:
                extra_classpath = ""
            extra_classpath = replace_classpath_separator(extra_classpath)
            subdir_mutants = os.path.join(self.results_dir_name, "mutants")
            error_prot_list = utils.load_list_from_file(error_prot_list) if error_prot_list != "" else [] 
            all_mutants_list = utils.load_list_from_file(all_mutants_list) if all_mutants_list != "" else []
            ignore_mutants_list = utils.load_list_from_file(ignore_mutants_list) if ignore_mutants_list != "" else []
            self.subjects[section] = Subject(name, instrumented_code_dir, mining_code_dir, original_code_dir, class_name, epa_path, mutants_dir, subdir_mutants, error_prot_list, all_mutants_list, ignore_mutants_list, extra_classpath)
            
    
    def read_runs_file(self, runs_file):

        with open(runs_file) as f:
            lines = f.readlines()
            lines = [line.strip() for line in lines]

        tests_to_run = []
        for line in lines:
            # line comment
            if line.strip().startswith("#") or len(line.strip()) == 0:
                continue
            
            terms = line.split('*')
            subject_name = terms[0][1:-1]
            bug_type = terms[1][1:-1]
            stopping_condition = terms[2][1:-1]
            search_budget = terms[3][1:-1]
            criterion = terms[4][1:-1]
            method = int(terms[5])
            rep = int(terms[6])

            # Si el criterio es Randoop, entonces no es necesario ingresar una strategy
            if criterion.upper() in "randoop".upper():
                strategy = "randoop"
            else:
                strategy = terms[7][1:-1]
                strategy = run_test_epa.StrategyEvosuiteGeneration[strategy.upper()]

            subject = self.subjects[subject_name]
            mutant_list = subject.all_mutants_list
            if(bug_type.upper() == run_test_epa.BugType.ERRPROT.name):
                mutant_list = subject.error_prot_list
            
            utils.init_histogram(bug_type, subject_name, criterion, mutant_list, subject.ignore_mutants_list)
            
            runid = 0
            for __ in range(rep):
                tests_to_run.append(RunTestEPA(name=subject.name, strategy=strategy, junit_jar=self.junit_jar, instrumented_code_dir=subject.instrumented_code_dir, mining_code_dir=subject.mining_code_dir, original_code_dir=subject.original_code_dir, evosuite_classes=self.evosuite_classes, evosuite_jar_path=self.evosuite_jar_path, evosuite_runtime_jar_path=self.evosuite_runtime_jar_path, class_name=subject.class_name, epa_path=subject.epa_path, criterion=criterion, bug_type=bug_type, stopping_condition=stopping_condition, search_budget=search_budget, runid=runid, method=method, results_dir_name=self.results_dir_name, subdir_mutants=subject.subdir_mutants, error_prot_list=subject.error_prot_list, ignore_mutants_list=subject.ignore_mutants_list, hamcrest_jar_path=self.hamcrest_jar_path, randoop_jar_path=self.randoop_jar_path, javancss_jar_path=self.javancss_jar_path, extra_classpath=subject.extra_classpath))
                runid += 1

        return [tests_to_run[x:x + self.workers] for x in range(0, len(tests_to_run), self.workers)]
    
    def setupmujava_and_subjects(self, test_chunks):
        subjects = set()
        for chunk in test_chunks:
            for test in chunk:
                if test.name in self.subjects:
                    subject = self.subjects[test.name]
                    run_test_epa.setup_subjects(self.results_dir_name, subject.original_code_dir, subject.instrumented_code_dir, subject.mining_code_dir, subject.name, self.evosuite_classes, subject.class_name, subject.extra_classpath)
                    if test.method == 3:
                        subjects.add(subject)
        
        for subject in subjects:
            mujava_coverage.setup_mujava(subject.mutants_dir, subject.class_name, subject.subdir_mutants, subject.original_code_dir)


_start_time = time.time()
def init():
    global _start_time 
    _start_time = time.time()
    
def elapsed_time():
    t_sec = round(time.time() - _start_time)
    (t_min, t_sec) = divmod(t_sec,60)
    (t_hour,t_min) = divmod(t_min,60)
    return [t_hour, t_min, t_sec]
    
def print_elapsed_time():
    total_time = elapsed_time()
    print('Total time: {}hour:{}min:{}sec'.format(total_time[0], total_time[1], total_time[2]))

global finished_subjects
global total_subjects
if __name__ == '__main__':
    init()
    print("Starting at time {} ...\n".format(time.strftime("%Y-%m-%d   %H:%M:%S")))
    config = EPAConfig()
    parser = argparse.ArgumentParser()
    parser.add_argument("config_file", help="The config file needed to run epatesting. See config_example.ini for an example.")
    parser.add_argument("runs_file", help="The runs file needed to run epatesting. See runs_example.ini for an example.")
    args = parser.parse_args()

    config.read_config_file(args.config_file)
    test_chunks = config.read_runs_file(args.runs_file)
    
    config.setupmujava_and_subjects(test_chunks)
    
    final_results = []
    total_subjects = 0
    finished_subjects = 0
    for chunk in test_chunks:
        total_subjects = total_subjects + len(chunk)

    for chunk in test_chunks:
        for test in chunk:
            test.start()
        for test in chunk:
            test.join()
            resume_file = os.path.join(test.subdir_metrics,'resume.csv')
            if(os.path.exists(resume_file)):
                if(len(open(resume_file, 'r').readlines()) > 1): # Debe tener al menos una linea
                    final_results.append(resume_file)
            finished_subjects = finished_subjects + 1
            percent_finished = finished_subjects*100/total_subjects
            total_time = elapsed_time()
            print("=====================================>{} PROGRESS {}% ({}/{}) Elapsed time: {}:{}:{}<=====================================".format(time.strftime("%Y-%m-%d   %H:%M:%S"), percent_finished, finished_subjects, total_subjects, total_time[0], total_time[1], total_time[2]))
    
    if(len(final_results) > 0):
        merge_final_results(final_results, os.path.join(config.results_dir_name, 'results.csv'))
    utils.save_file(os.path.join(config.results_dir_name, "mujava_histogram.csv"), utils.get_mutant_histogram())
    utils.save_file(os.path.join(config.results_dir_name, "pit_histogram.csv"), pit_mutants_histogram.get_histogram())
    print("Done! at time {} ...\n".format(time.strftime("%Y-%m-%d   %H:%M:%S")))
    print_elapsed_time()
