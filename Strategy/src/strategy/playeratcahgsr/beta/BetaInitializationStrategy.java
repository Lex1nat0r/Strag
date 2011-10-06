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

package strategy.playeratcahgsr.beta;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.playeratcahgsr.common.GameState;
import strategy.playeratcahgsr.common.InitializationStrategy;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.RectangularStrategyBoard;

/**
 * Defines some shared behavior and fields for the BetaStrategyGame
 * initialization strategies.
 * 
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Oct 1, 2011
 */
public abstract class BetaInitializationStrategy extends InitializationStrategy {

	private static final int width = 6;
	private static final int height = 6;
	
	/**
	 * @see InitializationStrategy#InitializationStrategy(GameState state)
	 * @param state
	 */
	protected BetaInitializationStrategy(GameState state) {
		super(state);
	}

	@Override
	public void initialize() {
		state.setBoard(new RectangularStrategyBoard(width, height));
		state.setNumMoves(0);
		state.setWinner(null);
		state.setTurn(PlayerColor.RED);
	}
	
	/**
	 * Place Piece piece at Position position on this board,
	 * if the strategy allows it.
	 * 
	 * @param position The position to place the Piece at
	 * @param piece The Piece to place on the board
	 * @throws StrategyException if a player is attempting to place a piece incorrectly
	 */
	public abstract void playerPlacePiece(Position position, Piece piece) throws StrategyException;

}
