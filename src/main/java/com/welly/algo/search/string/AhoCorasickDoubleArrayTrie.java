package com.welly.algo.search.string;

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
public class AhoCorasickDoubleArrayTrie {

}
