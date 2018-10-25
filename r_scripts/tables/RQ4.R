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
	cat("Subject", "EPA vs DEF+EPA", "EPA vs DEF+EPA", "EPA vs DEF+EPA", "EPA vs DEF+EPA", "EPAX vs DEF+EPAX", "EPAX vs DEF+EPAX", "EPAX vs DEF+EPAX", "EPAX vs DEF+EPAX", "EPAXP vs DEF+EPAXP", "EPAXP vs DEF+EPAXP", "EPAXP vs DEF+EPAXP", "EPAXP vs DEF+EPAXP", sep=", ")
	cat("\n")
	cat("Subject", "AVG", "AVG", "A12", "p-value", "AVG", "AVG", "A12", "p-value", "AVG", "AVG", "A12", "p-value", sep=", ")
	cat("\n")
	cat("Subject", "EPA", "DEF+EPA", "A12", "p-value", "EPAX", "DEF+EPAX", "A12", "p-value", "EPAXP", "DEF+EPAXP", "A12", "p-value", sep=", ")
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

roundDecimalsA12 <- function(value)
{
	if (value < 0.001) {
		return (round(value, digits=5))
	}
	return (round(value, digits=decimals_size_a12))
}

RQ4 <- function()
{
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		for (budget in budgets)
		{
			# EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition' & BUD==budget)
			epatransition_pit = epatransition_rows$PIMUT
			epatransition_mean_pit = round(mean(epatransition_pit)*100, digits=digits_size_to_percentage)
			epatransition_mean_pit = roundDecimals(epatransition_mean_pit)
			
			# DEF+EPATRANSITION
			def_epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			def_epatransition_pit = def_epatransition_rows$PIMUT
			def_epatransition_a12 = roundDecimalsA12(measureA(epatransition_pit, def_epatransition_pit))
			def_epatransition_p_value = pValueRefactor(wilcox.test(epatransition_pit, def_epatransition_pit)$p.value)
			def_epatransition_mean_pit = round(mean(def_epatransition_pit)*100, digits=digits_size_to_percentage)
			def_epatransition_mean_pit = roundDecimals(def_epatransition_mean_pit)
			
			# EPATRANSITION_EPAEXCEPTION
			epatransition_epaexception_rows  = subset(stats,SUBJ==subj & TOOL=='epatransition_epaexception' & BUD==budget)
			epatransition_epaexception_pit = epatransition_epaexception_rows$PIMUT
			epatransition_epaexception_mean_pit = round(mean(epatransition_epaexception_pit)*100, digits=digits_size_to_percentage)
			epatransition_epaexception_mean_pit = roundDecimals(epatransition_epaexception_mean_pit)
			
			# DEF+EPATRANSITION_EPAEXCEPTION
			def_epatransition_epaexception_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition_epaexception' & BUD==budget)
			def_epatransition_epaexception_pit = def_epatransition_epaexception_rows$PIMUT
			def_epatransition_epaexception_a12 = roundDecimalsA12(measureA(epatransition_epaexception_pit, def_epatransition_epaexception_pit))
			def_epatransition_epaexception_p_value = pValueRefactor(wilcox.test(epatransition_epaexception_pit, def_epatransition_epaexception_pit)$p.value)
			def_epatransition_epaexception_mean_pit = round(mean(def_epatransition_epaexception_pit)*100, digits=digits_size_to_percentage)
			def_epatransition_epaexception_mean_pit = roundDecimals(def_epatransition_epaexception_mean_pit)
			
			# EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedges' & BUD==budget)
			edges_epap = edges_rows$PIMUT
			edges_mean_pit = round(mean(edges_epap)*100, digits=digits_size_to_percentage)
			edges_mean_pit = roundDecimals(edges_mean_pit)
			
			# DEF+EPAADJACENTEDGES
			def_edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			def_edges_epap = def_edges_rows$PIMUT
			def_epadges_a12 = roundDecimalsA12(measureA(edges_epap, def_edges_epap))
			def_edges_p_value = pValueRefactor(wilcox.test(edges_epap, def_edges_epap)$p.value)
			def_edges_mean_pit = round(mean(def_edges_epap)*100, digits=digits_size_to_percentage)
			def_edges_mean_pit = roundDecimals(def_edges_mean_pit)
			
			
			cat(name_subj, paste(epatransition_mean_pit, "%", sep=""), paste(def_epatransition_mean_pit, "%", sep=""), def_epatransition_a12, def_epatransition_p_value, paste(epatransition_epaexception_mean_pit, "%", sep=""), paste(def_epatransition_epaexception_mean_pit, "%", sep=""), def_epatransition_epaexception_a12, def_epatransition_epaexception_p_value, paste(edges_mean_pit,"%", sep=""), paste(def_edges_mean_pit,"%", sep=""), def_epadges_a12, def_edges_p_value, sep=", ")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ4()
sink()