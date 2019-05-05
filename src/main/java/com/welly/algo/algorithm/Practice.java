package com.welly.algo.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author yangchuan02
 * @date 2019年4月30日
 */
public class Practice {

	public static class EditDistance {

		public int resolve(String a, String b) {
			// 回溯算法
			AtomicInteger result = new AtomicInteger(Integer.MAX_VALUE);
			resolve(a, b, 0, 0, 0, result);
			return result.get();
		}

		private void resolve(String a, String b, int indexA, int indexB,
				int d, AtomicInteger result) {
			if (indexA == a.length() || indexB == b.length()) {
				if (indexA < b.length()) {
					d += b.length() - indexA;
				}
				if (indexB < a.length()) {
					d += a.length() - indexB;
				}
				if (result.get() > d) {
					result.set(d);
				}
				return;
			}

			if (a.charAt(indexA) == b.charAt(indexB)) {
				resolve(a, b, indexA + 1, indexB + 1, d, result);
			} else {
				resolve(a, b, indexA + 1, indexB, d + 1, result);
				resolve(a, b, indexA, indexB + 1, d + 1, result);
				resolve(a, b, indexA + 1, indexB + 1, d + 1, result);
			}
		}

		public int resolve1(String a, String b) {
			// 动态规划
			int n = a.length();
			int m = b.length();
			int[][] minDist = new int[n][m];
			for (int j = 0; j < m; ++j) { // 初始化第 0 行:a[0..0] 与 b[0..j] 的编辑距离
				if (a.charAt(0) == b.charAt(j))
					minDist[0][j] = j;
				else if (j != 0)
					minDist[0][j] = minDist[0][j - 1] + 1;
				else
					minDist[0][j] = 1;
			}
			for (int i = 0; i < n; ++i) { // 初始化第 0 列:a[0..i] 与 b[0..0] 的编辑距离
				if (a.charAt(i) == b.charAt(0))
					minDist[i][0] = i;
				else if (i != 0)
					minDist[i][0] = minDist[i - 1][0] + 1;
				else
					minDist[i][0] = 1;
			}
			for (int i = 1; i < n; ++i) { // 按行填表
				for (int j = 1; j < m; ++j) {
					if (a.charAt(i) == b.charAt(j))
						minDist[i][j] = min(
								minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
					else
						minDist[i][j] = min(
								minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
				}
			}
			return minDist[n - 1][m - 1];
		}

		private int min(int... arr) {
			return IntStream.of(arr).min().getAsInt();
		}

	}

	public static void main(String[] args) {
		String a = "mitcmu";
		String b = "mtacnu";
		EditDistance ed = new EditDistance();
		System.out.println(ed.resolve(a, b));
		System.out.println(ed.resolve1(a, b));
	}

}
