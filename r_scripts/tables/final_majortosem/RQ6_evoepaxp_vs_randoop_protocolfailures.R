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
	cat("Subject","Evosuite+EPAXP","Randoop","Randoop","Randoop",sep=", ")
	cat("\n")
	cat("Subject","Evosuite+EPAXP","#pf","A12","p-value",sep=", ")
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
	    return ("0.5")
	}
	p_value = round(p_value, digits=decimals_size_pvalue)
	return (p_value)
	
	#if (p_value < 0.005)
	#{
	#	p_value = "< 0.005"
	#} else if (p_value < 0.05)
	#{
	#	p_value = "< 0.05"
	#} else
	#{
	#	p_value = round(p_value, digits=decimals_size_pvalue)
	#}
	#return (p_value)
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
	bug_type_errprot  = "errprot"
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# VS LINE:BRANCH:EXCEPTION:EPATRANSITION:EPAADJACENTEDGESMINING
			#------------------------------------------
			evoepaxp_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			evoepaxp_protocol = evoepaxp_rows$KILLED_PIMUT
			evoepaxp_protocolmean = roundDecimals(round(mean(evoepaxp_protocol), digits=digits_size_to_percentage))
			
			# RANDOOP
			randoop_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_strongmutation' & BUD==budget & BUG_TYPE==bug_type_errprot)
			randoop_protocol = randoop_rows$KILLED_PIMUT
			randoop_protocolmean = roundDecimals(round(mean(randoop_protocol), digits=digits_size_to_percentage))
			randoop_a12_vsevoepaxp = round(measureA(randoop_protocol, evoepaxp_protocol),digits=decimals_size_a12)
			randoop_p_value_vsevoepaxp = pValueRefactor(wilcox.test(randoop_protocol, evoepaxp_protocol)$p.value)
			
			
			cat(", ",evoepaxp_protocolmean, ", ", randoop_protocolmean, ", ", randoop_a12_vsevoepaxp,", ",randoop_p_value_vsevoepaxp, sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ3_2()
sink()
