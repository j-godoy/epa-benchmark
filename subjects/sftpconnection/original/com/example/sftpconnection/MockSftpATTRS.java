package com.example.sftpconnection;

public class MockSftpATTRS {

	public final String uid = "";

	public boolean isDir() {
		return true;
	}

	public int getSize() {
		return 1;
	}

	public static MockSftpATTRS getATTR(Buffer buf) {
		return new MockSftpATTRS();
	}

	// public void setFLAGS(int i) {
	// }
	//
	// public void setUIDGID(String uid2, int gid) {
	// }

	public int length() {
		return 0;
	}

	public void dump(Buffer buf) {
	}

}
