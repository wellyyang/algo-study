package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月23日
 */
public class No064_MinimumPathSum {

	public static void main(String[] args) {
		int[][] grid = {
				{ 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 }
		};
		System.out.println(new No064_MinimumPathSum().minPathSum(grid));
	}

	public int minPathSum(int[][] grid) {
		int l1 = grid.length;
		int l2 = grid[0].length;

		int[][] dp = new int[l1][l2];

		for (int i = 0; i < l1; i++) {
			dp[i][0] = grid[i][0] + (i > 0 ? dp[i - 1][0] : 0);
		}

		for (int j = 0; j < l2; j++) {
			dp[0][j] = grid[0][j] + (j > 0 ? dp[0][j - 1] : 0);
		}

		for (int m = 1; m < l1; m++) {
			for (int n = 1; n < l2; n++) {
				dp[m][n] = Math.min(dp[m - 1][n], dp[m][n - 1]) + grid[m][n];
			}
		}

		return dp[l1 - 1][l2 - 1];
	}

}
