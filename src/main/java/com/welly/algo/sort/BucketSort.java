package com.welly.algo.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 适用场景: 根据分桶条件, 数据量近似均匀分布, 如果不均匀分布, 极端条件下时间复杂度会退化为O(nlogn)<br>
 * 比较适合外部排序
 *
 * <pre>
 * 空间复杂度 O(n)
 * 时间复杂度O(n), 实际上是O(nlog(n/m)), m表示适用m个桶
 * 稳定排序
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class BucketSort {

	public int[] sort(int[] arr, int min, int max, int bucketNum) {
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < bucketNum; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < arr.length; i++) {
			int value = arr[i];
			int bucket = (int) Math.ceil(value / bucketNum);
			list.get(bucket).add(value);
		}

		for (int i = 0; i < list.size(); i++) {
			// 对于每个桶所使用的排序方法, 这里使用快排
			Collections.sort(list.get(i));
		}

		return list.stream().flatMap(l -> l.stream()).mapToInt(Integer::intValue).toArray();
	}

}
