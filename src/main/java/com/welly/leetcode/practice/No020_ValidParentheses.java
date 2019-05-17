package com.welly.leetcode.practice;

import java.util.Stack;

/**
 * @author yangchuan02
 * @date 2019年5月17日
 */
public class No020_ValidParentheses {

	public boolean isValid(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}

		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (stack.isEmpty()) {
				stack.push(c);
			} else if (isPair(stack.lastElement(), c)) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}

		return stack.isEmpty();
	}

	private boolean isPair(char a, char b) {
		return (a == '(' && b == ')')
				|| (a == '[' && b == ']')
				|| (a == '{' && b == '}');
	}

	public static void main(String[] args) {
		No020_ValidParentheses e = new No020_ValidParentheses();
		String[] arr = { "()", "()[]{}", "(]", "([)]", "{[]}" };
		for (String s : arr) {
			System.out.println(e.isValid(s));
		}
	}

}
