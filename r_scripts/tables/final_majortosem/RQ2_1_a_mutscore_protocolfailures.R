#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

# test if there is at least one argument: if not, return an error
if (length(args)!= 1) {
  stop("Input csv file argument must be supplied (report.csv with general bugs and protocol failures -needs columns added in excel-)", call.=FALSE)
}

csv_filename = args[1]
# run the script
stats = read.csv(csv_filename, header=TRUE, sep=",")

subjects = unique(stats$SUBJ)
tools = unique(stats$TOOL)
budgets = unique(stats$BUD)


decimals_size_pvalue = 4
decimals_size_a12 = 2
digits_size_to_percentage = 1

printHeader <- function()
{
	cat("Subject","Evosuite","EPA","EPAX","EPAXP","Evosuite+EPA","Evosuite+EPAX","Evosuite+EPAXP", sep=", ")
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

RQ1 <- function()
{
	def_ranking_mean_list <- c()
	onlyepa_ranking_mean_list <- c()
	onlyepax_ranking_mean_list <- c()
	only_epaxp_ranking_mean_list <- c()
	evoepa_ranking_mean_list <- c()
	evoepax_ranking_mean_list <- c()
	evoepaxp_ranking_mean_list <- c()
	
	bug_type_errprot  = "errprot"
		for (budget in budgets) {
			for(subj in subjects) {
				name_subj = strsplit(subj, "[.]")[[1]]
				name_subj = tail(name_subj, n=1)
				cat(name_subj)
				# LINE:BRANCH:EXCEPTION
				default_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception' & BUD==budget & BUG_TYPE==bug_type_errprot)
				default_pit = default_rows$KILLED_PIMUT
				default_pitmean = round(mean(default_pit), digits=digits_size_to_percentage)
				
				# EPATRANSITIONMINING
				epatransition_rows  = subset(stats,SUBJ==subj & TOOL=='epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				epatransition_pit = epatransition_rows$KILLED_PIMUT
				epatransition_pitmean = round(mean(epatransition_pit), digits=digits_size_to_percentage)
				
				# EPAEXCEPTIONMINING
				epa_epax_rows  = subset(stats,SUBJ==subj & TOOL=='epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				epa_epax_pit = epa_epax_rows$KILLED_PIMUT
				epa_epax_pitmean = round(mean(epa_epax_pit), digits=digits_size_to_percentage)
				
				# EPAADJACENTEDGESMINING
				edges_rows  = subset(stats,SUBJ==subj & TOOL=='epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				edges_pit = edges_rows$KILLED_PIMUT
				edges_pitmean = round(mean(edges_pit), digits=digits_size_to_percentage)
				
				# LINE:BRANCH:EXCEPTION:EPATRANSITIONMINING
				lbeepatransition_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epatransitionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				lbeepatransition_pit = lbeepatransition_rows$KILLED_PIMUT
				lbeepatransition_pitmean = round(mean(lbeepatransition_pit), digits=digits_size_to_percentage)
				
				# LINE:BRANCH:EXCEPTION:EPAEXCEPTIONMINING
				lbeepa_epax_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaexceptionmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				lbeepa_epax_pit = lbeepa_epax_rows$KILLED_PIMUT
				lbeepa_epax_pitmean = round(mean(lbeepa_epax_pit), digits=digits_size_to_percentage)
				
				# LINE:BRANCH:EXCEPTION:EPAADJACENTEDGESMINING
				lbeedges_rows  = subset(stats,SUBJ==subj & TOOL=='line_branch_exception_epaadjacentedgesmining' & BUD==budget & BUG_TYPE==bug_type_errprot)
				lbeedges_pit = lbeedges_rows$KILLED_PIMUT
				lbeedges_pitmean = round(mean(lbeedges_pit), digits=digits_size_to_percentage)
				
				####Ranking
				killed_pit_values <- c((default_pitmean), (epatransition_pitmean), epa_epax_pitmean, edges_pitmean, lbeepatransition_pitmean, lbeepa_epax_pitmean, lbeedges_pitmean)
				sorted_pit_values <- sort(killed_pit_values, method = "quick", decreasing=TRUE) # Orden inverso
				
				#print(sorted_pit_values)
				def_ranking = match(default_pitmean,sorted_pit_values)
				def_ranking_mean_list <- c(def_ranking_mean_list, def_ranking)
				
				onlyepa_ranking = match(epatransition_pitmean,sorted_pit_values)
				onlyepa_ranking_mean_list <- c(onlyepa_ranking_mean_list, onlyepa_ranking)
				
				onlyepax_ranking = match(epa_epax_pitmean,sorted_pit_values)
				onlyepax_ranking_mean_list <- c(onlyepax_ranking_mean_list, onlyepax_ranking)
				
				only_epaxp_ranking = match(edges_pitmean,sorted_pit_values)
				only_epaxp_ranking_mean_list <- c(only_epaxp_ranking_mean_list, only_epaxp_ranking)
				
				evoepa_ranking = match(lbeepatransition_pitmean,sorted_pit_values)
				evoepa_ranking_mean_list <- c(evoepa_ranking_mean_list, evoepa_ranking)
				
				evoepax_ranking = match(lbeepa_epax_pitmean,sorted_pit_values)
				evoepax_ranking_mean_list <- c(evoepax_ranking_mean_list, evoepax_ranking)
				
				evoepaxp_ranking = match(lbeedges_pitmean,sorted_pit_values)
				evoepaxp_ranking_mean_list <- c(evoepaxp_ranking_mean_list, evoepaxp_ranking)
				###end Ranking
				
				cat(", ", roundDecimals(default_pitmean), " (", def_ranking , "), ", roundDecimals(epatransition_pitmean), " (", onlyepa_ranking, "), ", roundDecimals(epa_epax_pitmean), " (", onlyepax_ranking, "), ", roundDecimals(edges_pitmean), " (", only_epaxp_ranking, "), ", roundDecimals(lbeepatransition_pitmean), " (", evoepa_ranking, "), ", roundDecimals(lbeepa_epax_pitmean), " (", evoepax_ranking, "), ",
				roundDecimals(lbeedges_pitmean), " (", evoepaxp_ranking, ")", sep="")
				cat("\n")
			}
		}
		
		def_ranking_mean <- round(mean(def_ranking_mean_list), digits=decimals_size_a12)
		onlyepa_ranking_mean <- round(mean(onlyepa_ranking_mean_list), digits=decimals_size_a12)
		onlyepax_ranking_mean <- round(mean(onlyepax_ranking_mean_list), digits=decimals_size_a12)
		only_epaxp_ranking_mean <- round(mean(only_epaxp_ranking_mean_list), digits=decimals_size_a12)
		evoepa_ranking_mean <- round(mean(evoepa_ranking_mean_list), digits=decimals_size_a12)
		evoepax_ranking_mean <- round(mean(evoepax_ranking_mean_list), digits=decimals_size_a12)
		evoepaxp_ranking_mean <- round(mean(evoepaxp_ranking_mean_list), digits=decimals_size_a12)
		
		ranking_values <- c(def_ranking_mean, onlyepa_ranking_mean, onlyepax_ranking_mean, only_epaxp_ranking_mean, evoepa_ranking_mean, evoepax_ranking_mean, evoepaxp_ranking_mean)
		sorted_ranking_values <- sort(ranking_values, method = "quick")
		
		def_ranking_mean_text <- paste("(", match(def_ranking_mean,sorted_ranking_values), ")", sep="")
		onlyepa_ranking_mean_text <- paste("(", match(onlyepa_ranking_mean,sorted_ranking_values), ")", sep="")
		onlyepax_ranking_mean_text <- paste("(", match(onlyepax_ranking_mean,sorted_ranking_values), ")", sep="")
		only_epaxp_ranking_mean_text <- paste("(", match(only_epaxp_ranking_mean,sorted_ranking_values), ")", sep="")
		evoepa_ranking_mean_text <- paste("(", match(evoepa_ranking_mean,sorted_ranking_values), ")", sep="")
		evoepax_ranking_mean_text <- paste("(", match(evoepax_ranking_mean,sorted_ranking_values), ")", sep="")
		evoepaxp_ranking_mean_text <- paste("(", match(evoepaxp_ranking_mean,sorted_ranking_values), ")", sep="")
				
		cat("Average Rank, ")
		cat(def_ranking_mean, def_ranking_mean_text, ",", onlyepa_ranking_mean, onlyepa_ranking_mean_text, ",",
		onlyepax_ranking_mean, onlyepax_ranking_mean_text, ",", only_epaxp_ranking_mean, only_epaxp_ranking_mean_text, ",",
		evoepa_ranking_mean, evoepa_ranking_mean_text, ",", evoepax_ranking_mean, evoepax_ranking_mean_text, ",",
		evoepaxp_ranking_mean, evoepaxp_ranking_mean_text, sep=" ")
}

sink("table.csv")
printHeader()
RQ1()
sink()
