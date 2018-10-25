#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!= 1) {
  stop("Input csv file argument must be supplied (report.csv)", call.=FALSE)
}

csv_filename = args[1]
# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)

printHeader <- function()
{
	cat("subject", "DEF", "EPA", "EPAX", "EPAXP", "DEF+EPA", "DEF+EPAX", "DEF+EPAXP",sep=", ")
	cat("\n")
	cat("EOH\n")
}

RQ1 <- function()
{
	digits_size = 1
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget)
			default_errors = default_rows$PIMUT
			default_mean = round(mean(default_errors)*100, digits=digits_size)
			
			# EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition' & BUD==budget)
			epatransition_errors = epatransition_rows$PIMUT
			epatransition_mean = round(mean(epatransition_errors)*100, digits=digits_size)
			
			# EPATRANSITION:EPAEXCEPTION
			epatransition_epaexception_rows = subset(stats,SUBJ==subj & TOOL=='epatransition_epaexception' & BUD==budget)
			epatransition_epaexception_errors = epatransition_epaexception_rows$PIMUT
			epatransition_epaexception_mean = round(mean(epatransition_epaexception_errors)*100, digits=digits_size)
			
			# EPAADJACENTEDGES
			edges_rows = subset(stats,SUBJ==subj & TOOL=='epaadjacentedges' & BUD==budget)
			edges_errors = edges_rows$PIMUT
			edges_mean = round(mean(edges_errors)*100, digits=digits_size)

			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			epamixed_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			line_branch_exception_epatransition_errors = epamixed_rows$PIMUT
			line_branch_exception_epatransition_mean = round(mean(line_branch_exception_epatransition_errors)*100, digits=digits_size)

			# LINE:BRANCH:EXCEPTION:EPATRANSITION:EPAEXCEPTION
			line_branch_exception_epatransition_epaexception_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition_epaexception' & BUD==budget)
			line_branch_exception_epatransition_epaexception_errors = line_branch_exception_epatransition_epaexception_rows$PIMUT
			line_branch_exception_epatransition_epaexception_mean = round(mean(line_branch_exception_epatransition_epaexception_errors)*100, digits=digits_size)

			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			line_branch_exception_edges_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			line_branch_exception_edges_errors = line_branch_exception_edges_rows$PIMUT
			line_branch_exception_edges_mean = round(mean(line_branch_exception_edges_errors)*100, digits=digits_size)
			
			cat(", ", default_mean, "%, ", epatransition_mean, "%, ", epatransition_epaexception_mean, "%, ", edges_mean, "%, ", line_branch_exception_epatransition_mean, "%, ", line_branch_exception_epatransition_epaexception_mean,"%, ", line_branch_exception_edges_mean, "%", sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ1()
sink()
