package com.welly.algo.search.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Boyer Moore 算法
 * 原理, 在模式串不匹配时, 尽可能的向前多滑动几位
 * BM算法包含两部分, 坏字符规则(bad character rule) 和 好后缀规则(good suffix shift)
 *
 * 坏字符规则:
 * 1. 从后往前匹配, 找到第一个不匹配的字符, 即坏字符
 * 2. 在主串的检索区域内, 从后往前查找第一个和坏字符相同的字符
 * 2.1 找到的情况, 将模式串的坏字符滑动到该字符
 * 2.2 找不到的情况, 将模式串整个向后滑动
 *
 * 坏字符规则是有缺陷的, 比如主串aaaaaaaaa, 模式串baaa, 使用坏字符规则后如果不做特殊处理, 反而会向前滑动
 *
 * 注: 模式串中 'b' 即为坏字符, 'aaa'即为下面讲述的好后缀
 *
 * 好后缀规则:
 * 1. 在查找坏字符的同时, 可以得到好后缀
 * 2. 在模式串内, 忽略最后的好后缀, 从后往前如果能找到和好后缀相同的子串
 * 2.1 找到的情况, 将模式串滑动到该子串和好后缀对齐的位置
 * 2.2 找不到的情况, 需要特别注意!!!, 不能整个向后滑动, 要确认好后缀的后子串中是否有和模式串前子串匹配的最长子串
 * 2.2.1 有匹配的情况, 滑动到匹配对齐的位置
 * 2.2.2 没有匹配的情况, 整个向后滑动
 *
 * 取坏字符规则和好后缀规则向后滑动距离较大者, 向后滑动
 *
 * BM空间复杂度O(n), 实际是O(2m + 字符集相关常量), 额外使用了3个数组.
 * 时间复杂度在预处理阶段O(m^2), 搜索阶段O(mn).
 * 本实现只是最基础版本, 还有可以优化的地方. 可以参照gnu grep的实现作为参考
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月13日
 * @see https://time.geekbang.org/column/article/71525
 */
public class BM implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		int n = search.length();
		int m = pattern.length();
		// bc means bad character
		Map<Character, Integer> bcMap = generateBc(pattern); //
		// gs means good suffix
		int[] suffix = new int[pattern.length()];
		boolean[] prefix = new boolean[pattern.length()];
		generateGS(pattern, suffix, prefix);
		int i = 0; // i 表示主串与模式串对齐的第一个字符
		while (i <= n - m) {
			int j;
			for (j = m - 1; j >= 0; j--) { // 模式串从后往前匹配
				if (search.charAt(i + j) != pattern.charAt(j)) {
					// 坏字符对应模式串中的下标是 j
					break;
				}
			}

			if (j < 0) { // 匹配成功，返回主串与模式串第一个匹配的字符的位置
				return i;
			}

			// 坏字符向后滑动x位
			int x = j - bcMap.getOrDefault(search.charAt(i + j), -1);
			// 好后缀向后滑动y位
			int y = 0;
			if (j < m - 1) {
				// 坏字符不是最后一位, 则存在好后缀
				y = moveByGS(j, m, suffix, prefix);
			}

			// 将模式串往后滑动
			// 如果search中对应位置的字符在bcMap中不存在, 要给默认值-1, 让模式串向后滑动1位
			i += Math.max(x, y);
		}

		return -1;
	}

	private Map<Character, Integer> generateBc(String s) {
		// 构建bcMap, key为s中所有存在的字符, value为最后一个出现的位置index
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), i);
		}
		return map;
	}

	// 生成suffix数组, 数组下标代表后缀长度, value代表该后缀在搜索串中不包含后缀的lastIndexOf的位置
	// 生成prefix数组, 数组下标代表后缀长度, value代表该后缀在搜索串前子串中是否存在
	// 以ababab为例
	// 后缀: 空, b, ab, bab, abab, babab
	// suffix: [-1, 3, 2, 1, 0, -1]
	// prefix: [false, false, true, false, true, false]
	private void generateGS(String s, int[] suffix, boolean[] prefix) {
		Arrays.fill(suffix, -1);

		int m = s.length();

		for (int i = 0; i < m - 1; i++) { // s[0, i]
			int j = i;
			int k = 0; // 公共后缀子串长度
			while (j >= 0 && s.charAt(j) == s.charAt(m - 1 - k)) { // 与 b[0, m-1] 求公共后缀子串
				--j;
				++k;
				suffix[k] = j + 1; // j+1 表示公共后缀子串在 b[0, i] 中的起始下标
			}

			if (j == -1) {
				prefix[k] = true;
			}
		}
	}

	private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		int k = m - 1 - j; // 好后缀长度
		if (suffix[k] != -1) {
			return j - suffix[k] + 1;
		}

		for (int r = j + 2; r <= m - 1; r++) {
			if (prefix[m - r]) {
				return r;
			}
		}

		return 0;
	}

}
