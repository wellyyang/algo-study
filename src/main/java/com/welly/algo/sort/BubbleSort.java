package com.welly.algo.sort;

/**
 * <pre>
 * 空间复杂度 O(1)
 * 时间复杂度O(n**2)
 * 稳定排序
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月8日
 */
public class BubbleSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		boolean hasSwap = false;

		for (int i = 0; i < arr.length; i++) {
			hasSwap = false;
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					hasSwap = true;
				}
			}
			if (!hasSwap) {
				break;
			}
		}

		return arr;
	}

}
