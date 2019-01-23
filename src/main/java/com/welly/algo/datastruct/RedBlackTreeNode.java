package com.welly.algo.datastruct;

import lombok.Data;

/**
 * @author yangchuan02
 * @date 2019年1月22日
 */
@Data
public class RedBlackTreeNode {

	private int value;

	private RedBlackTreeNode left;

	private RedBlackTreeNode right;

	private RedBlackTreeNode parent;

	/**
	 * true black, false red, default red
	 */
	private boolean color;

}
