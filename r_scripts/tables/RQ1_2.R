#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is two arguments: if not, return an error
if (length(args)!= 2) {
  stop("Input csv files arguments must be supplied (report.csv and test_suite_LOC.cvs)", call.=FALSE)
}

csv_filename = args[1]
csv_tsloc = args[2]
# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")
stats_loc = read.csv(csv_tsloc, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)

subjects_loc = unique(stats_loc$SUBJ)
tools_loc = unique(stats_loc$TOOL)
budgets_loc = unique(stats_loc$BUD)

digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite","Evosuite","Evosuite","Evosuite","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP",sep=", ")
	cat("\n")
	cat("Subject","stmt","branch","excep", "TS LOC","stmt","branch","excep", "TS LOC","stmt","branch","excep", "TS LOC",sep=", ")
	cat("\n")
	cat("EOH\n")
}


roundDecimals <- function(value)
{
	if (value%%1 == 0) {
		return (paste(value,".0",sep=""))
	}
	return (value)
}

RQ1_2 <- function()
{
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget)
			default_st = roundDecimals(round(mean(default_rows$LINE)*100, digits=digits_size_to_percentage))
			default_br = roundDecimals(round(mean(default_rows$BRNCH)*100, digits=digits_size_to_percentage))
			default_excep = roundDecimals(round(mean(default_rows$EXCEPCOV)*100, digits=digits_size_to_percentage))
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					default_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget_loc)
					default_test = round(mean(default_rows_loc$TS_LOC), digits=digits_size_to_percentage)
				}
			}
			
			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			epatransition_st = roundDecimals(round(mean(epatransition_rows$LINE)*100, digits=digits_size_to_percentage))
			epatransition_br = roundDecimals(round(mean(epatransition_rows$BRNCH)*100, digits=digits_size_to_percentage))
			epatransition_excep = roundDecimals(round(mean(epatransition_rows$EXCEPCOV)*100, digits=digits_size_to_percentage))
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					epatransition_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget_loc)
					epatransition_test = round(mean(epatransition_rows_loc$TS_LOC), digits=digits_size_to_percentage)
				}
			}
			
			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			edges_st = roundDecimals(round(mean(edges_rows$LINE)*100, digits=digits_size_to_percentage))
			edges_br = roundDecimals(round(mean(edges_rows$BRNCH)*100, digits=digits_size_to_percentage))
			edges_excep = roundDecimals(round(mean(edges_rows$EXCEPCOV)*100, digits=digits_size_to_percentage))
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					edges_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget_loc)
					edges_test = round(mean(edges_rows_loc$TS_LOC), digits=digits_size_to_percentage)
				}
			}
			
			cat(", ", default_st, "%, ", default_br, "%, ", default_excep, "%, ", default_test, ", ",epatransition_st, "%, ", epatransition_br, "%, ", epatransition_excep, "%, ", epatransition_test, ", ", edges_st, "%, ", edges_br, "%, ", edges_excep, "%, ", edges_test,sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ1_2()
sink()
