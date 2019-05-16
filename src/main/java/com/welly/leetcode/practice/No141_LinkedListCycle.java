package com.welly.leetcode.practice;

import com.welly.leetcode.def.ListNode;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No141_LinkedListCycle {

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode fast = head.next.next;
		ListNode slow = head.next;

		while (fast != null) {
			if (fast == slow) {
				return true;
			}

			if (fast.next == null) {
				return false;
			}

			fast = fast.next.next;
			slow = slow.next;
		}

		return false;
	}

}
