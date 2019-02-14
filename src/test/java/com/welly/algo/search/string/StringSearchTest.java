package com.welly.algo.search.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


/**
 * @author yangchuan02
 * @date 2019年2月13日
 */
public class StringSearchTest {

	@Test
	public void test() {
		List<ISearch> list = Arrays.asList(new BF(), new RK(), new RK2(),
				new BM(), new KMP());

		String search = "1234564564567890";
		String match = "456456";
		int matchIndex = 3;
		String notMatch = "654";

		list.forEach(s -> {
			assertEquals(matchIndex, s.indexOf(search, match));
			assertEquals(-1, s.indexOf(search, notMatch));
			System.out.println(s.getClass().getSimpleName() + " passed...");
		});
	}

}
