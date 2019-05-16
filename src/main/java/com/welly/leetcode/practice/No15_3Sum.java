package com.welly.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No15_3Sum {

	public List<List<Integer>> threeSum(int[] nums) {
		int[] temp = Arrays.copyOf(nums, nums.length);
		Arrays.sort(temp);

		List<List<Integer>> list = new ArrayList<>();

		for (int i = 0; i < temp.length - 2; i++) {
			if (i == 0 || (temp[i] != temp[i - 1])) {
				int lo = i + 1;
				int hi = temp.length - 1;
				int sum = 0 - temp[i];
				while (lo < hi) {
					if (temp[lo] + temp[hi] == sum) {
						list.add(Arrays.asList(temp[i], temp[lo], temp[hi]));
						while (lo < hi && temp[lo] == temp[lo + 1]) {
							lo++;
						}
						while (lo < hi && temp[hi] == temp[hi - 1]) {
							hi--;
						}
						lo++;
						hi--;
					} else if (temp[lo] + temp[hi] < sum) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println(new No15_3Sum().threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
	}

}
