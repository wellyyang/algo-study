package com.welly.algo.search.string;

import java.util.List;
import java.util.TreeMap;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

/**
 * <pre>
 * AC自动机, 多模式匹配算法, 只需要扫描一遍主串, 即可一次性查找多个模式串. 需要对多模式串进行预处理. 适用场景, 敏感词过滤
 *
 * 预处理的时间复杂度O(m*len), m为敏感词数量, len为敏感词平均长度
 * 预处理后Trie的节点个数为k, 高度不超过len, 失败指针的构建过程为O(k*len)
 * 匹配的时间复杂度为O(n*len), 通常来说len不会很大, 可以近似于O(n)
 *
 *
 * 具体实现可以参考 https://github.com/hankcs/AhoCorasickDoubleArrayTrie
 * &lt;dependency&gt;
 *     &lt;groupId&gt;com.hankcs&lt;/groupId&gt;
 *     &lt;artifactId&gt;aho-corasick-double-array-trie&lt;/artifactId&gt;
 * &lt;/dependency>
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年2月19日
 */
public class ACTrie {

	public static void main(String[] args) {
		// Collect test data set
		TreeMap<String, String> map = new TreeMap<>();
		String[] keyArray = new String[] {
				"hers",
				"his",
				"she",
				"he"
		};
		for (String key : keyArray) {
			map.put(key, key);
		}
		// Build an AhoCorasickDoubleArrayTrie
		AhoCorasickDoubleArrayTrie<String> acdat = new AhoCorasickDoubleArrayTrie<>();
		acdat.build(map);
		// Test it
		final String text = "uhers";
		List<AhoCorasickDoubleArrayTrie.Hit<String>> wordList = acdat.parseText(text);
		System.out.println(wordList);

		System.out.println(acdat.matches(text));
	}

}
