package com.welly.algo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * <pre>
 * 动态规划适合用来求解最优问题
 *
 * 一个模型三个特征
 * 多阶段决策最优解模型
 * 最优子结构、无后效性和重复子问题
 *
 * 状态转移表法解题思路大致可以概括为，回溯算法实现 - 定义状态 - 画递归树 - 找重复子问题 - 画状态转移表 - 根据递推关系填表 - 将填表过程翻译成代码。
 * 状态转移方程法的大致思路可以概括为，找最优子结构 - 写状态转移方程 - 将状态转移方程翻译成代码
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年4月25日
 */
public class DynamicProgramming {

	public static class Bag {

		public int resolve(int[] items, int maxWeight) {
			int n = items.length;
			boolean[] states = new boolean[maxWeight + 1];
			// 第一行的数据要特殊处理,可以利用哨兵优化
			states[0] = true;
			// 放入items[0]
			states[items[0]] = true;
			for (int i = 1; i < n; ++i) {
				for (int j = maxWeight - items[i]; j >= 0; --j) {
					// 比如从后往前, 避免for循环中重复计算
					// 放入items[i]
					if (states[j]) {
						states[j + items[i]] = true;
					}
				}
			}
			for (int i = maxWeight; i >= 0; --i) {
				if (states[i]) {
					return i;
				}
			}
			return 0;
		}

	}

	public static class Bag2 {

		public int resolve(int[] items, int[] values, int maxWeight) {
			int n = items.length;
			int[] states = new int[maxWeight + 1];
			Arrays.fill(states, -1);
			states[0] = 0;
			states[items[0]] = values[0];

			for (int i = 1; i < n; i++) {
				for (int j = maxWeight - items[i]; j >= 0; --j) {
					if (states[j] >= 0) {
						states[j + items[i]] = Math.max(states[j] + values[i], states[j + items[i]]);
					}
				}
			}

			int maxValue = 0;
			for (int i = 1; i < states.length; i++) {
				maxValue = Math.max(maxValue, states[i]);
			}

			return maxValue;
		}

	}

	public static class Triangle {

		public int resolve(int[][] matrix) {
			int[] state = new int[matrix.length];
			state[0] = matrix[0][0];

			for (int i = 1; i < matrix.length; i++) {
				int[] arr = matrix[i];
				state[arr.length - 1] = state[arr.length - 2] + arr[arr.length - 1];
				for (int j = arr.length - 2; j >= 1; j--) {
					state[j] = Math.min(state[j], state[j - 1]) + arr[j];
				}
				state[0] = state[0] + arr[0];
			}

			return IntStream.of(state).min().getAsInt();
		}

	}

	public static class Square {

		public int resolve(int[][] matrix) {
			int[] state = new int[matrix.length];
			state[0] = matrix[0][0];

			for (int i = 1; i < matrix.length; i++) {
				int[] arr = matrix[i];
				state[arr.length - 1] = state[arr.length - 2] + arr[arr.length - 1];
				for (int j = arr.length - 2; j >= 1; j--) {
					state[j] = Math.min(state[j], state[j - 1]) + arr[j];
				}
				state[0] = state[0] + arr[0];
			}

			return IntStream.of(state).min().getAsInt();
		}

	}

	public static class Coins {

		public int resolve(int[] coins, int value) {
			int[] temp = Arrays.copyOf(coins, coins.length);
			Arrays.sort(temp);

			HashMap<Integer, Integer> memo = new HashMap<>();
			int result = resolve(temp, value, memo);
			return result;
		}

		private int resolve(int[] coins, int value, Map<Integer, Integer> memo) {
			if (memo.containsKey(value)) {
				return memo.get(value);
			}

			if (Arrays.binarySearch(coins, value) >= 0) {
				memo.put(value, 1);
				return 1;
			}

			if (value <= 0) {
				memo.put(value, Integer.MAX_VALUE);
				return Integer.MAX_VALUE;
			}

			int min = IntStream.range(0, coins.length)
					.map(i -> resolve(coins, value - coins[i], memo))
					.min()
					.getAsInt() + 1;
			memo.put(value, min);
			return min;
		}

	}

	public static void main(String[] args) {
		int[] items = new int[] { 2, 2, 4, 6, 3 };
		int maxWeight = 9;
		int max = new Bag().resolve(items, maxWeight);
		System.out.println("从" + Arrays.toString(items) + "中选择物品放入总容量" + maxWeight + "的背包中, 最多放入" + max + "容量的物品");

		int[] values = new int[] { 3, 4, 8, 9, 6 };
		int max2 = new Bag2().resolve(items, values, maxWeight);
		System.out.println("从重量为" + Arrays.toString(items) + "/价值为" + Arrays.toString(values)
				+ "中选择物品放入总容量" + maxWeight + "的背包中, 最多放入" + max2 + "价值的物品");

		int[][] matrix = { { 5 }, { 7, 8 }, { 2, 3, 4 }, { 4, 9, 6, 1 }, { 2, 7, 9, 4, 5 } };
		int min = new Triangle().resolve(matrix);
		System.out.println(Arrays.deepToString(matrix) + "的最短路径长度为" + min);

		int[][] matrix2 = { { 1, 3, 5, 9 }, { 2, 1, 3, 4 }, { 5, 2, 6, 7 }, { 6, 8, 4, 3 } };
		int min2 = new Square().resolve(matrix2);
		System.out.println(Arrays.deepToString(matrix) + "的最短路径长度为" + min2);

		int[] coins = { 5, 3, 7 };
		int value = 5;
		int min3 = new Coins().resolve(coins, value);
		System.out.println(min3);
	}

}
