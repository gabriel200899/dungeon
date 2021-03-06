/*
 * Copyright (C) 2014 Bernardo Sulzbach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.dungeon.util;

import org.dungeon.io.IO;

import java.util.Date;

public final class SystemInfo {

  private SystemInfo() { // Ensure that this class cannot be instantiated.
    throw new AssertionError();
  }

  /**
   * Prints some information about the system.
   */
  public static void printSystemInfo() {
    Date currentDate = new Date();
    IO.writeString("Time: " + Constants.TIME_FORMAT.format(currentDate));
    IO.writeString("Date: " + Constants.DATE_FORMAT.format(currentDate));
    IO.writeString("User: " + System.getProperty("user.name"));
    IO.writeString(getJavaVersionString());
    IO.writeString(getOSVersionString());
  }

  private static String getJavaVersionString() {
    return "Java version " + System.getProperty("java.version") + " by " + System.getProperty("java.vendor");
  }

  private static String getOSVersionString() {
    String name = System.getProperty("os.name");
    String arch = System.getProperty("os.arch");
    String version = System.getProperty("os.version");
    return String.format("%s (%s) %s", name, arch, version);
  }

}
