package com.welly.algo.sort;

/**
 * <pre>
 * 空间复杂度 O(n)
 * 时间复杂度O(nlogn)
 * 稳定排序(是否稳定取决于mergeArr方法)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class MergeSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
		return arr;
	}

	private void mergeSort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		int middle = (start + end) / 2;
		mergeSort(arr, start, middle);
		mergeSort(arr, middle + 1, end);
		mergeArr(arr, start, middle, end);
	}

	private void mergeArr(int[] arr, int start, int middle, int end) {
		int[] temp = new int[end - start + 1];

		int i = start;
		int j = middle + 1;
		int index = 0;
		while (i <= middle && j <= end) {
			if (arr[i] < arr[j]) {
				temp[index] = arr[i];
				i++;
			} else {
				temp[index] = arr[j];
				j++;
			}
			index++;
		}

		if (i <= middle) {
			System.arraycopy(arr, i, temp, index, middle - i + 1);
		} else if (j <= end) {
			System.arraycopy(arr, j, temp, index, end - j + 1);
		}

		System.arraycopy(temp, 0, arr, start, temp.length);
	}

}
