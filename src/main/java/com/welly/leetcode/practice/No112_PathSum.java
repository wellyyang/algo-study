package com.welly.leetcode.practice;

import com.welly.leetcode.def.TreeNode;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No112_PathSum {

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		if (root.val == sum && root.left == null && root.right == null) {
			return true;
		}

		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}

}
