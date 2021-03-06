/*
 * Copyright (C) 2015 Bernardo Sulzbach
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

package org.dungeon.commands;

/**
 * An implementation of the CommandResult interface designed for standard commands.
 * This implementation supports only duration-based GameState changes.
 * Therefore, no standard command should ever change the GameState without consuming time.
 */
public class SimpleCommandResult implements CommandResult {

  private final int duration;

  /**
   * Creates a SimpleCommandResult that records the duration of a turn.
   *
   * @param duration the in-game duration of the command execution
   */
  public SimpleCommandResult(int duration) {
    if (duration < 0) {
      throw new IllegalArgumentException("Attempted to create a SimpleCommandResult with a negative duration!");
    }
    this.duration = duration;
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public boolean evaluateIfGameStateChanged() {
    return duration != 0;
  }

}
