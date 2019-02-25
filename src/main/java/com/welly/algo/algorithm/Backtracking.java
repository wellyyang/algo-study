package com.welly.algo.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * <pre>
 * 回溯算法, 本质可以认为是暴力枚举, 通常需要配合剪枝以减少耗时.
 *
 * 应用场景: 深度优先搜索, 正则表达式匹配, 语法分析...
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月22日
 */
public class Backtracking {

	// board中1代表有棋子, 八皇后目标是让8个棋子在棋盘上, 两两都不处于同一行(横/竖/斜)
	// board[0][5] 代表第一行第五列有棋子, 约定第n行有棋子, 则第m(m < n)行必有棋子
	// boardSize 代表 n 皇后
	public void cal8queens(int boardSize) {
		int[] board = new int[boardSize];
		AtomicInteger counter = new AtomicInteger();
		cal8queens(board, 0, counter);
		System.out.println("摆放方式总共有" + counter.get() + "种");
	}

	private void cal8queens(int[] board, int row, AtomicInteger counter) {
		if (row == board.length) {
			print(board);
			counter.incrementAndGet();
			return;
		}

		for (int col = 0; col < board.length; col++) {
			if (isOK(board, row, col)) {
				// 如果满足条件, 继续递归
				// 如果不满足条件, 剪枝
				board[row] = col;
				cal8queens(board, row + 1, counter);
			}
		}
	}

	private boolean isOK(int[] board, int row, int col) {
		for (int r = row - 1; r >= 0; r--) {
			int c = board[r];
			if (c == col) {
				// 在同一列
				return false;
			}
			if (Math.abs(row - r) == Math.abs(col - c)) {
				return false;
			}
		}
		return true;
	}

	private void print(int[] arr) {
		IntStream.range(0, arr.length)
				.forEach(row -> {
					int col = arr[row];
					IntStream.range(0, arr.length)
							.forEach(i -> {
								if (i == col) {
									System.out.print("Q ");
								} else {
									System.out.print("* ");
								}
							});
					System.out.println();
				});
		System.out.println("----------------------------");
	}

	public static void main(String[] args) {
		Backtracking b = new Backtracking();
		b.cal8queens(8);
	}

}
