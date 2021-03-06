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
import org.dungeon.io.ResourceReader;
import org.dungeon.util.AutomaticShuffledRange;
import org.dungeon.util.Poem;
import org.dungeon.util.PoemBuilder;

import java.util.ArrayList;

/**
 * A Library of Poems.
 * <p/>
 * Created by Bernardo Sulzbach on 15/12/14.
 */
public final class PoetryLibrary extends Library {

  private final ArrayList<Poem> poems = new ArrayList<Poem>();

  private AutomaticShuffledRange automaticShuffledRange;

  /**
   * Returns how many poems the library has.
   * <p/>
   * This should be the first method called in this Library, as it triggers its initialization if it has not happened
   * yet.
   */
  public int getPoemCount() {
    if (isUninitialized()) {
      initialize();
    }
    return poems.size();
  }

  /**
   * Returns the poem at the specified index.
   */
  public Poem getPoem(int index) {
    return poems.get(index);
  }

  /**
   * Returns the next poem according to the underlying {@code AutomaticShuffledRange}.
   */
  public Poem getNextPoem() {
    return poems.get(automaticShuffledRange.getNext());
  }

  @Override
  void load() {
    ResourceReader reader = new ResourceReader("poems.txt");
    final String IDENTIFIER_TITLE = "TITLE";
    final String IDENTIFIER_AUTHOR = "AUTHOR";
    final String IDENTIFIER_CONTENT = "CONTENT";
    while (reader.readNextElement()) {
      PoemBuilder pb = new PoemBuilder();
      pb.setTitle(reader.getValue(IDENTIFIER_TITLE));
      pb.setAuthor(reader.getValue(IDENTIFIER_AUTHOR));
      pb.setContent(reader.getValue(IDENTIFIER_CONTENT));
      if (pb.isComplete()) {
        poems.add(pb.createPoem());
      } else {
        DLogger.warning("Parsed incomplete poem (" + reader.getValue(IDENTIFIER_TITLE) + ")!");
      }
    }
    reader.close();
    poems.trimToSize();
    automaticShuffledRange = new AutomaticShuffledRange(0, poems.size());
    DLogger.info("Loaded " + poems.size() + " poems.");
  }

}
