import csv

header_names = ['ID', 'BUG_TYPE', 'STOP_COND', 'BUD', 'SUBJ',
                'TOOL', 'LINE', 'BRNCH', 'EXCEPTION', 'EPACOV',
                'EPA', 'EPATOT', 'EPAEXCEPCOV', 'EPAEXCEP', 'EPAEXCEPTOT',
                'ADJACCOV', 'ADJAC', 'ADJACTOT', 'TS_LOC', 'PIMUT',
                'MJMUT', 'MUT_KILLED', 'ERRPROT_KILLED', 'GENS', 'TOT_TIME']

def write_row(writer, row):
    writer.writerow({'ID': row[0], 'BUG_TYPE': row[1], 'STOP_COND': row[2], 'BUD': row[3], 'SUBJ': row[4],
                     'TOOL': row[5], 'LINE': row[6], 'BRNCH': row[7], 'EXCEPTION': row[8], 'EPACOV': row[9],
                     'EPA': row[10], 'EPATOT': row[11], 'EPAEXCEPCOV': row[12], 'EPAEXCEP': row[13], 'EPAEXCEPTOT': row[14],
                     'ADJACCOV': row[15], 'ADJAC': row[16], 'ADJACTOT': row[17], 'TS_LOC': row[18], 'PIMUT': row[19],
                     'MJMUT': row[20], 'MUT_KILLED': row[21], 'ERRPROT_KILLED': row[22], 'GENS': row[23], 'TOT_TIME': row[24]});
                     
header_names_ts_loc_exceptions = ['ID', 'BUG_TYPE', 'STOP_COND', 'BUD', 'SUBJ', 'TOOL', 'TS_LOC', 'EXCEPTIONS']

def write_row_test_suite_loc_and_exceptions(writer, row):
    writer.writerow({'ID': row[0], 'BUG_TYPE': row[1], 'STOP_COND': row[2], 'BUD': row[3], 'SUBJ': row[4], 'TOOL': row[5], 'TS_LOC': row[6], 'EXCEPTIONS': row[7]});

def get_complete_row(row):
    return [row[0], row[1], row[2], row[3], row[4],
            row[5], row[6], row[7], row[8], row[9],
            row[10], row[11], row[12], row[13], row[14],
            row[15], row[16], row[17], row[18], row[19],
            row[20], row[21], row[22], row[23], row[24]]

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
    
    return epatransition_coverage, epatransition_covered, epatransition_tot, epaexception_coverage, epaexception_covered, epaexception_tot, epaadjacentedges_coverage, epaadjacentedges_covered, epaadjacentedges_tot

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


def get_test_suite_loc(path_file):
    file = open(path_file, "r")
    init_loc_index = 11 
    ts_loc = int(file.read()[init_loc_index:])
    return ts_loc

def get_exceptions_in_testgenlog(testgen_log_file):
    file = open(testgen_log_file, "r")
    file_txt = file.read().split("\n")
    exceptions = 'N/A'
    init_exception_goals_covered_index = 27
    line_index = 1
    found = False
    for line in file_txt:
        if "Coverage of criterion EXCEPTION" in line:
            #line with exceptions info in two line below
            line_index += 2
            found = True
            break
        line_index += 1
    if found:
        exceptions = file_txt[line_index-1][init_exception_goals_covered_index:]
    return exceptions

def report_resume_row(target_class, evosuite, statistics_testgen, jacoco, pit, runid, bug_type, stopping_condition, search_budget, criterion, mujava_csv, path_file_jncss, testgen_log_file_path):
    epa_coverage, epa_covered, epa_tot, epaex_coverage, epaex_covered, epaex_tot, edges_coverage, edges_covered, edges_tot = read_evosuite_csv(evosuite)
    generations_test, total_time_test = read_generations_csv(statistics_testgen)
    branch_coverage, line_coverage = read_jacoco_csv(target_class, jacoco)
    mutation_coverage = read_pit_csv(pit)
    mujava_coverage, mutants_killed, err_prot_killed = read_mujava_coverage_csv(mujava_csv)
    ts_loc = get_test_suite_loc(path_file_jncss)
    # si el testgenlog incluye "Coverage of criterion EXCEPTION", con la linea siguiente alcanza
    exceptions = get_exceptions_in_testgenlog(testgen_log_file_path)
    row = [runid, bug_type, stopping_condition, search_budget, target_class,
           criterion, line_coverage, branch_coverage, exceptions, epa_coverage,
           epa_covered, epa_tot, epaex_coverage, epaex_covered, epaex_tot,
           edges_coverage, edges_covered, edges_tot, ts_loc, mutation_coverage,
           mujava_coverage, mutants_killed, err_prot_killed, generations_test, total_time_test]
    return row


