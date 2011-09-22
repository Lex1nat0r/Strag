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
	private final AlphaRulesStrategy rules;
	
	public AlphaStrategyGame() throws StrategyException {
		rules = new AlphaRulesStrategy();
		initializeGame();
	}

	@Override
	public Piece getPieceAt(Position position) throws StrategyException {
		return rules.getPieceAt(position);
	}

	@Override
	public PlayerColor getWinner() {
		return rules.getWinner();
	}

	@Override
	public void initializeGame() throws StrategyException {
		rules.initialize();
		
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
