package com.welly.algo.shuffle;

import java.util.Random;

/**
 * Fisher–Yates shuffle, 用于对有限集合进行洗牌(随机排序)<br>
 * 时间复杂度O(n), 空间复杂度0, in-place.<br>
 * 所以如果集合非常大, in-place shuffle就有优势
 *
 * @author yangchuan02
 * @date 2018年12月27日
 * @see https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 */
public class FisherYatesShuffle<T> {

	private Random r = new Random();

	public T[] shuffle(T[] arr) {
		int length = arr.length;
		for (int i = length - 1; i >= 1; i--) {
			// [0, i + 1)
			int j = r.nextInt(i + 1);
			if (i != j) {
				T temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		return arr;
	}

}
