package com.zevyirmiyahu;

import com.zevyirmiyahu.BuildTable;
import com.zevyirmiyahu.Calculation;

public class App {
	
	/* DESCRIPTION: This program is a plan generator for purposes of 
	 * pre-calculating repayment plans for borrowers throughout 
	 * the lifetime of an annuity loan. It is assumed the user provides 
	 * the following input parameters:
	 * 				duration(number of installments in months)
	 * 				nominal interest rate (as a percentage NOT a decimal)
	 * 				total loan amount("total principle amount")
	 * 				date of disbursement/payout
	 * 
	 * It is further assumed for simplicity:
	 * 				each month has 30 days
	 *  				each year has 360 days
	 * 
	 * 
	 * NOTES OF ANNUITY PAYMENT FORMULA:
	 * 				duration is n (and varies based on the number of months left of repayment)
	 * 				nominalInterestRate is r
	 * 				totalLoanAmount is PV
	 * 
	 * 
	 * Author: Zev Yirmiyahu
	 * GitHub: https://github.com/zevyirmiyahu
	 * Personal Site: www.zevyirmiyahu.com
	 * E-mail: zy@zevyirmiyahu.com
	 *  
	 */
	
	private static int duration = 2; 			// number of installments (user gives in year formula uses months)
	private static double nominalInterestRate = 5.0; 	// user gives as percentage 
	private static double totalLoanAmount = 5000.0; 	// total principle amount
	private static String dateOfPayout = "01.01.2018"; 	// as string
	
	public static void main(String args[]) {
		
		// NOTE: date format is assumed to be dd.mm.yyyy, and duration is assumed to be given in years
		new Calculation(duration, nominalInterestRate, totalLoanAmount, dateOfPayout); // calculates all values and stores data
		new BuildTable(duration); // creates CSV file and prints table to console 
	}
}

