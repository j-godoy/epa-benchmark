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


/**
 * Interface for all connection types
 */
public interface MockBasicConnection
{
	
    /**
     * Some Connections like FTP may allow raw command to be sent, use this method.
     * 
     * Implementation is optional.
     * 
     * @param cmd
     */
//    public void sendRawCommand(String cmd);

    //public boolean login(String user, String pass);
    
    /**
     * Teminates the connection if necessary.
     * 
     */
    public void disconnect();

    /**
     * Shows if the Connection is alive and initialized.
     * 
     * @return
     */
    public boolean isConnected();

    /**
     * Get the current working dir.
     * 
     * @return
     */
    public String getPWD();

    /**
     * Go up one directory if possible.
     * 
     * @return
     */
//    public boolean cdup();

    /**
     * Create a new directory.
     * 
     * @param dirName
     * @return
     */
    public boolean mkdir(String dirName);
    
    /**
     * Rename a file or directory.
     * 
     * @param from
     * @param to
     * @return
     */
    public boolean rename(String from, String to);

    /**
     * Perform any necessary actions so sortLs, sortSize and sortDates are up-to-date.
     * 
     * Implementation is optional.
     * 
     * @throws MockIOException 
     */
    public void list() throws MockIOException;

    /**
     * Change dircetory and inform listeners.
     * 
     * @param p
     * @return
     */
    public boolean chdir(String p);

    /**
     * Change directory, but do not trigger an event for the listeners.
     * 
     * @param p
     * @return
     */
//    public boolean chdirNoRefresh(String p);

    /**
     * Get the path downloaded to.
     * 
     * @return
     */
//    public String getLocalPath();

    /**
     * Set the path downloded to.
     * 
     * @param newPath
     * @return
     */
    public boolean setLocalPath(String newPath);

    /**
     * Get file and diretory names.
     * 
     * @return
     */
    public String[] sortLs();

    /**
     * Get file sizes.
     * 
     * @return
     */
    public String[] sortSize();

    /**
     * Get file dates.
     * 
     * @return
     */
//    public Date[] sortDates();

    /**
     * Get file/dir permissions.
     * 
     * @return
     */
    public int[] getPermissions();

    /**
     * Initiate a download, possibly non-blocking and in a new thread.
     * 
     * @param file
     * @return
     */
    public int handleDownload(String file);
    
    /**
     * Initiate an upload, possibly non-blocking and in a new thread.
     * 
     * @param file
     * @return
     */
    public int handleUpload(String file);

    /**
     * Initiate a download in the same thread.
     * 
     * @param file
     * @return
     */
    public int download(String file);

    /**
     * Initiate an upload in the same thread.
     * 
     * @param file
     * @return
     */    
    public int upload(String file);

    /**
     * Initiate a download in the same thread.
     * 
     * The InptuStream provides the date, file the remote file name.
     * 
     * @param file
     * @return
     */    
    public int upload(String file, MockInputStream in);

    /**
     * Initiate a download and return the content in form of an InputStream.
     * 
     * @param file
     * @return
     */
//    public InputStream getDownloadInputStream(String file);

    /**
     * Remove the given item, recursively if necessary.
     * 
     * @param file
     * @return
     */
    public int removeFileOrDir(String file);

    /**
     * Add a ConnectionListener to be notified about progress and events.
     * 
     * @param listener
     */
    public void addConnectionListener(ConnectionListener listener);

    /**
     * Remove a ConnectionListener.
     * 
     * @param listeners
     */
    public void setConnectionListeners(Vector<ConnectionListener> listeners);

}
