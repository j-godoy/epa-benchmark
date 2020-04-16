import argparse
import os
import subprocess


def run_diff(params1, params2, param3, n):
    def run_criterion(param1, param2):
        def update_params(param1, param2):
            dir_change = os.path.abspath(param1).split(os.path.sep)[-1]
            return param1.replace(os.path.sep+dir_change+os.path.sep, os.path.sep+str(i)+os.path.sep), \
               param2.replace(os.path.sep+dir_change+os.path.sep,os.path.sep+str(i)+os.path.sep)

        tot_diff = 0
        tot = 0
        i = 0
        while i < n:
            if not os.path.exists(os.path.join(param1, param3)) or not os.path.exists(os.path.join(param2, param3)):
                if not os.path.exists(os.path.join(param1, param3)):
                    print("Falta TS: " + param1)
                if not os.path.exists(os.path.join(param2, param3)):
                    print("Falta TS: " + param2)
                i += 1
                param1, param2 = update_params(param1, param2)
                continue
            stdout = subprocess.run("C:\\cygwin64\\bin\\diff.exe {}\{} {}\{}".format(param1, param3, param2, param3), capture_output=True, text=True).stdout
            if len(stdout) > 0:
                if tot_diff % 10 == 0:
                    print()
                    print("TestSuite detect change: " + str(os.path.abspath(param1).split(os.path.sep)[-1]), end="")
                elif tot_diff == 0:
                    print("TestSuite detect change: " + str(os.path.abspath(param1).split(os.path.sep)[-1]), end="")
                else:
                    print(", " + str(os.path.abspath(param1).split(os.path.sep)[-1]), end="")
                tot_diff += 1
                #print(stdout)
                #print(param1)
                #print(param2)
            # else:
            #     print(param1)
            #     print(param2)
            i += 1
            tot += 1
            param1, param2 = update_params(param1, param2)
        print()
        print("total diff = " + str(tot_diff))
        print("total analyzed = " + str(tot))

    print("PARAM 1: " + params1)
    print("PARAM 2: " + params2)
    print("Running first criterion: Default...")
    run_criterion(params1.split(",")[0], params2.split(",")[0])
    print()
    print("Running second criterion: Pairs...")
    run_criterion(params1.split(",")[1], params2.split(",")[1])


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("params1", help="param1 - first parameter (folder) to compare with diff")
    parser.add_argument("params2", help="param2 - second parameter (folder) to compare with diff")
    parser.add_argument("param3", help="param3 - file name to compare with diff")
    parser.add_argument("how_many", help="N indicating how many folders need to be copied")
    args = parser.parse_args()
    run_diff(args.params1, args.params2, args.param3, int(args.how_many))
    print("Done!")