package com.example.myboundedstack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

public class MyBoundedStack {

	private final static int DEFAULT_SIZE = 10;

	private final Object[] elements = new Object[DEFAULT_SIZE];

	private int index = -1;

	@EpaAction(name = "MyBoundedStack()")
	public MyBoundedStack() {
	}

	@EpaAction(name = "push()")
	public void push(Object object) {
		if (index == elements.length - 1) {
			throw new IllegalStateException();
		}
		elements[++index] = object;
	}

	@EpaAction(name = "pop()")
	public Object pop() {
		if (index == -1) {
			throw new IllegalStateException();
		}
		Object ret_val = elements[index--];
		return ret_val;
	}
	
	@EpaAction(name = "isFull()")
	public boolean isFull() {
		return index == elements.length - 1;
	}
	
	@EpaAction(name = "isEmpty()")
	public boolean isEmpty() {
		return index == -1;
	}

	// ======================================================
	// Boolean Queries
	// ======================================================
	@EpaActionPrecondition(name = "push()")
	private boolean isPushEnabled() {
		return !isFull();
	}

	@EpaActionPrecondition(name = "pop()")
	private boolean isPopEnabled() {
		return !isEmpty();
	}
	
	@EpaActionPrecondition(name = "isFull()")
	private boolean isFullEnabled() {
		return true;
	}
	
	@EpaActionPrecondition(name = "isEmpty()")
	private boolean isEmptyEnabled() {
		return true;
	}


}