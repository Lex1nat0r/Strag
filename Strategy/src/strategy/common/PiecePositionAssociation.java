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
package strategy.common;

/**
 * This class is an association class that relates pieces to positions. Its main
 * use is for initializing the pieces of a game. It is basically a data structure.
 * @author gpollice
 * @version Sep 22, 2011
 */
public class PiecePositionAssociation
{
	private final Piece piece;
	private final Position position;
	
	/**
	 * Constructor initializes the attribute values.
	 * @param piece
	 * @param position
	 */
	public PiecePositionAssociation(Piece piece, Position position)
	{
		this.piece = piece;
		this.position = position;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * @return the position
	 */
	public Position getPosition()
	{
		return position;
	}
}
