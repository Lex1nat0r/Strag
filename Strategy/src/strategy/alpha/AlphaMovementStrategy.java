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
package strategy.alpha;

import strategy.GameState;
import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.MovementStrategy;
import strategy.StrategyException;

/**
 * This class defines the rules of moving pieces 
 * and resolving battles for AlphaStrategy.
 * These movement and battle rules support scouts capturing flags.
 * The game will end after the first turn.
 *
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class AlphaMovementStrategy extends MovementStrategy {

	public AlphaMovementStrategy(GameState state) {
		super(state);
	}

	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(state.isOver()) {
			throw new StrategyException("Cannot move after game is over");
		}
		if(state.getBoard().getPieceAt(source).getType() == PieceType.FLAG) {
			throw new StrategyException("Cannot move flag");
		}
		if(state.getBoard().getPieceAt(source).getColor() != state.getTurn()) {
			throw new StrategyException("Cannot move out of turn");
		}
		if(source.equals(destination)) {
			throw new StrategyException("Must move to a different space");
		}
		if(source.isDiagonal(destination)) {
			throw new StrategyException("Cannot move diagonally");
		}
		if(state.getBoard().getPieceAt(source).getColor()
				== state.getBoard().getPieceAt(destination).getColor()) {
			throw new StrategyException("Cannot move to a space containing a friendly unit");
		}
		
		resolveBattle(state.getBoard().getPieceAt(source), 
				state.getBoard().getPieceAt(destination));
		state.getBoard().putPieceAt(destination, state.getBoard().getPieceAt(source));
		state.getBoard().putPieceAt(source, Piece.NULL_PIECE);
		return state.getBoard().getPieceAt(destination);
	}

	@Override
	public BattleResult resolveBattle(strategy.Piece attacker,
			strategy.Piece defender) {
		state.setWinner(PlayerColor.RED);
		return BattleResult.VICTORY;
	}

}
