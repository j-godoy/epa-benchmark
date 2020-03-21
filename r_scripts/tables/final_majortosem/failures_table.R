#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!= 1) {
  stop("Input csv file argument must be supplied (report.csv with general bugs and protocol failures -needs columns added in excel-)", call.=FALSE)
}

csv_filename = args[1]
# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)


digits_size = 2

printHeader <- function()
{
	cat("Subject","CRITERION","TOTAL","PF","GF","MU(GF)","MU(PF)","PF/GF", sep=", ")
	cat("\n")
	cat("EOH\n")
}

RQ1 <- function()
{
	bug_type_errprot  = "errprot"
	bug_type_all  = "all"
	for (budget in budgets) {
		for(subj in subjects) {
			for (criterion in tools) {
				name_subj = strsplit(subj, "[.]")[[1]]
				name_subj = tail(name_subj, n=1)
				cat(name_subj)
				# ALL
				rows_all  = subset(stats,SUBJ==subj & TOOL==criterion & BUD==budget & BUG_TYPE==bug_type_all)
				rows_total = round(mean(rows_all$TOT_PIMUT), digits=digits_size)
				rows_all_killed = rows_all$KILLED_PIMUT
				all_killed_mean = round(mean(rows_all_killed), digits=digits_size)
				all_killed_mu = round(all_killed_mean / rows_total, digits=digits_size)
				
				# ERRPROT
				rows_errprot  = subset(stats,SUBJ==subj & TOOL==criterion & BUD==budget & BUG_TYPE==bug_type_errprot)
				#rows_errprot_total = rows_errprot$TOT_PIMUT
				rows_errprot_killed = rows_errprot$KILLED_PIMUT
				errprot_killed_mean = round(mean(rows_errprot_killed), digits=digits_size)
				errprot_killed_mu = round(errprot_killed_mean / rows_total, digits=digits_size)
				
				pf_div_gf = round(errprot_killed_mean / all_killed_mean, digits=digits_size)
									
		
				
				cat(", ", criterion, ", ", rows_total, ", ", errprot_killed_mean, ", ", all_killed_mean, ", ", all_killed_mu, ", ", errprot_killed_mu, ", ", pf_div_gf, sep="")
				cat("\n")
			}
		}
	}
}

sink("table.csv")
printHeader()
RQ1()
sink()
