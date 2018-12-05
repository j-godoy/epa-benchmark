package com.example.tohtmlstream;

public class MockCharInfo {
	
	static final char S_SPACE = 0x20;
	static final char S_LINEFEED = 0x0A;
	static final char S_CARRIAGERETURN = 0x0D;
	static final char S_HORIZONAL_TAB = 0x09;
	static final char S_LINE_SEPARATOR = 0x2028;
	public static final String HTML_ENTITIES_RESOURCE = SerializerBase.PKG_NAME+".HTMLEntities";

	public static MockCharInfo getCharInfo(String entitiesFileName, String method) {
		return new MockCharInfo();
	}


	public String getOutputStringForChar(char ch) {
		return null;
	}


	public boolean shouldMapTextChar(char ch) {
		return true;
	}


	public boolean shouldMapAttrChar(char ch) {
		return true;
	}

}
