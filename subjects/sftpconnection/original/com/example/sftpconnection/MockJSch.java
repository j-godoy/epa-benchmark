/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/*
Copyright (c) 2002-2016 ymnk, JCraft,Inc. All rights reserved.
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
  1. Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.
  2. Redistributions in binary form must reproduce the above copyright 
     notice, this list of conditions and the following disclaimer in 
     the documentation and/or other materials provided with the distribution.
  3. The names of the authors may not be used to endorse or promote products
     derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JCRAFT,
INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.example.sftpconnection;

public class MockJSch {
	/**
	 * The version number.
	 */
	public static final String VERSION = "0.1.54";

	@SuppressWarnings("rawtypes")
	java.util.Hashtable config = new java.util.Hashtable();

	@SuppressWarnings("rawtypes")
	private java.util.Vector sessionPool = new java.util.Vector();

	// private IdentityRepository defaultIdentityRepository = new
	// LocalIdentityRepository(this);
	//
	// private IdentityRepository identityRepository = defaultIdentityRepository;
	//
	// private ConfigRepository configRepository = null;

	/**
	 * Sets the <code>identityRepository</code>, which will be referred in the
	 * public key authentication.
	 *
	 * @param identityRepository
	 *            if <code>null</code> is given, the default repository, which
	 *            usually refers to ~/.ssh/, will be used.
	 *
	 * @see #getIdentityRepository()
	 */
	// public synchronized void setIdentityRepository(IdentityRepository
	// identityRepository) {
	// if (identityRepository == null) {
	// this.identityRepository = defaultIdentityRepository;
	// } else {
	// this.identityRepository = identityRepository;
	// }
	// }
	//
	// public synchronized IdentityRepository getIdentityRepository() {
	// return this.identityRepository;
	// }
	//
	// public ConfigRepository getConfigRepository() {
	// return this.configRepository;
	// }
	//
	// public void setConfigRepository(ConfigRepository configRepository) {
	// this.configRepository = configRepository;
	// }
	//
	// private HostKeyRepository known_hosts = null;

	private static final MockLogger DEVNULL = new MockLogger() {
		public boolean isEnabled(int level) {
			return false;
		}

		public void log(int level, String message) {
		}
	};
	static MockLogger logger = DEVNULL;

	public MockJSch() {
		/*
		 * // The JCE of Sun's Java5 on Mac OS X has the resource leak bug // in
		 * calculating HMAC, so we need to use our own implementations. try{ String
		 * osname=(String)(System.getProperties().get("os.name")); if(osname!=null &&
		 * osname.equals("Mac OS X")){ config.put("hmac-sha1",
		 * "com.jcraft.jsch.jcraft.HMACSHA1"); config.put("hmac-md5",
		 * "com.jcraft.jsch.jcraft.HMACMD5"); config.put("hmac-md5-96",
		 * "com.jcraft.jsch.jcraft.HMACMD596"); config.put("hmac-sha1-96",
		 * "com.jcraft.jsch.jcraft.HMACSHA196"); } } catch(Exception e){ }
		 */
	}

	/**
	 * Instantiates the <code>Session</code> object with <code>host</code>. The user
	 * name and port number will be retrieved from ConfigRepository. If user name is
	 * not given, the system property "user.name" will be referred.
	 *
	 * @param host
	 *            hostname
	 *
	 * @throws MockJSchException
	 *             if <code>username</code> or <code>host</code> are invalid.
	 *
	 * @return the instance of <code>Session</code> class.
	 *
	 * @see #getSession(String username, String host, int port)
	 * @see com.MockSession.jsch.Session
	 * @see com.jcraft.jsch.ConfigRepository
	 */
