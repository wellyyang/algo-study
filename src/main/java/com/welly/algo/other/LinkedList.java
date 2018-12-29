package com.welly.algo.other;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangchuan02
 * @date 2018年12月29日
 */
public class LinkedList {

	public static <T> Node<T> reverse(Node<T> node) {
		if (node == null || node.next == null) {
			return node;
		}

		Node<T> head = null;

		Node<T> prev = null;
		Node<T> current = node;

		while (current != null) {
			head = current;

			Node<T> tempNext = current.next;
			current.next = prev;
			prev = current;
			current = tempNext;
		}

		return head;
	}

	public static boolean checkCircle(Node<?> node) {
		if (node == null || node.next == null) {
			return false;
		}

		Node<?> slow = node;
		Node<?> fast = node;

		do {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				return true;
			}
		} while (slow != null && fast != null && fast.next != null);

		return false;
	}

	public static boolean checkCircle2(Node<?> node) {
		if (node == null || node.next == null) {
			return false;
		}
		// 使用hashset
		Set<Node<?>> set = new HashSet<>();
		while (node != null) {
			if (set.contains(node)) {
				return true;
			} else {
				set.add(node);
			}

			node = node.next;
		}

		return false;
	}

}
