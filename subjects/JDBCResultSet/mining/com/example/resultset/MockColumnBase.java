package com.example.resultset;

public class MockColumnBase {

	public MockColumnBase(String schema, String table, String name, boolean isWriteable) {
		super();
		this.schema = schema;
		this.table = table;
		this.name = name;
		this.isWriteable = isWriteable;
	}

	private final String name;
	private final String table;
	private final String schema;
	private final boolean isWriteable;

	public String getNameString() {
        return name;
	}

	public String getTableNameString() {
        return table;
	}

	public String getSchemaNameString() {
        return schema;
	}

	public boolean isWriteable() {
        return isWriteable;
	}

}
