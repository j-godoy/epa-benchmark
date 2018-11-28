/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.example.sftpconnection;

import java.util.Vector;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaState;

public class SftpConnection implements MockBasicConnection {
	public final static int smbBuffer = 32000;
	private String path = "";
	private String pwd = "/";
	@SuppressWarnings("rawtypes")
	private Vector listeners = new Vector();
	// private String[] files;
	private String[] size = new String[0];
	private int[] perms = null;
	private String user;
	private String pass;
	private String host;
	private String baseFile;
	private int fileCount;
	private boolean isDirUpload = false;
	private boolean shortProgress = false;
	private int port = 22;
	private boolean connected = false;
	private String keyfile = null;
	private MockSession session;
	private MockChannelSftp channel;

	@EpaAction(name = "sftpConnection")
	public SftpConnection(String host, String port, String keyfile) {
		this.host = host;
		this.port = Integer.parseInt(port);
		this.keyfile = keyfile;

		// Log.out("Using JSch wrapper...");
	}

	private boolean login() {
		try {
			MockJSch jsch = new MockJSch();
			if (keyfile != null) {
				jsch.addIdentity(keyfile);
			}
			session = jsch.getSession(user, host, this.port);
			MockUserInfo ui = new MockUserInfo(pass);
			session.setUserInfo(ui);
			session.connect();

			channel = (MockChannelSftp) session.openChannel("sftp");
			channel.connect();

			// Log.debug("Host: "+host+":"+port);

			pwd = channel.pwd();

			connected = true;
			return true;
		} catch (Exception ex) {
//			ex.printStackTrace();
			// Log.debug("Error: " + ex);
			return false;
		}
	}

