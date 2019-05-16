package com.welly.leetcode.practice;

import java.util.BitSet;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No41_FirstMissingPositive {

	public int firstMissingPositive(int[] nums) {
		BitSet bs = new BitSet(nums.length);
		for (int num : nums) {
			if (num <= nums.length && num > 0) {
				bs.set(num - 1);
			}
		}
		int nextClearBit = bs.nextClearBit(0);
		return nextClearBit + 1;
	}

	public static void main(String[] args) {
		No41_FirstMissingPositive no41 = new No41_FirstMissingPositive();

		int[] nums = { 1, 2, 0, 1 };
		System.out.println(no41.firstMissingPositive(nums));
		int[] nums2 = { 3, 4, -1, 1 };
		System.out.println(no41.firstMissingPositive(nums2));
		int[] nums3 = { 7, 8, 9, 11, 12 };
		System.out.println(no41.firstMissingPositive(nums3));
	}

}
