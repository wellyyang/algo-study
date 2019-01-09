package com.welly.algo.shuffle;

import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.EqualsWithDelta;

import com.welly.algo.DistributionStat;

/**
 * @author yangchuan02
 * @date 2018年12月27日
 */
public class FisherYatesShuffleTest {

	private static final int count = 10;

	private static final int loop = 1000_0000;

	private static final double delta = loop / count * 0.005;

	private static FisherYatesShuffle s = new FisherYatesShuffle();

	private static int[] arr;

	private DistributionStat<Integer> stat;

	@Before
	public void setUp() {
		stat = new DistributionStat<>(count);
	}

	@Test
	public void testShuffle() {

		for (int i = 0; i < loop; i++) {
			arr = IntStream.range(0, count).toArray();
			int[] shuffled = s.shuffle(arr);

			for (int j = 0; j < shuffled.length; j++) {
				stat.increase(shuffled[j], j);
			}
		}

		for (int value : stat.getStat().keySet()) {
			for (int pos : stat.getStat().get(value).keySet()) {
				assertThat(loop / count,
						new EqualsWithDelta(stat.getStat().get(value).get(pos).doubleValue(), delta));
			}
		}
	}

}
