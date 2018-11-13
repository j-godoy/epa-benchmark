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

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.example.sftpconnection;

public class StringUtils
{
    /**
    * Get a filename out of a full path string
    */
    public static String getFile(String file)
    {
        int x = file.lastIndexOf("/");

        // unix
        if(x >= 0)
        {
            file = file.substring(x + 1);
        }

        // windows
        x = file.lastIndexOf("\\");

        if(x >= 0)
        {
            file = file.substring(x + 1);
        }

        // may work, but can test the other method better
        //int x  = file.lastIndexOf(File.separatorChar);
        //if(x >= 0) file = file.substring(x+1);
        //System.out.println(file);
        return file;
    }

    /**
    * Returns a string representing a relative directory path.
    * Examples: "/tmp/dir/" -> "dir/" and "/tmp/dir" -> "dir"
    */
    public static String getDir(String tmp)
    {
        int x;

        while(true)
        {
            x = tmp.indexOf("/");

            if((x == (tmp.length() - 1)) || (x < 0))
            {
                break;
            }
            else
            {
                tmp = tmp.substring(x + 1);
            }
        }

        while(true)
        {
            x = tmp.indexOf("\\");

            if((x == (tmp.length() - 1)) || (x < 0))
            {
                break;
            }
            else
            {
                tmp = tmp.substring(x + 1);
            }
        }

        return tmp;
    }

    /*
    * Returns true if the string represents a relative filename, false otherwise
    */
    public static boolean isRelative(String file)
    {
        // unix
        if(file.startsWith("/"))
        {
            return false;
        }

        // windows
        if((file.length() > 2) && (file.charAt(1) == ':'))
        {
            return false;
        }

        //System.out.println("true: " + file);
        // default
        return true;
    }

}
