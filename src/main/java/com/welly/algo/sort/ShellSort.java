package com.welly.algo.sort;

/**
 * 希尔排序, 插入排序的优化版, 与插入排序相比, 时间复杂度降低, 但是从稳定排序变成不稳定排序
 * 
 * <pre>
 * 空间复杂度 O(1)
 * 时间复杂度O(nlogn)
 * 不稳定排序
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月8日
 */
public class ShellSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
		int incrementNum = arr.length / 2;

		for (; incrementNum > 0; incrementNum--) {
			for (int i = incrementNum; i < arr.length; i++) {
				int value = arr[i];
				int j = i - incrementNum;
				for (; j >= 0; j -= incrementNum) {
					if (value < arr[j]) {
						arr[j + incrementNum] = arr[j];
					} else {
						break;
					}
				}
				arr[j + incrementNum] = value;
			}

		}

		return arr;
	}

}
