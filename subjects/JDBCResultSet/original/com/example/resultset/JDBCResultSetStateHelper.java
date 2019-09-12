package com.example.resultset;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JDBCResultSetStateHelper {

	public enum State {
		INIT_STATE, BEFORE_FIRST_AFTER_LAST_STATE, ON_INSERT_ROW_STATE, ROW_INSERTED_STATE, SCROLLING_STATE, WAS_NULL_ENABLED_STATE, UPDATE_ROW_ENABLED_STATE, WAS_NULL_UPDATE_ROW_ENABLED_STATE, CLOSED_STATE, ERROR_STATE;
	}

	private static boolean isAfterLast(JDBCResultSet resultSet) {
		String method_name = "internal_isAfterLast";
		boolean rv = invokeBooleanMethod(resultSet, method_name);
		return rv;
	}

	private static boolean invokeBooleanMethod(JDBCResultSet resultSet, String method_name) {
		try {
			Method m;
			m = JDBCResultSet.class.getDeclaredMethod(method_name);
			boolean isAccessible = m.isAccessible();
			m.setAccessible(true);
			Object ret_val = m.invoke(resultSet);
			m.setAccessible(isAccessible);
			if (!(ret_val instanceof Boolean)) {
				throw new ClassCastException("Expecting Boolean but returned " + ret_val.getClass().getName());
			}
			boolean boolean_value = (Boolean) ret_val;
			return boolean_value;
		} catch (NoSuchMethodException ex) {
			throw new RuntimeException("Unexpected exception " + ex.getClass().getName(), ex);
		} catch (SecurityException ex) {
			throw new RuntimeException("Unexpected exception " + ex.getClass().getName(), ex);

		} catch (IllegalAccessException ex) {
			throw new RuntimeException("Unexpected exception " + ex.getClass().getName(), ex);

		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("Unexpected exception " + ex.getClass().getName(), ex);

		} catch (InvocationTargetException ex) {
			throw new RuntimeException("Unexpected exception " + ex.getClass().getName(), ex);
		}
	}

	private static boolean isBeforeFirst(JDBCResultSet resultSet) {
		String method_name = "internal_isBeforeFirst";
		boolean rv = invokeBooleanMethod(resultSet, method_name);
		return rv;
	}

	private State current_state = State.INIT_STATE;

	public State getCurrentState() {
		return current_state;
	}

	private final JDBCResultSet resultSet;

	protected JDBCResultSetStateHelper(JDBCResultSet resultSet) {
		this.resultSet = resultSet;
		this.processNewInstance();
	}

	public void processAbsolute() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			if (isAfterLast(resultSet) || isBeforeFirst(resultSet)) {
				newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			} else {
				newState = State.SCROLLING_STATE;
			}
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processAfterLast() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			break;
		}
		}
		setNewState(newState);
	}

	public void processBeforeFirst() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			break;
		}
		}
		setNewState(newState);
	}

	public void processCancelRowUpdates() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case UPDATE_ROW_ENABLED_STATE: {
			newState = State.SCROLLING_STATE;
			break;
		}
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = State.WAS_NULL_ENABLED_STATE;
			break;
		}
		default: {
			newState = State.ERROR_STATE;

		}
		}
		setNewState(newState);
	}

	public void processClearWarnings() {
		this.processSimpleAction();
	}

	/**
	 * Sets the new state for the ResultSet
	 * 
	 * @param newState
	 *            the new state in which the ResultSet instance is
	 */
	private void setNewState(State newState) {
		this.current_state = newState;
	}

	public void processClose() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState = State.CLOSED_STATE;
		setNewState(newState);
	}

	public void processFirst() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.SCROLLING_STATE;
			break;
		}
		}
		setNewState(newState);
	}

	public void processGetConcurrency() {
		processSimpleAction();
	}

	public void processGetCursorName() {
		processSimpleAction();
	}

	public void processGetFetchDirection() {
		processSimpleAction();
	}

	public void processGetFetchSize() {
		processSimpleAction();
	}

	public void processGetHoldability() {
		processSimpleAction();
	}

	public void processGetInt() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case SCROLLING_STATE:
		case WAS_NULL_ENABLED_STATE: {
			newState = State.WAS_NULL_ENABLED_STATE;
			break;
		}
		case UPDATE_ROW_ENABLED_STATE:
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = State.WAS_NULL_UPDATE_ROW_ENABLED_STATE;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processGetMetaData() {
		processSimpleAction();
	}

	public void processGetRow() {
		processSimpleAction();
	}

	public void processGetStatement() {
		processSimpleAction();
	}

	public void processGetType() {
		processSimpleAction();
	}

	public void processGetWarnings() {
		processSimpleAction();
	}

	public void processInsertRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE: {
			newState = State.ROW_INSERTED_STATE;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
		}
		}
		this.setNewState(newState);
	}

	public void processDeleteRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case BEFORE_FIRST_AFTER_LAST_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.SCROLLING_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processUpdateRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case UPDATE_ROW_ENABLED_STATE: {
			newState = State.SCROLLING_STATE;
			break;
		}
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = State.WAS_NULL_ENABLED_STATE;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processIsAfterLast() {
		processSimpleAction();
	}

	public void processIsBeforeFirst() {
		processSimpleAction();
	}

	public void processIsClosed() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		newState = this.current_state;
		this.setNewState(newState);
	}

	public void processIsFirst() {
		processSimpleAction();
	}

	public void processIsLast() {
		processSimpleAction();
	}

	public void processLast() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.SCROLLING_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processMoveToCurrentRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE: {
			if (isAfterLast(resultSet) || isBeforeFirst(resultSet)) {
				newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			} else {
				newState = State.SCROLLING_STATE;
			}
			break;
		}
		default: {
			newState = State.ERROR_STATE;
		}
		}
		this.setNewState(newState);
	}

	public void processMoveToInsertRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = State.ON_INSERT_ROW_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	/**
	 * 
	 */
	public void processNewInstance() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
		this.setNewState(newState);

	}

	/**
	 * 
	 */
	public void processNext() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			if (isAfterLast(resultSet)) {
				newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			} else {
				newState = State.SCROLLING_STATE;
			}
			break;
		}
		}
		this.setNewState(newState);
	}

	/**
	 * 
	 */
	public void processPrevious() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			if (isBeforeFirst(resultSet)) {
				newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			} else {
				newState = State.SCROLLING_STATE;
			}
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processRefreshRow() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case SCROLLING_STATE: {
			newState = State.SCROLLING_STATE;
			break;
		}
		case UPDATE_ROW_ENABLED_STATE: {
			newState = State.SCROLLING_STATE;
			break;
		}
		case WAS_NULL_ENABLED_STATE: {
			newState = State.WAS_NULL_ENABLED_STATE;
			break;
		}
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = State.WAS_NULL_ENABLED_STATE;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processRelative() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case ON_INSERT_ROW_STATE:
		case ROW_INSERTED_STATE:
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			if (isAfterLast(resultSet) || isBeforeFirst(resultSet)) {
				newState = State.BEFORE_FIRST_AFTER_LAST_STATE;
			} else {
				newState = State.SCROLLING_STATE;
			}
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processRowDeleted() {
		processSimpleAction();
	}

	public void processRowInserted() {
		processSimpleAction();
	}

	public void processRowUpdated() {
		processSimpleAction();
	}

	/**
	 * This behavior is common to all the actions that do not change the state and
	 * are also enabled for all the JDBCResultSet states.
	 */
	private void processSimpleAction() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case CLOSED_STATE: {
			newState = State.ERROR_STATE;
			break;
		}
		default: {
			newState = this.current_state;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processSetFetchDirection() {
		this.processSimpleAction();
	}

	public void processSetFetchSize() {
		this.processSimpleAction();
	}

	public void processUpdateInt() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (this.current_state) {
		case SCROLLING_STATE: {
			newState = State.UPDATE_ROW_ENABLED_STATE;
			break;
		}
		case WAS_NULL_ENABLED_STATE: {
			newState = State.WAS_NULL_UPDATE_ROW_ENABLED_STATE;
			break;
		}
		case ON_INSERT_ROW_STATE:
		case UPDATE_ROW_ENABLED_STATE:
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = current_state;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
		}
		}
		this.setNewState(newState);
	}

	public void processWasNull() {
		if (this.current_state == State.ERROR_STATE) {
			return;
		}
		State newState;
		switch (current_state) {
		case WAS_NULL_ENABLED_STATE:
		case WAS_NULL_UPDATE_ROW_ENABLED_STATE: {
			newState = this.current_state;
			break;
		}
		default: {
			newState = State.ERROR_STATE;
			break;
		}
		}
		this.setNewState(newState);
	}

	public void processFindColumn() {
		processSimpleAction();
	}

	/**
	 * Any exception leads to the ERROR_STATE
	 */
	public void processException() {
		State newState = State.ERROR_STATE;
		this.setNewState(newState);
	}

}
