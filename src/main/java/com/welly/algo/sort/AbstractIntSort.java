package com.welly.algo.sort;

/**
 * @author yangchuan02
 * @date 2019年1月8日
 */
public abstract class AbstractIntSort implements IIntSort {

	@Override
	public int[] sort(int... arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}

		return sort0(arr);
	}

	protected abstract int[] sort0(int[] arr);

	protected void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
