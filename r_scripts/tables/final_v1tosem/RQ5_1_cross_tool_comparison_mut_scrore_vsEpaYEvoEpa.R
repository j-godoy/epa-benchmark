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
	cat("Subject","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA","VS EPA",
	"VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA", sep=", ")
	cat("\n")
	cat("Subject","Evosuite+SMC","Evosuite+SMC","Evosuite+SMC","Evosuite+SMC","EvosuiteMOSA","EvosuiteMOSA","EvosuiteMOSA","EvosuiteMOSA","RANDOOP","RANDOOP","RANDOOP","RANDOOP","Evosuite+SMC","Evosuite+SMC","Evosuite+SMC","Evosuite+SMC","EvosuiteMOSA","EvosuiteMOSA","EvosuiteMOSA","EvosuiteMOSA","RANDOOP","RANDOOP","RANDOOP","RANDOOP",sep=", ")
	cat("\n")
	cat("Subject","A12 mu","p-value","A12 #pf","p-value","A12 mu","p-value","A12 #pf","p-value","A12 mu","p-value","A12 #pf","p-value","A12 mu","p-value","A12 #pf","p-value","A12 mu","p-value","A12 #pf","p-value","A12 mu","p-value","A12 #pf","p-value",sep=", ")
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
	if (p_value < 0.001)
	{
		p_value = "< 0.001"
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
			# VS EPATRANSITIONMINING
			#---------------------------------------
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			epatransition_pit = epatransition_rows$PIMUT
			
			epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			epatransition_pf = epatransition_rows_protocol$KILLED_PIMUT
			
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="evosuite" & BUG_TYPE==bug_type_all)
			strongmut_pit = strongmut_rows$PIMUT
			strongmut_a12_vsepa = round(measureA(strongmut_pit, epatransition_pit),digits=decimals_size_a12)
			strongmut_p_value_vsepa = pValueRefactor(wilcox.test(strongmut_pit, epatransition_pit)$p.value)
			
			strongmut_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="evosuite" & BUG_TYPE==bug_type_errprot)
			strongmut_pf = strongmut_rows_protocol$KILLED_PIMUT
			strongmut_pf_a12_vsepa = round(measureA(strongmut_pf, epatransition_pf),digits=decimals_size_a12)
			strongmut_pf_p_value_vsepa = pValueRefactor(wilcox.test(strongmut_pf, epatransition_pf)$p.value)
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="mosuite" & BUG_TYPE==bug_type_all)
			mosa_strongmut_pit = mosa_strongmut_rows$PIMUT
			mosa_strongmut_a12_vsepa = round(measureA(mosa_strongmut_pit, epatransition_pit),digits=decimals_size_a12)
			mosa_strongmut_p_value_vsepa = pValueRefactor(wilcox.test(mosa_strongmut_pit, epatransition_pit)$p.value)
			
			mosa_strongmut_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & STRATEGY=="mosuite" & BUG_TYPE==bug_type_errprot)
			mosa_strongmut_pf = mosa_strongmut_rows_protocol$KILLED_PIMUT
			mosa_strongmut_pf_a12_vsepa = round(measureA(mosa_strongmut_pf, epatransition_pf),digits=decimals_size_a12)
			mosa_strongmut_pf_p_value_vsepa = pValueRefactor(wilcox.test(mosa_strongmut_pf, epatransition_pf)$p.value)
						
			# RANDOOP
			randoop_rows  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget & BUG_TYPE==bug_type_all)
			randoop_pit = randoop_rows$PIMUT
			randoop_a12_vsepa = round(measureA(randoop_pit, epatransition_pit),digits=decimals_size_a12)
			randoop_p_value_vsepa = pValueRefactor(wilcox.test(randoop_pit, epatransition_pit)$p.value)
			
			randoop_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget & BUG_TYPE==bug_type_errprot)
			randoop_pf = randoop_rows_protocol$KILLED_PIMUT
			randoop_pf_a12_vsepa = round(measureA(randoop_pf, epatransition_pf),digits=decimals_size_a12)
			randoop_pf_p_value_vsepa = pValueRefactor(wilcox.test(randoop_pf, epatransition_pf)$p.value)
			
			
			# VS LINE:BRANCH:EXCEPTION:EPATRANSITIONMINING
			#------------------------------------------
			evoepa_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			evoepa_pit = evoepa_rows$PIMUT
			
			evoepa_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			evoepa_pf = evoepa_rows_protocol$KILLED_PIMUT
			
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_a12_vsevoepa = round(measureA(strongmut_pit, evoepa_pit),digits=decimals_size_a12)
			strongmut_p_value_vsevoepa = pValueRefactor(wilcox.test(strongmut_pit, evoepa_pit)$p.value)
			
			strongmut_pf_a12_vsevoepa = round(measureA(strongmut_pf, evoepa_pf),digits=decimals_size_a12)
			strongmut_pf_p_value_vsevoepa = pValueRefactor(wilcox.test(strongmut_pf, evoepa_pf)$p.value)
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_a12_vsevoepa = round(measureA(mosa_strongmut_pit, evoepa_pit),digits=decimals_size_a12)
			mosa_strongmut_p_value_vsevoepa = pValueRefactor(wilcox.test(mosa_strongmut_pit, evoepa_pit)$p.value)
			
			mosa_strongmut_pf_a12_vsevoepa = round(measureA(mosa_strongmut_pf, evoepa_pf),digits=decimals_size_a12)
			mosa_strongmut_pf_p_value_vsevoepa = pValueRefactor(wilcox.test(mosa_strongmut_pf, evoepa_pf)$p.value)
						
			# RANDOOP
			randoop_a12_vsevoepa = round(measureA(randoop_pit, evoepa_pit),digits=decimals_size_a12)
			randoop_p_value_vsevoepa = pValueRefactor(wilcox.test(randoop_pit, evoepa_pit)$p.value)
			
			randoop_pf_a12_vsevoepa = round(measureA(randoop_pf, evoepa_pf),digits=decimals_size_a12)
			randoop_pf_p_value_vsevoepa = pValueRefactor(wilcox.test(randoop_pf, evoepa_pf)$p.value)
			
			
			cat(", ",strongmut_a12_vsepa,", ",strongmut_p_value_vsepa,", ",strongmut_pf_a12_vsepa,", ",strongmut_pf_p_value_vsepa,", ",mosa_strongmut_a12_vsepa,", ",mosa_strongmut_p_value_vsepa,", ",mosa_strongmut_pf_a12_vsepa,", ",mosa_strongmut_pf_p_value_vsepa,", ",randoop_a12_vsepa,", ",randoop_p_value_vsepa,", ",randoop_pf_a12_vsepa,", ",randoop_pf_p_value_vsepa,", ",
			strongmut_a12_vsevoepa,", ",strongmut_p_value_vsevoepa,", ",strongmut_pf_a12_vsevoepa,", ",strongmut_pf_p_value_vsevoepa,", ",mosa_strongmut_a12_vsevoepa,", ",mosa_strongmut_p_value_vsevoepa,", ",mosa_strongmut_pf_a12_vsevoepa,", ",mosa_strongmut_pf_p_value_vsevoepa,", ",randoop_a12_vsevoepa,", ",randoop_p_value_vsevoepa,", ",randoop_pf_a12_vsevoepa,", ",randoop_pf_p_value_vsevoepa,sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ2_1()
sink()
