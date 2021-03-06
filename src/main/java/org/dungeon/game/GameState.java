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

import org.dungeon.achievements.Achievement;
import org.dungeon.achievements.AchievementTracker;
import org.dungeon.achievements.UnlockedAchievement;
import org.dungeon.commands.CommandHistory;
import org.dungeon.commands.IssuedCommand;
import org.dungeon.date.Date;
import org.dungeon.date.Period;
import org.dungeon.entity.creatures.CreatureFactory;
import org.dungeon.entity.creatures.Hero;
import org.dungeon.io.DLogger;
import org.dungeon.io.IO;
import org.dungeon.stats.Statistics;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

  private final CommandHistory commandHistory;
  private final World world;
  private final Statistics statistics = new Statistics();
  private Hero hero;
  private Point heroPosition;

  transient private boolean saved = false;

  public GameState() {
    commandHistory = new CommandHistory();
    world = new World(statistics.getWorldStatistics());
    createHeroAndStartingLocation();
  }

  /**
   * Returns a String with a story about how the character got where he or she currently is.
   */
  public String getPreface() {
    String prefaceFormat = "You wake up at %s. Your head hurts and you can't remember what happened to you.\n"
        + "You were born in a city called Everdusk and raised by your parents, a man who was a trader and a woman who "
        + "helped him and owned the local watermill.";
    return String.format(prefaceFormat, hero.getLocation());
  }

  /**
   * Creates the Hero and the starting Location.
   */
  private void createHeroAndStartingLocation() {
    hero = CreatureFactory.makeHero(world.getWorldDate());
    heroPosition = new Point(0, 0);
    world.getLocation(heroPosition).addCreature(hero);
    getStatistics().getExplorationStatistics().addVisit(heroPosition, world.getLocation(heroPosition).getID());
  }

  public CommandHistory getCommandHistory() {
    return commandHistory;
  }

  public World getWorld() {
    return world;
  }

  public Statistics getStatistics() {
    return statistics;
  }

  public Hero getHero() {
    return hero;
  }

  public Point getHeroPosition() {
    return heroPosition;
  }

  public void setHeroPosition(Point heroPosition) {
    this.heroPosition = heroPosition;
  }

  public boolean isSaved() {
    return saved;
  }

  public void setSaved(boolean saved) {
    this.saved = saved;
  }

  /**
   * Prints the next hint.
   */
  public void printNextHint() {
    if (GameData.getHintLibrary().getHintCount() == 0) {
      IO.writeString("No hints were loaded.");
    } else {
      IO.writeString(GameData.getHintLibrary().getNextHint());
    }
  }

  /**
   * Prints a poem based on the issued command.
   * <p/>
   * If the command has arguments, the game attempts to use the first one as the poem's index (one-based).
   * <p/>
   * Otherwise, the next poem is based on a behind-the-scenes poem index.
   *
   * @param command the issued command.
   */
  public void printPoem(IssuedCommand command) {
    if (GameData.getPoetryLibrary().getPoemCount() == 0) {
      IO.writeString("No poems were loaded.");
    } else {
      if (command.hasArguments()) {
        try {
          // Indexing is zero-based to the implementation, but one-based to the player.
          int index = Integer.parseInt(command.getFirstArgument()) - 1;
          if (index >= 0 && index < GameData.getPoetryLibrary().getPoemCount()) {
            IO.writePoem(GameData.getPoetryLibrary().getPoem(index));
            return;
          }
        } catch (NumberFormatException ignore) {
          // This exception reproduces the same error message an invalid index does.
        }
        IO.writeString("Invalid poem index.");
      } else {
        IO.writePoem(GameData.getPoetryLibrary().getNextPoem());
      }
    }
  }

  /**
   * Prints all unlocked achievements.
   */
  public void printUnlockedAchievements() {
    Date now = world.getWorldDate();
    AchievementTracker tracker = hero.getAchievementTracker();
    ArrayList<Achievement> achievements = new ArrayList<Achievement>();
    ArrayList<Period> timeSinceUnlocked = new ArrayList<Period>();
    for (UnlockedAchievement ua : tracker.getUnlockedAchievementArray()) {
      Achievement achievement = GameData.ACHIEVEMENTS.get(ua.id);
      if (achievement != null) {
        achievements.add(achievement);
        timeSinceUnlocked.add(new Period(ua.date, now));
      } else {
        DLogger.warning("Unlocked achievement ID not found in GameData.");
      }
    }
    IO.writeString("Progress: " + tracker.getUnlockedCount() + "/" + GameData.ACHIEVEMENTS.size(), Color.CYAN);
    IO.writeAchievementList(achievements, timeSinceUnlocked);
  }

  /**
   * Retrieves the Location object that contains the hero.
   *
   * @return a Location object.
   */
  public Location getHeroLocation() {
    return world.getLocation(heroPosition);
  }

}
