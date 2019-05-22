package com.welly.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No076_MinimumWindowSubstring {

	public static void main(String[] args) {
		String s = "ABDKASD:CLASDCBDA";
		String t = "ABC";
		System.out.println(new No076_MinimumWindowSubstring().minWindow(s, t));
	}

	public String minWindow(String s, String t) {
		if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
			return "";
		}

		Map<Character, Integer> dict = new HashMap<>();
		t.chars().forEach(c -> dict.compute((char) c, (k, v) -> v == null ? 1 : v + 1));

		List<Pair> filteredS = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (dict.containsKey(c)) {
				filteredS.add(new Pair(i, c));
			}
		}

		int l = 0, r = 0, formed = 0;
		Map<Character, Integer> windowCount = new HashMap<>();
		int required = dict.size();
		int[] result = { -1, 0, 0 };
		while (r < filteredS.size()) {
			char cR = filteredS.get(r).c;
			windowCount.compute(cR, (k, v) -> v == null ? 1 : v + 1);
			if (windowCount.get(cR) == dict.get(cR)) {
				formed++;
			}

			while (l <= r && formed == required) {
				int start = filteredS.get(l).index;
				int end = filteredS.get(r).index;
				if (result[0] == -1 || end - start + 1 < result[0]) {
					result[0] = end - start + 1;
					result[1] = start;
					result[2] = end;
				}

				char cL = filteredS.get(l).c;
				windowCount.put(cL, windowCount.get(cL) - 1);
				if (windowCount.get(cL) < dict.get(cL)) {
					formed--;
				}
				l++;
			}
			r++;
		}

		return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
	}

	private static class Pair {

		public final int index;

		public final char c;

		public Pair(int index, char c) {
			this.index = index;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair("
					+ "i: " + index + ", c: " + c
					+ ")";
		}

	}

}
