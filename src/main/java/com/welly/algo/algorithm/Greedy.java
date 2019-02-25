package com.welly.algo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

/**
 * <pre>
 * 贪心算法, 多适用于, 满足限制值的情况下，期望值最大的场景.
 * 贪心类似于全局最优解是局部最优解的综合. 局部的选择不能影响后续的选择, 否则贪心会失效(因为局部最优解可能会导致全局非最优解), 这种情况更适合使用动态规划.
 *
 * 实际应用中, 霍夫曼编码(霍夫曼树/最优二叉树)/Prim 和 Kruskal 最小生成树算法/Dijkstra 单源最短路径算法 都是使用了贪心算法
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月22日
 */
public class Greedy {

	@Data(staticConstructor = "of")
	public static class Range {

		public final int min;

		public final int max;
	}

	// 给定多个区间rl, 从rl中选择尽可能多的range, 使得其满足互不相交且都落在full内. 端点相交不算相交.
	public List<Range> rangeCover(List<Range> rl) {
		// 此处使用贪心, 每次选择时, 选择min和之前max不想交且max最小.
		// 所以实现时, 先对Range进行按照max升序, 依次选择min和之前max不想交的
		List<Range> sortedRl = new ArrayList<>(rl);
		sortedRl.sort((r1, r2) -> r1.max - r2.max);

		List<Range> ret = new ArrayList<>();

		sortedRl.forEach(r -> {
			if (ret.isEmpty()) {
				ret.add(r);
			} else {
				Range prev = ret.get(ret.size() - 1);
				if (r.getMin() >= prev.getMax()) {
					ret.add(r);
				}
			}
		});

		return ret;
	}

	public static void main(String[] args) {
		Greedy g = new Greedy();
		List<Range> ranges = Arrays.asList(Range.of(6, 8),
				Range.of(2, 4),
				Range.of(3, 5),
				Range.of(1, 5),
				Range.of(5, 9),
				Range.of(8, 10));
		System.out.println(g.rangeCover(ranges));
	}

}
