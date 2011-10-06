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
package strategy.tournament;

import strategy.*;
import strategy.common.PieceType;

/**
 * This class is the result of making a move. It is used when a player is
 * asked to move. It is a parameter to the <tt>move</tt> method. It indicates
 * the move that the opponent has just made, and any information that was gained
 * from your previous move. Specifically, if you attacked an opponent piece, it tells you
 * what the piece was so you can update your private board with the information and
 * if the opponent attacked you, it tells you which piece the opponent attacke
 * you with.
 * 
 * @author gpollice
 * @version Oct 5, 2011
 */
public class MoveResult
{
	private final PlayerMove opponentsLastMove;
	private final PieceType myLastTarget;
	private final PieceType opponentsAttacker;
	
	/**
	 * Constructor initializes the fields.
	 * 
	 * @param opponentsLastMove
	 * @param myLastTarget
	 * @param opponentsAttacker
	 */
	public MoveResult(PlayerMove opponentsLastMove, PieceType myLastTarget,
			PieceType opponentsAttacker)
	{
		this.opponentsLastMove = opponentsLastMove;
		this.myLastTarget = myLastTarget;
		this.opponentsAttacker = opponentsAttacker;
	}

	/**
	 * @return the opponentsLastMove
	 */
	public PlayerMove getOpponentsLastMove()
	{
		return opponentsLastMove;
	}

	/**
	 * @return the myLastTarget
	 */
	public PieceType getMyLastTarget()
	{
		return myLastTarget;
	}

	/**
	 * @return the opponentsAttacker
	 */
	public PieceType getOpponentsAttacker()
	{
		return opponentsAttacker;
	}
}
