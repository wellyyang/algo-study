package com.welly.algo.sort;

/**
 * <pre>
 * 空间复杂度 O(1)
 * 时间复杂度O(n**2)
 * 不稳定排序
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月8日
 */
public class SelectionSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minValueIndex = findMinValueIndex(arr, i);
			if (minValueIndex > i) {
				swap(arr, i, minValueIndex);
			}
		}

		return arr;
	}

	private int findMinValueIndex(int[] arr, int i) {
		int index = i;
		for (int j = i + 1; j < arr.length; j++) {
			if (arr[j] < arr[index]) {
				index = j;
			}
		}
		return index;
	}

}
