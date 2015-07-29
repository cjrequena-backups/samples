package com.sample.math.solutions;

/**
 * A non-empty zero-indexed string S consisting of Q characters is given. The period of this string is the smallest
 * positive integer P such that:
 * 
 * P ≤ Q / 2 and S[K] = S[K+P] for 0 ≤ K < Q− P.
 * 
 * For example, 8 is the period of “codilitycodilityco”. A positive integer M is the binary period of a positive integer
 * N if M is the period of the binary representation of N.
 * 
 * For example, 4 is the binary period of 955, because the binary representation of 955 is “1110111011” and its period
 * is 4. On the other hand, 102 does not have a binary period, because its binary representation is “1100110” and it
 * does not have a period.
 * 
 * Write a function: int solution(int N);
 * 
 * that, given a positive integer N, returns the binary period of N. The function should return −1 if N does not have a
 * binary period.
 * 
 * For example, given N = 955 the function should return 4, and given N = 102 the function should return −1, as
 * explained in the example above.
 * 
 * Assume that:
 * 
 * N is an integer within the range [1..1,000,000,000].
 * 
 * Complexity:
 * 
 * expected worst-case time complexity is O(log(N)^2);
 * 
 * expected worst-case space complexity is O(log(N)).
 */
class PeriodOfBinaryString {

	public static void main(String[] args) {
		int N = 955;
		System.out.println(new PeriodOfBinaryString().solution(N));
	}

	public int solution(int N) {

		String binary = Integer.toBinaryString(N);

		int min = Integer.MAX_VALUE;
		int Q = binary.length();

		System.out.println("binario: " + binary);

		for (int P = 1; P < Q / 2; P++) {
			// System.out.print("P: " + P);
			int delta = Q - P;
			int fail = 0;
			for (int K = 0; K < delta; K++) {
				// System.out.print(" " + binary.charAt(K));
				if (binary.charAt(K) != binary.charAt(K + P)) {
					K = delta;
					fail = 1;
				}
			}
			// System.out.println("");
			if (fail != 1) {
				return P;
			}
		}

		if (min == Integer.MAX_VALUE) {
			return -1;
		}
		return min;

	}

}