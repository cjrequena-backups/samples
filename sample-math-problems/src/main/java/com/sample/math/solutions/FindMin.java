package com.sample.math.solutions;

import java.util.Arrays;

// Write a function:
// class Solution { public int solution(int[] A); } 
// that, given a non-empty zero-indexed array A of N integers, returns the minimum value from array A:
// min{ A[i] : 0 ≤ i < N }
// For example, given:
// A[0] = 1     A[1] = 2    A[2] = 3
// A[3] = 42    A[4] = 1    A[5] = -10
// your function should return −10.

class FindMin {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 42, 1, -10, -19 };
		System.out.println(new FindMin().solution(A));
	}

	public int solution(int[] A) {
		Arrays.sort(A);
		return A[0];
//		 int minValue = 1;
//		 for(int value: A){
//		 if (value == minValue){
//		 minValue++;
//		 }
//		 }
//		 return minValue;
	}

}