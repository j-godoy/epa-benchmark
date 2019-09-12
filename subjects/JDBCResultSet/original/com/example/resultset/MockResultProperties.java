package com.example.resultset;


public class MockResultProperties {

	public static int getJDBCScrollability(int props) {
		return isScrollable(props) ? MockResultConstants.TYPE_SCROLL_INSENSITIVE : MockResultConstants.TYPE_FORWARD_ONLY;
	}

	private static final int idx_holdable = 1;

	private static final int idx_scrollable = 2;

	private static final int idx_updatable = 3;

	private static boolean isReadOnly(int props) {
		return (props & (1 << idx_updatable)) == 0 ? true : false;
	}


	private static boolean isHoldable(int props) {
		return (props & (1 << idx_holdable)) == 0 ? false : true;
	}

	public static int getJDBCConcurrency(int props) {
		return isReadOnly(props) ? MockResultConstants.CONCUR_READ_ONLY : MockResultConstants.CONCUR_UPDATABLE;
	}

	public static int getJDBCHoldability(int props) {
		return isHoldable(props) ? MockResultConstants.HOLD_CURSORS_OVER_COMMIT : MockResultConstants.CLOSE_CURSORS_AT_COMMIT;
	}

	public static boolean isScrollable(int rsProperties) {
		return (rsProperties & (1 << idx_scrollable)) == 0 ? false : true;
	}

	public static boolean isUpdatable(int rsProperties) {
		return (rsProperties & (1 << idx_updatable)) == 0 ? false : true;
	}

	public static int addScrollable(int props, boolean flag) {
		return flag ? props | ((1) << idx_scrollable) : props & (~(1 << idx_scrollable));
	}
	
	public static int addUpdatable(int props, boolean flag) {
		return flag ? props | ((1) << idx_updatable) : props & (~(1 << idx_updatable));
	}

}
