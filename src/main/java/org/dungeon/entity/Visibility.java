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

package org.dungeon.entity;

import org.dungeon.util.Percentage;

import java.io.Serializable;

/**
 * A simple wrapper for a Percentage object.
 */
public class Visibility implements Serializable {

  private final Percentage value;

  public Visibility(Percentage value) {
    this.value = value;
  }

  public Percentage toPercentage() {
    return value;
  }

  /**
   * Evaluates if an Entity with this Visibility should be visible under the specified luminosity.
   *
   * @param luminosity a Percentage, not null
   * @return true if an Entity with this Visibility is visible, false otherwise
   */
  public boolean visibleUnder(Percentage luminosity) {
    return luminosity.toDouble() >= 1 - value.toDouble();
  }

}
