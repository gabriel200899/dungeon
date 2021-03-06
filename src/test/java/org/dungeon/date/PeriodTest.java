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

package org.dungeon.date;

import static org.dungeon.date.DungeonTimeUnit.DAY;
import static org.dungeon.date.DungeonTimeUnit.MONTH;
import static org.dungeon.date.DungeonTimeUnit.YEAR;

import org.junit.Assert;
import org.junit.Test;

public class PeriodTest {

  @Test
  public void testToString() throws Exception {
    Date start = new Date(2014, YEAR.as(MONTH), MONTH.as(DAY), 0, 0, 0);
    Date end = new Date(2014, YEAR.as(MONTH), MONTH.as(DAY), 0, 0, 0);

    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("1 day", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("2 days", new Period(start, end).toString());
    end = end.minus(2, DungeonTimeUnit.DAY);

    end = end.plus(1, DungeonTimeUnit.MONTH);
    Assert.assertEquals("1 month", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.MONTH);
    Assert.assertEquals("2 months", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("2 months and 1 day", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("2 months and 2 days", new Period(start, end).toString());
    end = end.minus(2, DungeonTimeUnit.DAY);
    end = end.minus(2, DungeonTimeUnit.MONTH);

    end = end.plus(1, DungeonTimeUnit.YEAR);
    Assert.assertEquals("1 year", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.YEAR);
    Assert.assertEquals("2 years", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("2 years and 1 day", new Period(start, end).toString());
    end = end.minus(1, DungeonTimeUnit.DAY);
    end = end.plus(1, DungeonTimeUnit.MONTH);
    Assert.assertEquals("2 years and 1 month", new Period(start, end).toString());
    end = end.plus(1, DungeonTimeUnit.DAY);
    Assert.assertEquals("2 years, 1 month and 1 day", new Period(start, end).toString());
  }

}
