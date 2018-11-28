package com.example.sftpconnection;

public class MockUserAuth {
	protected MockUserInfo userinfo;
	protected MockPacket packet;
	protected Buffer buf;
	protected String username;

	public boolean start(MockSession session) throws Exception {
		this.userinfo = session.getUserInfo();
		this.packet = session.packet;
		this.buf = packet.getBuffer();
		this.username = session.getUserName();
		return true;
	}
}