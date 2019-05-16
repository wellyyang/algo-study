package com.welly.leetcode.def;

import lombok.Data;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
@Data
public class ListNode {

	public int val;

	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

}
