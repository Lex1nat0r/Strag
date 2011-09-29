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

import strategy.*;

/**
 * The main Game for AlphaStrategy. All interactions with the game go through this class.
 * 
 * @author gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Aug 29, 2011
 */
public class AlphaStrategyGame implements StrategyGame
{
	private final GameState state;
	private final AlphaRulesStrategy rules;
	private final AlphaInitializationStrategy init;
	
	public AlphaStrategyGame() {
		state = new GameState();
		rules = new AlphaRulesStrategy(state);
		init = new AlphaInitializationStrategy(state);
		initializeGame();
	}

	@Override
	public Piece getPieceAt(Position position) {
		return rules.getPieceAt(position);
	}

	@Override
	public PlayerColor getWinner() {
		return rules.getWinner();
	}

	@Override
	public void initializeGame() {
		init.initialize();
		
	}

	@Override
	public boolean isGameOver() {
		return rules.isOver();
	}

	@Override
	public Piece move(Position source, Position destination)
			throws StrategyException {
		return rules.makeMove(source, destination);
	}
	
}
