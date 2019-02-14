package com.welly.algo.search.string;

/**
 * <pre>
 * Rabin-Karp 算法
 * 通过哈希算法对主串中的 n-m+1 个子串分别求哈希值，然后逐个与模式串的哈希值比较大小。如果某个子串的哈希值与模式串相等，那就说明对应的子串和模式串匹配
 *
 * 时间复杂度O(n), 如果hash冲突严重, 时间复杂度会降低到O(m * n)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月13日
 */
public class RK implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		int patternHash = pattern.hashCode();
		int patternLength = pattern.length();
		int stopIndex = search.length() - patternLength + 1;
		for (int i = 0; i < stopIndex; i++) {
			if (hash(search, i, i + patternLength) == patternHash
					&& eq(search, i, pattern)) {
				return i;
			}
		}

		return -1;
	}

	private boolean eq(String s, int start, String pattern) {
		int i = -1;
		while (++i < pattern.length()) {
			if (pattern.charAt(i) != s.charAt(start + i))
				return false;
		}
		return true;
	}

	private int hash(String s, int start, int end) {
		// 不要用substring.hashcode, 会生成新的string对象
		int ret = 0;
		for (int i = start; i < end; i++) {
			char c = s.charAt(i);
			ret = ret * 31 + c;
		}
		return ret;
	}

}
