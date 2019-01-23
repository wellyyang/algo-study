package com.welly.algo.datastruct;

import java.util.function.Consumer;

/**
 * 最简单的二叉树, 用链表实现, 不平衡
 *
 * @author yangchuan02
 * @date 2019年1月22日
 */
public class BinaryTree implements IBinaryTree {

	private BinaryTreeNode root;

	@Override
	public void insert(int n) {
		if (root == null) {
			root = new BinaryTreeNode(n);
			return;
		}

		BinaryTreeNode node = root;
		while (node != null) {
			if (n > node.getValue()) {
				if (node.getRight() == null) {
					node.setRight(new BinaryTreeNode(n));
					return;
				} else {
					node = node.getRight();
				}
			} else {
				if (node.getLeft() == null) {
					node.setLeft(new BinaryTreeNode(n));
					return;
				} else {
					node = node.getLeft();
				}
			}
		}
	}

	@Override
	public void remove(int n) {
		BinaryTreeNode node = root;
		BinaryTreeNode nodeParent = null;

		while (node != null && n != node.getValue()) {
			nodeParent = node;
			if (n > node.getValue()) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}

		if (node.getLeft() != null && node.getRight() != null) {
			// 如果是有2个子节点, 则交换右子树的最小节点和当前节点的值
			// 删除交换前的最小节点

			BinaryTreeNode minNode = root.getRight();
			BinaryTreeNode minNodeParent = root;
			while (minNode.getLeft() != null) {
				minNodeParent = minNode;
				minNode = minNode.getLeft();
			}

			node.setValue(minNode.getValue());

			node = minNode;
			nodeParent = minNodeParent;
		}

		// 无论是处理过的两个子节点还是只有一个子节点或者没有子节点, 到此node都至多有一个子节点
		BinaryTreeNode child = null;
		if (node.getLeft() != null) {
			child = node.getLeft();
		} else if (node.getRight() != null) {
			child = node.getRight();
		}

		if (nodeParent == null) {
			// 父节点为null, 说明node为根节点
			root = child;
		} else if (nodeParent.getLeft() == node) {
			nodeParent.setLeft(child);
		} else {
			nodeParent.setRight(child);
		}

	}

	@Override
	public BinaryTreeNode find(int n) {
		if (root == null) {
			return null;
		}

		BinaryTreeNode node = root;
		while (node != null && n != node.getValue()) {
			if (n > node.getValue()) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}
		return node;
	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		inOrder(node -> builder.append(node.getValue()).append(","));
		return builder.insert(0, "BinaryTree(").append(")").toString();
	}

	/**
	 * 中序遍历, 左根右
	 *
	 * @param func
	 */
	public void inOrder(Consumer<BinaryTreeNode> func) {
		inOrder0(root, func);
	}

	private void inOrder0(BinaryTreeNode node, Consumer<BinaryTreeNode> func) {
		if (node == null) {
			return;
		}

		inOrder0(node.getLeft(), func);
		func.accept(node);
		inOrder0(node.getRight(), func);
	}

	/**
	 * 后序遍历, 左右根
	 *
	 * @param func
	 */
	public void postOrder(Consumer<BinaryTreeNode> func) {
		postOrder0(root, func);
	}

	private void postOrder0(BinaryTreeNode node, Consumer<BinaryTreeNode> func) {
		if (node == null) {
			return;
		}

		postOrder0(node.getLeft(), func);
		postOrder0(node.getRight(), func);
		func.accept(node);
	}

	/**
	 * 前序遍历, 根左右
	 *
	 * @param func
	 */
	public void preOrder(Consumer<BinaryTreeNode> func) {
		preOrder0(root, func);
	}

	private void preOrder0(BinaryTreeNode node, Consumer<BinaryTreeNode> func) {
		if (node == null) {
			return;
		}

		func.accept(node);
		preOrder0(node.getLeft(), func);
		preOrder0(node.getRight(), func);
	}

}
