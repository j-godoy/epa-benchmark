#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!=1) {
  stop("Input csv file argument must be supplied (report.csv)", call.=FALSE)
}

csv_filename = args[1]


# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
bug_type = unique(stats$BUG_TYPE)
budgets = unique(stats$BUD)

printHeader <- function()
{
																															
	cat("SUBJECT", "BUDGET", "BUG_TYPE", "CRITERION", "REP", "LINE", "BRNCH", "EPA%", "EPAEXCEP%", "ADJAC%", "EPAMINING_TX", "EPAEXCEPMINING_TX", "ADJACMINING_TX", "PIMUT", "GENS", sep=", ")
	cat("\n")
}


printPitMutationScoreMedian <- function() {
	for(subj in subjects) {
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		for (budget in budgets) {
			for (b_type in bug_type) {
				error_type = b_type
				default_rows = {}
				for (criterion in tools) {
					default_rows  = subset(stats,SUBJ==subj & TOOL==criterion & BUG_TYPE==b_type & BUD==budget)
					line_avg = paste(round(mean(default_rows$LINE), digits=3)*100, "%", sep="")
					brnch_avg = paste(round(mean(default_rows$BRNCH), digits=3)*100, "%", sep="")
					epacov_avg = paste(round(mean(default_rows$EPACOV), digits=3)*100, "%", sep="")
					epa = paste("(", round(mean(default_rows$EPA), digits=2), "/", mean(default_rows$EPATOT), ")", sep="")
					excepcov_avg = paste(round(mean(default_rows$EPAEXCEPCOV), digits=3)*100, "%", sep="")
					excep = paste("(", round(mean(default_rows$EPAEXCEP), digits=2), "/", mean(default_rows$EPAEXCEPTOT), ")", sep="")
					adjaccov_avg = paste(round(mean(default_rows$ADJACCOV), digits=3)*100, "%", sep="")
					adjac = paste("(", round(mean(default_rows$ADJAC), digits=2), "/", mean(default_rows$ADJACTOT), ")", sep="")
					epamining = round(mean(default_rows$EPAMINING), digits=3)
					epaexcepmining = round(mean(default_rows$EPAEXCEPMINING), digits=3)
					adjacmining = round(mean(default_rows$EDGESMINING), digits=3)
					pit_avg = paste(round(mean(default_rows$PIMUT), digits=3)*100, "%", sep="")
					gens_avg = round(mean(default_rows$GENS), digits=3)
					repeticiones = length(default_rows$LINE)

					cat(name_subj, budget, error_type, criterion, repeticiones, line_avg, brnch_avg, paste(epacov_avg, epa, sep=""), paste(excepcov_avg, excep, sep=""), paste(adjaccov_avg, adjac, sep=""), epamining, epaexcepmining, adjacmining, pit_avg, gens_avg, sep=", ")
					cat("\n")
				}
			}
		}
	}
}

sink("results_avg.csv")
printHeader()
printPitMutationScoreMedian()
sink()
