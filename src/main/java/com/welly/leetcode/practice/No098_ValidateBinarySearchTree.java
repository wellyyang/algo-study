package com.welly.leetcode.practice;

import com.welly.leetcode.def.TreeNode;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No098_ValidateBinarySearchTree {

	public boolean isValidBST(TreeNode root) {
		return isValidBST0(root, null, null);
	}

	private boolean isValidBST0(TreeNode root, Integer low, Integer high) {
		if (root == null) {
			return true;
		}

		int val = root.val;
		if (low != null && val <= low) {
			return false;
		}
		if (high != null && val >= high) {
			return false;
		}

		if (!isValidBST0(root.left, low, val)) {
			return false;
		}
		if (!isValidBST0(root.right, val, high)) {
			return false;
		}

		return true;
	}

}
