package com.welly.algo.datastruct;

import static com.welly.algo.datastruct.LinkedList.checkCircle;
import static com.welly.algo.datastruct.LinkedList.checkCircle2;
import static com.welly.algo.datastruct.LinkedList.reverse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.welly.algo.datastruct.Node;


/**
 * @author yangchuan02
 * @date 2018年12月29日
 */
public class LinkedListTest {

	@Test
	public void testReverse() {
		Node<Integer> node = new Node<>(1, null);
		Node<Integer> head = node;
		for (int i = 2; i < 5; i++) {
			node.next = new Node<>();
			node = node.next;
			node.data = i;
		}

		Node<Integer> node2 = new Node<>(4, null);
		Node<Integer> head2 = node2;
		for (int i = 3; i > 0; i--) {
			node2.next = new Node<>();
			node2 = node2.next;
			node2.data = i;
		}

		assertEquals(head2, reverse(head));

		assertNull(reverse(null));

		Node<Integer> single = new Node<>(1, null);
		assertEquals(single, reverse(single));
	}

	@Test
	public void testCheckCircle() {
		assertFalse(checkCircle(null));

		Node<Integer> single = new Node<>(1, null);
		assertFalse(checkCircle(single));

		Node<Integer> node = new Node<>(1, null);
		Node<Integer> noCircle = node;
		for (int i = 2; i < 5; i++) {
			node.next = new Node<>();
			node = node.next;
			node.data = i;
		}
		assertFalse(checkCircle(noCircle));
		assertFalse(checkCircle2(noCircle));

		Node<Integer> node2 = new Node<>(1, null);
		Node<Integer> hasCircle = node2;
		for (int i = 2; i < 5; i++) {
			node2.next = new Node<>();
			node2 = node2.next;
			node2.data = i;
		}
		node2.next = hasCircle.next;
		assertTrue(checkCircle(hasCircle));
		assertTrue(checkCircle2(hasCircle));
	}

}
