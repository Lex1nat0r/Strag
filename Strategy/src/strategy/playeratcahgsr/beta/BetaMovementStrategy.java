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
package strategy.playeratcahgsr.beta;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.playeratcahgsr.common.GameState;
import strategy.playeratcahgsr.common.MovementStrategy;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;

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
		validateMove(source, destination);
		
		final Piece sourcePiece = state.getBoard().getPieceAt(source);
		final Piece destinationPiece = state.getBoard().getPieceAt(destination);

		if(state.getTurn() == PlayerColor.RED){
			state.setTurn(PlayerColor.BLUE);
		}
		else{
			state.setTurn(PlayerColor.RED);
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
		return state.getBoard().getPieceAt(destination);
	}
	
	/**
	 * Throws an exception if the given move is invalid.
	 * Does not change the state of the game.
	 * @see BetaMovementStrategy#makeMove(Position, Position)
	 * 
	 * @param source
	 * @param destination
	 * @throws StrategyException
	 */
	public void validateMove(Position source, Position destination) throws StrategyException {
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
		
		final Piece sourcePiece = state.getBoard().getPieceAt(source);
		final Piece destinationPiece = state.getBoard().getPieceAt(destination);
		
		//change turn/dont double move
		if(!sourcePiece.equals(Piece.UNKNOWN_PIECE) && sourcePiece.getColor() != state.getTurn()) {
			throw new StrategyException("not your turn");
		}
		
		if(!sourcePiece.equals(Piece.UNKNOWN_PIECE)) {
			final int distance = state.getBoard().getDistance(source, destination);
			final int range = sourcePiece.getType().getRange();
			if(range >= 0 && distance > range) {
				throw new StrategyException("Cannot move piece farther than its range");
			}
			
			if (sourcePiece.getColor().equals(destinationPiece.getColor())) {
				throw new StrategyException("Cannot move onto a friendly piece");
			}
		}

		if (!state.getBoard().isPathValid(source, destination)) {
			throw new StrategyException("Cannot move through occupied spaces or water");
		}
	}

	@Override
	public BattleResult resolveBattle(Piece attacker, Piece defender) {
		if(defender.getType().equals(PieceType.FLAG)){
			state.setWinner(attacker.getColor());
		}
		return determineBattleResult(attacker, defender);
	}
	
	/**
	 * Determines the result of a battle between the two pieces.
	 * Does not change the state of the board.
	 * @see BetaMovementStrategy#resolveBattle(Piece, Piece)
	 * 
	 * @param attacker
	 * @param defender
	 * @return The result of the battle
	 */
	public static BattleResult determineBattleResult(Piece attacker, Piece defender) {
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
