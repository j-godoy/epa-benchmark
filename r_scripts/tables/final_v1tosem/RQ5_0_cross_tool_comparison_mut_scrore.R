#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!= 1) {
  stop("Input csv file argument must be supplied (report.csv with general bugs and protocol failures -needs columns added in excel-)", call.=FALSE)
}

csv_filename_general_bug = args[1]
# run the script
stats = read.csv(csv_filename_general_bug, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)

decimals_size_pvalue = 3
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite+SMC","Evosuite+SMC","EvosuiteMOSA","EvosuiteMOSA","RANDOOP","RANDOOP", sep=", ")
	cat("\n")
	cat("Subject","mu","#pf","mu","#pf","mu","#pf", sep=", ")
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

RQ2_1 <- function()
{
	bug_type_all  = "all"
	bug_type_errprot  = "errprot"
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="evosuite" & BUG_TYPE==bug_type_all)
			strongmut_pit = strongmut_rows$PIMUT
			strongmut_pitmean = roundDecimals(round(mean(strongmut_pit)*100, digits=digits_size_to_percentage))
			
			strongmut_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="evosuite"  & BUG_TYPE==bug_type_errprot)
			strongmut_pf = strongmut_rows_protocol$KILLED_PIMUT
			strongmut_pf_mean = roundDecimals(round(mean(strongmut_pf), digits=digits_size_to_percentage))
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="mosuite" & BUG_TYPE==bug_type_all)
			mosa_strongmut_pit = mosa_strongmut_rows$PIMUT
			mosa_strongmut_pitmean = roundDecimals(round(mean(mosa_strongmut_pit)*100, digits=digits_size_to_percentage))
			
			mosa_strongmut_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="mosuite" & BUG_TYPE==bug_type_errprot)
			mosa_strongmut_pf = mosa_strongmut_rows_protocol$KILLED_PIMUT
			mosa_strongmut_pf_mean = roundDecimals(round(mean(mosa_strongmut_pf), digits=digits_size_to_percentage))
						
			# RANDOOP
			randoop_rows  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget & BUG_TYPE==bug_type_all)
			randoop_pit = randoop_rows$PIMUT
			randoop_pitmean = roundDecimals(round(mean(randoop_pit)*100, digits=digits_size_to_percentage))
			
			randoop_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget & BUG_TYPE==bug_type_errprot)
			randoop_pf = randoop_rows_protocol$KILLED_PIMUT
			randoop_pf_mean = roundDecimals(round(mean(randoop_pf), digits=digits_size_to_percentage))
			
			cat(", ", strongmut_pitmean, "%, ", strongmut_pf_mean, ", ", mosa_strongmut_pitmean, "%, ", mosa_strongmut_pf_mean, ", ", randoop_pitmean, "%, ", randoop_pf_mean, sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ2_1()
sink()
