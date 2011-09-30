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

package strategy.delta;

import strategy.GameState;
import strategy.InitializationStrategy;
import strategy.PlayerColor;
import strategy.common.PiecePositionAssociation;
import strategy.common.RectangularStrategyBoard;

/**
 * Defines the initialization method for the DeltaStrategyGame
 * 
 * @author Alex Thortnon-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class DeltaInitializationStrategy extends InitializationStrategy {

	private static final int width = 10;
	private static final int height = 10;
	
	public DeltaInitializationStrategy(GameState state) {
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
	 * Initialize (reset) the game.
	 * Clears the game board, then places all the pieces
	 * from startingRedPieces and startingBluePieces on 
	 * the board, assuming the configurations are correct.
	 * Should be able to reset the board at any time.
	 * 
	 * @param startingRedPieces the red Pieces to place on the board when it is initialized 
	 * @param startingBluePieces the blue Pieces to place on the board when it is initialized
	 */
	public void initialize(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		initialize();
		
		for (PiecePositionAssociation i : startingRedPieces) {
			state.getBoard().putPieceAt(i.getPosition(), i.getPiece());
		}
		
		for (PiecePositionAssociation i : startingBluePieces) {
			state.getBoard().putPieceAt(i.getPosition(), i.getPiece());
		}
	}

}
