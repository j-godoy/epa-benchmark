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

decimals_size_pvalue = 2
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPA","VS Evosuite+EPAXP","VS Evosuite+EPAXP","VS Evosuite+EPAXP","VS Evosuite+EPAXP","VS Evosuite+EPAXP","VS Evosuite+EPAXP", sep=", ")
	cat("\n")
	cat("Subject","Evosuite+SMC","Evosuite+SMC","EvosuiteMOSA","EvosuiteMOSA","RANDOOP","RANDOOP","Evosuite+SMC","Evosuite+SMC","EvosuiteMOSA","EvosuiteMOSA","RANDOOP","RANDOOP",sep=", ")
	cat("\n")
	cat("Subject","A12","p-value","A12","p-value","A12","p-value","A12","p-value","A12","p-value","A12","p-value",sep=", ")
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
	if (p_value < 0.005)
	{
		p_value = "< 0.005"
	} else if (p_value < 0.05)
	{
		p_value = "< 0.05"
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
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# VS LINE:BRANCH:EXCEPTION:EPATRANSITION
			#---------------------------------------
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			epatransition_pit = epatransition_rows$PIMUT
			
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget)
			strongmut_pit = strongmut_rows$PIMUT
			strongmut_a12_vsepa = round(measureA(strongmut_pit, epatransition_pit),digits=decimals_size_a12)
			strongmut_p_value_vsepa = pValueRefactor(wilcox.test(strongmut_pit, epatransition_pit)$p.value)
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_rows  = subset(stats,SUBJ==subj & TOOL=='mosa_line_branch_exception_strongmutation' & BUD==budget)
			mosa_strongmut_pit = mosa_strongmut_rows$PIMUT
			mosa_strongmut_a12_vsepa = round(measureA(mosa_strongmut_pit, epatransition_pit),digits=decimals_size_a12)
			mosa_strongmut_p_value_vsepa = pValueRefactor(wilcox.test(mosa_strongmut_pit, epatransition_pit)$p.value)
						
			# RANDOOP
			randoop_rows  = subset(stats,SUBJ==subj & TOOL=='randoop' & BUD==budget)
			randoop_pit = randoop_rows$PIMUT
			randoop_a12_vsepa = round(measureA(randoop_pit, epatransition_pit),digits=decimals_size_a12)
			randoop_p_value_vsepa = pValueRefactor(wilcox.test(randoop_pit, epatransition_pit)$p.value)
			
			
			# VS LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			#------------------------------------------
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			edges_pit = edges_rows$PIMUT
			
			# LINE:BRANCH:EXCEPTION:STRONGMUTATION
			strongmut_a12_vsedges = round(measureA(strongmut_pit, edges_pit),digits=decimals_size_a12)
			strongmut_p_value_vsedges = pValueRefactor(wilcox.test(strongmut_pit, edges_pit)$p.value)
			
			# MOSA LINE:BRANCH:EXCEPTION:STRONGMUTATION
			mosa_strongmut_a12_vsedges = round(measureA(mosa_strongmut_pit, edges_pit),digits=decimals_size_a12)
			mosa_strongmut_p_value_vsedges = pValueRefactor(wilcox.test(mosa_strongmut_pit, edges_pit)$p.value)
						
			# RANDOOP
			randoop_a12_vsedges = round(measureA(randoop_pit, edges_pit),digits=decimals_size_a12)
			randoop_p_value_vsedges = pValueRefactor(wilcox.test(randoop_pit, edges_pit)$p.value)
			
			
			cat(", ",strongmut_a12_vsepa,", ",strongmut_p_value_vsepa,", ",mosa_strongmut_a12_vsepa,", ",mosa_strongmut_p_value_vsepa,", ",randoop_a12_vsepa,", ",randoop_p_value_vsepa,", ",strongmut_a12_vsedges,", ",strongmut_p_value_vsedges,", ",mosa_strongmut_a12_vsedges,", ",mosa_strongmut_p_value_vsedges,", ",randoop_a12_vsedges,", ",randoop_p_value_vsedges,sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ2_1()
sink()