def make_report_resume(target_class, evosuite, statistics_testgen, jacoco, pit, output_file, runid, stopping_condition, search_budget, criterion, bug_type, mujava_csv, path_file_jncss, testgen_log_file_path):
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header_names)

        writer.writeheader()
        row = report_resume_row(target_class, evosuite, statistics_testgen, jacoco, pit, runid, bug_type, stopping_condition, search_budget, criterion, mujava_csv, path_file_jncss, testgen_log_file_path)
        row = get_complete_row(row)
        write_row(writer, row)
        
def make_report_resume_test_suite_loc_and_exceptions(target_class, output_file, runid, stopping_condition, search_budget, criterion, bug_type, javancss_file, testgen_log_file, epacoverage_csv, statistics_testgen_csv):
    def get_exceptions(target_class, output_file, runid, stopping_condition, search_budget, criterion, bug_type, testgen_log_file, epacoverage_csv, statistics_testgen_csv):
        def get_exceptions_in_testgenlog(testgen_log_file):
            file = open(testgen_log_file, "r")
            file_txt = file.read().split("\n")
            exceptions = 'N/A'
            line_index = 1
            for line in file_txt:
                if "Coverage of criterion EXCEPTION" in line:
                    #line with exceptions info in two line below
                    line_index += 2
                    break
                line_index += 1
            if line_index != 1:
                exceptions = file_txt[line_index-1][27:]
            return exceptions
        
        def read_criterion_covered_goals(file_path, criterion_to_search):
            criterion_covered_goals = 'N/A'
            
            with open(file_path, newline='') as csvfile:
                reader = csv.DictReader(csvfile)
                for row in reader:
                    if criterion_to_search == row['criterion']:
                        criterion_covered_goals = row['Covered_Goals']
            
            return criterion_covered_goals
            
        if ("line_branch_exception_epaadjacentedges" in criterion or "line_branch_exception_epatransition" in criterion) and ("Socket" in target_class or "ListItr" in target_class):
            total_criterion_covered_goals = read_criterion_covered_goals(statistics_testgen_csv, criterion.upper().replace("_",";"))
            criterion_covered_goals_without_exceptions = read_criterion_covered_goals(epacoverage_csv, criterion.upper().replace("_",";"))
            exceptions = int(total_criterion_covered_goals) - int(criterion_covered_goals_without_exceptions)
        else:
            # si el testgenlog incluye "Coverage of criterion EXCEPTION", con la linea siguiente alcanza
            exceptions = get_exceptions_in_testgenlog(testgen_log_file)
    
        return exceptions
    
    
    file = open(javancss_file, "r")
    init_loc_index = 11 
    ts_loc = int(file.read()[init_loc_index:])
    exceptions = get_exceptions(target_class, output_file, runid, stopping_condition, search_budget, criterion, bug_type, testgen_log_file, epacoverage_csv, statistics_testgen_csv)
    row = [runid, bug_type, stopping_condition, search_budget, target_class, criterion, ts_loc, exceptions]
        
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header_names_ts_loc_exceptions)
        writer.writeheader()
        write_row_test_suite_loc_and_exceptions(writer, row)


def merge_final_results(final_results, output_file):
    # hack
    # Solo si tiene 8 columnas, entonces uso el header para TS_LOC/Exceptions
    hack_columns = 8
    column_size = len(open(final_results[0], "r").readline().split(","))
    header = header_names if column_size != hack_columns else header_names_ts_loc_exceptions
    output_file = output_file if column_size != hack_columns else output_file.replace(".csv","_TS_LOC_EXCEPTIONS.csv")
    with open(output_file, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header)
        writer.writeheader()

        for resume in final_results:
            try:
                with open(resume, newline='') as csvfile:
                    reader = csv.reader(csvfile)
                    next(reader) # Evito el header
                    for row in reader:
                        if len(row) != hack_columns:
                            write_row(writer, row)
                        else:
                            write_row_test_suite_loc_and_exceptions(writer, row)
            except FileNotFoundError:
                print("{} doesn't exists".format(resume))