package com.welly.leetcode.practice;

import java.util.Stack;

/**
 * @author yangchuan02
 * @date 2019年5月17日
 */
public class No032_LongestParentheses {

	public int longestValidParentheses(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}

		int max = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(i);
			} else if (c == ')') {
				stack.pop();

				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					max = Math.max(max, i - stack.peek());
				}
			}
		}
		return max;
	}

	// advantage space O(1)
	public int longestValidParentheses2(String s) {
		int max = 0;
		// left to right traversal
		int left = 0, right = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				left++;
			} else if (c == ')') {
				right++;
			}

			if (left == right) {
				max = Math.max(max, right * 2);
			} else if (right > left) {
				left = right = 0;
			}
		}
		// right to left traversal
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == '(') {
				left++;
			} else if (c == ')') {
				right++;
			}

			if (left == right) {
				max = Math.max(max, right * 2);
			} else if (right < left) {
				left = right = 0;
			}
		}

		return max;
	}

	public static void main(String[] args) {
		No032_LongestParentheses e = new No032_LongestParentheses();
		String[] arr = { "(()(())", "()(()" };
		for (String s : arr) {
			System.out.println(e.longestValidParentheses(s) + "\t" + e.longestValidParentheses2(s));
		}
	}

}
