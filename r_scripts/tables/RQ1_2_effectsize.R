#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is two arguments: if not, return an error
if (length(args)!= 2) {
  stop("Input csv files arguments must be supplied (report.csv and test_suite_LOC_EXCEPTIONS.cvs)", call.=FALSE)
}

csv_filename = args[1]
csv_tsloc = args[2]
# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")
stats_loc = read.csv(csv_tsloc, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)

subjects_loc = unique(stats_loc$SUBJ)
tools_loc = unique(stats_loc$TOOL)
budgets_loc = unique(stats_loc$BUD)

decimals_size_pvalue = 2
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite","Evosuite","Evosuite","Evosuite","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPA","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP","Evosuite+EPAXP",sep=", ")
	cat("\n")
	cat("Subject","stmt","branch","excep","TS LOC","stmt","A12","p-value","branch","A12","p-value","excep","A12","p-value","TS LOC","stmt","A12","p-value","branch","A12","p-value","excep","A12","p-value","TS LOC",sep=", ")
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

RQ1_2 <- function()
{
	for(subj in subjects)
	{
		name_subj = strsplit(subj, "[.]")[[1]]
		name_subj = tail(name_subj, n=1)
		cat(name_subj)
		for (budget in budgets)
		{
			# LINE:BRANCH:EXCEPTION
			default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget)
			defaultst = default_rows$LINE
			defaultbr = default_rows$BRNCH
			default_st = roundDecimals(round(mean(default_rows$LINE)*100, digits=digits_size_to_percentage))
			default_br = roundDecimals(round(mean(default_rows$BRNCH)*100, digits=digits_size_to_percentage))
			default_excep = "???"
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					default_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget_loc)
					default_test = round(mean(default_rows_loc$TS_LOC), digits=digits_size_to_percentage)
					default_excep = default_rows_loc$EXCEPTIONS
					default_excep_mean = roundDecimals(round(mean(default_excep), digits=digits_size_to_percentage))
				}
			}
			
			# LINE:BRANCH:EXCEPTION:EPATRANSITION
			epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget)
			epatransitionst = epatransition_rows$LINE
			epatransitionbr = epatransition_rows$BRNCH
			epatransition_st = roundDecimals(round(mean(epatransition_rows$LINE)*100, digits=digits_size_to_percentage))
			epatransition_br = roundDecimals(round(mean(epatransition_rows$BRNCH)*100, digits=digits_size_to_percentage))
			epatransition_excep = "???"
			epatransition_a12_st = round(measureA(defaultst, epatransitionst),digits=decimals_size_a12)
			epatransition_p_value_st = pValueRefactor(wilcox.test(defaultst, epatransitionst)$p.value)
			epatransition_a12_br = round(measureA(defaultbr, epatransitionbr),digits=decimals_size_a12)
			epatransition_p_value_br = pValueRefactor(wilcox.test(defaultbr, epatransitionbr)$p.value)
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					epatransition_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception_epatransition' & BUD==budget_loc)
					epatransition_test = round(mean(epatransition_rows_loc$TS_LOC), digits=digits_size_to_percentage)
					epatransition_excep = epatransition_rows_loc$EXCEPTIONS
					epatransition_excep_mean = roundDecimals(round(mean(epatransition_excep), digits=digits_size_to_percentage))
				}
			}
			epatransition_a12_excep = round(measureA(default_excep, epatransition_excep),digits=decimals_size_a12)
			epatransition_p_value_excep = pValueRefactor(wilcox.test(default_excep, epatransition_excep)$p.value)
			
			# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGES
			edges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget)
			edgesst = edges_rows$LINE
			edgesbr = edges_rows$BRNCH
			edges_st = roundDecimals(round(mean(edges_rows$LINE)*100, digits=digits_size_to_percentage))
			edges_br = roundDecimals(round(mean(edges_rows$BRNCH)*100, digits=digits_size_to_percentage))
			edges_excep = "???"
			edges_a12_st = round(measureA(defaultst, edgesst),digits=decimals_size_a12)
			edges_p_value_st = pValueRefactor(wilcox.test(defaultst, edgesst)$p.value)
			edges_a12_br = round(measureA(defaultbr, edgesbr),digits=decimals_size_a12)
			edges_p_value_br = pValueRefactor(wilcox.test(defaultbr, edgesbr)$p.value)
			
			for(subj_loc in subjects_loc)
			{
				for (budget_loc in budgets_loc)
				{
					edges_rows_loc  = subset(stats_loc,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedges' & BUD==budget_loc)
					edges_test = round(mean(edges_rows_loc$TS_LOC), digits=digits_size_to_percentage)
					edges_excep = edges_rows_loc$EXCEPTIONS
					edges_excep_mean = roundDecimals(round(mean(edges_excep), digits=digits_size_to_percentage))
				}
			}
			edges_a12_excep = round(measureA(default_excep, edges_excep),digits=decimals_size_a12)
			edges_p_value_excep = pValueRefactor(wilcox.test(default_excep, edges_excep)$p.value)
			
			cat(", ", default_st, "%, ", default_br, "%, ", default_excep_mean, ", ", default_test, ", ",
			epatransition_st, "%, ", epatransition_a12_st, ", ", epatransition_p_value_st, ", ", epatransition_br, "%, ", epatransition_a12_br, ", ", epatransition_p_value_br, ", ", epatransition_excep_mean, ", ", epatransition_test, ", ",
			edges_st, "%, ", edges_a12_st, ", ", edges_p_value_st, ", ", edges_br, "%, ", edges_a12_br, ", ", edges_p_value_br, ", ", edges_excep_mean, ", ", edges_test,sep="")
			cat("\n")
		}
	}
}

sink("table.csv")
printHeader()
RQ1_2()
sink()
