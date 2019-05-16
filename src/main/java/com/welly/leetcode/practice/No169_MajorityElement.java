package com.welly.leetcode.practice;

import com.welly.algo.advanced.BoyerMooreVoting;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No169_MajorityElement {

	public int majorityElement(int[] nums) {
		return new BoyerMooreVoting().resolve(nums);

		// int[] temp = Arrays.copyOf(nums, nums.length);
		// Arrays.sort(temp);
		// return temp[temp.length / 2];

		// return 使用hashmap的朴素暴力统计
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
		System.out.println(new No169_MajorityElement().majorityElement(nums));

		int[] nums2 = { 3, 2, 3 };
		System.out.println(new No169_MajorityElement().majorityElement(nums2));
	}

}
