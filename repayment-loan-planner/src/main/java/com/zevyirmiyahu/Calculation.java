package com.zevyirmiyahu;

import com.zevyirmiyahu.BuildTable;

/*
 * Class handles ALL calculations for planner.
 * Includes:
 * 		Annuity method: Calculates annuity
 * 		Interest method: Calculates interest
 * 		Principle method: calculates principle
 * 		Iterator: call 3 methods above and iterates through all months with given duration 
 * 				  and stores values BuildTable's ArrayList.
 * 
 */

public class Calculation {	
	
	public Calculation(int duration, double nominalInterestRate, double totalLoanAmount, String dateOfPayout) {
		Iterator(duration, nominalInterestRate, totalLoanAmount, dateOfPayout);
	}
	
	
	// Annuity payment formula
	private double Annuity(int index, double duration, double nominalInterestRate, double initOutPrincipal) {
		
		// note: the n in the PV formula is NOT constant, duration changes accordingly to index
		double divisor = (1 - Math.pow((1.0 + (nominalInterestRate / 1200.0)), -duration * 12.0 + index))
								/ (nominalInterestRate / 1200.0);
		
		double annuityPayment = initOutPrincipal / divisor; 
		return annuityPayment;
	}
	
	
	private double Interest(double nominalInterestRate, double initOutPrincipal) {
		double interest = (1.0 / 1200.0) * initOutPrincipal * nominalInterestRate;
		return interest;
	}
	
	
	private double Principal(double annuityPayment, double interest, double initOutPrincipal) {
		if(interest > initOutPrincipal) return initOutPrincipal;
		else return annuityPayment - interest;
	}
	
	
	private String DateIncrementor(String dateOfPayout) {
		
		// example string of dateOfPayout = "01.01.2018
		String day = dateOfPayout.substring(0, 3); // format dd.
		int month = Integer.parseInt(dateOfPayout.substring(3, 5));
		int year = Integer.parseInt(dateOfPayout.substring(6, 10));
		
		if(month + 1 < 10) { // single digit 
			String date = day + "0" + (month + 1) + "." + year;
			return date;
		}
		else if(month == 12) { // alter year
			String date = day + "01." + (year + 1);
			return date;
		}
		else {
			String date = day + (month + 1) + "." + year;
			return date;
		}
	}
	
	
	// method iterates over all months for the given duration. 
	protected void Iterator(int duration, double nominalInterestRate, double totalLoanAmount, String dateOfPayout) {
		BuildTable bt = new BuildTable();
		
		// initialize values
		String date = dateOfPayout;
		double principal = 0; 
		double initOutPrincipal = totalLoanAmount;
		double remainOutPrincipal = 0;
		double interest = nominalInterestRate;
		double annuityPayment = 0;
		
		// store initial values
		for(int i = 0; i < duration * 12; i++) { // Note: user gives duration in years, must multiply by 12
			
			// calculate current values and store data directly after calculation
			annuityPayment = Annuity(i, duration, nominalInterestRate, initOutPrincipal);
			BuildTable.annuityVals.add(annuityPayment);

			interest = Interest(nominalInterestRate, initOutPrincipal);
			BuildTable.interestVals.add(interest);

			principal = Principal(annuityPayment, interest, initOutPrincipal);
			BuildTable.principalVals.add(principal);
			
			BuildTable.dateOfPayoutVals.add(date); 
			date = DateIncrementor(date);
			
			
			// update initial and remaining outstanding principals
			remainOutPrincipal = initOutPrincipal - principal;
			
			if(remainOutPrincipal < 0) {
				remainOutPrincipal = 0;
				
				// avoids skipping storage of initOutPrincipal & remainOutPrincipal
				// when remainOutPrincipal is < 0
				BuildTable.remainOutPrincipalVals.add(remainOutPrincipal);
				BuildTable.initOutPrincipalVals.add(initOutPrincipal); 
				initOutPrincipal = remainOutPrincipal;
				
				break; // complete, payments all made
			}
			BuildTable.remainOutPrincipalVals.add(remainOutPrincipal);
			BuildTable.initOutPrincipalVals.add(initOutPrincipal); // Must store initial value first
			initOutPrincipal = remainOutPrincipal;
		}
	}
}
