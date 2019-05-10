package com.welly.algo.advanced;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

/**
 * 拓扑排序
 *
 * @author yangchuan02
 * @date 2019年5月10日
 */
@Data
public class TopoSort {

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(0, 3);
		g.addEdge(2, 1);

		TopoSort ts = new TopoSort(g);
		System.out.println(ts.topoSortByKahn());
		System.out.println(ts.topoSortByDFS());
	}

	private final Graph g;

	private int v;

	private LinkedList<Integer>[] adj;

	public TopoSort(@NonNull Graph g) {
		this.g = g;
		this.v = g.getV();
		this.adj = g.getAdj();
	}

	public List<Integer> topoSortByKahn() {
		// 统计每个顶点的入度
		int[] inDegree = new int[v];
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < adj[i].size(); ++j) {
				int w = adj[i].get(j); // i->w
				inDegree[w]++;
			}
		}

		// 将入度为0的首先放入队列
		LinkedList<Integer> queue = new LinkedList<>();
		IntStream.of(inDegree)
				.filter(i -> i == 0)
				.forEach(queue::add);

		// 打印入度为0的顶点, 并将其连接的其他顶点的入度减1
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int i = queue.remove();
			result.add(i);
			for (int j = 0; j < adj[i].size(); ++j) {
				int k = adj[i].get(j);
				if (--inDegree[k] == 0)
					queue.add(k);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> topoSortByDFS() {
		// 先构建逆邻接表，边 s->t 表示，s 依赖于 t，t 先于 s
		LinkedList<Integer> inverseAdj[] = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			inverseAdj[i] = new LinkedList<>();
		}
		for (int i = 0; i < v; ++i) { // 通过邻接表生成逆邻接表
			for (int j = 0; j < adj[i].size(); ++j) {
				int w = adj[i].get(j); // i->w
				inverseAdj[w].add(i); // w->i
			}
		}

		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; ++i) { // 深度优先遍历图
			if (visited[i] == false) {
				visited[i] = true;
				dfs(i, inverseAdj, visited, result);
			}
		}
		return result;
	}

	private void dfs(int vertex, LinkedList<Integer> inverseAdj[],
			boolean[] visited, List<Integer> result) {
		// 先把 vertex 这个顶点可达的所有顶点都打印出来之后，再打印它自己
		for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
			int w = inverseAdj[vertex].get(i);
			if (visited[w] == true)
				continue;
			visited[w] = true;
			dfs(w, inverseAdj, visited, result);
		}
		result.add(vertex);
	}

	// 有向无权图
	public static class Graph {

		@Getter
		private final int v; // 顶点数量

		@Getter
		private final LinkedList<Integer>[] adj; // 每个点的边的列表(出度)

		@SuppressWarnings("unchecked")
		public Graph(int v) {
			this.v = v;
			this.adj = new LinkedList[v];
			for (int i = 0; i < v; i++) {
				adj[i] = new LinkedList<>();
			}
		}

		public void addEdge(int s, int t) {
			adj[s].add(t);
		}

	}

}
