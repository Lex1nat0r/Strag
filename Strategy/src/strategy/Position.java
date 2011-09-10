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
package strategy;

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
 * @author gpollice
 * @version Aug 1, 2011
 */
public class Position
{
	private final int row;
	private final int column;
	
	/**
	 * Constructor for a rectangular coordinate.
	 * 
	 * @param row
	 * @param column
	 */
	public Position(int row, int column)
	{
		this.row = row;
		this.column = column;
	}

	
	/**
	 * @return the row
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn()
	{
		return column;
	}
	
	@Override
	public String toString()
	{
		return "(" + row + ',' + column + ')';
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Position) {
			final Position other = (Position) obj;
			return column == other.column && row == other.row;
		}
		return false;
	}
}
