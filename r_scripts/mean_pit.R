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
budgets = unique(stats$BUD)

printPitMutationScoreMedian <- function() {
	for(subj in subjects) {
		for (budget in budgets) {
			cat("###############################################################\n")
			cat("subject:", subj)
			cat("\n")
			cat("budget:",budget)
			cat("\n")
			cat("###############################################################\n")

				
			# LINE:BRANCH
			default_rows  = subset(stats,SUBJ==subj & TOOL=='evosuite_default' & BUD==budget)
			line_branch_errors = default_rows$PIMUT
			cat("length(LINE:BRANCH)=", length(line_branch_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH)=", mean(line_branch_errors))
			cat("\n")
			
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget)
			default_errors = default_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION)=", length(default_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION)=", mean(default_errors))
			cat("\n")
			
			# EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition' & BUD==budget)
			epatransition_errors = epatransition_rows$PIMUT
			cat("length(EPATRANSITION)=", length(epatransition_errors))
			cat("\n")
			cat("mean_pimut(EPATRANSITION)=", mean(epatransition_errors))
			cat("\n")
			
			# EPAEXCEPTION
			epatransition_epaexception_rows = subset(stats,SUBJ==subj & TOOL=='epaexception' & BUD==budget)
			epaexception_errors = epatransition_epaexception_rows$PIMUT
			cat("length(EPAEXCEPTION)=", length(epaexception_errors))
			cat("\n")
			cat("mean_pimut(EPAEXCEPTION)=", mean(epaexception_errors))
			cat("\n")
			
			# EPAADJACENTEDGES
			edges_rows = subset(stats,SUBJ==subj & TOOL=='epaadjacentedges' & BUD==budget)
			edges_errors = edges_rows$PIMUT
			cat("length(EPAADJACENTEDGES)=", length(edges_errors))
			cat("\n")
			cat("mean_pimut(EPAADJACENTEDGES)=", mean(edges_errors))
			cat("\n")
			
			# RANDOOP
			randoop_rows = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget)
			randoop_errors = randoop_rows$PIMUT
			cat("length(RANDOOP)=", length(randoop_errors))
			cat("\n")
			cat("mean_pimut(RANDOOP)=", mean(randoop_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			epamixed_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			line_branch_exception_epatransition_errors = epamixed_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPATRANSITION)=", length(line_branch_exception_epatransition_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPATRANSITION)=", mean(line_branch_exception_epatransition_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPAEXCEPTION
			line_branch_exception_epaexception_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexception' & BUD==budget)
			line_branch_exception_epaexception_errors = line_branch_exception_epaexception_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPATRANSITION:EPAEXCEPTION)=", length(line_branch_exception_epaexception_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPATRANSITION:EPAEXCEPTION)=", mean(line_branch_exception_epaexception_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			line_branch_exception_edges_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			line_branch_exception_edges_errors = line_branch_exception_edges_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES)=", length(line_branch_exception_edges_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES)=", mean(line_branch_exception_edges_errors))
			cat("\n")
			cat("\n")
			
			
			######################## MINING ################################
			
			
			# EPATRANSITIONMINING
			epatransitionmining_rows  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget)
			epatransitionmining_errors = epatransitionmining_rows$PIMUT
			cat("length(EPATRANSITIONMINING)=", length(epatransitionmining_errors))
			cat("\n")
			cat("mean_pimut(EPATRANSITIONMINING)=", mean(epatransitionmining_errors))
			cat("\n")
			
			# EPAEXCEPTIONMINING
			epaexceptionmining_rows = subset(stats,SUBJ==subj & TOOL=='epaexceptionmining' & BUD==budget)
			epaexceptionmining_errors = epaexceptionmining_rows$PIMUT
			cat("length(EPAEXCEPTIONMINING)=", length(epaexceptionmining_errors))
			cat("\n")
			cat("mean_pimut(EPAEXCEPTIONMINING)=", mean(epaexceptionmining_errors))
			cat("\n")
			
			# EPAADJACENTEDGESMINING
			edgesmining_rows = subset(stats,SUBJ==subj & TOOL=='epaadjacentedgesmining' & BUD==budget)
			edgesmining_errors = edgesmining_rows$PIMUT
			cat("length(EPAADJACENTEDGESMINING)=", length(edgesmining_errors))
			cat("\n")
			cat("mean_pimut(EPAADJACENTEDGESMINING)=", mean(edgesmining_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPATRANSITIONMINING
			epatransitionmixedmining  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget)
			line_branch_exception_epatransition_errors = epatransitionmixedmining$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPATRANSITIONMINING)=", length(line_branch_exception_epatransition_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPATRANSITIONMINING)=", mean(line_branch_exception_epatransition_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPAEXCEPTIONMINING
			line_branch_exception_epaexceptionmining_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget)
			line_branch_exception_epaexceptionmining_errors = line_branch_exception_epaexceptionmining_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPAEXCEPTIONMINING)=", length(line_branch_exception_epaexceptionmining_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPAEXCEPTIONMINIG)=", mean(line_branch_exception_epaexceptionmining_errors))
			cat("\n")

			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGESMINING
			line_branch_exception_edgesmining_rows = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget)
			line_branch_exception_edgesmining_errors = line_branch_exception_edgesmining_rows$PIMUT
			cat("length(LINE:BRANCH:EXCEPTION:EPAADJACENTEDGESMINING)=", length(line_branch_exception_edgesmining_errors))
			cat("\n")
			cat("mean_pimut(LINE:BRANCH:EXCEPTION:EPAADJACENTEDGESMINING)=", mean(line_branch_exception_edgesmining_errors))
			cat("\n")
			cat("\n")
		}
	}
}

printPitMutationScoreMedian()


