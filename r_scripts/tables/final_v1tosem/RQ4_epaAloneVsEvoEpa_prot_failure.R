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
	cat("Subject", "EPA vs Evosuite+EPA", "EPA vs Evosuite+EPA", "EPA vs Evosuite+EPA", "EPA vs Evosuite+EPA", "EPAX vs Evosuite+EPAX", "EPAX vs Evosuite+EPAX", "EPAX vs Evosuite+EPAX", "EPAX vs Evosuite+EPAX", "EPAXP vs Evosuite+EPAXP", "EPAXP vs Evosuite+EPAXP", "EPAXP vs Evosuite+EPAXP", "EPAXP vs Evosuite+EPAXP", sep=", ")
	cat("\n")
	cat("Subject","mu A12","p-value","#pf A12","p-value","mu A12","p-value","#pf A12","p-value","mu A12","p-value","#pf A12","p-value",sep=", ")
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

roundDecimalsA12 <- function(value)
{
	if (value < 0.001) {
		return (round(value, digits=5))
	}
	return (round(value, digits=decimals_size_a12))
}

RQ4 <- function()
{
	bug_type_all  = "all"
	bug_type_errprot  = "errprot"
	for (budget in budgets) {
		for(subj in subjects) {
			name_subj = strsplit(subj, "[.]")[[1]]
			name_subj = tail(name_subj, n=1)
			# EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			epatransition_pit = epatransition_rows$PIMUT
			epatransition_mean_pit = round(mean(epatransition_pit)*100, digits=digits_size_to_percentage)
			epatransition_mean_pit = roundDecimals(epatransition_mean_pit)
			
			epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			epatransition_pf = epatransition_rows_protocol$KILLED_PIMUT
			epatransition_pf_mean = roundDecimals(round(mean(epatransition_pf), digits=digits_size_to_percentage))
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget_protocol)
					# epatransition_pf = epatransition_rows_protocol$KILLED_PIMUT
					# epatransition_pf_mean = roundDecimals(round(mean(epatransition_pf), digits=digits_size_to_percentage))
				# }
			# }
			
			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			def_epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			def_epatransition_pit = def_epatransition_rows$PIMUT
			def_epatransition_a12 = roundDecimalsA12(measureA(epatransition_pit, def_epatransition_pit))
			def_epatransition_p_value = pValueRefactor(wilcox.test(epatransition_pit, def_epatransition_pit)$p.value)
			def_epatransition_mean_pit = round(mean(def_epatransition_pit)*100, digits=digits_size_to_percentage)
			def_epatransition_mean_pit = roundDecimals(def_epatransition_mean_pit)
			
			def_epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			def_epatransition_pf = def_epatransition_rows_protocol$KILLED_PIMUT
			def_epatransition_pf_mean = roundDecimals(round(mean(def_epatransition_pf), digits=digits_size_to_percentage))
			def_epatransition_a12_pf = round(measureA(epatransition_pf, def_epatransition_pf),digits=decimals_size_a12)
			def_epatransition_pf_p_value = pValueRefactor(wilcox.test(epatransition_pf, def_epatransition_pf)$p.value)
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# def_epatransition_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget_protocol)
					# def_epatransition_pf = def_epatransition_rows_protocol$KILLED_PIMUT
					# def_epatransition_pf_mean = roundDecimals(round(mean(def_epatransition_pf), digits=digits_size_to_percentage))
					# def_epatransition_a12_pf = round(measureA(epatransition_pf, def_epatransition_pf),digits=decimals_size_a12)
					# def_epatransition_pf_p_value = pValueRefactor(wilcox.test(epatransition_pf, def_epatransition_pf)$p.value)
				# }
			# }
			
			
			
			# EPAEXCEPTION
			epaex_rows  = subset(stats,SUBJ==subj & TOOL=='epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			epaex_pit = epaex_rows$PIMUT
			epaex_mean_pit = round(mean(epaex_pit)*100, digits=digits_size_to_percentage)
			epaex_mean_pit = roundDecimals(epaex_mean_pit)
			
			epaex_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			epaex_pf = epaex_rows_protocol$KILLED_PIMUT
			epaex_pf_mean = roundDecimals(round(mean(epaex_pf), digits=digits_size_to_percentage))
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# epaex_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epaexceptionmining' & BUD==budget_protocol)
					# epaex_pf = epaex_rows_protocol$KILLED_PIMUT
					# epaex_pf_mean = roundDecimals(round(mean(epaex_pf), digits=digits_size_to_percentage))
				# }
			# }
			
			
			
			# LINE:BRANCH:EXCEPTION:EPAEXCEPTION
			def_epaex_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_all)
			def_epaex_pit = def_epaex_rows$PIMUT
			def_epaex_a12 = roundDecimalsA12(measureA(epaex_pit, def_epaex_pit))
			def_epaex_p_value = pValueRefactor(wilcox.test(epaex_pit, def_epaex_pit)$p.value)
			def_epaex_mean_pit = round(mean(def_epaex_pit)*100, digits=digits_size_to_percentage)
			def_epaex_mean_pit = roundDecimals(def_epaex_mean_pit)
			
			def_epaex_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			def_epaex_pf = def_epaex_rows_protocol$KILLED_PIMUT
			def_epaex_pf_mean = roundDecimals(round(mean(def_epaex_pf), digits=digits_size_to_percentage))
			def_epaex_a12_pf = round(measureA(epaex_pf, def_epaex_pf),digits=decimals_size_a12)
			def_epaex_pf_p_value = pValueRefactor(wilcox.test(epaex_pf, def_epaex_pf)$p.value)
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# def_epaex_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget_protocol)
					# def_epaex_pf = def_epaex_rows_protocol$KILLED_PIMUT
					# def_epaex_pf_mean = roundDecimals(round(mean(def_epaex_pf), digits=digits_size_to_percentage))
					# def_epaex_a12_pf = round(measureA(epaex_pf, def_epaex_pf),digits=decimals_size_a12)
					# def_epaex_pf_p_value = pValueRefactor(wilcox.test(epaex_pf, def_epaex_pf)$p.value)
				# }
			# }

			
			# EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_all)
			edges_epap = edges_rows$PIMUT
			edges_mean_pit = round(mean(edges_epap)*100, digits=digits_size_to_percentage)
			edges_mean_pit = roundDecimals(edges_mean_pit)
			
			edges_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			edges_pf = edges_rows_protocol$KILLED_PIMUT
			edges_pf_mean = roundDecimals(round(mean(edges_pf), digits=digits_size_to_percentage))
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# edges_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedgesmining' & BUD==budget_protocol)
					# edges_pf = edges_rows_protocol$KILLED_PIMUT
					# edges_pf_mean = roundDecimals(round(mean(edges_pf), digits=digits_size_to_percentage))
				# }
			# }
			
			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			def_edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_all)
			def_edges_epap = def_edges_rows$PIMUT
			def_epadges_a12 = roundDecimalsA12(measureA(edges_epap, def_edges_epap))
			def_edges_p_value = pValueRefactor(wilcox.test(edges_epap, def_edges_epap)$p.value)
			def_edges_mean_pit = round(mean(def_edges_epap)*100, digits=digits_size_to_percentage)
			def_edges_mean_pit = roundDecimals(def_edges_mean_pit)
			
			def_edges_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
			def_edges_pf = def_edges_rows_protocol$KILLED_PIMUT
			def_edges_pf_mean = roundDecimals(round(mean(def_edges_pf), digits=digits_size_to_percentage))
			def_edges_a12_pf = round(measureA(edges_pf, def_edges_pf),digits=decimals_size_a12)
			def_edges_pf_p_value = pValueRefactor(wilcox.test(edges_pf, def_edges_pf)$p.value)
			
			# for(subj_protocol in subjects_protocol)
			# {
				# for (budget_protocol in budgets_protocol)
				# {
					# def_edges_rows_protocol  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget_protocol)
					# def_edges_pf = def_edges_rows_protocol$KILLED_PIMUT
					# def_edges_pf_mean = roundDecimals(round(mean(def_edges_pf), digits=digits_size_to_percentage))
					# def_edges_a12_pf = round(measureA(edges_pf, def_edges_pf),digits=decimals_size_a12)
					# def_edges_pf_p_value = pValueRefactor(wilcox.test(edges_pf, def_edges_pf)$p.value)
				# }
			# }
			
			
			cat(name_subj, def_epatransition_a12, def_epatransition_p_value, def_epatransition_a12_pf, def_epatransition_pf_p_value,
			def_epaex_a12, def_epaex_p_value,  def_epaex_a12_pf, def_epaex_pf_p_value,
			def_epadges_a12, def_edges_p_value, def_edges_a12_pf, def_edges_pf_p_value, sep=", ")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ4()
sink()