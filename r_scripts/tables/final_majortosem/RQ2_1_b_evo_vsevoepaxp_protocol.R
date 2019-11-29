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
	cat("Subject","#pf Evosuite","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP", sep=", ")
	cat("\n")
	cat("Subject","#pf Evosuite","#pf","A12","p-value","#pf Diff",sep=", ")
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
	bug_type_errprot  = "errprot"
	for (budget in budgets) {
		for(subj in subjects) {
			name_subj = strsplit(subj, "[.]")[[1]]
			name_subj = tail(name_subj, n=1)
			cat(name_subj)
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget & BUG_TYPE==bug_type_errprot)
			default_protocol = default_rows$KILLED_PIMUT
			default_protocolmean = round(mean(default_protocol), digits=digits_size_to_percentage)
					
			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			edges_protocol = edges_rows$KILLED_PIMUT
			edges_protocolmean = round(mean(edges_protocol), digits=digits_size_to_percentage)
			edges_a12 = round(measureA(default_protocol, edges_protocol),digits=decimals_size_a12)
			edges_p_value = pValueRefactor(wilcox.test(default_protocol, edges_protocol)$p.value)
			pfdiff =  round((edges_protocolmean / default_protocolmean - 1) * 100 , digits=decimals_size_pvalue)
			if(pfdiff > 0)
				pfdiff = paste("+",pfdiff,sep="")
			
			cat(", ", roundDecimals(default_protocolmean), ", ", roundDecimals(edges_protocolmean), ", ", edges_a12, ", ", edges_p_value, ", ", pfdiff, "%", sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ2_1()
sink()
