package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No069_Sqrt {

	public static void main(String[] args) {
		No069_Sqrt e = new No069_Sqrt();
		System.out.println(e.mySqrt(1));
	}

	public int mySqrt(int x) {
		if (x == 0 || x == 1) {
			return x;
		}
		return trySqrt(x, 0, x);
	}

	private int trySqrt(int x, int min, int max) {
		if (min == max || (min + 1) == max) {
			return min;
		}

		int avg = (min + max) / 2;

		if (x / avg < avg) {
			return trySqrt(x, min, avg);
		}

		if (x / avg > avg) {
			return trySqrt(x, avg, max);
		}

		return avg;
	}

}
