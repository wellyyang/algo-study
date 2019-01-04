package com.welly.algo.hash;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author yangchuan02
 * @date 2019年1月4日
 */
public class ConsistentHashingTest {

	private static final List<String> realNodes = Arrays.asList("1", "2", "3");

	public static ConsistentHashing hashing;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hashing = new ConsistentHashing(realNodes, 5, Hashing::murmurHash32);
	}

	@Test
	public void testGetRealNode() {
		IntStream.range(0, 100)
				.forEach(i -> assertTrue(realNodes.contains(hashing.getRealNode(String.valueOf(i)))));

		List<String> realNodes2 = IntStream.range(100, 200)
				.mapToObj(String::valueOf)
				.collect(Collectors.toList());

		hashing.setRealNodes(realNodes2);
		IntStream.range(0, 100)
				.forEach(i -> assertTrue(realNodes2.contains(hashing.getRealNode(String.valueOf(i)))));
	}

}
