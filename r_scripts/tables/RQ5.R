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
	cat("Subject", "DEF+EPAX vs DEF+EPAXP", "DEF+EPAX vs DEF+EPAXP", "DEF+EPAX vs DEF+EPAXP", "DEF+EPAX vs DEF+EPAXP", sep=", ")
	cat("\n")
	cat("Subject", "AVG", "AVG", "A12", "p-value", sep=", ")
	cat("\n")
	cat("Subject", "DEF+EPAX", "DEF+EPAXP", "A12", "p-value", sep=", ")
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

RQ4 <- function()
{
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		for (budget in budgets)
		{
			# DEF+EPATRANSITION_EPAEXCEPTION
			def_epatransition_epaexception_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition_epaexception' & BUD==budget)
			def_epatransition_epaexception_pit = def_epatransition_epaexception_rows$PIMUT
			def_epatransition_epaexception_mean_pit = round(mean(def_epatransition_epaexception_pit)*100, digits=digits_size_to_percentage)
						
			# DEF+EPAADJACENTEDGES
			def_edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			def_edges_pit = def_edges_rows$PIMUT
			def_epadges_a12_epaex = round(measureA(def_epatransition_epaexception_pit, def_edges_pit), digits=decimals_size_a12)
			def_edges_p_value_epaex = pValueRefactor(wilcox.test(def_epatransition_epaexception_pit, def_edges_pit)$p.value)
			def_edges_mean_pit = round(mean(def_edges_pit)*100, digits=digits_size_to_percentage)
			
			
			cat(name_subj, paste(def_epatransition_epaexception_mean_pit,"%",sep=""), paste(def_edges_mean_pit,"%",sep=""), def_epadges_a12_epaex, def_edges_p_value_epaex, sep=", ")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ4()
sink()