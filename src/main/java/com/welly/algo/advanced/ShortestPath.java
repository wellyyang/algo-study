package com.welly.algo.advanced;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 单源最短路径算法（一个顶点到一个顶点）
 *
 * @author yangchuan02
 * @date 2019年5月10日
 */
public class ShortestPath {

	public static final int M = Integer.MAX_VALUE; // 代表正无穷

	public static void main(String[] args) {
		int[][] w = {
				{ 0, 4, M, 2, M },
				{ 4, 0, 4, 1, M },
				{ M, 4, 0, 1, 3 },
				{ 2, 1, 1, 0, 7 },
				{ M, M, 3, 7, 0 }
		};

		int start = 0;

		System.out.println(Arrays.toString(new ShortestPath().dijkstra(w, start)));
	}

	public int[] dijkstra(int[][] w, int s) {
		int[][] cp = deepCopy(w);

		int n = w.length;

		boolean[] visited = new boolean[n];
		int[] shortestPath = new int[n];
		String[] path = IntStream.range(0, n)
				.mapToObj(i -> s + " --> " + i)
				.toArray(String[]::new);

		visited[s] = true;
		shortestPath[s] = 0;

		// 选出剩下的n - 1个顶点加入visited
		for (int count = 1; count < n; count++) {
			int k = findMinWeight(cp, s, visited);
			int minW = cp[s][k];

			visited[k] = true;
			shortestPath[k] = minW;

			// 用 s 和 k 的 w 更新cp
			for (int i = 0; i < n; i++) {
				if (visited[i] || cp[k][i] == Integer.MAX_VALUE) {
					// 已经访问过或者k到i无路径
					continue;
				}
				if (cp[s][k] + cp[k][i] < cp[s][i]) {
					cp[s][i] = cp[s][k] + cp[k][i];
					path[i] = path[k] + " --> " + i;
				}
			}
		}

		System.out.println(Arrays.toString(path));

		return shortestPath;
	}

	private int[][] deepCopy(int[][] g) {
		int[][] cp = new int[g.length][g.length];
		for (int i = 0; i < g.length; i++) {
			cp[i] = Arrays.copyOf(g[i], g[i].length);
		}
		return cp;
	}

	private int findMinWeight(int[][] g, int s, boolean[] visited) {
		int k = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < g.length; i++) {
			if (visited[i]) {
				continue;
			}
			if (g[s][i] < min) {
				min = g[s][i];
				k = i;
			}
		}
		return k;
	}

}
