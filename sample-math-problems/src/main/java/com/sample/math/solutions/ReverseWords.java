package com.sample.math.solutions;

/**
 * You are given a non-empty string S consisting of N characters. In this problem
 * 
 * we consider only strings that consist of lower-case English letters (a−z) and
 * 
 * spaces.
 * 
 * S can be divided into words by splitting it at the spaces and removing them.
 * 
 * We want to reverse every word in S.
 * 
 * For example, given S = "we test coders", there are three words: "we", "test"
 * 
 * and "coders". Reversing the words gives "ew", "tset" and "sredoc".
 * 
 * The goal is to return a string with every word in string S reversed and
 * 
 * separated by spaces, so the result in the above example should be "ew tset
 * 
 * sredoc". You can assume that if there are K spaces in S then there are exactly
 * 
 * K + 1 words.
 * 
 * Write a function:
 * 
 * class Solution { public String solution(String S); }
 * 
 * that, given a non-empty string S consisting of N characters, returns the
 * 
 * reversal of every word of S.
 * 
 * For example, given S = "we test coders", the function should return "ew tset
 * 
 * sredoc", as explained above.
 * 
 * Assume that:
 * 
 * • the length of S is within the range [1..100];
 * 
 * • string S consists only of lower-case letters (a−z) and spaces.
 * 
 * In your solution, focus on correctness. The performance of your solution will
 * 
 * not be the focus of the assessment.
 * 
 * Task idea contributed by Stephen Law of Electrum.
 * 
 * Copyright 2009–2015 by Codility Limited. All Rights Reserved. Unauthorized copying,
 * 
 * publication or disclosure prohibited.
 */
public class ReverseWords {

	public static void main(String[] args) {
		String sentence = "hello world cruel";
		System.out.println(new ReverseWords().reverse(sentence));
	}

	public String reverse(String sentence) {
		String reverseWord = "";
		for (int i = sentence.length() - 1; i >= 0; i--) {
			reverseWord += (sentence.charAt(i) + "");
		}
		return reverseWord;
	}

}