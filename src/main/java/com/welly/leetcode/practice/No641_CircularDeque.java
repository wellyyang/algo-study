package com.welly.leetcode.practice;

/**
 * @author yangchuan02
 * @date 2019年5月17日
 */
public class No641_CircularDeque {

	private static class MyCircularDeque {

		private int[] arr;

		private int count = 0;

		private int headIndex = 0;

		/** Initialize your data structure here. Set the size of the deque to be k. */
	    public MyCircularDeque(int k) {
			arr = new int[k];
	    }

		/** Adds an item at the front of Deque. Return true if the operation is successful. */
		public boolean insertFront(int value) {
			if (isFull()) {
				return false;
			}

			int index = (headIndex + arr.length - 1) % arr.length;
			arr[index] = value;
			count++;
			headIndex = index;
			return true;
		}

		/** Adds an item at the rear of Deque. Return true if the operation is successful. */
		public boolean insertLast(int value) {
			if (isFull()) {
				return false;
			}

			int index = (headIndex + count) % arr.length;
			arr[index] = value;
			count++;
			return true;
		}

		/** Deletes an item from the front of Deque. Return true if the operation is successful. */
		public boolean deleteFront() {
			if (isEmpty()) {
				return false;
			}

			headIndex = (headIndex + 1) % arr.length;
			count--;
			return true;
		}

		/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
		public boolean deleteLast() {
			if (isEmpty()) {
				return false;
			}

			count--;
			return true;
		}

		/** Get the front item from the deque. */
		public int getFront() {
			if (isEmpty()) {
				return -1;
			}

			return arr[headIndex];
		}

		/** Get the last item from the deque. */
		public int getRear() {
			if (isEmpty()) {
				return -1;
			}

			return arr[(headIndex + count - 1) % arr.length];
		}

		/** Checks whether the circular deque is empty or not. */
		public boolean isEmpty() {
			return count == 0;
		}

		/** Checks whether the circular deque is full or not. */
		public boolean isFull() {
			return count == arr.length;
		}

	}

	public static void main(String[] args) {
		MyCircularDeque circularDeque = new MyCircularDeque(3); // set the size to be 3
		System.out.println(circularDeque.insertLast(1)); // return true
		System.out.println(circularDeque.insertLast(2)); // return true
		System.out.println(circularDeque.insertFront(3)); // return true
		System.out.println(circularDeque.insertFront(4)); // return false, the queue is full
		System.out.println(circularDeque.getRear()); // return 2
		System.out.println(circularDeque.isFull()); // return true
		System.out.println(circularDeque.deleteLast()); // return true
		System.out.println(circularDeque.insertFront(4)); // return true
		System.out.println(circularDeque.getFront()); // return 4
	}

}
