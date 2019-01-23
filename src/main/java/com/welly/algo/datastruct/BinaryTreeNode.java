package com.welly.algo.datastruct;

import lombok.Data;

/**
 * @author yangchuan02
 * @date 2019年1月22日
 */
@Data
public class BinaryTreeNode {

	private int value;

	private BinaryTreeNode left;

	private BinaryTreeNode right;

	@Override
	public String toString() {
		return "Node(data=" + value + ")";
	}

	public BinaryTreeNode(int value) {
		this.value = value;
	}

}
