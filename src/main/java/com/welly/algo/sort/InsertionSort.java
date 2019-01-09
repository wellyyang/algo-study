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
public class InsertionSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int value = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (value < arr[j]) {
					arr[j + 1] = arr[j];
				} else {
					break;
				}
			}
			arr[j + 1] = value;
		}

		return arr;
	}

}
