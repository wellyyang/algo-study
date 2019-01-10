package com.welly.algo.search;

/**
 * <pre>
 * 二分查找适用场景:
 * 1. 数据有序, 这里实现中假定数据升序
 * 2. 数据支持随机访问, 即数组或者实现了{@linkplain java.util.RandomAccess}的集合
 * 3. 数据量适量时二分查找更有优势, 小数据量时优势不明显, 过大数据量时可能会因为无法分配连续内存而导致查找变慢
 * 4. 数据之间的比较耗时较长时二分查找也有优势
 *
 * 由于需要数据有序, 在大多场合下需要先对数据进行排序, 所以二分查找更适合一次排序多次查找的场景
 *
 * 时间复杂度 O(logn)
 * </pre>
 *
 * <pre>
 * 数据无重复的简单二分查找, 在实际应用中多被散列表或二叉树取代
 *
 * 允许有重复值的近似查找, 是实际中使用更多的场景, 且无法被散列表和二叉树取代:
 * 查找第一个值等于给定值的元素
 * 查找最后一个值等于给定值的元素
 * 查找第一个大于等于给定值的元素
 * 查找最后一个小于等于给定值的元素
 * </pre>
 *
 * @author yangchuan02
 * @date 2019年1月10日
 */
public class BinarySearch {

	public static int firstEqual(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (arr[mid] == value) {
				if (mid == 0 || arr[mid - 1] != value) {
					return mid;
				} else {
					high = mid - 1;
				}
			} else if (arr[mid] < value) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}

	public static int lastEqual(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (arr[mid] == value) {
				if (mid == arr.length - 1 || arr[mid + 1] != value) {
					return mid;
				} else {
					low = mid + 1;
				}
			} else if (arr[mid] < value) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}

	public static int firstGte(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (arr[mid] >= value) {
				if (mid == 0 || arr[mid - 1] < value) {
					return mid;
				} else {
					high = mid - 1;
				}
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	public static int lastLte(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;
		
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (arr[mid] <= value) {
				if (mid == arr.length - 1 || arr[mid + 1] > value) {
					return mid;
				} else {
					low = mid + 1;
				}
			} else {
				high = mid - 1;
			}
		}
		
		return -1;
	}

	/**
	 * 最简单的使用循环的二分查找, 数据无重复
	 *
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int simple(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			// int mid = (low + high) / 2;
			// 避免low + high溢出
			int mid = low + ((high - low) >> 1);
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] < value) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}

	/**
	 * 最简单的使用递归的二分查找, 数据无重复
	 *
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int simple2(int[] arr, int value) {
		return recursiveSimple(arr, value, 0, arr.length - 1);
	}

	private static int recursiveSimple(int[] arr, int value, int low, int high) {
		if (low > high) {
			return -1;
		}

		int mid = low + ((high - low) >> 1);
		if (arr[mid] == value) {
			return mid;
		}

		if (arr[mid] < value) {
			return recursiveSimple(arr, value, mid + 1, high);
		} else {
			return recursiveSimple(arr, value, low, mid - 1);
		}
	}

	// leetcode 33
	public static int search(int[] nums, int target) {
		if (nums == null) {
			return -1;
		}

		int low = 0;
		int high = nums.length - 1;

		while (low <= high) {
			int mid = low + ((high - low) >> 1);

			if (nums[mid] == target) {
				return mid;
			}


			if (nums[mid] < target) {
				if (mid == high || (nums[high] < target && nums[high] > nums[mid])) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (mid == low || (nums[low] > target && nums[low] < nums[mid])) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}

		return -1;
	}

}
