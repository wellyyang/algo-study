package com.welly.algo.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * 可以认为是桶排序的特例, 即每个桶中只有值相同的数据, 所以桶内无需排序. 桶排序适用于桶数量不大且每个桶有多个值的场景, 即数据范围远小于数据总量.
 * 例如统计高考分数排名, 分数只有0~750的751个桶, 但是数据量会有几十万到上百万<br>
 *
 * 另外一点优势是, 可以预先准备数据, 然后快速查询指定值的排位. 例如考生根据自己的分数查询排名
 *
 * <pre>
 * 空间复杂度 O(n)
 * 时间复杂度O(n)
 * 稳定排序(依赖于取数方式)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class CountSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		// 统计每个值的数量, 使用SortedMap是为了便于后续的lesserCount统计
		SortedMap<Integer, Integer> counterMap = count(arr);

		// 统计每个小于等于该值的数量
		Map<Integer, Integer> lesserCountMap = countLess(counterMap);

		// 这里是该排序算法为什么会称为计数排序的原因, 说明如下:
		// arr: 2, 3, 1, 3, 3, 2, 1, 3, 1
		// counterMap: 1 = 3, 2 = 2, 3 = 4
		// lesserCountMap: 1 = 3, 2 = 5, 3 = 9
		// 取出arr[0], 值为2, lesserCountMap中key为2的值为5, 将2放到ret[5],
		// 同时lesserCountMap中将key为2的值减1(变为4)
		// 循环取出arr中所有值并按前述规则执行, 最后得到的就是排序后的ret
		int[] ret = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			// 必须是从尾部开始取数才是稳定排序
			// 从头部开始取数则不是稳定排序
			int index = lesserCountMap.compute(arr[i], (k, v) -> {
				return v - 1;
			});
			ret[index] = arr[i];
		}

		return ret;
	}

	private Map<Integer, Integer> countLess(SortedMap<Integer, Integer> counterMap) {
		Map<Integer, Integer> map = new HashMap<>(counterMap.size());
		int prevValue = 0;
		for (Entry<Integer, Integer> entry : counterMap.entrySet()) {
			Integer key = entry.getKey();
			int value = entry.getValue();
			map.put(key, prevValue + value);
			prevValue += value;
		}
		return map;
	}

	private SortedMap<Integer, Integer> count(int... arr) {
		SortedMap<Integer, Integer> map = IntStream.of(arr).parallel().collect(
				TreeMap<Integer, Integer>::new,
				(m, i) -> {
					m.compute(i, (k, v) -> {
						return v == null ? 1 : v + 1;
					});
				},
				(m1, m2) -> {
					for (Integer key : m2.keySet()) {
						m1.compute(key, (k, v) -> {
							Integer value = m2.get(k);
							return v == null ? value : v + value;
						});
					}
				});
		return map;
	}

}
