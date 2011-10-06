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
package strategy.common;

/**
 * Enumeration for the PieceType. This enumeration defines constants that identify the various piece
 * types used in the games and their ranks.
 * 
 * @author gpollice
 * @version Jul 27, 2011
 */
public enum PieceType
{
	MARSHAL(1, 'm'), 
	GENERAL(2, 'n'),
	COLONEL(3, 'c'), 
	MAJOR(4, 'j'), 
	CAPTAIN(5, 'p'), 
	LIEUTENANT(6, 't'), 
	SERGEANT(7, 'g'), 
	MINER(8, 'r'), 
	SCOUT(9, 's'), 
	SPY(10, 'y'), 
	BOMB(0, 'b'), 
	FLAG(0, 'f');

	private final int rank;
	private final char id;

	/**
	 * Constructor for the instances of PieceType.
	 * @param rank
	 */
	private PieceType(int rank, char id)
	{
		this.rank = rank;
		this.id = id;
	}

	/**
	 * @return the rank
	 */
	public int getRank()
	{
		return rank;
	}
	
	/**
	 * @return a character that represents this piece type
	 */
	public String getId()
	{
		return "" + id;
	}
	
	/**
	 * @return true if pieces of this type can move
	 */
	public boolean isMoveable()
	{
		return rank != 0;
	}
}
