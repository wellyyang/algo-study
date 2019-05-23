package com.welly.leetcode.practice;

import com.welly.leetcode.def.TreeNode;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No226_InvertBinaryTree {

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode l = root.left;
		TreeNode r = root.right;

		root.right = l;
		root.left = r;

		invertTree(l);
		invertTree(r);

		return root;
	}

}
