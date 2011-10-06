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
 * The Piece class represents a piece in the game. One instance of this class is created for each
 * piece on the board.
 * 
 * @author gpollice
 * @version Jul 27, 2011
 */
public class Piece
{
	public static final Piece NULL_PIECE = new Piece(null, null);
	public static final Piece CHOKE_POINT = new Piece(null, null);
	public static final Piece UNKNOWN_OPPONENT = new Piece(null, null);
	private final PieceType type;
	private final PlayerColor color;

	/**
	 * Constructor that takes the type and color of this piece.
	 * 
	 * @param type
	 * @param color
	 */
	public Piece(PieceType type, PlayerColor color)
	{
		this.type = type;
		this.color = color;
	}

	/**
	 * @return tye type of this piece
	 */
	public PieceType getType()
	{
		return type;
	}

	/**
	 * @return the color of this piece
	 */
	public PlayerColor getColor()
	{
		return color;
	}

	@Override
	public boolean equals(Object other)
	{
		return ((this == NULL_PIECE || this == CHOKE_POINT || this == UNKNOWN_OPPONENT) && this == other)
				|| ((this != NULL_PIECE && this != CHOKE_POINT && this != UNKNOWN_OPPONENT) 
					&& ((other instanceof Piece) 
						&& (type == ((Piece) other).type) 
						&& (color == ((Piece) other).color)));
	}

	@Override
	public int hashCode()
	{
		final String s = type == null ? "" : type.toString() + color == null ? ""
				: color.toString();
		return s.hashCode();
	}

	@Override
	public String toString()
	{
		String result = ".";	// NULL_PIECE
		if (!this.equals(NULL_PIECE)) {
			result = color == PlayerColor.RED ? type.getId().toUpperCase() : type.getId();
		}
		return result;
	}

	/**
	 * @return true if the piece can be moved
	 */
	public boolean isMoveable()
	{
		return !(type == null || type == PieceType.BOMB || type == PieceType.FLAG);
	}
}
