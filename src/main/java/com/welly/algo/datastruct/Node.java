package com.welly.algo.datastruct;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yangchuan02
 * @date 2018年12月29日
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Node<T> {

	public T data;

	public Node<T> next;

	@Override
	public String toString() {
		// lombok 对于toString的循环引用处理不当, 会导致StackOverflowError
		StringBuilder ret = new StringBuilder("Node(");
		Node<T> node = this;
		int i = 0;
		while (node != null && i < 10) {
			ret.append(node.data).append(" -> ");
			node = node.next;
			i++;
		}

		if (node != null && node.next != null) {
			ret.append("more than 10 or has a circle");
		} else {
			ret.delete(ret.length() - 4, ret.length());
		}

		return ret.append(")").toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (obj instanceof Node) {
			Node<?> node = (Node<?>) obj;
			return Objects.equals(data, node.data) && Objects.equals(next, node.next);
		}
		return false;
	}

}