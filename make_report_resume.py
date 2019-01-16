import csv
from tkinter.tix import COLUMN

header_names = ['ID', 'BUG_TYPE', 'STOP_COND', 'BUD', 'SUBJ',
                'TOOL', 'LINE', 'BRNCH', 'EPACOV', 'EPA',
                'EPATOT', 'EXCEPCOV', 'EXCEP', 'EXCEPTOT', 'EXCEPVALCOV',
                'EXCEPVAL', 'EXCEPVALTOT', 'ADJACCOV', 'ADJAC', 'ADJACTOT',
                'ERR', 'NERR', 'TERR', 'MUT', 'TIME',
                'LOC', 'PIMUT', 'ERRF', 'MJMUT', 'MUT_KILLED',
                'ERRPROT_KILLED', 'GENS', 'TOT_TIME', 'TS_LOC']

def write_row(writer, row):
    writer.writerow({'ID': row[0], 'BUG_TYPE': row[1], 'STOP_COND': row[2], 'BUD': row[3], 'SUBJ': row[4],
                     'TOOL': row[5], 'LINE': row[6], 'BRNCH': row[7], 'EPACOV': row[8], 'EPA': row[9],
                     'EPATOT': row[10], 'EXCEPCOV': row[11], 'EXCEP': row[12], 'EXCEPTOT': row[13], 'EXCEPVALCOV': row[14],
                     'EXCEPVAL': row[15], 'EXCEPVALTOT': row[16], 'ADJACCOV': row[17], 'ADJAC': row[18], 'ADJACTOT': row[19],
                     'ERR': row[20], 'NERR': row[21], 'TERR': row[22], 'MUT': row[23], 'TIME': row[24],
                     'LOC': row[25], 'PIMUT': row[26], 'ERRF': row[27], 'MJMUT': row[28], 'MUT_KILLED': row[29],
                     'ERRPROT_KILLED': row[30], 'GENS': row[31], 'TOT_TIME': row[32]});
                     
header_names_test_suite_loc = ['ID', 'BUG_TYPE', 'STOP_COND', 'BUD', 'SUBJ', 'TOOL', 'TS_LOC']

def write_row_test_suite_loc(writer, row):
    writer.writerow({'ID': row[0], 'BUG_TYPE': row[1], 'STOP_COND': row[2], 'BUD': row[3], 'SUBJ': row[4], 'TOOL': row[5], 'TS_LOC': row[6]});

def get_complete_row(row):
    return [row[0], row[1], row[2], row[3], row[4],
            row[5], row[6], row[7], row[8], row[9],
            row[10], row[11], row[12], row[13], row[14],
            row[15], row[16], row[17], row[18], row[19],
            'N/A', 'N/A', 'N/A', 'N/A', 'N/A',
            'N/A', row[20], 'N/A', row[21], row[22],
            row[23], row[24], row[25]]

