#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)<2) {
  stop("Input csv file argument must be supplied (report.csv) and a subject name (or list) (ex: com.example.socket.Socket)", call.=FALSE)
}

csv_filename = args[1]

# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")

subjects_in_file = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)
bug_type = unique(stats$BUG_TYPE)

subjects <- list()
if (length(args) == 2)
{
	if (args[2] == "ALL")
	{
		subjects = subjects_in_file
	}
} else
{
	i = 2
	while( i <= length(args))
	{
		curr_subj = args[i]
		if (!(curr_subj %in% subjects_in_file))
		{
			cat("WARN! Does not exists subject '", curr_subj, "' in '", csv_filename,"'\n", sep="")
			i = i + 1
			next
		}
		subjects <- c(subjects, curr_subj)
		i = i + 1
	}
}

createBoxPlot <- function()
{
	for (b_type in bug_type) {
		for (budget in budgets) {
			for(subj in subjects) {
				default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget & BUG_TYPE==b_type)
				
				#line_branch_exception_epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget)
				line_branch_exception_edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==b_type)
				#line_branch_exception_smc_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget)
				#line_branch_exception_mosa_rows  = subset(stats,SUBJ==subj & TOOL=='mosa_line_branch_exception_strongmutation' & BUD==budget)
				#randoop_rows  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget)

				default_pit = default_rows$PIMUT
				#line_branch_exception_epatransition_pit = line_branch_exception_epatransition_rows$PIMUT
				line_branch_exception_edges_pit = line_branch_exception_edges_rows$PIMUT
				#line_branch_exception_smc_pit = line_branch_exception_smc_rows$PIMUT
				#line_branch_exception_mosa_pit = line_branch_exception_mosa_rows$PIMUT
				#line_branch_exception_randoop_pit = randoop_rows$PIMUT

				name_subj = strsplit(subj, "[.]")[[1]]
				name_subj = tail(name_subj, n=1)
				outputname = paste("boxplot_combined_",name_subj,"_",b_type,"_",budget,".pdf",sep = "")
				pdf(outputname)
				budget_txt = paste("Budget: ", budget, " segs", sep="")
				#boxplot(default_pit, line_branch_exception_epatransition_pit, line_branch_exception_edges_pit, line_branch_exception_smc_pit, line_branch_exception_mosa_pit, line_branch_exception_randoop_pit, names=c("A","B","C","D","E","F"),main=c(name_subj),ylab="Mutation Score",cex.lab=1.6, cex.axis=1.3, cex.main=2)
				
				boxplot(default_pit, line_branch_exception_edges_pit, names=c("EVO","EVO+EDGES"),main=c(name_subj),ylab="Mutation Score",cex.lab=1.6, cex.axis=1.3, cex.main=2)
				
				#default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget)
				#epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition' & BUD==budget)
				#epatransition_epaexception_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition_epaexception' & BUD==budget)
				#edges_rows  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedges' & BUD==budget)

				#default_pit = default_rows$PIMUT
				#epatransition_pit = epatransition_rows$PIMUT
				#epatransition_epaexception_pit = epatransition_epaexception_rows$PIMUT
				#edges_pit = edges_rows$PIMUT
				
				#outputname = paste("only_epa_criterion_",name_subj,".pdf",sep = "")
				#pdf(outputname)
				#boxplot(default_pit, epatransition_pit, epatransition_epaexception_pit, edges_pit, names=c("EVO","EVO+EPA","EVO+EPAXP","EVO+SMC","EVOMOSA","RAND"),main=c(name_subj),ylab="PIT Score",xlab=budget_txt,cex.lab=1.5, cex.axis=1.6)
			}
		}
	}
}

createBoxPlot()