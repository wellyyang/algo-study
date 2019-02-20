package com.welly.algo.search.string;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Sunday是BM的优化, 从前往后匹配, 匹配失败时尽可能往后多滑动
 * Sunday时间复杂度是 O(n), 在最坏情况下下降到O(mn), 空间复杂度O(k), k和字符集大小相关, 可以优化为O(m)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月19日
 */
public class Sunday implements ISearch {

	@Override
	public int indexOf(String search, String pattern) {
		int n = search.length();
		int m = pattern.length();

		// 如果能确定查找范围, 例如只是在ascii, 那么定义为
		// int[] move = new int[128]更合理, 空间占用少, 且查找更快, 数组连续空间也利于jvm优化
		Map<Integer, Integer> moveMap = new HashMap<>();
		for (int i = 0; i < m; i++) {
			moveMap.put(Integer.valueOf(pattern.charAt(i)), m - i);
		}

		int s = 0; // 模式串头部在主串位置
		int j; // 模式串已经匹配了的长度
		while (s <= n - m) {
			j = 0;
			while (search.charAt(s + j) == pattern.charAt(j)) {
				j++;
				if (j >= m) {
					return s;
				}
			}
			if (s + m >= n) {
				break;
			}
			s += moveMap.getOrDefault(Integer.valueOf(search.charAt(s + m)), m + 1);
		}

		return -1;
	}

}
