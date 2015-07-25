package com.sample.math.solutions;

/*---------------------------------------------------------------------------
 a non-empty zero-indexed array a consisting of n integers is given. a slice
 of that array is a pair of integers (p, q) such that 0 <= p <= q < n.
 integer p is called the beginning of the slice; integer q is called the end
 of the slice. the number q - p + 1 is called the size of the slice. a slice
 (p, q) of array a is called ascending if a[p] < a[p+1] < ... a[q-1] < a[q].
 write a function:

 class ascendingslice { public int ascendingslice(int[] a); }

 that, given a zero-indexed array a consisting of n integers, returns the 
 beginning of any ascending slice of a of maximal size.
 ------------------------------------------------------------------------------*/
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