package com.welly.algo.shuffle;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author yangchuan02
 * @date 2018年12月27日
 */
public class FisherYatesShuffleTest {

	private static final int count = 10;

	private static FisherYatesShuffle<Integer> s = new FisherYatesShuffle<>();

	private static Integer[] arr;

	private static int[][] stat = new int[count][count];

	@BeforeClass
	public static void setUp() {
		for (int i = 0; i < stat.length; i++) {
			stat[i] = new int[count];
			Arrays.fill(stat[i], 0);
		}
	}

	@Test
	public void testShuffle() {
		for (int i = 0; i < 10000000; i++) {
			arr = IntStream.range(0, count).mapToObj(Integer::valueOf).toArray(Integer[]::new);
			Integer[] shuffled = s.shuffle(arr);

			for (int j = 0; j < shuffled.length; j++) {
				stat[j][shuffled[j]] += 1;
			}
		}

		for (int i = 0; i < stat.length; i++) {
			System.out.println(Arrays.toString(stat[i]));
		}
	}

}
