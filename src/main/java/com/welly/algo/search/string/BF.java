package com.welly.algo.search.string;

/**
 * <pre>
 * Brute Force 的缩写，中文叫作暴力匹配算法，也叫朴素匹配算法.
 * BF时间复杂度是 O(n * m), 虽然时间复杂度高, 但是在实际场景中, 通常主串和模式串都比较短, 且算法实现简单
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月13日
 */
public class BF implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		if (search.length() < pattern.length()) {
			return -1;
		}

		int stopIndex = search.length() - pattern.length() + 1;
		for (int i = 0; i < stopIndex; i++) {
			boolean match = true;
			for (int j = i; j - i < pattern.length(); j++) {
				if (search.charAt(j) != pattern.charAt(j - i)) {
					match = false;
					break;
				}
			}

			if (match) {
				return i;
			}
		}

		return -1;
	}

}
