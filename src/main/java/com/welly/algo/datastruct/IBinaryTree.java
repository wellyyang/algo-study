package com.welly.algo.datastruct;

/**
 * 二叉树, 常用的平衡二叉树和哈希表相比, 虽然时间复杂度O(logn)比O(1)要慢, 但是在绝大多数情况下时间复杂度稳定为O(logn) <br>
 * 可以使用数组/链表实现, 数组更适合完全二叉树, 或满二叉树.
 *
 * @author yangchuan02
 * @date 2019年1月22日
 */
public interface IBinaryTree {

	void insert(int n);

	void remove(int n);

	BinaryTreeNode find(int n);

}
