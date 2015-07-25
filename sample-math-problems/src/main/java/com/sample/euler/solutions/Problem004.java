package com.sample.euler.solutions;

import com.sample.Library;

/*------------------------------------------------------------------------------------------------------------------------------------
 ==========
 Problem004
 ==========

 Largest palindrome product

 A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

 Find the largest palindrome made from the product of two 3-digit numbers.
 ------------------------------------------------------------------------------------------------------------------------------------*/
public final class Problem004 {

	public static void main(String[] args) {
		System.out.println(new Problem004().run());
	}

	public String run() {
		int maxPalin = -1;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int prod = i * j;
				if (Library.isPalindrome(prod) && prod > maxPalin)
					maxPalin = prod;
			}
		}
		return Integer.toString(maxPalin);
	}
}