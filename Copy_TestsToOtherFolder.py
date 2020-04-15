import argparse
import os
import utils
import re


def copy_test(inputs, output, id, n, qualified_name, keywords_to_remove_file, replace_in_imports, replace_original_testsuite):
    def get_rename_test(java_file, class_name, new_test_file_name, replace_in_imports):
        replace_import = "=" in replace_in_imports
        old = ""
        new = ""
        if replace_import:
            old = replace_in_imports.split("=")[0]
            new = replace_in_imports.split("=")[1]
        new_file = ""
        test_name = class_name + "_ESTest"
        with open(java_file) as file:
            for line in file:
                if replace_import and old in line:
                    line = re.sub(old,new, line.rstrip())+"\n"
                if(test_name in line):
                    line = re.sub(test_name,new_test_file_name, line.rstrip())
                new_file += line
        return new_file

    def remove_flacky_test(new_test_file_name, java_file, keywords_to_remove_file):
        def get_keywords_to_remove(keywords_to_remove_file, wich):
            with open(keywords_to_remove_file) as f:
                lines = f.readlines()
                #lines = [line.strip() for line in lines]
            keywords_to_remove = []
            for line in lines:
                line = line.strip()
                # line comment
                if line.startswith("#") or len(line) == 0:
                    continue
                if("ALL" in line):

                    keywords_to_remove.extend(line.split('=')[1].strip().split(","))
                    continue
                if(wich in line):
                    keywords_to_remove.extend(line.split('=')[1].strip().split(","))
            return keywords_to_remove


        def remove_line(line, keywords_to_remove_array):
            remove_line = False
            for key in keywords_to_remove_array:
                if key in line:
                    remove_line = True
                    break
            return remove_line

        new_file = ""
        keywords_to_remove_all = get_keywords_to_remove(keywords_to_remove_file, "ALL")
        file = java_file.split("\n")
        start = False
        test = ""
        remove_test = False
        #with open(java_file) as file:
        for line in file:
            line += "\n"
            if("@Test" in line):
                start =True
                if not remove_test and len(test) != 0:
                    new_file += test +"\n"
                elif remove_test:
                    remove_test = False
                test = ""
            if remove_test:
                continue
            if(not start):
                if(not remove_line(line, keywords_to_remove_all)):
                    new_file += line
                    continue
            if(start):
                test += line
                if(remove_line(line, get_keywords_to_remove(keywords_to_remove_file, new_test_file_name))):
                    remove_test = True
        if remove_test:
            new_file += "\n}"
        else:
            new_file += test
        return new_file

    def get_short_criterion(criterion):
        #criterion = criterion.replace("line_branch_exception", "lbe")
        #criterion = criterion.replace("epaadjacentedgesmining", "pairs")
        criterion = criterion.replace("line_branch_exception_epaadjacentedgesmining", "pa")
        criterion = criterion.replace("line_branch_exception", "d")
        return criterion


    inputs_array = inputs.split(",")
    id = "ID"+id
    for input in inputs_array:
        print("parsing path " + input + "...")
        i = 0
        class_name = qualified_name.split(".")[-1]
        package_dir = utils.get_package_name_from_qualifiedname(qualified_name)
        package_dir = utils.get_package_dir(package_dir.split("."))
        criterion = get_short_criterion(input.split(os.path.sep)[-1])
        file_name = class_name+"_ESTest.java"
        test_file = os.path.join(input, str(i), "test", package_dir, file_name)
        while(i < n):
            while (not os.path.exists(test_file) and i < n):
                i += 1
                test_file = os.path.join(input, str(i), "test", package_dir, file_name)
            if i >= n:
                break
            new_test_file_name = class_name + "_" + id + "_" + criterion + "_" + str(i) + "_ESTest.java"
            new_test_file_name_without_extension = new_test_file_name.split(".")[0]
            test_file_output = os.path.join(output, new_test_file_name)
            new_test_file = get_rename_test(test_file, class_name, new_test_file_name_without_extension, replace_in_imports)
            new_test_file = remove_flacky_test(new_test_file_name_without_extension, new_test_file, keywords_to_remove_file)
            utils.save_file(test_file_output, new_test_file)

            ###Replace_original TS
            if replace_original_testsuite == "True":
                new_test_file = get_rename_test(test_file_output, new_test_file_name_without_extension.replace("_ESTest",""), class_name+"_ESTest", replace_in_imports)
                utils.save_file(test_file, new_test_file)
            i += 1
            test_file = os.path.join(input, str(i), "test", package_dir, file_name)
    print("saved files to "+ output + "...")


#Example:  "C:\Users\JGodoy\Replication-Package\epa-benchmark\results_newpre\testgen\Compiler_18\all\maxtime\30\evosuite\line_branch_exception_epaadjacentedgesmining"
#          "C:\Users\JGodoy\Documents\Defects4J\Closure\closure_18_fix_j8_min\test\com\google\javascript\jscomp"  1 com.google.javascript.jscomp.Compiler
if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("source_path", help="path folder - generated by epa-evosuite")
    parser.add_argument("dest_path", help="output path - To copy test suite from source path")
    parser.add_argument("id", help="ID - id to add to test suite file name")
    parser.add_argument("how_many_testsuite", help="N indicating how many test suite needs to be copied")
    parser.add_argument("qualified_name_class", help="fully qualified name for subject to copy test suite")
    parser.add_argument("keywords_to_remove_file", help="keywords_to_remove in test suite")
    parser.add_argument("replace_in_imports", help="replace in imports of test suite. Example: package name")
    parser.add_argument("replace_original_testsuite", help="replace original test suite (removing test cases). True or False")
    args = parser.parse_args()
    if args.replace_original_testsuite == "True":
        print("Replace original test suite, too!")
    copy_test(args.source_path, args.dest_path, args.id, int(args.how_many_testsuite), args.qualified_name_class, args.keywords_to_remove_file,  args.replace_in_imports, args.replace_original_testsuite)
    print("Done!")