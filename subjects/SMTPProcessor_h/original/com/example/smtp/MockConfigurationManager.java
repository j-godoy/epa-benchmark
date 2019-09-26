package com.example.smtp;

public class MockConfigurationManager {

    public static final int NEVER = 0;
	public static final int ALWAYS = 2;

	public MockConfigurationManager() {	}

    public static void shutdown() {
    }
    
    public boolean isDebugSSL() {
       return false;
    }

	public int getMaxErrorCount() {
		return 2;
	}

	public boolean isVerifyIP() {
		return false;
	}

	public boolean is8bitMIME() {
		return false;
	}

	public boolean isHELOEnabled() {
		return false;
	}

	public int getMaxPassAttempts() {
		return 2;
	}

	public int getMaximumMessageSize() {
		return 1;
	}

	public boolean isNonExistentLocalRejected() {
		return false;
	}

	public boolean isLocalDomain(String domain) {
		return true;
	}

	public Object getUser(MockEmailAddress address) {
		return address.getAddress();
	}

	public int getAddPctRCPT() {
		return 10;
	}

	public int getMaxValidRCPT() {
		return 100;
	}

	public int getMinTotFailRCPT() {
		return 20;
	}

	public int getMinPctFailRCPT() {
		return 90;
	}

	public boolean isGSSEnabled() {
		return false;
	}

	public boolean isDigestMD5Enabled() {
		return false;
	}

	public int allowClearTextSMTP() {
		return ALWAYS;
	}

	public int getSecureSMTPPort() {
		return 0;
	}

}
