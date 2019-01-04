package com.welly.algo.hash;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * 一致性hash, 在增删节点时(remapping)不会产生大量的缓存未命中<br>
 * 构造2**32的哈希环, 将节点分布在环上, 数据hash后在环上搜索距离最近的下一个节点, 并保存在该节点上.<br>
 * 通常会将真实节点生成多个虚拟节点并均匀分布到哈希环上, 目的是负载均衡, 尤其是对于真实节点比较少时,
 * 如果没有虚拟节点的话可能大量的数据会落在某几个真实节点上. 对于不同数量的真实节点, 需要选取不同数量的虚拟节点,
 * 一般10个真实节点选择100个虚拟节点. 真实节点越少, 需要的虚拟节点越多.<br>
 * 选取合适的hashFunc也很重要
 *
 * @author yangchuan02
 * @date 2019年1月4日
 */
public class ConsistentHashing {

	private List<String> realNodes;

	// 直接使用SortedMap, key为Integer来构建2**32的哈希环
	private SortedMap<Integer, String> hashCircle;

	private int virtualNodesNum = 5;

	private Function<String, Integer> hashFunc;

	private final String seperator = "&&VN";

	public ConsistentHashing(List<String> realNodes, Function<String, Integer> hashFunc) {
		this(realNodes, 1, hashFunc);
	}

	public ConsistentHashing(List<String> realNodes, int virtualNodesNum,
			Function<String, Integer> hashFunc) {
		if (virtualNodesNum <= 0) {
			virtualNodesNum = 1;
		}

		this.virtualNodesNum = virtualNodesNum;
		this.hashFunc = Objects.requireNonNull(hashFunc);

		setRealNodes(realNodes);
	}

	public List<String> getRealNodes() {
		return Collections.unmodifiableList(realNodes);
	}

	public String getRealNode(String data) {
		int hash = hashFunc.apply(data);
		SortedMap<Integer, String> tailMap = hashCircle.tailMap(hash);
		Integer key;
		// 如果环上没有节点比hash大, 则取所有节点的第一个节点
		if (tailMap.isEmpty()) {
			key = hashCircle.firstKey();
		} else {
			key = tailMap.firstKey();
		}

		String node = hashCircle.get(key);
		return node.substring(0, node.indexOf(seperator));
	}

	public SortedMap<Integer, String> getVirtualNodes() {
		return Collections.unmodifiableSortedMap(hashCircle);
	}

	public void setRealNodes(List<String> realNodes) {
		this.realNodes = Objects.requireNonNull(realNodes);
		this.hashCircle = createHashCircle();
	}

	private String[] flatMapRealNodeToVirtualNodes(String realNode) {
		return IntStream.range(0, virtualNodesNum)
				.mapToObj(i -> realNode + seperator + i)
				.toArray(String[]::new);
	}
	
	private void putVirtualNodes(SortedMap<Integer, String> map, String[] arr) {
		for (String vn : arr) {
			map.put(hashFunc.apply(vn), vn);
		}
	}
	
	private SortedMap<Integer, String> createHashCircle() {
		SortedMap<Integer, String> map = realNodes.stream()
				.map(this::flatMapRealNodeToVirtualNodes)
				.collect(TreeMap<Integer, String>::new, this::putVirtualNodes, TreeMap::putAll);
		return map;
	}
	
}
