/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: gpollice
 *******************************************************************************/
package strategy.playeratcahgsr.common;


/**
 * Interface for a Strategy board.
 * 
 * @author gpollice
 * @version Aug 1, 2011
 */
public interface StrategyBoard extends Iterable<Piece>
{
	/**
	 * Get the piece located at the specified coordinate
	 * 
	 * @param position
	 *            the coordinate of the selected space
	 * @return the piece at the coordinate
	 */
	Piece getPieceAt(Position position);

	/**
	 * Place a piece at the specified position.
	 * 
	 * @param position
	 *            the coordinate for the position
	 * @param piece
	 * 			  the piece to put at the specified position
	 */
	void putPieceAt(Position position, Piece piece);

	/**
	 * @param position
	 * @return true if the specified position is occupied
	 */
	boolean isOccupied(Position position);
	
	/**
	 * Get the distance between two coordinates.
	 * @param from the first coordinate
	 * @param to the second coordinate
	 * @return the distance between the two coordinates, according to the game rules
	 */
	int getDistance(Position from, Position to);
	
	/**
	 * Makes sure that the given Position is within this board
	 * @param pos The Position to test
	 */
	void validatePosition(Position pos);
}
