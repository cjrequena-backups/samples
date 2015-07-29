package com.sample.math.solutions;

import java.util.Arrays;

/**
 * OJO - REVISAR PARA CASOS NUMERO NEGATIVOS.
 * 
 * 
 * 
 * Write a method called  that returns the most frequently occurring element of an array of integers. Assume that
 * the array has at least one element and that every element in the array has a value between 0 and 100 inclusive. Break
 * ties by choosing the lower value.
 * 
 * For example, if the array passed contains the values {27, 15, 15, 11, 27}, your method should return 15. (Hint: You
 * may wish to look at the Tally program from earlier in this chapter to get an idea of how to solve this problem.)
 *
 */
class CountElementsIntoArrayInt {

	public static void main(String[] args) {
		int[] A = { 20, 10, 30, 30, 30, 40, 10 };
		System.out.println(new CountElementsIntoArrayInt().solution(A));
	}

	public int solution(int[] A) {

		int max = 0;
		int maxFreq = 0;

		Arrays.sort(A);
		max = A[A.length - 1];

		int[] count = new int[max + 1];

		for (int i = 0; i < A.length; i++) {
			count[A[i]]++;
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i] > maxFreq) {
				maxFreq = count[i];
			}
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i] == maxFreq) {
				return i;
			}
		}
		return -1;
	}

}