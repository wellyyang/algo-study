package com.welly.algo.advanced;

import lombok.NonNull;

/**
 * 摩尔投票算法, 可以在O(n)时间/O(1)空间内统计出得票最多者, 缺点是如果有打平, 只会随机获取到其中一个. <br>
 * 逻辑: 删除2个不相同的元素, 无论删除的元素是否为次数最多的, 最终剩下的元素中依然是之前次数最多的元素出现的次数最多. <br>
 * 可以扩展到1/k次, 使用多个count/candidate
 *
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class BoyerMooreVoting {

	public int resolve(@NonNull int... nums) {
		int candidate = nums[0];
		int count = 1;
		for (int i : nums) {
			if (count == 0) {
				candidate = i;
			}
			count += i == candidate ? 1 : -1;
		}
		return candidate;
	}

	/**
	 *
	 * @param k top k, 但是要注意次数未必会大于 1/k
	 * @param nums
	 * @return
	 */
	public int[] resolve(int k, @NonNull int... nums) {
		int[] candidate = new int[k];
		int[] count = new int[k];

		for (int i = 0; i < nums.length; i++) {
			int index = findInCandidate(nums[i], candidate);
			if (index >= 0) {
				count[index] = count[index] + 1;
			} else {
				int firstCountZeroIndex = findFirstCountZero(count);
				if (firstCountZeroIndex >= 0) {
					candidate[firstCountZeroIndex] = nums[i];
					count[firstCountZeroIndex] = 1;
				} else {
					countdown(count);
				}
			}
		}

		return candidate;
	}

	private void countdown(int[] count) {
		for (int i = 0; i < count.length; i++) {
			count[i] = count[i] - 1;
		}
	}

	private int findFirstCountZero(int[] count) {
		for (int i = 0; i < count.length; i++) {
			if (count[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	private int findInCandidate(int num, int[] candidate) {
		for (int i = 0; i < candidate.length; i++) {
			if (candidate[i] == num) {
				return i;
			}
		}
		return -1;
	}

}
