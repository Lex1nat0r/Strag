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

package strategy.alpha;

import strategy.GameState;
import strategy.InitializationStrategy;
import strategy.PlayerColor;
import strategy.Position;
import strategy.common.RectangularStrategyBoard;

/**
 * Defines the initialization method for the AlphaStrategyGame
 * 
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 29, 2011
 */
public class AlphaInitializationStrategy extends InitializationStrategy {

	private static final int width = 2;
	private static final int height = 2;
	
	public AlphaInitializationStrategy(GameState state)
	{
		super(state);
	}
	
	@Override
	public void initialize() {
		state.setWinner(null);
		state.setTurn(PlayerColor.RED);
		state.setBoard(new RectangularStrategyBoard(height, width));
		state.getBoard().putPieceAt(new Position(0, 0), 
				new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.RED));
		state.getBoard().putPieceAt(new Position(0, 1), 
				new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.RED));
		state.getBoard().putPieceAt(new Position(1, 0), 
				new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.BLUE));
		state.getBoard().putPieceAt(new Position(1, 1), 
				new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.BLUE));
	}

}
