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

decimals_size_pvalue = 2
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite","Evosuite","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPAX","Evosuite+EPAX","Evosuite+EPAX","Evosuite+EPAX","Evosuite+EPAX","Evosuite+EPAX","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP", sep=", ")
	cat("\n")
	cat("Subject","mu","#pf","mu","A12","p-value","#pf","A12","p-value","mu","A12","p-value","#pf","A12","p-value","mu","A12","p-value","#pf","A12","p-value",sep=", ")
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

RQ2_1 <- function()
{
	bug_type_all  = "all"
	bug_type_errprot  = "errprot"
	for (budget in budgets) {
		for(subj in subjects) {
			name_subj = strsplit(subj, "[.]")[[1]]
			name_subj = tail(name_subj, n=1)
			cat(name_subj)
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget & BUG_TYPE==bug_type_all & STRATEGY=="evosuite")
			default_pit = default_rows$PIMUT
			default_pitmean = roundDecimals(round(mean(default_pit)*100, digits=digits_size_to_percentage))
			
			default_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget & BUG_TYPE==bug_type_errprot)
			default_pf = default_rows_protocol$KILLED_PIMUT
			default_pf_mean = roundDecimals(round(mean(default_pf), digits=digits_size_to_percentage))
						
			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			epatransition_pit = epatransition_rows$PIMUT
			epatransition_pitmean = roundDecimals(round(mean(epatransition_pit)*100, digits=digits_size_to_percentage))
			epatransition_a12 = round(measureA(default_pit, epatransition_pit),digits=decimals_size_a12)
			epatransition_p_value = pValueRefactor(wilcox.test(default_pit, epatransition_pit)$p.value)
			
			epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			epatransition_pf = epatransition_rows_protocol$KILLED_PIMUT
			epatransition_pf_mean = roundDecimals(round(mean(epatransition_pf), digits=digits_size_to_percentage))
			epatransition_a12_pf = round(measureA(default_pf, epatransition_pf),digits=decimals_size_a12)
			epatransition_pf_p_value = pValueRefactor(wilcox.test(default_pf, epatransition_pf)$p.value)
			
			# LINE:BRANCH:EXCEPTION:EPAEXCEPTION
			epaex_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			epaex_pit = epaex_rows$PIMUT
			epaex_pitmean = roundDecimals(round(mean(epaex_pit)*100, digits=digits_size_to_percentage))
			epaex_a12 = round(measureA(default_pit, epaex_pit),digits=decimals_size_a12)
			epaex_p_value = pValueRefactor(wilcox.test(default_pit, epaex_pit)$p.value)
			
			epaex_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			epaex_pf = epaex_rows_protocol$KILLED_PIMUT
			epaex_pf_mean = roundDecimals(round(mean(epaex_pf), digits=digits_size_to_percentage))
			epaex_a12_pf = round(measureA(default_pf, epaex_pf),digits=decimals_size_a12)
			epaex_pf_p_value = pValueRefactor(wilcox.test(default_pf, epaex_pf)$p.value)
			
			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_all)
			edges_pit = edges_rows$PIMUT
			edges_pitmean = roundDecimals(round(mean(edges_pit)*100, digits=digits_size_to_percentage))
			edges_a12 = round(measureA(default_pit, edges_pit),digits=decimals_size_a12)
			edges_p_value = pValueRefactor(wilcox.test(default_pit, edges_pit)$p.value)
			
			edges_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			edges_pf = edges_rows_protocol$KILLED_PIMUT
			edges_pf_mean = roundDecimals(round(mean(edges_pf), digits=digits_size_to_percentage))
			edges_a12_pf = round(measureA(default_pf, edges_pf),digits=decimals_size_a12)
			edges_pf_p_value = pValueRefactor(wilcox.test(default_pf, edges_pf)$p.value)
			
			cat(", ", default_pitmean, "%, ", default_pf_mean, ", ", epatransition_pitmean, "%, ", epatransition_a12, ", ", epatransition_p_value, ", ", epatransition_pf_mean, ", ", epatransition_a12_pf, ", ", epatransition_pf_p_value, ", ",
			
			epaex_pitmean, "%, ", epaex_a12, ", ", epaex_p_value, ", ", epaex_pf_mean, ", ", epaex_a12_pf, ", ", epaex_pf_p_value, ", ",
			
			edges_pitmean, "%, ", edges_a12, ", ", edges_p_value, ", ", edges_pf_mean, ", ", edges_a12_pf, ", ", edges_pf_p_value, sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ2_1()
sink()
