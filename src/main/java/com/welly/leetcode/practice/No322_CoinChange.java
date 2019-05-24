package com.welly.leetcode.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangchuan02
 * @date 2019年5月23日
 */
public class No322_CoinChange {

	public static final int i = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int[] coins = { 1, i };
		int amount = 2;
		System.out.println(new No322_CoinChange().coinChange(coins, amount));
		System.out.println(new No322_CoinChange().coinChangeWithDp(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}

		Map<Integer, Integer> memo = new HashMap<>();
		int result = coinChange0(coins, amount, memo);
		return result <= 0 ? -1 : result;
	}

	private int coinChange0(int[] coins, int amount, Map<Integer, Integer> memo) {
		if (amount < 0) {
			return -1;
		}

		if (amount == 0) {
			return 0;
		}

		if (memo.containsKey(amount)) {
			return memo.get(amount);
		}

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int temp = coinChange0(coins, amount - coin, memo);
			if (temp >= 0 && temp < min) {
				min = temp + 1;
			}
		}
		min = min == Integer.MAX_VALUE ? -1 : min;
		memo.put(amount, min);
		return min;
	}

	public int coinChangeWithDp(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

}
