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

import java.util.Map;
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
	private final Map<PieceType, Integer> unknowns;
	private final DeltaStrategyGame game;
	private double winProb;
	private MoveType type;
	private int rankDifference;

	/**
	 * @param from The Position this move is from
	 * @param to The Position this move is going to
	 * @param unknowns 
	 * @param game The game that this movement is occurring in
	 */
	public ComparablePlayerMove(Position from, Position to, Map<PieceType, Integer> unknowns, 
			DeltaStrategyGame game) {
		this.from = from;
		this.to = to;
		this.unknowns = unknowns;
		this.game = game;
		makeType();
		findRankDifference();
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
	
	private void findRankDifference() {
		rankDifference = 0;
		if(!type.equals(MoveType.INVALID)) {
			final Piece fromPiece = game.getPieceAt(from);
			final Piece toPiece = game.getPieceAt(to);
			if(!toPiece.equals(Piece.NULL_PIECE) && !toPiece.equals(Piece.UNKNOWN_PIECE)) {
				rankDifference = toPiece.getType().getRank() - fromPiece.getType().getRank() ;
			}
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
			else if(toPiece.getType().equals(PieceType.FLAG)) {
				return MoveType.CAPTURE_FLAG;
			}
			else {
				return MoveType.ATTACK_VICTORY;
			}
		}
		else if(toPiece.equals(Piece.UNKNOWN_PIECE)) {
			calculateWinProb();
			if (winProb > 0.9) {
				return MoveType.POSSIBLE_VICTORY;
			}
		}
		return MoveType.VALID;
	}
	
	private void calculateWinProb() {
		winProb = 0;
		int numWins = 0;
		int numPossible = 0;
		final Piece thisPiece = game.getPieceAt(from);
		
		for (PieceType i : PieceType.values()) {
			if(DeltaStrategyGame.determineBattleResult(thisPiece, 
					new Piece(i, thisPiece.getColor())) == BattleResult.VICTORY) {
				numWins++;
			}
			numPossible += unknowns.get(i);
		}
		winProb = (float) numWins / (float) numPossible;
	}
	
	@Override
	public int compareTo(ComparablePlayerMove other) {
		final int typeComparison = type.compareTo(other.getType());
		if(typeComparison == 0) {
			if(rankDifference < other.getRankDifference()) {
				return 1;
			}
			else if(rankDifference == other.getRankDifference()) {
				return 0;
			}
			else {
				return -1;
			}
		}
		return typeComparison;
	}

	public int getRankDifference() {
		return rankDifference;
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
