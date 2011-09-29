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
 *    Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 *******************************************************************************/
package strategy.beta;

import strategy.GameState;
import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.MovementStrategy;
import strategy.StrategyException;

/**
 * This class defines the rules of moving pieces 
 * and resolving battles for BetaStrategy.
 * These movement and battle rules support all pieces.
 * The game ends after 10 turns.
 *
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class BetaMovementStrategy extends MovementStrategy {
	
	/**
	 * @param state The GameState this should modify
	 */
	public BetaMovementStrategy(GameState state)
	{
		super(state);
	}
	
	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(source.equals(destination)) {
			throw new StrategyException("destination must be different from source");
		}
		if(source.isDiagonal(destination)) {
			throw new StrategyException("Pieces cannot move diagonally");
		}
		if(!state.getBoard().isOccupied(source)) {
			throw new StrategyException("source must be occupied by a piece");
		}
		if(state.isOver()){
			throw new StrategyException("Game is already over");
		}
		
		//change turn/dont double move
		if(state.getBoard().getPieceAt(source).getColor() != state.getTurn()){
			throw new StrategyException("not your turn");
		}
		
		if(state.getTurn() == PlayerColor.RED){
			state.setTurn(PlayerColor.BLUE);
		}
		else{
			state.setTurn(PlayerColor.RED);
		}
		
		final Piece sourcePiece = state.getBoard().getPieceAt(source);
		final Piece destinationPiece = state.getBoard().getPieceAt(destination);
		
		final int distance = state.getBoard().getDistance(source, destination);
		final int range = sourcePiece.getType().getRange();
		if(range >= 0 && distance > range) {
			throw new StrategyException("Cannot move piece farther than its range");
		}
		
		if (sourcePiece.getColor().equals(destinationPiece.getColor())) {
			throw new StrategyException("Cannot move onto a friendly piece");
		}

		if (state.getBoard().isOccupiedSpaceBetweenPositions(source, destination)) {
			throw new StrategyException("Cannot move through occupied spaces");
		}

		BattleResult result = BattleResult.VICTORY;
		if (state.getBoard().isOccupied(destination)) {
			result = resolveBattle(sourcePiece, destinationPiece);
		}

		switch (result) {
		case VICTORY:
			state.getBoard().putPieceAt(destination, sourcePiece);
			state.getBoard().putPieceAt(source, Piece.NULL_PIECE);
			break;
		case DRAW:
			state.getBoard().putPieceAt(destination, Piece.NULL_PIECE);
			// fallthrough
		case DEFEAT:
			state.getBoard().putPieceAt(source, Piece.NULL_PIECE);
			break;
		}
		//if moves>10 end game
		state.setNumMoves(state.getNumMoves() + 1);
		if(state.getNumMoves() >= 10){
			state.setWinner(PlayerColor.BLUE);
		}
		return state.getBoard().getPieceAt(destination);
	}

	@Override
	public BattleResult resolveBattle(Piece attacker, Piece defender) {
		if(defender.getType().equals(PieceType.FLAG)){
			state.setWinner(attacker.getColor());
			return BattleResult.VICTORY;
		}
		if(attacker.getType().equals(PieceType.MINER)
				&& defender.getType().equals(PieceType.BOMB)) {
			return BattleResult.VICTORY;
		}
		if(attacker.getType().equals(PieceType.SPY)
				&& defender.getType().equals(PieceType.MARSHAL)) {
			return BattleResult.VICTORY;
		}
		
		if(attacker.getType().getRank() < defender.getType().getRank()) {
			return BattleResult.VICTORY;
		}
		if(attacker.getType().getRank() == defender.getType().getRank()) {
			return BattleResult.DRAW;
		}
		return BattleResult.DEFEAT;
	}

}
