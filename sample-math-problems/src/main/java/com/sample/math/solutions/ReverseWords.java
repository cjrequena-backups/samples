package com.sample.math.solutions;

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------
 You are given a non-empty string S consisting of N characters. In this problem we consider only strings that consist of lower-case English letters (a−z) and spaces.
 S can be divided into words by splitting it at the spaces and removing them. We want to reverse every word in S.
 For example, given S = "we test coders", there are three words: "we", "test" and "coders". Reversing the words gives "ew", "tset" and "sredoc".
 The goal is to return a string with every word in string S reversed and separated by spaces, so the result in the above example should be "ew tset sredoc". 
 You can assume that if there are K spaces in S then there are exactly K + 1 words.
 Write a function:
 --------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

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