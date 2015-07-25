package com.sample.euler.solutions;

import com.sample.Library;

/*--------------------------------------------------------------------------
 ==========
 Problem003
 ==========

 Largest prime factor

 The prime factors of 13195 are 5, 7, 13 and 29.

 What is the largest prime factor of the number 600851475143 ?
 ---------------------------------------------------------------------------*/
public final class Problem003 {

	public static void main(String[] args) {
		System.out.println(new Problem003().run());
	}

	/*
	 * Algorithm: Divide out all the smallest prime factors except the last one. For example, 1596 = 2 * 2 * 3 * 7 * 19.
	 * The algorithm ensures that the smallest factors will be found first. After dividing out the smallest factors, the
	 * last factor to be found will be equal to the current quotient, so it must be the largest prime factor.
	 */
	public String run() {
		long n = 600851475143L;
		while (true) {
			long p = smallestFactor(n);
			if (p < n)
				n /= p;
			else
				return Long.toString(n);
		}
	}

	// Returns the smallest factor of n, which is in the range [2, n]. The result is always prime.
	private static long smallestFactor(long n) {
		if (n <= 1)
			throw new IllegalArgumentException();
		for (long i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return i;
		}
		return n; // n itself is prime
	}

}