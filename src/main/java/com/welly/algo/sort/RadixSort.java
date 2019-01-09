package com.welly.algo.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 基数排序, 类似于直觉的对数字进行排序的方式, 需要数据可以切割成位进行比较
 *
 * @author yangchuan02
 * @date 2019年1月9日
 */
public class RadixSort extends AbstractIntSort {

	@Override
	protected int[] sort0(int[] arr) {
        // Find the maximum number to know number of digits
        int m = IntStream.of(arr).max().getAsInt();

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10) {
			countSort(arr, arr.length, exp);
        }

		return arr;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp. (eg. 300 is represented by 100)
	private void countSort(int arr[], int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	}

}
