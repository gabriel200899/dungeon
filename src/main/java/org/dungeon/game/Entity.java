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

import java.io.Serializable;

/**
 * Entity abstract class that is a common type for everything that can be placed into a Location and interacted with.
 * Namely, items and creatures.
 * <p/>
 * All subclasses of Entity are Selectable and Serializable.
 *
 * @author Bernardo Sulzbach
 */
public abstract class Entity implements Selectable, Serializable {

  protected final String type;
  protected final Name name;
  private final ID id;

  protected Entity(ID id, String type, Name name) {
    this.id = id;
    this.type = type;
    this.name = name;
  }

  public ID getID() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public String getName() {
    return name.getName();
  }

  public String getQuantifiedName(int quantity) {
    return name.getQuantifiedName(quantity, QuantificationMode.WORD);
  }

}
