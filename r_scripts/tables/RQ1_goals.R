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
	cat("Subject", "EPA goals", "EPA goals", "EPAX goals", "EPAX goals", "EPAXP goals", "EPAXP goals", sep=", ")
	cat("\n")
	cat("Subject", "DEF", "EPA", "DEF", "EPAX", "DEF", "EPAXP", sep=", ")
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

RQ1 <- function()
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
			default_epa = round(mean(default_rows$EPA), digits=1)
			default_epa = roundDecimals(default_epa)
			default_epaex = round(mean(default_rows$EXCEPVAL), digits=1)
			default_epaex = roundDecimals(default_epaex)
			default_epap = round(mean(default_rows$ADJAC), digits=1)
			default_epap = roundDecimals(default_epap)
			
			# EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition' & BUD==budget)
			epatransition_epa = round(mean(epatransition_rows$EPA), digits=1)
			epatransition_epa = roundDecimals(epatransition_epa)
			
			# EPATRANSITION_EPAEXCEPTION
			epatransition_epaexception_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition_epaexception' & BUD==budget)
			epatransition_epaexception_epaex = round(mean(epatransition_epaexception_rows$EXCEPVAL), digits=1)
			epatransition_epaexception_epaex = roundDecimals(epatransition_epaexception_epaex)
			
			# EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedges' & BUD==budget)
			edges_epap = round(mean(edges_rows$ADJAC), digits=1)
			edges_epap = roundDecimals(edges_epap)
			
			cat(", ", default_epa, ", ", epatransition_epa, ", ", default_epaex, ", ", epatransition_epaexception_epaex, ", ", default_epap, ", ", edges_epap, sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ1()
sink()
