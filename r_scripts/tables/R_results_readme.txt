command to run: 

	python R_results.py Rscript "effect_size_pit_vs_default.R" "path/to/results.csv" "ALL" "summary_table.csv" "summary_table.tex"

notes:
 - This command generates two tables: a .csv file and a .tex file with same data
 - ALL: generates .csv and .tex file for ALL criterios in the results.csv file. An alternative is to put desired criterios as a parameter separated by ",". Example: "epatransition, epatransition_epaexception, epaadjacentedges"
 - Rscript: In Windows, use path/to/Rscript.exe
 - summary_table.csv: path to save csv file
 - summary_table.tex: path to save tex file
 

To generate .tex file from .csv:
 	python Latex_table.py "table.csv" "summary_table.tex"

notes:
- table.csv: input csv
- summary_table.tex: path to save tex file