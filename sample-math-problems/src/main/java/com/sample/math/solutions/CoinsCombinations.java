package com.sample.math.solutions;


/*----------------------------------------------------------------------------------------------------------------------
 Given some dollar value in cents (e.g. 200 = 2 dollars, 1000 = 10 dollars), find all the combinations of coins that
 make up the dollar value. There are only penny, nickel, dime, and quarter. (quarter = 25 cents, dime = 10 cents,
 nickel = 5 cents, penny = 1 cent)

 For example, if 100 was given, the answer should be... 
 4 quarter(s) 0 dime(s) 0 nickel(s) 0 pennies 
 3 quarter(s) 1 dime(s) 0 nickel(s) 15 pennies etc.
 ----------------------------------------------------------------------------------------------------------------------*/
public class CoinsCombinations {
	public static void main(String[] args) {
		// int dollarAmount = Integer.valueOf(args[0]);
		new CoinsCombinations().printCombinations(120);
	}

	public void printCombinations(int totalCents) {

		// int dollars = totalCents / 100;
		int quarters = totalCents / 25;
		int dimes = totalCents / 10;
		int nickels = totalCents / 5;
		int pennies = totalCents;

		// for (int dol = 0; dol <= dollars; dol++) {
		for (int qu = 0; qu <= quarters; qu++) {
			for (int di = 0; di <= dimes; di++) {
				for (int ni = 0; ni <= nickels; ni++) {
					for (int pe = 0; pe <= pennies; pe++) {
						if (((qu * 25) + (di * 10) + (ni * 5) + pe) == totalCents) {
							System.out.println(qu + " quarter(s) " + di + " dime(s) " + ni + " nickel(s) " + pe + " pennies");
						}
						// if (((dol * 100) + (qu * 25) + (di * 10) + (ni * 5) + pe) == totalCents) {
						// System.out.println(dol + " dollar(s) " + qu + " quarter(s) " + di + " dime(s) " + ni +
						// " nickel(s) " + pe + " pennies");
						// }
					}
				}
			}
			// }
		}
	}

}
