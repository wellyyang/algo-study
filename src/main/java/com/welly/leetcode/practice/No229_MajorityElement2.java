package com.welly.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用改进的摩尔投票算法, 可以统计大于1/k次的数据
 *
 * <ul>
 * <li>因为出现次数大于n/3的元素最多只有两个，所以最开始可以维护两个数字(num1,num2)和两个计数器(counter1,counter2)；</li>
 * <li>遍历数组，当数组中元素和num1或者num2相同，对应的counter1或者counter2加1；</li>
 * <li>如果counter1或counter2为0，将遍历到的该元素赋给num1或者nums2；</li>
 * <li>否则counter1和counter2都减1。</li>
 * </ul>
 *
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No229_MajorityElement2 {

	public List<Integer> majorityElement(int[] nums) {
		Integer candidate1 = null;
		Integer candidate2 = null;
		int count1 = 0;
		int count2 = 0;

		for (int num : nums) {
			if (candidate1 != null && num == candidate1.intValue()) {
				count1++;
			} else if (candidate2 != null && num == candidate2.intValue()) {
				count2++;
			} else if (count1 == 0) {
				candidate1 = num;
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = num;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}

		int candidate1Count = 0;
		int candidate2Count = 0;
		for (int num : nums) {
			if (num == candidate1) {
				candidate1Count++;
			} else if (num == candidate2) {
				candidate2Count++;
			}
		}

		List<Integer> list = new ArrayList<>();
		if (count1 > 0 && candidate1Count > nums.length / 3) {
			list.add(candidate1);
		}
		if (count2 > 0 && candidate2Count > nums.length / 3) {
			list.add(candidate2);
		}

		return list;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 3, 3, 2, 2, 2 };
		System.out.println(new No229_MajorityElement2().majorityElement(nums));

		int[] nums2 = { 3, 2, 3 };
		System.out.println(new No229_MajorityElement2().majorityElement(nums2));
	}

}
