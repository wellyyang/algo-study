package com.welly.algo.sort;

/**
 * <pre>
 * 空间复杂度 O(1)
 * 时间复杂度O(nlogn)
 * 非稳定排序
 * </pre>
 *
 * 大顶堆
 *
 * <pre>
 * 在实际开发中，为什么快速排序要比堆排序性能好？
 * 第一点，堆排序数据访问的方式没有快速排序友好。数据访问时是跳跃的, 比如1-2-4-8, 对于缓存不友好
 * 第二点，对于同样的数据，在排序过程中，堆排序算法的数据交换次数要多于快速排序。比如对于一个已经排序好的数据集, 反而会打乱数据集的顺序
 *
 * 应用场景:
 * 1. top n. 例如求top n最大值, 可以使用包含n个元素的小顶堆.
 * 2. 优先队列. 使用小顶堆, 值最小的优先级最高
 * 3. 数据流的中位数
 * 4. 数据流的中值
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月23日
 */
public class HeapSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		buildHeap(arr);

		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			heapify(arr, i, 0);
		}

		return arr;
	}

	// 时间复杂度O(n)
	private void buildHeap(int[] arr) {
		// 从第一个非叶节点开始, n/2之后都是叶节点
		for (int i = arr.length / 2; i >= 0; i--) {
			heapify(arr, arr.length, i);
		}
	}

	// 对arr中前n个元素堆化的第i个元素进行堆化
	private void heapify(int[] arr, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 + 1 < n && arr[i] < arr[i * 2 + 1]) {
				maxPos = i * 2 + 1;
			}
			if (i * 2 + 2 < n && arr[maxPos] < arr[i * 2 + 2]) {
				maxPos = i * 2 + 2;
			}
			if (maxPos == i) {
				break;
			}

			swap(arr, i, maxPos);
			i = maxPos;
		}
	}

}
