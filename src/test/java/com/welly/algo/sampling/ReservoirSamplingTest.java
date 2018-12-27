package com.welly.algo.sampling;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author yangchuan02
 * @date 2018年12月27日
 */
public class ReservoirSamplingTest {

	private static final int size = 100000;

	private static final int n = 10;

	private static final Integer[] sample = new Integer[size];

	private static final ReservoirSampling<Integer> s = new ReservoirSampling<Integer>() {

		@Override
		public Class<Integer> getTClass() {
			return Integer.class;
		}

	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for (int i = 0; i < sample.length; i++) {
			sample[i] = i;
		}
	}

	@Test
	public void testSamplingTArrayInt() {
		System.out.println(Arrays.toString(s.sampling(sample, n)));
	}

	@Test
	public void testSamplingCollectionOfTInt() {
		System.out.println(Arrays.toString(s.sampling(Arrays.asList(sample), n)));
	}

}