	@EpaAction(name = "removeFileOrDir")
	public int removeFileOrDir(String file) {
		file = toSFTP(file);

		try {

			if (!file.endsWith("/")) {
				// Log.out(">>>>>>>> remove file: " + file);
				channel.rm(file);
			} else {
				// Log.out(">>>>>>>> remove dir: " + file);
				cleanSftpDir(file);
				channel.rmdir(file);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			// Log.debug("Removal failed (" + ex + ").");
//			ex.printStackTrace();

			return -1;
		}

		return 1;
	}

	private void cleanSftpDir(String dir) throws Exception {
		// Log.out(">>>>>>>> cleanSftpDir: " + dir);

		channel.ls(dir);

		// String[] tmp = new String[v.size()];
		// Enumeration e = v.elements();
		// int x = 0;
		//
		// while(e.hasMoreElements())
		// {
		// LsEntry entry = ((LsEntry)e.nextElement());
		// tmp[x] = entry.getFilename();
		//
		// //Log.out("sftp delete: " + tmp[x]);
		// if(entry.getAttrs().isDir() && !tmp[x].endsWith("/"))
		// {
		// tmp[x] = tmp[x] + "/";
		// }
		//
		// x++;
		// }

		// if(tmp == null)
		// {
		// return;
		// }
		//
		// for(int i = 0; i < tmp.length; i++)
		// {
		// if(tmp[i].equals("./") || tmp[i].equals("../"))
		// {
		// continue;
		// }
		//
		//// Log.out(">>>>>>>> remove file/dir: " + dir + tmp[i]);
		//
		// if(tmp[i].endsWith("/"))
		// {
		// cleanSftpDir(dir + tmp[i]);
		// channel.rmdir(dir + tmp[i]);
		// }
		// else
		// {
		// channel.rm(dir + tmp[i]);
		// }
		// }
	}

	@EpaAction(name = "disconnect")
	public void disconnect() {
		try {
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
//			e.printStackTrace();
			// Log.debug("Sftp2Connection.disconnect()" + e);
		}

		connected = false;
	}

	@EpaAction(name = "isConnected")
	public boolean isConnected() {
		return connected;
	}

	@EpaAction(name = "getPWD")
	public String getPWD() {
		return toSFTPDir(pwd);
	}

	@EpaAction(name = "mkdir")
	public boolean mkdir(String dirName) {
		try {
			if (!dirName.endsWith("/")) {
				dirName = dirName + "/";
			}
			dirName = toSFTP(dirName);
			channel.mkdir(dirName);
			fireDirectoryUpdate();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@EpaAction(name = "list")
	public void list() throws MockIOException {
	}

	@EpaAction(name = "chdir")
	public boolean chdir(String p) {
		return chdir(p, true);
	}

	@EpaAction(name = "chdir")
	public boolean chdir(String p, boolean refresh) {
		String tmp = toSFTP(p);

		try {
			if (!tmp.endsWith("/")) {
				tmp = tmp + "/";
			}
			if (tmp.endsWith("../")) {
				return cdup();
			}

			// System.out.println("sftp path: "+tmp+", chan: "+channel);
			channel.cd(tmp);

			pwd = tmp;

			// Log.debug("chdir: " + getPWD());
			if (refresh) {
				fireDirectoryUpdate();
			}

			// System.out.println("chdir2: " + getPWD());
			// Log.debug("Changed path to: " + tmp);
			return true;
		} catch (Exception ex) {
//			ex.printStackTrace();

			// System.out.println(tmp);
			// Log.debug("Could not change directory (" + ex + ").");

			return false;
		}
	}

	private boolean cdup() {
		String tmp = pwd;

		if (pwd.endsWith("/")) {
			tmp = pwd.substring(0, pwd.lastIndexOf("/"));
		}

		return chdir(tmp.substring(0, tmp.lastIndexOf("/") + 1));
	}

	private String getLocalPath() {
		return path;
	}

	@EpaAction(name = "setLocalPath")
	public boolean setLocalPath(String p) {
		if (StringUtils.isRelative(p)) {
			p = path + p;
		}

		p = p.replace('\\', '/');

		// System.out.println(", local 2:" + p);
		MockFile f = new MockFile(p);

		if (f.exists()) {
			try {
				path = f.getCanonicalPath();
				path = path.replace('\\', '/');

				if (!path.endsWith("/")) {
					path = path + "/";
				}

				// System.out.println("localPath: "+path);
			} catch (MockIOException ex) {
				// Log.debug("Error: can not get pathname (local)!");

				return false;
			}
		} else {
			// Log.debug("(local) No such path: \"" + p + "\"");

			return false;
		}

		return true;
	}

	@EpaAction(name = "sortLs")
	public String[] sortLs() {
		try {
			// System.out.println(pwd);
			channel.ls(pwd);

			// String[] tmp = new String[v.size()];
			// files = new String[tmp.length];
			// size = new String[tmp.length];
			// perms = new int[tmp.length];

			// Enumeration e = v.elements();
			// int x = 0;
			//
			// while(e.hasMoreElements())
			// {
			// LsEntry entry = ((LsEntry)e.nextElement());
			// tmp[x] = entry.getFilename();
			//
			// size[x] = ""+entry.getAttrs().getSize();

			// Log.debug("Perms: "+entry.getAttrs().getPermissionsString());

			/*
			 * if(!entry.getAttrs().getPermissionsString()) { perms[x] =
			 * FtpConnection.DENIED; } else {
			 */
			// perms[x] = FtpConnection.R;
			// }

			// Log.debugRaw(".");
			// if(entry.getAttrs().isDir() && !tmp[x].endsWith("/"))
			// {
			// tmp[x] = tmp[x] + "/";
			// }
			//
			// x++;
			// }

			// for(int i = 0; i < tmp.length; i++)
			// {
			// files[i] = tmp[i];
			// }

			// return files;
		} catch (Exception ex) {
//			ex.printStackTrace();
			// Log.debug(" Error while listing directory: " + ex);
			return new String[0];
		}
		return new String[0];
	}

	@EpaAction(name = "sortSize")
	public String[] sortSize() {
		return size;
	}

	@EpaAction(name = "getPermissions")
	public int[] getPermissions() {
		return perms;
	}

	@EpaAction(name = "handleUpload")
	public int handleUpload(String f) {
		if (MockSettings.getEnableSftpMultiThreading()) {

			MockSftp2Transfer t = new MockSftp2Transfer(getLocalPath(), getPWD(), f, user, pass, listeners, "UPLOAD",
					keyfile, host, "" + port);
		} else {
			upload(f);
		}

		return 0;
	}

	@EpaAction(name = "handleDownload")
	public int handleDownload(String f) {
		if (MockSettings.getEnableSftpMultiThreading()) {
			MockSftp2Transfer t = new MockSftp2Transfer(getLocalPath(), getPWD(), f, user, pass, listeners, "DOWNLOAD",
					keyfile, host, "" + port);
		} else {
			download(f);
		}

		return 0;
	}

	@EpaAction(name = "upload")
	public int upload(String f) {
		String file = toSFTP(f);

		if (file.endsWith("/")) {
			String out = StringUtils.getDir(file);
			uploadDir(file, getLocalPath() + out);
			fireActionFinished(this);
		} else {
			String outfile = StringUtils.getFile(file);

			// System.out.println("transfer: " + file + ", " + getLocalPath() + outfile);
			work(getLocalPath() + outfile, file, true);
			fireActionFinished(this);
		}

		return 0;
	}

	@EpaAction(name = "download")
	public int download(String f) {
		String file = toSFTP(f);

		if (file.endsWith("/")) {
			String out = StringUtils.getDir(file);
			downloadDir(file, getLocalPath() + out);
			fireActionFinished(this);
		} else {
			String outfile = StringUtils.getFile(file);

			// System.out.println("transfer: " + file + ", " + getLocalPath() + outfile);
			work(file, getLocalPath() + outfile, false);
			fireActionFinished(this);
		}

		return 0;
	}

	private void downloadDir(String dir, String out) {
		try {
			// System.out.println("downloadDir: " + dir + "," + out);
			fileCount = 0;
			shortProgress = true;
			baseFile = StringUtils.getDir(dir);

			channel.ls(dir);

			// String[] tmp = new String[v.size()];
			// Enumeration e = v.elements();
			// int x = 0;
			//
			// while (e.hasMoreElements()) {
			// LsEntry entry = ((LsEntry) e.nextElement());
			// tmp[x] = entry.getFilename();
			//
			// if (entry.getAttrs().isDir() && !tmp[x].endsWith("/")) {
			// tmp[x] = tmp[x] + "/";
			// }
			//
			// x++;
			// }

			MockFile fx = new MockFile(out);
			fx.mkdir();

			// for (int i = 0; i < tmp.length; i++) {
			// if (tmp[i].equals("./") || tmp[i].equals("../")) {
			// continue;
			// }
			//
			// tmp[i] = tmp[i].replace('\\', '/');
			//
			// // System.out.println("1: " + dir+tmp[i] + ", " + out +tmp[i]);
			//
			// if (tmp[i].endsWith("/")) {
			// if (!tmp[i].endsWith("/")) {
			// tmp[i] = tmp[i] + "/";
			// }
			//
			// downloadDir(dir + tmp[i], out + tmp[i]);
			// } else {
			// fileCount++;
			// fireProgressUpdate(baseFile, DataConnection.GETDIR + ":" + fileCount, -1);
			// work(dir + tmp[i], out + tmp[i], false);
			// }

			// }

			// System.out.println("enddir");

			fireProgressUpdate(baseFile, MockDataConnection.DFINISHED + ":" + fileCount, -1);
		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println(dir + ", " + out);
			// Log.debug("Transfer error: " + ex);
			fireProgressUpdate(baseFile, MockDataConnection.FAILED + ":" + fileCount, -1);
		}

		shortProgress = false;
	}

	private void uploadDir(String dir, String out) {
		try {
			// System.out.println("uploadDir: " + dir + "," + out);
			isDirUpload = true;
			fileCount = 0;
			shortProgress = true;
			baseFile = StringUtils.getDir(dir);

			MockFile f2 = new MockFile(out);
			String[] tmp = f2.list();

			if (tmp == null) {
				return;
			}

			channel.mkdir(dir);
			// channel.chmod(744, dir);

			// for (int i = 0; i < tmp.length; i++) {
			// if (tmp[i].equals("./") || tmp[i].equals("../")) {
			// continue;
			// }
			//
			// tmp[i] = tmp[i].replace('\\', '/');
			//
			// // System.out.println("1: " + dir+tmp[i] + ", " + out +tmp[i]);
			// MockFile f3 = new MockFile(out + tmp[i]);
			//
			// if (f3.isDirectory()) {
			// if (!tmp[i].endsWith("/")) {
			// tmp[i] = tmp[i] + "/";
			// }
			//
			// uploadDir(dir + tmp[i], out + tmp[i]);
			// } else {
			// fileCount++;
			// fireProgressUpdate(baseFile, DataConnection.PUTDIR + ":" + fileCount, -1);
			// work(out + tmp[i], dir + tmp[i], true);
			// }
			// }

			fireProgressUpdate(baseFile, MockDataConnection.DFINISHED + ":" + fileCount, -1);
		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println(dir + ", " + out);
			// Log.debug("Transfer error: " + ex);
			fireProgressUpdate(baseFile, MockDataConnection.FAILED + ":" + fileCount, -1);
		}

		isDirUpload = false;
		shortProgress = true;
	}

	private String toSFTP(String f) {
		String file;

		if (f.startsWith("/")) {
			file = f;
		} else {
			file = getPWD() + f;
		}

		file = file.replace('\\', '/');

		// System.out.println("file: "+file);
		return file;
	}

	private String toSFTPDir(String f) {
		String file;

		if (f.startsWith("/")) {
			file = f;
		} else {
			file = pwd + f;
		}

		file = file.replace('\\', '/');

		if (!file.endsWith("/")) {
			file = file + "/";
		}

		// System.out.println("file: "+file);
		return file;
	}

	private void work(String file, String outfile, boolean up) {
		MockBufferedInputStream in = null;
		MockBufferedOutputStream out = null;

		// System.out.println("work");

		try {
			boolean outflag = false;

			if (up) {
				in = new MockBufferedInputStream(new MockFileInputStream(file));
			} else {
				in = new MockBufferedInputStream(channel.get(file));
			}

			if (up) {
				outflag = true;

				try {
					channel.rm(outfile);
				} catch (Exception ex) {

				}
				out = new MockBufferedOutputStream(channel.put(outfile));
			} else {
				out = new MockBufferedOutputStream(new MockFileOutputStream(outfile));
			}

			// System.out.println("out: " + outfile + ", in: " + file);
			byte[] buf = new byte[smbBuffer];
			int len = 0;
			int reallen = 0;

			// System.out.println(file+":"+getLocalPath()+outfile);
			// while (true) {
			len = in.read(buf);

			// System.out.print(".");
			// if (len == StreamTokenizer.TT_EOF) {
			// break;
			// }

			out.write(buf, 0, len);
			reallen += len;

			// System.out.println(file + ":" + StringUtils.getFile(file));
			if (outflag) {
				fireProgressUpdate(StringUtils.getFile(outfile), MockDataConnection.PUT, reallen);
			} else {
				fireProgressUpdate(StringUtils.getFile(file), MockDataConnection.GET, reallen);
			}
			// }

			// if(up) {
			// channel.chmod(744, outfile);
			// }

			fireProgressUpdate(file, MockDataConnection.FINISHED, -1);
		}
		// catch(MockIOException ex)
		// {
		// ex.printStackTrace();
		//// Log.debug("Error with file IO (" + ex + ")!");
		// fireProgressUpdate(file, DataConnection.FAILED, -1);
		// }
		catch (MockSftpException ex) {
//			ex.printStackTrace();
			// Log.debug("Error with SFTP IO (" + ex + ")!");
			fireProgressUpdate(file, MockDataConnection.FAILED, -1);
		} finally {
			try {
				out.flush();
				out.close();
				in.close();
			} catch (Exception ex) {
//				ex.printStackTrace();
			}
		}
	}

	@EpaAction(name = "rename")
	public boolean rename(String oldName, String newName) {
		try {
			oldName = toSFTP(oldName);
			newName = toSFTP(newName);

			channel.rename(oldName, newName);

			return true;
		} catch (Exception ex) {
//			ex.printStackTrace();

			// Log.debug("Could rename file (" + ex + ").");

			return false;
		}
	}

	// private void update(String file, String type, int bytes) {
	// if (listeners == null) {
	// return;
	// } else {
	// for (int i = 0; i < listeners.size(); i++) {
	// ConnectionListener listener = (ConnectionListener) listeners.elementAt(i);
	// listener.updateProgress(file, type, bytes);
	// }
	// }
	// }

	@EpaAction(name = "addConnectionListener")
	public void addConnectionListener(ConnectionListener l) {
		listeners.add(l);
	}

	@EpaAction(name = "setConnectionListeners")
	public void setConnectionListeners(Vector l) {
		listeners = l;
	}

	/** remote directory has changed */
	private void fireDirectoryUpdate() {
		if (listeners == null) {
			return;
		} else {
			for (int i = 0; i < listeners.size(); i++) {
				((ConnectionListener) listeners.elementAt(i)).updateRemoteDirectory(this);
			}
		}
	}

	@EpaAction(name = "login")
	public boolean login(String user, String pass) {
		this.user = user;
		this.pass = pass;

		if (!login()) {
			// Log.debug("Login failed.");

			return false;
		} else {
			// Log.debug("Authed successfully.");

			// if(!chdir(getPWD())) chdir("/");
		}

		return true;
	}

	/** progress update */
	private void fireProgressUpdate(String file, String type, int bytes) {
		if (listeners == null) {
			return;
		}

		for (int i = 0; i < listeners.size(); i++) {
			ConnectionListener listener = (ConnectionListener) listeners.elementAt(i);

			if (shortProgress && MockSettings.shortProgress) {
				if (type.startsWith(MockDataConnection.DFINISHED)) {
					listener.updateProgress(baseFile, MockDataConnection.DFINISHED + ":" + fileCount, bytes);
				} else if (isDirUpload) {
					listener.updateProgress(baseFile, MockDataConnection.PUTDIR + ":" + fileCount, bytes);
				} else {
					listener.updateProgress(baseFile, MockDataConnection.GETDIR + ":" + fileCount, bytes);
				}
			} else {
				listener.updateProgress(file, type, bytes);
			}
		}
	}

	private void fireActionFinished(SftpConnection con) {
		if (listeners == null) {
			return;
		} else {
			for (int i = 0; i < listeners.size(); i++) {
				((ConnectionListener) listeners.elementAt(i)).actionFinished(con);
			}
		}
	}

//	private int upload(String file, MockInputStream i) {
//		MockBufferedOutputStream out = null;
//		MockBufferedInputStream in = null;
//
//		try {
//			file = toSFTP(file);
//
//			out = new MockBufferedOutputStream(channel.put(file));
//			in = new MockBufferedInputStream(i);
//
//			// Log.debug(getLocalPath() + ":" + file+ ":"+getPWD());
//			byte[] buf = new byte[smbBuffer];
//			int len = 0;
//			int reallen = 0;
//
//			// while (true) {
//			len = in.read(buf);
//
//			// System.out.print(".");
//			// if (len == StreamTokenizer.TT_EOF) {
//			// break;
//			// }
//
//			out.write(buf, 0, len);
//			reallen += len;
//
//			// fireProgressUpdate(StringUtils.getFile(file), DataConnection.PUT, reallen);
//			// }
//
//			// channel.chmod(744, file);
//
//			fireProgressUpdate(file, MockDataConnection.FINISHED, -1);
//
//			return 0;
//		}
//		// catch(MockIOException ex)
//		// {
//		// ex.printStackTrace();
//		//// Log.debug("Error with file IO (" + ex + ")!");
//		// fireProgressUpdate(file, DataConnection.FAILED, -1);
//		//
//		// return -1;
//		// }
//		catch (MockSftpException ex) {
//			ex.printStackTrace();
//			// Log.debug("Error with file SFTP IO (" + ex + ")!");
//			fireProgressUpdate(file, MockDataConnection.FAILED, -1);
//
//			return -1;
//		} finally {
//			try {
//				out.flush();
//				out.close();
//				in.close();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//	}

	// public InputStream getDownloadInputStream(String file)
	// {
	// try
	// {
	//
	// return channel.get(file);
	// }
	// catch(MockSftpException ex)
	// {
	// ex.printStackTrace();
	//// Log.debug(ex.toString() +
	//// " @Sftp2Connection::getDownloadInputStream");
	//
	// return null;
	// }
	// }

	// public Date[] sortDates()
	// {
	// return null;
	// }

	/*
	 * EPA States
	 */

	@EpaState(name = "S1")
	private boolean stateS1() {
		return !this.connected && this.listeners.isEmpty();
	}

	@EpaState(name = "S2")
	private boolean stateS2() {
		return !this.connected && !this.listeners.isEmpty();
	}

	@EpaState(name = "S3")
	private boolean stateS3() {
		return this.connected && this.listeners.isEmpty();
	}

	@EpaState(name = "S4")
	private boolean stateS4() {
		return this.connected && !this.listeners.isEmpty();
	}
}

class MyUserInfo {

	String password;

	public MyUserInfo(String pass) {
		this.password = pass;
	}

	public String getPassword() {
		return password;
	}

	public String getPassphrase() {
		return password;
	}

}
