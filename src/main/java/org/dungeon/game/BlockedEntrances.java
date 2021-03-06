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

package org.dungeon.game;

import org.dungeon.io.DLogger;
import org.dungeon.util.Utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * BlockedEntrances class that defines a allows blocking the entrances of a Locations.
 */
public class BlockedEntrances implements Serializable {

  private Set<Direction> setOfBlockedEntrances = new HashSet<Direction>();

  public BlockedEntrances() {
  }

  /**
   * Copy constructor.
   *
   * @param source the object to be copied.
   */
  public BlockedEntrances(BlockedEntrances source) {
    setOfBlockedEntrances = new HashSet<Direction>(source.setOfBlockedEntrances);
  }

  public void block(Direction direction) {
    if (isBlocked(direction)) {
      DLogger.warning("Tried to block an already blocked direction!");
    } else {
      setOfBlockedEntrances.add(direction);
    }
  }

  public boolean isBlocked(Direction direction) {
    return setOfBlockedEntrances.contains(direction);
  }

  @Override
  public String toString() {
    return Utils.enumerate(Arrays.asList(setOfBlockedEntrances.toArray()));
  }

}
