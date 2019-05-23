package com.welly.leetcode.practice;

import com.welly.leetcode.def.TreeNode;

/**
 * @author yangchuan02
 * @date 2019年5月22日
 */
public class No104_MaximumDepthOfBinaryTree {

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int result = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		return result;
	}

}
