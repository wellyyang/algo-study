package com.welly.algo.datastruct;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 跳表, 类似于支持二分查找的链表, 有链表的快速插入删除, 有二分查找的快速访问, 还可以快速查找范围数据(这点是红黑树无法做到的,
 * 也是redis有序集合选择跳表而不是红黑树的原因之一), 且跳表的实现比红黑树简单.<br>
 * 跳表需要额外的空间保存索引数据, 由于使用链表获取size需要O(n)
 *
 * <pre>
 * 空间复杂度 O(n)
 * 时间复杂度O(logn)
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月11日
 * @see {@linkplain ConcurrentSkipListMap}
 * @see {@linkplain ConcurrentSkipListSet}
 */
public class SkipList {

	/**
	 * 最大索引层数, 16层在二分查找下可以支持2**16的数据, 实际实现中索引数据是随机提升, 所以和理论值不匹配
	 */
	private static final int MAX_LEVEL = 16;

	/**
	 * 总的索引层数
	 */
	private int levelCount = 1;

	/**
	 * 带头链表
	 */
	private Node head = new Node();

	/**
	 * 随机数, 用于随机生成索引层数
	 */
	private Random r = new Random();

	public Node find(int value) {
		Node p = head;

		// value = 4
		// head.forward[n] 代表第n层的链表, 第0层代表原始数据
		// 2层(head.forward[2]): 1 ----------------> 5
		// 1层(head.forward[1]): 1 ------> 3 ------> 5 ------> 7 ------> 9
		// 0层(head.forward[0]): 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		// 循环执行完毕后p指向4或者不满足条件的节点或者null
		for (int i = levelCount - 1; i >= 0; i--) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
		}

		return (p.forwards[0] != null && p.forwards[0].data == value) ? p : null;
	}

	public SkipList insert(int value) {
		int level = randomLevel();
		Node newNode = new Node();
		newNode.data = value;
		newNode.maxLevel = level;
		Node update[] = new Node[level];
		for (int i = 0; i < level; ++i) {
			update[i] = head;
		}

		// record every level largest value which smaller than insert value in update[]
		Node p = head;
		for (int i = level - 1; i >= 0; --i) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
			update[i] = p;// use update save node in search path
		}

		// in search path node next node become new node forwords(next)
		for (int i = 0; i < level; ++i) {
			newNode.forwards[i] = update[i].forwards[i];
			update[i].forwards[i] = newNode;
		}

		// update node hight
		if (levelCount < level) {
			levelCount = level;
		}

		return this;
	}

	public SkipList delete(int value) {
		Node[] update = new Node[levelCount]; // 找出每一层索引中对应的Node
		Node p = head; // 同 find 里返回的 Node
		for (int i = levelCount - 1; i >= 0; --i) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
			update[i] = p;
		}

		if (p.forwards[0] != null && p.forwards[0].data == value) { // 判断Node是否有找到
			for (int i = levelCount - 1; i >= 0; --i) {
				if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
					update[i].forwards[i] = update[i].forwards[i].forwards[i];
				}
			}
		}

		return this;
	}

	// 随机 level 次，如果是奇数, 层数 +1，防止伪随机
	private int randomLevel() {
		int level = 1;
		for (int i = 1; i < MAX_LEVEL; ++i) {
			if (r.nextInt() % 2 == 1) {
				level++;
			}
		}
		return level;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("SkipList[");
		Node p = head;
		do {
			p = p.forwards[0];
			builder.append(p.data).append("[").append(p.maxLevel).append("] -> ");
		} while (p.forwards[0] != null);
		builder.append("]");
		return builder.toString();
	}

	public class Node {

		public int data = -1;

		public Node[] forwards = new Node[MAX_LEVEL];

		public int maxLevel = 0;

		@Override
		public String toString() {
			return "Node[data: " + data + ", level: " + maxLevel + "]";
		}
	}

}
