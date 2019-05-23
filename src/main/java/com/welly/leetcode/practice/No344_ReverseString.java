package com.welly.leetcode.practice;

import java.util.Arrays;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No344_ReverseString {

	public static void main(String[] args) {
		char[] s = "1".toCharArray();
		new No344_ReverseString().reverseString(s);
		System.out.println(Arrays.toString(s));
	}

	public void reverseString(char[] s) {
		for (int i = 0; i < s.length / 2; i++) {
			swap(s, i, s.length - i - 1);
		}
	}

	private void swap(char[] s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

}