//	public MockSession getSession(String host) throws MockJSchException {
//		return getSession(null, host, 22);
//	}

	/**
	 * Instantiates the <code>Session</code> object with <code>username</code> and
	 * <code>host</code>. The TCP port 22 will be used in making the connection.
	 * Note that the TCP connection must not be established until Session#connect().
	 *
	 * @param username
	 *            user name
	 * @param host
	 *            hostname
	 *
	 * @throws MockJSchException
	 *             if <code>username</code> or <code>host</code> are invalid.
	 *
	 * @return the instance of <code>Session</code> class.
	 *
	 * @see #getSession(String username, String host, int port)
	 * @see com.MockSession.jsch.Session
	 */
//	public MockSession getSession(String username, String host) throws MockJSchException {
//		return getSession(username, host, 22);
//	}

	/**
	 * Instantiates the <code>Session</code> object with given
	 * <code>username</code>, <code>host</code> and <code>port</code>. Note that the
	 * TCP connection must not be established until Session#connect().
	 *
	 * @param username
	 *            user name
	 * @param host
	 *            hostname
	 * @param port
	 *            port number
	 *
	 * @throws MockJSchException
	 *             if <code>username</code> or <code>host</code> are invalid.
	 *
	 * @return the instance of <code>Session</code> class.
	 *
	 * @see #getSession(String username, String host, int port)
	 * @see com.MockSession.jsch.Session
	 */
	public MockSession getSession(String username, String host, int port) throws MockJSchException {
		if (host == null) {
			throw new MockJSchException("host must not be null.");
		}
		MockSession s = new MockSession(this, username, host, port);
		return s;
	}

	@SuppressWarnings("unchecked")
	protected void addSession(MockSession session) {
		synchronized (sessionPool) {
			sessionPool.addElement(session);
		}
	}

	protected boolean removeSession(MockSession session) {
		synchronized (sessionPool) {
			return sessionPool.remove(session);
		}
	}

	/**
	 * Sets the hostkey repository.
	 *
	 * @param hkrepo
	 *
	 * @see com.jcraft.jsch.HostKeyRepository
	 * @see com.jcraft.jsch.KnownHosts
	 */
	// public void setHostKeyRepository(HostKeyRepository hkrepo){
	// known_hosts=hkrepo;
	// }

	/**
	 * Sets the instance of <code>KnownHosts</code>, which refers to
	 * <code>filename</code>.
	 *
	 * @param filename
	 *            filename of known_hosts file.
	 *
	 * @throws MockJSchException
	 *             if the given filename is invalid.
	 *
	 * @see com.jcraft.jsch.KnownHosts
	 */
	// public void setKnownHosts(String filename) throws MockJSchException {
	// if (known_hosts == null)
	// known_hosts = new KnownHosts(this);
	// if (known_hosts instanceof KnownHosts) {
	// synchronized (known_hosts) {
	// ((KnownHosts) known_hosts).setKnownHosts(filename);
	// }
	// }
	// }
	//
	// /**
	// * Sets the instance of <code>KnownHosts</code> generated with
	// * <code>stream</code>.
	// *
	// * @param stream
	// * the instance of InputStream from known_hosts file.
	// *
	// * @throws MockJSchException
	// * if an I/O error occurs.
	// *
	// * @see com.jcraft.jsch.KnownHosts
	// */
	// public void setKnownHosts(InputStream stream) throws MockJSchException {
	// if (known_hosts == null)
	// known_hosts = new KnownHosts(this);
	// if (known_hosts instanceof KnownHosts) {
	// synchronized (known_hosts) {
	// ((KnownHosts) known_hosts).setKnownHosts(stream);
	// }
	// }
	// }

	/**
	 * Returns the current hostkey repository. By the default, this method will the
	 * instance of <code>KnownHosts</code>.
	 *
	 * @return current hostkey repository.
	 *
	 * @see com.jcraft.jsch.HostKeyRepository
	 * @see com.jcraft.jsch.KnownHosts
	 */
	// public HostKeyRepository getHostKeyRepository() {
	// if (known_hosts == null)
	// known_hosts = new KnownHosts(this);
	// return known_hosts;
	// }

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication.
	 *
	 * @param prvkey
	 *            filename of the private key.
	 *
	 * @throws MockJSchException
	 *             if <code>prvkey</code> is invalid.
	 *
	 * @see #addIdentity(String prvkey, String passphrase)
	 */
	public void addIdentity(String prvkey) throws MockJSchException {
		addIdentity(prvkey, null);
	}

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication. Before registering it into identityRepository, it will be
	 * deciphered with <code>passphrase</code>.
	 *
	 * @param prvkey
	 *            filename of the private key.
	 * @param passphrase
	 *            passphrase for <code>prvkey</code>.
	 *
	 * @throws MockJSchException
	 *             if <code>passphrase</code> is not right.
	 *
	 * @see #addIdentity(String prvkey, byte[] passphrase)
	 */
	public void addIdentity(String prvkey, String passphrase) throws MockJSchException {
		byte[] _passphrase = null;
		if (passphrase != null) {
			_passphrase = MockUtil.str2byte(passphrase);
		}
		// addIdentity(prvkey, _passphrase);
		if (_passphrase != null)
			MockUtil.bzero(_passphrase);
	}

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication. Before registering it into identityRepository, it will be
	 * deciphered with <code>passphrase</code>.
	 *
	 * @param prvkey
	 *            filename of the private key.
	 * @param passphrase
	 *            passphrase for <code>prvkey</code>.
	 *
	 * @throws MockJSchException
	 *             if <code>passphrase</code> is not right.
	 *
	 * @see #addIdentity(String prvkey, String pubkey, byte[] passphrase)
	 */
	// public void addIdentity(String prvkey, byte[] passphrase) throws
	// MockJSchException{
	// Identity identity=IdentityFile.newInstance(prvkey, null, this);
	// addIdentity(identity, passphrase);
	// }

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication. Before registering it into identityRepository, it will be
	 * deciphered with <code>passphrase</code>.
	 *
	 * @param prvkey
	 *            filename of the private key.
	 * @param pubkey
	 *            filename of the public key.
	 * @param passphrase
	 *            passphrase for <code>prvkey</code>.
	 *
	 * @throws MockJSchException
	 *             if <code>passphrase</code> is not right.
	 */
	// public void addIdentity(String prvkey, String pubkey, byte[] passphrase)
	// throws MockJSchException{
	// Identity identity=IdentityFile.newInstance(prvkey, pubkey, this);
	// addIdentity(identity, passphrase);
	// }

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication. Before registering it into identityRepository, it will be
	 * deciphered with <code>passphrase</code>.
	 *
	 * @param name
	 *            name of the identity to be used to retrieve it in the
	 *            identityRepository.
	 * @param prvkey
	 *            private key in byte array.
	 * @param pubkey
	 *            public key in byte array.
	 * @param passphrase
	 *            passphrase for <code>prvkey</code>.
	 *
	 */
	// public void addIdentity(String name, byte[]prvkey, byte[]pubkey, byte[]
	// passphrase) throws MockJSchException{
	// Identity identity=IdentityFile.newInstance(name, prvkey, pubkey, this);
	// addIdentity(identity, passphrase);
	// }

	/**
	 * Sets the private key, which will be referred in the public key
	 * authentication. Before registering it into identityRepository, it will be
	 * deciphered with <code>passphrase</code>.
	 *
	 * @param identity
	 *            private key.
	 * @param passphrase
	 *            passphrase for <code>identity</code>.
	 *
	 * @throws MockJSchException
	 *             if <code>passphrase</code> is not right.
	 */
	// public void addIdentity(Identity identity, byte[] passphrase) throws
	// MockJSchException {
	// if (passphrase != null) {
	// try {
	// byte[] goo = new byte[passphrase.length];
	// System.arraycopy(passphrase, 0, goo, 0, passphrase.length);
	// passphrase = goo;
	// identity.setPassphrase(passphrase);
	// } finally {
	// Util.bzero(passphrase);
	// }
	// }
	//
	// if (identityRepository instanceof LocalIdentityRepository) {
	// ((LocalIdentityRepository) identityRepository).add(identity);
	// } else if (identity instanceof IdentityFile && !identity.isEncrypted()) {
	// identityRepository.add(((IdentityFile) identity).getKeyPair().forSSHAgent());
	// } else {
	// synchronized (this) {
	// if (!(identityRepository instanceof IdentityRepository.Wrapper)) {
	// setIdentityRepository(new IdentityRepository.Wrapper(identityRepository));
	// }
	// }
	// ((IdentityRepository.Wrapper) identityRepository).add(identity);
	// }
	// }
	//
	// /**
	// * @deprecated use #removeIdentity(Identity identity)
	// */
	// public void removeIdentity(String name) throws MockJSchException {
	// Vector identities = identityRepository.getIdentities();
	// for (int i = 0; i < identities.size(); i++) {
	// Identity identity = (Identity) (identities.elementAt(i));
	// if (!identity.getName().equals(name))
	// continue;
	// if (identityRepository instanceof LocalIdentityRepository) {
	// ((LocalIdentityRepository) identityRepository).remove(identity);
	// } else
	// identityRepository.remove(identity.getPublicKeyBlob());
	// }
	// }
	//
	// /**
	// * Removes the identity from identityRepository.
	// *
	// * @param identity
	// * the indentity to be removed.
	// *
	// * @throws MockJSchException
	// * if <code>identity</code> is invalid.
	// */
	// public void removeIdentity(Identity identity) throws MockJSchException {
	// identityRepository.remove(identity.getPublicKeyBlob());
	// }
	//
	// /**
	// * Lists names of identities included in the identityRepository.
	// *
	// * @return names of identities
	// *
	// * @throws MockJSchException
	// * if identityReposory has problems.
	// */
	// public Vector getIdentityNames() throws MockJSchException {
	// Vector foo = new Vector();
	// Vector identities = identityRepository.getIdentities();
	// for (int i = 0; i < identities.size(); i++) {
	// Identity identity = (Identity) (identities.elementAt(i));
	// foo.addElement(identity.getName());
	// }
	// return foo;
	// }
	//
	// /**
	// * Removes all identities from identityRepository.
	// *
	// * @throws MockJSchException
	// * if identityReposory has problems.
	// */
	// public void removeAllIdentity() throws MockJSchException {
	// identityRepository.removeAll();
	// }

