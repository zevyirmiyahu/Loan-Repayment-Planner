package com.zevyirmiyahu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * This Class only puts together data (i.e. builds the table) 
 * from the date that was calculated and stored by Calculation class.
 */

public class BuildTable {

	// store all values
	protected static ArrayList<String> dateOfPayoutVals = new ArrayList<String>();
	protected static ArrayList<Double> annuityVals = new ArrayList<Double>();
	protected static ArrayList<Double> principalVals = new ArrayList<Double>();
	protected static ArrayList<Double> interestVals = new ArrayList<Double>();
	protected static ArrayList<Double> initOutPrincipalVals = new ArrayList<Double>();
	protected static ArrayList<Double> remainOutPrincipalVals = new ArrayList<Double>();
		
	private PrintWriter pw;
	private StringBuilder sb = new StringBuilder();
	
	public BuildTable() {}
	
	public BuildTable(int duration) {
		makeCSVFile(duration);
		PrintTableToConsole(duration);
	}
	
	private void makeCSVFile(int duration) {
		
		try {
			pw = new PrintWriter(new File("repayment_plan.csv"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// build stringBuilder to be written to CSV file
		sb.append("Date");
		sb.append(',');
		sb.append("Annuity");
		sb.append(',');
		sb.append("Principal");
		sb.append(',');
		sb.append("Interest");
		sb.append(',');
		sb.append("Initial Outstanding Principal");
		sb.append(',');
		sb.append("Remaining Outstanding Principal");
		sb.append('\n');
		
		for(int i = 0; i < duration * 12; i++) {
			sb.append(dateOfPayoutVals.get(i));
			sb.append(',');
			sb.append(annuityVals.get(i));
			sb.append(',');
			sb.append(principalVals.get(i));
			sb.append(',');
			sb.append(principalVals.get(i));
			sb.append(',');
			sb.append(interestVals.get(i));
			sb.append(',');
			sb.append(initOutPrincipalVals.get(i));
			sb.append(',');
			sb.append(remainOutPrincipalVals.get(i));
			sb.append('\n');
		}
		pw.write(sb.toString()); // writes file to repayment-loan-planner project folder
		pw.close();
	}
	
	private void PrintTableToConsole(int duration) {
		
		String categories = "date, Annuity, Principal, Interest, "
				+ "Initial Outstanding Principal, "
				+"Remaining Outstanding Principal";
		
		System.out.println(categories);
		// prints to console
		for(int i = 0; i < duration * 12; i++) {
			String s = 
					dateOfPayoutVals.get(i) + ", " + annuityVals.get(i) + ", " 
							+ principalVals.get(i) + ", " + interestVals.get(i) + ", " 
							+ initOutPrincipalVals.get(i) + ", " + remainOutPrincipalVals.get(i);
			System.out.println(s);
		}
	}
}
