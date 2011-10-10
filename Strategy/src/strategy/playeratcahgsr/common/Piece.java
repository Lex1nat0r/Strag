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

import strategy.common.PlayerColor;

/**
 * The Piece class represents a piece in the game. One instance of this class is created for each
 * piece on the board.
 * 
 * @author gpollice, Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 7, 2011
 */
public class Piece
{
	private final PieceType myType;
	private final PlayerColor myColor;
	
	public static final Piece NULL_PIECE = new Piece(null, null);
	public static final Piece WATER_PIECE = new Piece(null, null);
	public static final Piece UNKNOWN_PIECE = new Piece(null, null);

	/**
	 * Constructor that takes the type and color of this piece.
	 * 
	 * @param type
	 * @param color
	 */
	public Piece(PieceType type, PlayerColor color)
	{
		myType = type;
		myColor = color;
	}

	/**
	 * @return tye type of this piece
	 */
	public PieceType getType()
	{
		return myType;
	}

	/**
	 * @return the color of this piece
	 */
	public PlayerColor getColor()
	{
		return myColor;
	}

	@Override
	public boolean equals(Object other)
	{
		if(this == other) {
			return true;
		}
		if (other instanceof Piece) {
			final Piece that = (Piece) other;
			if(myType == null && myColor == null) {
				return false;
			}
			return myColor.equals(that.getColor()) && myType.equals(that.getType());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + myType.hashCode();
		result = prime * result + myColor.hashCode();
		return result;
	}

	/**
	 * @return true if the piece can be moved
	 */
	public boolean isMoveable()
	{
		return myType.isMoveable();
	}
	
	@Override
	public String toString() {
		return myColor + " " + myType + " Piece";
	}
}
