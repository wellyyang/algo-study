package com.welly.algo.sampling;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * 蓄水池算法<br>
 * 采样问题可以抽象为从n个对象的列表中随机选取k个对象.<br>
 * 蓄水池算法适用场景:
 * <ul>
 * <li>n是非常大的值
 * <li>n是未知或者无限(例如生成器)
 * </ul>
 *
 * @author yangchuan02
 * @date 2018年12月27日
 * @see https://en.wikipedia.org/wiki/Reservoir_sampling
 */
public abstract class ReservoirSampling<T> {

	private Random r = new Random();

	public abstract Class<T> getTClass();

	public T[] sampling(T[] sample, int n) {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(getTClass(), n);
		for (int i = 0; i < sample.length; i++) {
			sampling(arr, i, sample[i]);
		}
		return arr;
	}

	public T[] sampling(Collection<T> sample, int n) {
		return sampling(sample.iterator(), n);
	}

	public T[] sampling(Iterator<T> iter, int n) {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(getTClass(), n);
		for (int i = 0; iter.hasNext(); i++) {
			sampling(arr, i, iter.next());
		}
		return arr;
	}

	/**
	 *
	 * @param arr 当前采样结果
	 * @param m 第m个
	 * @param data 第m个数据
	 */
	private void sampling(T[] arr, int m, T data) {
		// 对于前n个数据, 直接放入采样结果
		// 对于第m(k > n)个数据, 以 n / m 的概率保留
		int n = arr.length;
		if (m < n) {
			arr[m] = data;
		} else {
			int nextInt = r.nextInt(m + 1);
			if (nextInt < n) {
				arr[nextInt] = data;
			}
		}
	}

}
