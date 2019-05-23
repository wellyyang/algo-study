package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No151_ReverseWordsInString {

	public static void main(String[] args) {
		String s = " ";
		System.out.println(new No151_ReverseWordsInString().reverseWords(s));
	}

	public String reverseWords(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		String[] arr = s.trim().split(" +");
		StringBuilder result = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			result.append(arr[i]).append(" ");
		}
		if (result.length() != 0) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

}
