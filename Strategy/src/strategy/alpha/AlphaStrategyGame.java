/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: gpollice, Andrew Hurle
 *******************************************************************************/
package strategy.alpha;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.common.StrategyGame;
import strategy.playeratcahgsr.common.GameState;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.Position;

/**
 * The main Game for AlphaStrategy. All interactions with the game go through this class.
 * 
 * @author gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Aug 29, 2011
 */
public class AlphaStrategyGame implements StrategyGame
{
	private final GameState state;
	private final AlphaMovementStrategy movement;
	private final AlphaInitializationStrategy init;
	
	public AlphaStrategyGame() {
		state = new GameState();
		movement = new AlphaMovementStrategy(state);
		init = new AlphaInitializationStrategy(state);
		initializeGame();
	}

	@Override
	public Piece getPieceAt(Position position) {
		return state.getBoard().getPieceAt(position);
	}

	@Override
	public PlayerColor getWinner() {
		return state.getWinner();
	}

	@Override
	public void initializeGame() {
		init.initialize();
		
	}

	@Override
	public boolean isGameOver() {
		return state.isOver();
	}

	@Override
	public Piece move(Position source, Position destination)
			throws StrategyException {
		return movement.makeMove(source, destination);
	}
	
}
