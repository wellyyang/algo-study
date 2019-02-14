package com.welly.algo.search.string;

/**
 * <pre>
 * 和RK相比, 优化hash, 每次计算hash时候不再遍历字符串, 只需要获取前一个字符和后一个字符即可.
 * 可以调整hash/rehash方法, 让计算更简单一些, 但是要注意hash冲突问题
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月13日
 */
public class RK2 implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		int patternHash = hash(pattern, 0, pattern.length());
		int patternLength = pattern.length();
		int stopIndex = search.length() - patternLength + 1;
		int prevHash = hash(search, 0, patternLength);

		if (prevHash == patternHash && eq(search, 0, pattern)) {
			return 0;
		}

		for (int i = 1; i < stopIndex; i++) {
			int hash = rehash(prevHash, search.charAt(i - 1), search.charAt(i + patternLength - 1), patternLength);
			if (hash == patternHash && eq(search, i, pattern)) {
				return i;
			}
			prevHash = hash;
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

	private int rehash(int hash, char prev, char next, int n) {
		return (hash - prev * pow(31, n - 1)) * 31 + next;
	}

	private int pow(int a, int b) {
		int ret = 1;
		while (b-- > 0) {
			ret *= a;
		}
		return ret;
	}

}
