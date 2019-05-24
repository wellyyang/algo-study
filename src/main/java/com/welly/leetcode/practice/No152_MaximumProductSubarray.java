package com.welly.leetcode.practice;

import java.util.Arrays;

/**
 * @author yangchuan02
 * @date 2019年5月24日
 */
public class No152_MaximumProductSubarray {

	public static void main(String[] args) {
		int[] nums = { 2, 3, -2, 4 };
		System.out.println(new No152_MaximumProductSubarray().maxProduct(nums));
	}

	public int maxProduct(int[] nums) {
		// TODO
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// 存放从每个num开始的最大非负数和最小负数
		int[][] dp = new int[nums.length][2];

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (num >= 0) {
				dp[i][0] = num;
			} else {
				dp[i][1] = num;
			}

			for (int j = 0; j < i; j++) {
				int[] arr = dp[j];
				int p1 = arr[0] * num;
				int p2 = arr[1] * num;
				arr[0] = Math.max(arr[0], Math.max(p1, p2));
				arr[1] = Math.min(arr[1], Math.min(p1, p2));
			}
		}

		int result = Arrays.stream(dp)
				.flatMapToInt(Arrays::stream)
				.max()
				.getAsInt();
		return result;
	}

}
