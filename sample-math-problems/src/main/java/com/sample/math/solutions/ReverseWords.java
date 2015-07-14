package com.sample.math.solutions;

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------
// You are given a non-empty string S consisting of N characters. In this problem we consider only strings that consist of lower-case English letters (a−z) and spaces.
// S can be divided into words by splitting it at the spaces and removing them. We want to reverse every word in S.
// For example, given S = "we test coders", there are three words: "we", "test" and "coders". Reversing the words gives "ew", "tset" and "sredoc".
// The goal is to return a string with every word in string S reversed and separated by spaces, so the result in the above example should be "ew tset sredoc". 
// You can assume that if there are K spaces in S then there are exactly K + 1 words.
// Write a function:
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------


public class ReverseWords {

	public static void main(String[] args) {
		String sentence = "hello world cruel";
		System.out.println(new ReverseWords().backWardsWord(sentence));
	}

	public String reverseWords(String sentence) {
		StringBuilder sb = new StringBuilder(sentence.length() + 1);
		String[] words = sentence.split(" ");
		for (int i = words.length - 1; i >= 0; i--) {
			sb.append(words[i]).append(' ');
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public String backWardsWord(String sentence) {
		String backwardsWord = "";
		for (int i = sentence.length() - 1; i >= 0; i--) {
			backwardsWord += (sentence.charAt(i) + "");
		}
		return backwardsWord;
	}

}