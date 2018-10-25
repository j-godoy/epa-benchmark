import csv
import argparse
import os
from sys import maxsize 

EOH = "EOH" # End Of Header


def generate_latex_table(r_results_file, output):
    def get_max_column_size(table_matrix):
        max_column_size = -1
        for row in table_matrix:
            if max_column_size < len(row):
                max_column_size = len(row)
        return max_column_size

    def clean_and_get_matrix(results):
        table_matrix = []
        for line in results:
            line = line.replace("A12", "$\\vargha$")
            line = line.replace("TxExcep EPAX", "$|\delta^E|$")
            line = line.replace("TxPairs EPAXP", "$|all_txs_pairs|$")
            line = line.replace("TxExcep", "$|\delta^E|$")
            line = line.replace("TxPairs", "$|all_txs_pairs|$")
            line = line.replace("Tx", "$|\delta|$")
            
            line = line.replace("DEF", "$\\base$")
            line = line.replace("EPAXP", "$\epaxp$")
            line = line.replace("EPAX", "$\epax$")
            line = line.replace("EPA", "$\epa$")
            
            line = line.replace("line_branch_exception_epaadjacentedges", "$\\base$ + $\epaxp$")
            line = line.replace("line_branch_exception_epatransition_epaexception", "$\\base$ + $\epax$")
            line = line.replace("line_branch_exception_epatransition", "$\\base$ + $\epa$")
            
            line = line.replace("epaadjacentedges", "$\epaxp$")
            line = line.replace("epatransition_epaexception", "$\epax$")
            line = line.replace("epatransition", "$\epa$")
            
            line = line.replace("_", "\_")
            line = line.replace("%", "\%")
            line = line.replace("\n", "")
            table_matrix.append(line.split(", "))

        return table_matrix 
    
    def get_latex_declaration_init(tabular, text_align):
        max_column_size = get_max_column_size(table_matrix)
        for _ in range(max_column_size):
            tabular += text_align
        
        declaration_init = "% Please add the following required packages to your document preamble:\n% \\usepackage{multirow}\n"
        declaration_init += "\\begin{table*}[t]\n"
        declaration_init += "\\center\n"
        declaration_init += "\\caption{Insert here description}\label{summary-table}\n"
        declaration_init += "\\begin{tabular}{" + "{}".format(tabular)+"}"
        declaration_init += "\n\\hline\n"
        return declaration_init

    def get_index_p_values(table_matrix):
        p_values_index = []
        for _ in table_matrix:
            column_index = 0
            for __ in _:
                if "p-value" in __:
                    p_values_index.append(column_index)
                column_index += 1
        return p_values_index
    
    def p_values_signif(table_matrix, p_values_index):
        for row in table_matrix:
            column_index = 0
            for column in row:
                if column_index in p_values_index:
                    p_value = column
                    if "< 0.05" in p_value or "< 0.005" in p_value:
                        a12 = row[column_index-1].strip()
                        row[column_index-1] = "\\textbf{" + a12 + "}"
                        row[column_index] = p_value.replace("< ", "\\textless{") + "}"
                column_index += 1
    
    def get_latex_header(table_matrix):
        def header_add_multirow(table_matrix):
            eoh_index = -1
            column_index = 0
            # Dada una columna, busco igual nombre de header en distitas filas
            columns_size = len(table_matrix[0])
            while column_index < columns_size:
                row_index = 0
                while row_index < len(table_matrix)-1:
                    # Si la fila actual o la fila siguiente es EOH, dejo de buscar multirow
                    if eoh_index == row_index or eoh_index == (row_index+1):
                        break
                    first_value = table_matrix[row_index][column_index]
                    if first_value == EOH: # se termina el header (end of header)
                        eoh_index = row_index
                        break
                    
                    n = 1
                    first_row = row_index
                    while row_index < len(table_matrix)-1:
                        next_row  = table_matrix[row_index+1]
                        is_next_row_end_of_header = next_row[0] == EOH
                        if column_index < len(next_row) and not is_next_row_end_of_header:
                            next_value = next_row[column_index]
                        if not is_next_row_end_of_header and first_value.strip() == next_value.strip():
                            n += 1
                        else:
                            if n > 1:
                                table_matrix[first_row][column_index] = "\multirow{"+str(n)+"}{*}{"+first_value+"}"
                                for i in range(n-1):
                                    table_matrix[first_row+1+i][column_index] = ""
                            row_index += 1
                            break
                        row_index += 1
                column_index += 1
                
        def get_header_multicolumn(table_matrix, text_align):
            def get_cline(table_matrix, row_index):
                max_column_size = get_max_column_size(table_matrix)
                cline = " \\\\"
                multirow_index = []
                multirow_array = []
                j = 0
                for _ in table_matrix[row_index]:
                    if "multirow" in _ or (_ == "" and "multirow" in table_matrix[row_index-1][j]):
                        multirow_index.append(j)
                    j += 1
                
                min_col = 0
                max_col = max_column_size
                flag_multirow = False
                for i in range(len(table_matrix[row_index])):
                    if i in multirow_index:
                        if not flag_multirow:
                            max_col = i
                            multirow_array.append([min_col,max_col])
                        flag_multirow = True
                    else:
                        if flag_multirow:
                            min_col = i + 1
                        else:
                            max_col = i + 1
                            if i+1 == len(table_matrix[row_index]):
                                multirow_array.append([min_col,max_col])
                        flag_multirow = False
                for _ in multirow_array:
                    if _[0] == _[1]:
                        continue
                    else:
                        cline += " \\cline{"+ "{}-".format(_[0])+"{}".format(_[1])+"}"

                cline += "\n"
                return cline
                
            latex_header = ""
            eoh_index = maxsize
            row_index = 0
            # multicolumn
            rows_size = len(table_matrix)
            # Dada una fila, busco igual nombre de header en columnas consecutivas. Si hay, entonces son multicolumn
            while row_index < rows_size:
                if eoh_index <= row_index: # se termina el header (end of header)
                        break
                column_index = 0
                first = True
                while column_index < len(table_matrix[0]):
                    first_value = table_matrix[row_index][column_index]
                    if first_value == EOH: # se termina el header (end of header)
                        eoh_index = row_index
                        break
                    
                    n = 1
                    while column_index < len(table_matrix[row_index]):
                        if column_index+1 < len(table_matrix[row_index]):
                            next_col  = table_matrix[row_index][column_index+1]
                            if first_value == next_col and first_value != "":
                                n += 1
                        # si es ultima columna o hay dos columnas consecutivas, entonces dejo de contar para armar un multicolumn
                        if column_index+1 == len(table_matrix[row_index]) or first_value != next_col or first_value == "":
                            if not first:
                                latex_header += " & "
                            else:
                                first = False
                            if n > 1:
                                latex_header += "\multicolumn{"+str(n)+"}{" + text_align + "}{"+first_value+"}"
                            else:
                                latex_header += first_value
                            column_index += 1
                            break
                        column_index += 1
                if any("multirow" in s for s in table_matrix[row_index]):
                    latex_header += get_cline(table_matrix, row_index)
                else:
                    if eoh_index > row_index:
                        latex_header += " \\\\ \\hline\n"
                row_index += 1

            return latex_header
        
        header_add_multirow(table_matrix)
        header_latex = get_header_multicolumn(table_matrix, text_align)
        return header_latex
    
    def get_content(table_matrix):
        content = ""
        is_content = False
        for row in table_matrix:
            if is_content:
                i = 0
                for column in row:
                    content += column
                    if i != len(row)-1:
                        content += " & "
                    i += 1
                content +=  " \\\\ \\hline\n"
            is_content = is_content or row[0] == EOH
        
        return content
            

    if not os.path.exists(r_results_file):
        print("generate_latex_table: Does not exits file (r_results_file): {}".format(r_results_file))
        return
    
    tabular  = "|"
    text_align = "c|" # c = center
    
    with open(r_results_file, "r") as results:
        table_matrix = clean_and_get_matrix(results)
    
    declaration_init = get_latex_declaration_init(tabular, text_align)
    p_values_index = get_index_p_values(table_matrix)
    header = get_latex_header(table_matrix)
    p_values_signif(table_matrix, p_values_index)
    content = get_content(table_matrix)
    declaration_end = "{}\n{}\n".format("\end{tabular}", "\end{table*}")
                
    table = declaration_init
    table += header
    table += content
    table += declaration_end
    # Save file
    file = open(output,"w")
    file.write(table)
    file.close()


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("input_csv", help="input_csv")
    parser.add_argument("output_tex", help="output.tex")
    args = parser.parse_args()
    generate_latex_table(args.input_csv, args.output_tex)
    print("Done!")