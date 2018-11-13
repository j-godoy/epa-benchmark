package com.example.sftpconnection;

public class MockUserAuth {
//	protected static final int SSH_MSG_USERAUTH_REQUEST = 50;
//	protected static final int SSH_MSG_USERAUTH_FAILURE = 51;
//	protected static final int SSH_MSG_USERAUTH_SUCCESS = 52;
//	protected static final int SSH_MSG_USERAUTH_BANNER = 53;
//	protected static final int SSH_MSG_USERAUTH_INFO_REQUEST = 60;
//	protected static final int SSH_MSG_USERAUTH_INFO_RESPONSE = 61;
//	protected static final int SSH_MSG_USERAUTH_PK_OK = 60;

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