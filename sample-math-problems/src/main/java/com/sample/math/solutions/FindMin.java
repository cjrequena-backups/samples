package com.sample.math.solutions;

import java.util.Arrays;

/**
 * -- SOLVED --
 * 
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty zero-indexed array A of N integers, returns the
 * 
 * minimum value from array A:
 * 
 * min{ A[i] : 0 ≤ i < N }
 * 
 * For example, given:
 * 
 * A[0] = 1 A[1] = 2 A[2] = 3
 * 
 * A[3] = 42 A[4] = 1 A[5] = ­10
 * 
 * your function should return −10.
 * 
 * Assume that:
 * 
 * • N is an integer within the range [1..100,000];
 * 
 * • each element of array A is an integer within the range
 * 
 * [−2,147,483,648..2,147,483,647].
 * 
 * Complexity:
 * 
 * • expected worst-case time complexity is O(N);
 * 
 * • expected worst-case space complexity is O(1), beyond input storage
 * 
 * (not counting the storage required for input arguments).
 * 
 * Elements of input arrays can be modified.
 * 
 * Copyright 2009–2015 by Codility Limited. All Rights Reserved. Unauthorized copying,
 * 
 * publication or disclosure prohibited.
 */
class FindMin {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 42, 1, -2147483, -19 };
		System.out.println(new FindMin().solution(A));
	}

	public int solution(int[] A) {
		Arrays.sort(A);
		return A[0];
	}

}