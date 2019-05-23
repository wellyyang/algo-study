package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月23日
 */
public class No200_NumberOfIslands {

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int n = grid.length;
		int m = grid[0].length;

		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '1') {
					mark(grid, i, j);
					count++;
				}
			}
		}

		return count;
	}

	private void mark(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
				|| grid[i][j] == '0') {
			return;
		}

		grid[i][j] = '0';
		mark(grid, i + 1, j);
		mark(grid, i - 1, j);
		mark(grid, i, j + 1);
		mark(grid, i, j - 1);
	}

}
