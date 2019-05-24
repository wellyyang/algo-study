package com.welly.leetcode.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangchuan02
 * @date 2019年5月23日
 */
public class No036_ValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		Set<String> found = new HashSet<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char c = board[i][j];
				if (c == '.') {
					continue;
				}
				if (!found.add("row-" + i + "-num-" + c)
						|| !found.add("col-" + j + "-num-" + c)
						|| !found.add("block-" + i / 3 + "-" + j / 3 + "-" + c)) {
					return false;
				}
			}
		}
		return true;
	}

}
