package com.welly.leetcode.practice;

import java.util.PriorityQueue;

import com.welly.leetcode.def.ListNode;

/**
 * @author yangchuan02
 * @date 2019年5月16日
 */
public class No023_MergeKSortedList {

	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.val, n2.val));
		for (ListNode node : lists) {
			while (node != null) {
				ListNode temp = node;
				node = node.next;
				temp.next = null;
				pq.add(temp);
			}
		}

		ListNode sentinal = new ListNode(0);
		ListNode cur;
		ListNode prev = sentinal;
		while ((cur = pq.poll()) != null) {
			prev.next = cur;
			prev = cur;
		}

		return sentinal.next;
	}

	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		if (lists.length == 1) {
			return lists[0];
		}

		ListNode head = null;
		ListNode cur = head;

		ListNode minNode;
		while ((minNode = pollMinNode(lists)) != null) {
			if (head == null) {
				head = minNode;
				cur = head;
			} else {
				cur.next = minNode;
				cur = minNode;
			}
		}

		return head;
	}

	private ListNode pollMinNode(ListNode[] lists) {
		ListNode minNode = lists[0];
		int minNodeIndex = 0;
		for (int i = 1; i < lists.length; i++) {
			ListNode node = lists[i];
			if (node != null && (minNode == null || minNode.val > node.val)) {
				minNode = node;
				minNodeIndex = i;
			}
		}
		lists[minNodeIndex] = minNode == null ? null : minNode.next;
		return minNode;
	}

	public static void main(String[] args) {
		ListNode[] lists = new ListNode[3];
		lists[0] = buildListNode(1, 4, 5);
		lists[1] = buildListNode(1, 3, 4);
		lists[2] = buildListNode(2, 6);
		System.out.println(new No023_MergeKSortedList().mergeKLists(lists));

		lists = new ListNode[3];
		lists[0] = buildListNode(1, 4, 5);
		lists[1] = buildListNode(1, 3, 4);
		lists[2] = buildListNode(2, 6);
		System.out.println(new No023_MergeKSortedList().mergeKLists2(lists));
	}

	private static ListNode buildListNode(int... vals) {
		ListNode sentinal = new ListNode(0);
		ListNode node = sentinal;
		for (int val : vals) {
			ListNode temp = new ListNode(val);
			node.next = temp;
			node = temp;
		}
		return sentinal.next;
	}

}
