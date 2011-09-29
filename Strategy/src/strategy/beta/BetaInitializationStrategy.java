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

package strategy.beta;

import strategy.GameState;
import strategy.InitializationStrategy;
import strategy.Piece;
import strategy.PlayerColor;
import strategy.Position;
import strategy.StrategyException;
import strategy.common.RectangularStrategyBoard;

public abstract class BetaInitializationStrategy extends InitializationStrategy {

	private static final int width = 6;
	private static final int height = 6;
	
	public BetaInitializationStrategy(GameState state) {
		super(state);
	}

	@Override
	public void initialize() {
		state.setBoard(new RectangularStrategyBoard(width, height));
		state.setNumMoves(0);
		state.setWinner(null);
		state.setTurn(PlayerColor.RED);
	}
	
	public void playerPlacePiece(Position position, Piece piece) throws StrategyException {
		throw new StrategyException("Player not allowed to place Piece");
	}

}