//	/**
//	 * Returns the config value for the specified key.
//	 *
//	 * @param key
//	 *            key for the configuration.
//	 * @return config value
//	 */
//	public static String getConfig(String key) {
//		synchronized (config) {
//			return (String) (config.get(key));
//		}
//	}

//	/**
//	 * Sets or Overrides the configuration.
//	 *
//	 * @param newconf
//	 *            configurations
//	 */
//	@SuppressWarnings("unchecked")
//	public static void setConfig(@SuppressWarnings("rawtypes") java.util.Hashtable newconf) {
//		synchronized (config) {
//			for (@SuppressWarnings("rawtypes")
//			java.util.Enumeration e = newconf.keys(); e.hasMoreElements();) {
//				String key = (String) (e.nextElement());
//				config.put(key, (String) (newconf.get(key)));
//			}
//		}
//	}

	/**
	 * Sets or Overrides the configuration.
	 *
	 * @param key
	 *            key for the configuration
	 * @param value
	 *            value for the configuration
	 */
//	@SuppressWarnings("unchecked")
//	public static void setConfig(String key, String value) {
//		config.put(key, value);
//	}

	/**
	 * Sets the logger
	 *
	 * @param logger
	 *            logger
	 *
	 * @see com.MockLogger.jsch.Logger
	 */
//	public static void setLogger(MockLogger logger) {
//		if (logger == null)
//			logger = DEVNULL;
//		MockJSch.logger = logger;
//	}

	static MockLogger getLogger() {
		return logger;
	}
}