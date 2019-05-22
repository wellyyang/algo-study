package com.welly.leetcode.practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No239_SlidingWindowMaximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}

		int[] result = new int[nums.length - k + 1];

		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < nums.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				result[ri++] = nums[q.peek()];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		System.out.println(Arrays.toString(new No239_SlidingWindowMaximum().maxSlidingWindow(nums, k)));
	}

}
