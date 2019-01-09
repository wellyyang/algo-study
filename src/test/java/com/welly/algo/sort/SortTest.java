package com.welly.algo.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import com.welly.algo.shuffle.FisherYatesShuffle;

/**
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class SortTest {

	@Test
	public void test() {
		List<IIntSort> list
				= Arrays.asList(new SelectionSort(), new InsertionSort(), new BubbleSort(), new ShellSort(),
						new MergeSort(), new QuickSort(), new RadixSort());

		list.forEach(s -> {
			int[] arr = IntStream.rangeClosed(1, 20).toArray();
			arr = new FisherYatesShuffle().shuffle(arr);
			System.out.println("-------------  " + s.getClass().getSimpleName() + "  --------------");
			System.out.println(Arrays.toString(arr));
			System.out.println(Arrays.toString(s.sort(arr)));
		});
	}

	@Test
	public void bucketSortTest() {
		int[] arr = IntStream.rangeClosed(1, 20).toArray();
		arr = new FisherYatesShuffle().shuffle(arr);
		System.out.println("-------------  BucketSort  --------------");
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(new BucketSort().sort(arr, 1, 20, 5)));
	}

	@Test
	public void countSortTest() {
		int[] arr2 = new int[] { -1, 1, -1, 1, 1, 3, 3, 3, 4, 5, 5, 6, 3, 2, 1, 2, 3, 4, 5, 2, 2, 1, 3, 4, 3, 1, 2 };
		System.out.println("-------------  CountSort  --------------");
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(new CountSort().sort(arr2)));
	}

}
