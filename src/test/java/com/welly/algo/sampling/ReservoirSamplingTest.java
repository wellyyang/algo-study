package com.welly.algo.sampling;

import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.matchers.EqualsWithDelta;

import com.welly.algo.DistributionStat;


/**
 * @author yangchuan02
 * @date 2018年12月27日
 */
public class ReservoirSamplingTest {

	private static final int size = 100;

	private static final int count = 10;

	private static final int loop = 100_0000;

	private static final double delta = loop * count / size * 0.01;

	private static final Integer[] sample = new Integer[size];

	private static final ReservoirSampling<Integer> s = new ReservoirSampling<Integer>() {

		@Override
		public Class<Integer> getTClass() {
			return Integer.class;
		}

	};

	private DistributionStat<Integer> stat;

	@Before
	public void setUp() {
		stat = new DistributionStat<>(count);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for (int i = 0; i < sample.length; i++) {
			sample[i] = i;
		}
	}

	@Test
	public void testSamplingTArrayInt() {
		IntStream.range(0, loop)
		.mapToObj(i -> s.sampling(sample, count))
		.forEach(arr -> {
			for (int i = 0; i < arr.length; i++) {
				// 采样不区分pos
				stat.increase(arr[i], 0);
			}
		});

		for (int value : stat.getStat().keySet()) {
			for (int pos : stat.getStat().get(value).keySet()) {
				assertThat(loop * count / size,
						new EqualsWithDelta(stat.getStat().get(value).get(pos).intValue(),
								delta));
			}
		}
	}

}
