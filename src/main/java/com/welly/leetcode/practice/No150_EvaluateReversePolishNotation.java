package com.welly.leetcode.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * @author yangchuan02
 * @date 2019年5月17日
 */
public class No150_EvaluateReversePolishNotation {

	Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();

	{
		map.put("+", (s1, s2) -> s1 + s2);
		map.put("-", (s1, s2) -> s1 - s2);
		map.put("*", (s1, s2) -> s1 * s2);
		map.put("/", (s1, s2) -> s1 / s2);
	}

	Set<String> operators = map.keySet();

	public int evalRPN(String[] tokens) {

		Stack<Integer> stack = new Stack<>();
		for (String s : tokens) {
			if (operators.contains(s)) {
				int second = stack.pop();
				int first = stack.pop();
				stack.push(map.get(s).apply(first, second));
			} else {
				stack.push(Integer.parseInt(s));
			}
		}

		return stack.pop();
	}

	public static void main(String[] args) {
		No150_EvaluateReversePolishNotation e = new No150_EvaluateReversePolishNotation();

		String[][] arr = { { "2", "1", "+", "3", "*" }, { "4", "13", "5", "/", "+" },
				{ "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" } };
		for (String[] a : arr) {
			System.out.println(e.evalRPN(a));
		}
	}

}
