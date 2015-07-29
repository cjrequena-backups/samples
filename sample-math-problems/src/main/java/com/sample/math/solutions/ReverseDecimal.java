package com.sample.math.solutions;

import com.sample.Library;


/**
 * 
 * @author cjrequena
 *
 */
class ReverseDecimal {

	public static void main(String[] args) {
		int N = 24;
		System.out.println(new ReverseDecimal().solution(N));
	}

	public int solution(int N) {
		
		String input = String.valueOf(N);
		String output = Library.reverse(input);				
		return Integer.valueOf(output);

	}

}