def read_evosuite_csv(file_path):
    epatransition_coverage = 'N/A'
    epatransition_covered = 'N/A'
    epatransition_tot = 'N/A'
    epaexception_coverage = 'N/A'
    epaexception_covered = 'N/A'
    epaexception_tot = 'N/A'
    epaadjacentedges_coverage = 'N/A'
    epaadjacentedges_covered = 'N/A'
    epaadjacentedges_tot = 'N/A'
    
    with open(file_path, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            if 'EPATRANSITION' == row['criterion']:
                epatransition_coverage = row['Coverage']
                epatransition_covered = row['Covered_Goals']
                epatransition_tot = row['Total_Goals']
            if 'EPAEXCEPTION' == row['criterion']:
                epaexception_coverage = row['Coverage']
                epaexception_covered = row['Covered_Goals']
                epaexception_tot = row['Total_Goals']
            if 'EPAADJACENTEDGES' == row['criterion']:
                epaadjacentedges_coverage = row['Coverage']
                epaadjacentedges_covered = row['Covered_Goals']
                epaadjacentedges_tot = row['Total_Goals']
    
    epaexcepval_covered = int(epatransition_covered)+int(epaexception_covered)
    try:
        epaexcepval_tot = int(epatransition_tot)+int(epaexception_tot)
    except:
        epaexcepval_tot = 'N/A'
    epaexcepval_coverage = int(epaexcepval_covered)/int(epaexcepval_tot)

    return epatransition_coverage, epatransition_covered, epatransition_tot, epaexception_coverage, epaexception_covered, epaexception_tot, epaexcepval_coverage, epaexcepval_covered, epaexcepval_tot, epaadjacentedges_coverage, epaadjacentedges_covered, epaadjacentedges_tot

def read_generations_csv(file_path):
    generations = 'N/A'
    total_time = 'N/A'
    try:
        with open(file_path, newline='') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                generations = row['Generations']
                total_time = int(row['Total_Time'])/1000
    except:
        print("Error in \"read_generations_csv\": File {} doesn't exists. Randoop doesnt generate that file".format(file_path))
        
    return generations, total_time


def read_jacoco_csv(target_class, file_path):
    with open(file_path, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            package_name = row['PACKAGE']
            class_name = row['CLASS']
            if package_name.strip() != "":
                fully_qualified_class_name = "{}.{}".format(package_name, class_name)
            else:
                fully_qualified_class_name = class_name
                
            if fully_qualified_class_name == target_class:
                branch_missed = float(row['BRANCH_MISSED'])
                branch_covered = float(row['BRANCH_COVERED'])
                line_missed = float(row['LINE_MISSED'])
                line_covered = float(row['LINE_COVERED'])
                return branch_covered / (branch_missed + branch_covered), line_covered / (line_missed + line_covered)
    return 0, 0


def read_pit_csv(file_path):
    killed = 0
    total = 0
    with open(file_path, newline='') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            total += 1
            if row[5] == 'KILLED':
                killed += 1
    return killed / total

def read_mujava_coverage_csv(mujava_csv):
    mujava_coverage = 'N/A'
    mutants_killed = 'N/A'
    err_prot_killed = 'N/A'
    try:
        with open(mujava_csv, newline='') as csvfile:
            reader = csv.reader(csvfile)
            next(reader)
            for row in reader:
                mutants_killed = row[1]
                mujava_coverage = row[2]
                err_prot_killed = row[4]
    except:
        print("File {} doesn't exists".format(mujava_csv))
    return mujava_coverage, mutants_killed, err_prot_killed


def report_resume_row(target_class, evosuite, statistics_testgen, jacoco, pit, runid, bug_type, stopping_condition, search_budget, criterion, mujava_csv):
    epa_coverage, epa_covered, epa_tot, epaex_coverage, epaex_covered, epaex_tot, epaexval_coverage, epaexval_covered, epaexval_tot, edges_coverage, edges_covered, edges_tot = read_evosuite_csv(evosuite)
    generations_test, total_time_test = read_generations_csv(statistics_testgen)
    branch_coverage, line_coverage = read_jacoco_csv(target_class, jacoco)
    mutation_coverage = read_pit_csv(pit)
    mujava_coverage, mutants_killed, err_prot_killed = read_mujava_coverage_csv(mujava_csv)
    row = [runid, bug_type, stopping_condition, search_budget, target_class,
           criterion, line_coverage, branch_coverage, epa_coverage, epa_covered,
           epa_tot, epaex_coverage, epaex_covered, epaex_tot, epaexval_coverage,
           epaexval_covered, epaexval_tot, edges_coverage, edges_covered, edges_tot,
           mutation_coverage, mujava_coverage, mutants_killed, err_prot_killed, generations_test,
           total_time_test]
    return row


def make_report_resume(target_class, evosuite, statistics_testgen, jacoco, pit, output_file, runid, stopping_condition, search_budget, criterion, bug_type, mujava_csv):
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header_names)

        writer.writeheader()
        row = report_resume_row(target_class, evosuite, statistics_testgen, jacoco, pit, runid, bug_type, stopping_condition, search_budget, criterion, mujava_csv)
        row = get_complete_row(row)
        write_row(writer, row)
        
def make_report_resume_test_suite_loc(target_class, output_file, runid, stopping_condition, search_budget, criterion, bug_type, javancss_file):
    file = open(javancss_file, "r")
    init_loc_index = 11 
    ts_loc = int(file.read()[init_loc_index:])
    row = [runid, bug_type, stopping_condition, search_budget, target_class, criterion, ts_loc]
        
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header_names_test_suite_loc)
        writer.writeheader()
        write_row_test_suite_loc(writer, row)


def merge_final_results(final_results, output_file):
    # hack
    # Sólo si tiene 7 columnas, entonces uso el header para TS_LOC
    column_size = len(open(final_results[0], "r").readline().split(","))
    header = header_names if column_size != 7 else header_names_test_suite_loc
    output_file = output_file if column_size != 7 else output_file.replace(".csv","_TS_LOC.csv")
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header)
        writer.writeheader()

        for resume in final_results:
            try:
                with open(resume, newline='') as csvfile:
                    reader = csv.reader(csvfile)
                    next(reader) # Evito el header
                    for row in reader:
                        if len(row) != 7:
                            write_row(writer, row)
                        else:
                            write_row_test_suite_loc(writer, row)
            except FileNotFoundError:
                print("{} doesn't exists".format(resume))