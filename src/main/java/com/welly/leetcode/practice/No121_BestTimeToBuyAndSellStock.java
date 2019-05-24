package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月24日
 */
public class No121_BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		System.out.println(new No121_BestTimeToBuyAndSellStock().maxProfit(prices));
	}

	public int maxProfit(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;

		for (int price : prices) {
			minPrice = Math.min(price, minPrice);
			maxProfit = Math.max(maxProfit, price - minPrice);
		}
		return maxProfit;
	}

}
