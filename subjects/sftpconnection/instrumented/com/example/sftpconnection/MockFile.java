package com.example.sftpconnection;

public class MockFile {
	private String path; 

	public MockFile(String p) {
		this.path = p;
	}

	public boolean exists() {
		return true;
	}

	public String getCanonicalPath() throws MockIOException {
		return this.path;
	}

	public String[] list() {
		return new String[0];
	}

	public void mkdir() {
	}

}
