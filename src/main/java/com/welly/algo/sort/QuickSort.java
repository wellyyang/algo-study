package com.welly.algo.sort;

/**
 * <pre>
 * 空间复杂度 O(1)
 * 时间复杂度O(nlogn)
 * 不稳定排序
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class QuickSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	private void quickSort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		int n = partition(arr, start, end);
		quickSort(arr, start, n - 1);
		quickSort(arr, n + 1, end);
	}

	private int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		// i指向分区的index
		int i = start;
		for (int j = start; j < end; j++) {
			if (arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, end);
		return i;
	}

}
