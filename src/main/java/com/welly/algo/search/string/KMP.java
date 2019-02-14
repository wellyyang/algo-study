package com.welly.algo.search.string;

/**
 * <pre>
 * KMP 算法是根据三位作者（D.E.Knuth，J.H.Morris 和 V.R.Pratt）的名字来命名的，算法的全称是 Knuth Morris Pratt 算法，简称为 KMP 算法。
 *
 * 核心思想和BM相同, 都是找规律, 尽可能向后多滑动. KMP从前往后匹配, 使用好前缀和坏字符
 *
 * 空间复杂度O(m), 时间复杂度O(m + n)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月14日
 * @see https://time.geekbang.org/column/article/71845
 */
public class KMP implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		int n = search.length();
		int m = pattern.length();
		int[] next = generateNext(pattern);
		int j = 0;
		for (int i = 0; i < n; i++) {
			while (j > 0 && search.charAt(i) != pattern.charAt(j)) {
				j = next[j - 1] + 1;
			}

			if (search.charAt(i) == pattern.charAt(j)) {
				++j;
			}

			if (j == m) { // 找到匹配
				return i - m + 1;
			}
		}
		return -1;
	}

	// next数组, 数组下标当前长度的前缀的结尾下标, 数组value为当前长度的前缀的'最长可匹配前缀子串'的结尾下标
	// 例如模式串ababacd, 以前缀ababa为例, 该前缀长度为5, 前缀结尾下标为4, 所以存储在next[4]中
	// 最长可匹配前缀子串/最长可匹配后缀子串为 aba, 其最长可匹配前缀子串结尾下标为2, 所以next[4] = 2
	private int[] generateNext(String s) {
		int m = s.length();
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; i++) {
			while (k != -1 && s.charAt(k + 1) != s.charAt(i)) {
				k = next[k];
			}
			if (s.charAt(k + 1) == s.charAt(i)) {
				++k;
			}
			next[i] = k;
		}
		return next;
	}

}
