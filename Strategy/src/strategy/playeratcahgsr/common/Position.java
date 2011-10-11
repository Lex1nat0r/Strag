/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2011 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Author:
 *    gpollice
 *******************************************************************************/
package strategy.playeratcahgsr.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * This class implements the data structure for board positions. A position
 * is referenced by two coordinates, the row and column. It is specified as
 * (row, column).
 * </p><p>
 * In the standard game, the lower left corner (on RED's side) is (0, 0). A
 * 6 x 6 board would look like this:
 * </p>
 <pre>
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (5,0) | (5, 1) | (5, 2) | (5, 3) | (5, 4) | (5, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (4,0) | (4, 1) | (4, 2) | (4, 3) | (4, 4) | (4, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (3,0) | (3, 1) | (3, 2) | (3, 3) | (3, 4) | (3, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (2,0) | (2, 1) | (2, 2) | (2, 3) | (2, 4) | (2, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (1,0) | (1, 1) | (1, 2) | (1, 3) | (1, 4) | (1, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 |       |        |        |        |        |        |
 | (0,0) | (0, 1) | (0, 2) | (0, 3) | (0, 4) | (0, 5) |
 |       |        |        |        |        |        |
 +-------+--------+--------+--------+--------+--------+
 </pre>
 * 
 * @author gpollice, Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Aug 1, 2011
 */
public class Position extends strategy.common.Position
{
	
	/**
	 * Constructor for a rectangular coordinate.
	 * 
	 * @param row
	 * @param column
	 */
	public Position(int row, int column)
	{
		super(row, column);
	}
	
	/**
	 * @param other 
	 * 			Position to compare to
	 * @return
	 * 			true if this Position is diagonal from Position other, otherwise false
	 */
	public boolean isDiagonal(Position other) {
		final boolean horizontal = getColumn() != other.getColumn();
		final boolean vertical = getRow() != other.getRow();
		
		return horizontal && vertical;
	}
	
	/**
	 * @return A List of Position containing bordering Positions in the cardinal directions
	 */
	public Collection<Position> getCardinalPositions() {
		final Set<Position> set = new HashSet<Position>();
		set.add(new Position(getRow() + 1, getColumn()));
		set.add(new Position(getRow(), getColumn() + 1));
		set.add(new Position(getRow() - 1, getColumn()));
		set.add(new Position(getRow(), getColumn() - 1));
		return set;
	}
	
}
