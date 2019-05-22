package com.welly.leetcode.practice;

import java.util.HashMap;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No070_ClimbingStairs {

	public static void main(String[] args) {
		No070_ClimbingStairs e = new No070_ClimbingStairs();
		System.out.println(e.climbStairs(44));
	}

	public int climbStairs(int n) {
		return climbStairs0(n, new HashMap<>());
	}

	private int climbStairs0(int n, HashMap<Integer, Integer> memo) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (memo.containsKey(n)) {
			return memo.get(n);
		}

		int result = climbStairs0(n - 1, memo) + climbStairs0(n - 2, memo);
		memo.put(n, result);
		return result;
	}

}
