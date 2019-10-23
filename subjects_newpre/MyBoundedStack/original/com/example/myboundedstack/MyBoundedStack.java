package com.example.myboundedstack;

public class MyBoundedStack {

	private final static int DEFAULT_SIZE = 10;

	private final Object[] elements = new Object[DEFAULT_SIZE];

	private int index = -1;

	public MyBoundedStack() {
	}

	public void push(Object object) {
		if (index == elements.length - 1) {
			throw new IllegalStateException();
		}
		elements[++index] = object;
	}

	public Object pop() {
		if (index == -1) {
			throw new IllegalStateException();
		}
		Object ret_val = elements[index--];

		return ret_val;
	}
	
	public boolean isFull() {
		return index == elements.length - 1;
	}
	
	public boolean isEmpty() {
		return index == -1;
	}

}
