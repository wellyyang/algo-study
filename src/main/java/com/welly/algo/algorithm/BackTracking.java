package com.welly.algo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * <pre>
 * 回溯算法, 本质可以认为是暴力枚举, 通常需要配合剪枝以减少耗时.
 *
 * 应用场景: 深度优先搜索, 正则表达式匹配, 语法分析...
 *
 * 回溯的三要素:
 * 1. 选择, 有限的选择
 * 2. 条件, 选择是有条件约束
 * 3. 结束, 达到特定条件会结束
 *
 * 回溯通常用递归来实现, 递归方法的参数设计原则:
 * 1. 临时变量存放不完整的解
 * 2. 结束条件的判断参数
 * 3. 存放完整解的变量
 *
 * 注意: 在递归方法返回后, 要把状态恢复为递归前, 才是回溯
 *
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月22日
 * @see https://blog.csdn.net/sinat_27908213/article/details/80599460
 */
public class BackTracking {

	public static class Queens {

		public void resolve(int boardSize, Consumer<int[]> c) {
			resolve0(new int[boardSize], 0, c);
		}

		// board中1代表有棋子, 八皇后目标是让8个棋子在棋盘上, 两两都不处于同一行(横/竖/斜)
		// board[0][5] 代表第一行第五列有棋子, 约定第n行有棋子, 则第m(m < n)行必有棋子
		// boardSize 代表 n 皇后
		private void resolve0(int[] board, int row, Consumer<int[]> c) {
			if (row == board.length) {
				c.accept(board);
				return;
			}

			for (int i = 0; i < board.length; i++) {
				if (isOK(board, row, i)) {
					board[row] = i;
					resolve0(board, row + 1, c);
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
					// 在同一斜线
					return false;
				}
			}
			return true;
		}

	}

	/**
	 * 0-1背包问题, 有n个物品, 放入总承载量k的背包中, 如何让放入的物品总重量最大. 物品不可分割, 要么放入, 要么不放入, 所以称为0-1
	 *
	 * @author yangchuan02
	 * @date 2019年4月25日
	 */
	public static class Bag {

		public int resolve(int[] items, int maxWeight) {
			AtomicInteger result = new AtomicInteger();
			resolve0(0, items, 0, maxWeight, result);
			return result.get();
		}

		private void resolve0(int curWeight, int[] items, int curIndex, int maxWeight, AtomicInteger result) {
			// 结束条件
			if (items.length == curIndex || curWeight == maxWeight) {
				if (curWeight > result.get()) {
					result.set(curWeight);
				}
				return;
			}

			// 放入或者不放入当前物品
			if (curWeight + items[curIndex] <= maxWeight) {
				resolve0(curWeight + items[curIndex], items, curIndex + 1, maxWeight, result);
			}

			// 继续递归, 放入下一个物品
			resolve0(curWeight, items, curIndex + 1, maxWeight, result);
		}

	}

	/**
	 * n个物品, 每个物品有重量和价值, 放入不超过m承重的背包中, 求放入的最大价值
	 * 
	 * @author yangchuan02
	 * @date 2019年4月28日
	 */
	public static class Bag2 {

		public int resolve(int[] items, int[] values, int maxWeight) {
			AtomicInteger result = new AtomicInteger();
			resolve0(0, 0, items, values, 0, maxWeight, result);
			return result.get();
		}

		private void resolve0(int curWeight, int curValue, int[] items, int[] values,
				int curIndex, int maxWeight, AtomicInteger result) {
			if (items.length == curIndex || curWeight == maxWeight) {
				if (curValue > result.get()) {
					result.set(curValue);
				}
				return;
			}

			if (curWeight + items[curIndex] <= maxWeight) {
				resolve0(curWeight + items[curIndex], curValue + values[curIndex],
						items, values, curIndex + 1, maxWeight, result);
			}

			resolve0(curWeight, curValue, items, values, curIndex + 1, maxWeight, result);
		}

	}

	/**
	 * 给定n对括号, 输出有几种合法的组合
	 *
	 * @author yangchuan02
	 * @date 2019年4月25日
	 */
	public static class Parentheses {

		public List<String> resolve(int pairs) {
			List<String> result = new ArrayList<>();
			resolve0("", pairs, pairs, result);
			return result;
		}

		private void resolve0(String str, int left, int right, List<String> result) {
			// 结束条件
			if (left == 0 && right == 0) {
				result.add(str);
				return;
			}

			// 选择, 可以有多个, 顺序仅影响输出结果的顺序, 不影响结果的数量
			// 注意: 多个选择不能使用else, 如果使用else则无法达到回溯的效果
			if (right > left) {
				resolve0(str + ")", left, right - 1, result);
			}

			if (left > 0) {
				resolve0(str + "(", left - 1, right, result);
			}
		}

	}

	public static void main(String[] args) {
		int pairs = 3;
		List<String> l1 = new Parentheses().resolve(pairs);
		System.out.println(pairs + "对括号匹配: " + l1.size() + "种");
		System.out.println(l1);

		int[] items = new int[] { 2, 2, 4, 6, 3 };
		int maxWeight = 9;
		int max = new Bag().resolve(items, maxWeight);
		System.out.println("从" + Arrays.toString(items) + "中选择物品放入总容量" + maxWeight + "的背包中, 最多放入" + max + "容量的物品");

		int[] values = new int[] { 3, 4, 8, 9, 6 };
		int max2 = new Bag2().resolve(items, values, maxWeight);
		System.out.println("从重量为" + Arrays.toString(items) + "/价值为" + Arrays.toString(values)
				+ "中选择物品放入总容量" + maxWeight + "的背包中, 最多放入" + max2 + "价值的物品");

		AtomicInteger counter = new AtomicInteger();
		int n = 8;
		new Queens().resolve(n, arr -> counter.incrementAndGet());
		System.out.println(n + "个皇后有" + counter.get() + "种放置方法");
	}

}
