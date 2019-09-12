package com.example.resultset;


public class MockResult {

	public MockResultMetaData metaData;
	public int rsProperties;

	private String mainString = null;

	@SuppressWarnings("unused")
	private int updateCount;

	private MockRowSetNavigator navigator;

	public MockResult(String mainString, MockRowSetNavigator navigator) {
		this.mainString = mainString;
		this.navigator = navigator;
	}

	public String getMainString() {
		return mainString;
	}

	public void setActionType(int type) {
		updateCount = type;
	}

	public int getActionType() {
		return updateCount;
	}

	public MockRowSetNavigator getNavigator() {
		return navigator;
	}

}
