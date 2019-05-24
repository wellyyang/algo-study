package com.welly.leetcode.practice;

import java.util.Arrays;

/**
 * @author yangchuan02
 * @date 2019年5月23日
 */
public class No010_RegularExpressionMatching {

	public static void main(String[] args) {
		No010_RegularExpressionMatching e = new No010_RegularExpressionMatching();
		String s = "mississippi";
		String p = "mis*is*p*.";
		System.out.println(e.isMatch(s, p));
	}

	/**
	 * <pre>
	1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	3, If p.charAt(j) == '*':
	   here are two sub conditions:
	               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
	               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
	                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
	                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
	                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	 * </pre>
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null || p.startsWith("*")) {
			return false;
		}

		// 二维状态矩阵, true表示可以匹配到此处
		// 例如dp[i][j] = true, 表示, p.substring(0, j)可以匹配s.substring(0, i)
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
	    dp[0][0] = true;
		// 初始化, 用于标识可以初始的位置, 例如a*b*c
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				dp[0][i + 1] = true;
			}
		}

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				} else if (p.charAt(j) == s.charAt(i)) {
					dp[i + 1][j + 1] = dp[i][j];
				} else if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
					}
				}
			}
		}

		for (boolean[] arr : dp) {
			System.out.println(Arrays.toString(arr));
		}

		return dp[s.length()][p.length()];
	}

}
