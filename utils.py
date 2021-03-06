import os
import shutil
import re
import subprocess
from sys import platform
import threading
import run_test_epa

def get_package_dir(package_array):
    packages_dir = ""
    for f in package_array:
        packages_dir += f
        packages_dir += os.path.sep
    return packages_dir

def get_package_name_from_qualifiedname(class_name):
    package_name = ""
    for f in class_name.split(".")[:-1]:
        package_name += f
        package_name += "."
    return package_name[:-1]

def remove_and_make_dirs(path):
    if os.path.exists(path):
        shutil.rmtree(path)
    os.makedirs(path)

def make_dirs_if_not_exist(path):
    lock.acquire()
    try:
        if not os.path.exists(path):
            os.makedirs(path)
    except Exception as e:
        print ("Error al ejecutar os.makedirs({}). Error {}".format(path, e))
    finally:
        lock.release()
    
def replace_assert_catch_in_test(java_file, assert_type):
    new_file = ""
    with open(java_file) as file:
        for line in file:
            if assert_type.upper() in [run_test_epa.AssertType.NO_ASSERT.name, run_test_epa.AssertType.NO_ASSERT_EXCEPTION.name]:
                line = re.sub('\sassert','//assert', line.rstrip())
                line = re.sub('\sorg.junit.Assert.assert','//org.junit.Assert.assert', line.rstrip())
            if assert_type.upper() == run_test_epa.AssertType.NO_ASSERT_EXCEPTION.name:
                line = re.sub('catch\\(\w*','catch(Exception', line.rstrip())
                line = re.sub('catch \\((\w.+\w)* ','catch (Exception ', line.rstrip())
            new_file += line+"\n"

    shutil.move(java_file, java_file+".original")
    save_file(java_file, new_file)

def add_fails_in_test(java_file):
    new_file = ""
    with open(java_file) as file:
        for line in file:
            if("catch(Exception" in line):
                line = "{}\n{}".format("\t\tfail();", line)
            new_file += line

    shutil.move(java_file, java_file+".original.withoutfails")
    save_file(java_file, new_file)
    
"""def rename_class(java_file, old_class_name, new_class_name):
    new_file_content = ""
    with open(java_file) as file:
        for line in file:
            line = re.sub("class " + old_class_name, "class " + new_class_name, line.rstrip())
            #line = re.sub("@Suite.SuiteClasses", "//@Suite.SuiteClasses", line.rstrip())
            #line = re.sub("@RunWith", "//@RunWith", line.rstrip())
            new_file_content += line + "\n"

    save_file(java_file, new_file_content)
    shutil.move(java_file, java_file.replace(old_class_name, new_class_name))"""
            
def save_file(path, content):
    file = open(path,"w")
    file.write(content)
    file.close()

#def compile_test_workdir(workdir, *classpath):
 #   compile_workdir(workdir, None, *classpath)

def compile_workdir(workdir, output_directory, *classpath):
    command_find = find_and_save_command("*.java", "sources.txt")
    print_command(command_find, workdir)
    
    lock_if_windows()
    try:
        subprocess.check_output(command_find, cwd=workdir, shell=True)
    except Exception as e:
        print("Error al ejecutar el comando '{}'. Error {}".format(command_find, e))
    finally:
        release_if_windows()

    #print_command("mkdir {}".format(output_directory))
    if (output_directory != None and not os.path.exists(output_directory)):
        os.makedirs(output_directory)
        
    all_classpath = ""
    for p in classpath:
        all_classpath += p + os.path.pathsep

    output_dir = "" if (output_directory == None) else "-d {}".format(output_directory)

    command_compile = "javac -classpath {} {} @sources.txt".format(all_classpath, output_dir)
    print_command(command_compile, workdir)
    lock_if_windows()
    try:
        subprocess.check_output(command_compile, cwd=workdir, shell=True)
    except Exception as e:
        print("Error al compilar con el comando '{}'. Error {}".format(command_compile, e))
    finally:
        release_if_windows()
    
def find_and_save_command(toFind, saveIn):
    command_win = "FORFILES /S /M {} /C \"CMD /C echo|set /p=@relpath & echo:\" > {}".format(toFind, saveIn)
    command_unix = "find . -name '{}' > {}".format(toFind, saveIn)
    command_find = command_win if (platform == "win32") else command_unix
    return command_find

def print_command(command, workdir=None):
    print('Executing command in shell:')
    if workdir is not None:
        print('In workdir: {}'.format(workdir))
    print(command)
    
lock = threading.Lock()
def lock_if_windows():
    if(platform == "win32"):
        lock.acquire()

def release_if_windows():
    if(platform == "win32"):
        lock.release()
        
def load_list_from_file(file):
    try:
        with open(file) as f:
            content = f.readlines()
    except FileNotFoundError:
        print("File '{}' does not exists!".format(file))
        return []
    list_item = []
    for line in content:
        list_item.append(line.replace("\n", ""))
    return list_item

mutants_histogram = {}
def init_histogram(bug_type, subject, criterion, mutants_list, ignore_list):
    global mutants_histogram
    for mutant in mutants_list:
        if mutant in ignore_list:
            continue
        key = get_key(bug_type, subject, criterion, mutant)
        if not key in mutants_histogram:
            mutants_histogram.update({key: 0})

def get_key(bug_type, subject, criterion, mutant):
    return "[{}] [{}] [{}] {}".format(bug_type, subject, criterion, mutant)

def count_mutant(bug_type, subject, criterion, mutant_name):
    lock.acquire()
    try:
        global mutants_histogram
        mutant_name_key = get_key(bug_type, subject, criterion, mutant_name)
        value = mutants_histogram[mutant_name_key] + 1
        mutants_histogram.update({mutant_name_key:value})
    except:
        is_errprot = bug_type.upper() == run_test_epa.BugType.ERRPROT.name
        err_prot = "NOERRPROT" if is_errprot else "ERROR"
        newkey = err_prot + mutant_name_key
        if not newkey in mutants_histogram:
            mutants_histogram.update({newkey: 1})
        else:
            value = mutants_histogram[newkey] + 1
            mutants_histogram.update({newkey:value})
            print("WARN! The mutant_name {} has been killed, but not included in the mutant_name list using the bug_type '{}'".format(mutant_name_key, bug_type))
    finally:
        lock.release()

def get_mutant_histogram():
    global mutants_histogram
    ret = "Mutant, # Killed"
    for criterion_mut in mutants_histogram.keys():
        ret += "\n{}, {}".format(criterion_mut, mutants_histogram[criterion_mut])
    return ret