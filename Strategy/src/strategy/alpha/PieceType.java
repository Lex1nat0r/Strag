/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: gpollice, Andrew Hurle
 *******************************************************************************/
package strategy.alpha;

/**
 * Enumeration for the PieceType. This enumeration defines constants that identify the various piece
 * types used in the games and their ranks.
 * 
 * @author gpollice, Andrew Hurle
 * @version Aug 29, 2011
 */
public enum PieceType
{
	MARSHAL(1), 
	COLONEL(2), 
	MAJOR(3), 
	CAPTAIN(4), 
	LIEUTENANT(5), 
	SERGEANT(6), 
	MINER(7), 
	SCOUT(8), 
	SPY(9), 
	BOMB(0), 
	FLAG(0);

	private final int rank;

	/**
	 * Constructor for the instances of PieceType.
	 * @param rank
	 */
	private PieceType(int rank)
	{
		this.rank = rank;
	}

	/**
	 * @return the rank
	 */
	public int getRank()
	{
		return rank;
	}
	
	/**
	 * @return the movement range of this piece.
	 * 			0 if the piece cannot move.
	 * 			-1 if the piece has unlimited range.
	 */
	public int getRange()
	{
		switch(this) {
		case SCOUT:
			return -1;
		case BOMB: //fallthrough
		case FLAG:
			return 0;
		default:
			return 1;
		}
	}
}
