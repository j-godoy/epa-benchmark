package com.example.sftpconnection;

public class MockConnListener implements ConnectionListener {

	@Override
	public void updateRemoteDirectory(MockBasicConnection con) {
	}

	@Override
	public void updateProgress(String file, String type, long bytes) {
	}

	@Override
	public void connectionInitialized(MockBasicConnection con) {
	}

	@Override
	public void connectionFailed(MockBasicConnection con, String why) {
	}

	@Override
	public void actionFinished(MockBasicConnection con) {
	}

}
