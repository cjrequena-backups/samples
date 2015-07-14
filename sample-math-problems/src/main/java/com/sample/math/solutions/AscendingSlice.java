package com.sample.math.solutions;

//A non-empty zero-indexed array A consisting of N integers is given. A slice
//of that array is a pair of integers (P, Q) such that 0 <= P <= Q < N.
//Integer P is called the beginning of the slice; integer Q is called the end
//of the slice. The number Q - P + 1 is called the size of the slice. A slice
//(P, Q) of array A is called ascending if A[P] < A[P+1] < ... A[Q-1] < A[Q].
//Write a function:
//
//class AscendingSlice { public int ascendingSlice(int[] A); }
//
//that, given a zero-indexed array A consisting of N integers, returns the 
//beginning of any ascending slice of A of maximal size.

class AscendingSlice {

	public static void main(String[] args) {
		int[] A = { 2, 2, 2, 2, 1, 2, -1, 2, 1, 3 };
		System.out.println(new AscendingSlice().ascendingSlice(A));
	}

	public int ascendingSlice(int[] A) {

		int start = 0;
		int end = 0;
		int startTemp = 0;
		int endTemp = 0;

		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] < A[i]) {
				endTemp = i;
			} else {
				if (end - start < endTemp - startTemp) {
					start = startTemp;
					end = endTemp;
				}

				startTemp = i;
				endTemp = i;
			}
		}

		if (end - start < endTemp - startTemp) {
			start = startTemp;
		}

		System.out.println("Slice (" + start + "," + end + ")");
		System.out.println("Slice size " + (end - start + 1));
		return start; 
	}
}