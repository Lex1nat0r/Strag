/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 *******************************************************************************/
package strategy.playeratcahgsr.common;

import strategy.playeratcahgsr.delta.DeltaStrategyGame;

/**
 * Describes a move.  ComparablePlayerMoves can be compared with each other to
 * determine which move is better.
 * 
 * @version 10 Oct 2011
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 */
public class ComparablePlayerMove implements Comparable<ComparablePlayerMove> {

	private final Position from;
	private final Position to;
	private final DeltaStrategyGame game;
	private MoveType type;

	/**
	 * @param from The Position this move is from
	 * @param to The Position this move is going to
	 * @param game The game that this movement is occurring in
	 */
	public ComparablePlayerMove(Position from, Position to, DeltaStrategyGame game) {
		this.from = from;
		this.to = to;
		this.game = game;
		makeType();
	}
	
	private void makeType() {
		boolean isValid = true;
		try {
			game.validateMove(from, to);
		} catch(Exception e) {
			isValid = false;
		}
		if(isValid) {
			type = determineValidType();
		}
		else {
			type = MoveType.INVALID;
		}
	}
	
	private MoveType determineValidType() {
		final Piece fromPiece = game.getPieceAt(from);
		final Piece toPiece = game.getPieceAt(to);
		if(!toPiece.equals(Piece.UNKNOWN_PIECE) && !toPiece.equals(Piece.NULL_PIECE)) {
			final BattleResult result = DeltaStrategyGame.determineBattleResult(fromPiece, toPiece);
			if(result.equals(BattleResult.DEFEAT)) {
				return MoveType.ATTACK_DEFEAT;
			}
			else if(result.equals(BattleResult.DRAW)) {
				return MoveType.ATTACK_DRAW;
			}
			else {
				return MoveType.ATTACK_VICTORY;
			}
		}
		return MoveType.VALID;
	}
	
	@Override
	public int compareTo(ComparablePlayerMove other) {
		return type.compareTo(other.getType());
	}

	/**
	 * @return the from
	 */
	public Position getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public Position getTo() {
		return to;
	}
	
	/**
	 * @return The type
	 */
	public MoveType getType() {
		return type;
	}

}
