#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!= 1) {
  stop("Input csv file argument must be supplied (report.csv )", call.=FALSE)
}

csv_filename_general_bug = args[1]
# run the script
stats = read.csv(csv_filename_general_bug, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)

decimals_size_pvalue = 2
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite+EPAXP (3)","Evosuite+SMC (1)","Evosuite+SMC (1)","Evosuite+SMC (1)","Evosuite+SMC (1)","EvosuiteMOSA (2)","EvosuiteMOSA (2)","EvosuiteMOSA (2)","EvosuiteMOSA (2)",sep=", ")
	cat("\n")
	cat("Subject","Evosuite+EPAXP (3)","mu","A12","p-value","mu Diff","mu","A12","p-value","Improvement",sep=", ")
	cat("\n")
	cat("EOH\n")
}

measureA <- function(a,b){

        if(length(a)==0 & length(b)==0){
                return(0.5)
        } else if(length(a)==0){
                ## motivation is that we have no data for "a" but we do for "b".
                ## maybe the process generating "a" always fail (eg out of memory)
                return(0)
        } else if(length(b)==0){
                return(1)
        } 

        r = rank(c(a,b))
        r1 = sum(r[seq_along(a)])

        m = length(a)
        n = length(b)
        A = (r1/m - (m+1)/2)/n

        return(A)

}

pValueRefactor <- function(p_value)
{	
	if (p_value == "NaN") 
	{
	    return ("1")
	}
	if (p_value < 0.0001)
	{
		p_value = "< 0.0001"
	} else
	{
		p_value = round(p_value, digits=decimals_size_pvalue)
	}
	return (p_value)
}

roundDecimals <- function(value)
{
	if (value%%1 == 0) {
		return (paste(value,".0",sep=""))
	}
	return (value)
}

RQ3_2 <- function()
{
	bug_type_all  = "all"
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# VS LINE:BRANCH:EXCEPTION:EPATRANSITION:EPAADJACENTEDGESMINING
			#------------------------------------------
			evoepaxp_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_all)
			evoepaxp_pit = evoepaxp_rows$PIMUT
			evoepaxp_pitmean = round(mean(evoepaxp_pit)*100, digits=digits_size_to_percentage)
			
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="evosuite" & BUG_TYPE==bug_type_all)
			strongmut_pit = strongmut_rows$PIMUT
			strongmut_pitmean = round(mean(strongmut_pit)*100, digits=digits_size_to_percentage)
			strongmut_a12_vsevoepaxp = round(measureA(strongmut_pit, evoepaxp_pit),digits=decimals_size_a12)
			strongmut_p_value_vsevoepaxp = pValueRefactor(wilcox.test(strongmut_pit, evoepaxp_pit)$p.value)
			mudiff_strongmut =  round(((evoepaxp_pitmean / strongmut_pitmean)-1)*100, digits=digits_size_to_percentage)
			if(mudiff_strongmut > 0)
				mudiff_strongmut = paste("+",mudiff_strongmut,sep="")
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="mosuite" & BUG_TYPE==bug_type_all)
			mosa_strongmut_pit = mosa_strongmut_rows$PIMUT
			mosa_strongmut_pitmean = round(mean(mosa_strongmut_pit)*100, digits=digits_size_to_percentage)
			mosa_strongmut_a12_vsevoepaxp = round(measureA(mosa_strongmut_pit, evoepaxp_pit),digits=decimals_size_a12)
			mosa_strongmut_p_value_vsevoepaxp = pValueRefactor(wilcox.test(mosa_strongmut_pit, evoepaxp_pit)$p.value)
			mudiff_mosa =  round(((evoepaxp_pitmean / mosa_strongmut_pitmean)-1)*100, digits=digits_size_to_percentage)
			if(mudiff_mosa > 0)
				mudiff_mosa = paste("+",mudiff_mosa,sep="")
			
			cat(", ",roundDecimals(evoepaxp_pitmean), "%, ", roundDecimals(strongmut_pitmean), "%, ", strongmut_a12_vsevoepaxp,", ",strongmut_p_value_vsevoepaxp,", ", mudiff_strongmut, "%, ", roundDecimals(mosa_strongmut_pitmean), "%, ", mosa_strongmut_a12_vsevoepaxp,", ",mosa_strongmut_p_value_vsevoepaxp, ", ", mudiff_mosa, "% ", sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ3_2()
sink()
