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

package org.dungeon.achievements;

import org.dungeon.date.Date;
import org.dungeon.game.ID;

import java.io.Serializable;

/**
 * UnlockedAchievement class that records the unlocking of an achievement.
 */
public final class UnlockedAchievement implements Serializable {

  public final ID id;
  public final String name;
  // Date is immutable, therefore it can be public.
  public final Date date;

  /**
   * Construct a new UnlockedAchievement.
   *
   * @param name the name of the unlocked achievement.
   * @param date the date when the achievement was unlocked.
   */
  public UnlockedAchievement(ID id, String name, Date date) {
    this.id = id;
    this.name = name;
    this.date = date;
  }

}
