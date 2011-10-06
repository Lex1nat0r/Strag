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

package strategy.playeratcahgsr.delta;

import strategy.common.PlayerColor;
import strategy.common.StrategyBoard;
import strategy.common.StrategyException;
import strategy.common.StrategyGame;
import strategy.playeratcahgsr.common.GameState;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PiecePositionAssociation;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.RectangularStrategyBoard;

/**
 * The main Game for DeltaStrategy. All interactions with the game go through this class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class DeltaStrategyGame implements StrategyGame {

	private final GameState state;
	private final DeltaInitializationStrategy init;
	private final DeltaMovementStrategy movement;
	
	/**
	 * Constructs a DeltaStrategyGame.
	 * Calls the initializeGame method to place the Pieces in startingRedPieces
	 * and startingBluePieces on the board
	 * 
	 * @param startingRedPieces 
	 * 			the array of PiecePositionAssociations used to place the red Pieces on the board
	 * @param startingBluePieces 
	 * 			the array of PiecePositionAssociations used to place the blue Pieces on the board
	 */
	public DeltaStrategyGame(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		state = new GameState();
		init = new DeltaInitializationStrategy(state);
		movement = new DeltaMovementStrategy(state);
		initializeGame(startingRedPieces, startingBluePieces);
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
	
	/**
	 * Places the Pieces in startingRedPieces and startingBluePieces on the board
	 * 
	 * @param startingRedPieces 
	 * 		the array of PiecePositionAssociations used to place the red Pieces on the board
	 * @param startingBluePieces 
	 * 		the array of PiecePositionAssociations used to place the blue Pieces on the board 
	 */
	public void initializeGame(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		init.initialize(startingRedPieces, startingBluePieces);
	}

	@Override
	public boolean isGameOver() {
		return state.isOver();
	}

	@Override
	public Piece move(Position source, Position destination) throws StrategyException {
		return movement.makeMove(source, destination);
	}
	
	/**
	 * This method is used for testing purposes only.
	 * 
	 * @param board the board to set
	 */
	protected void setBoard(StrategyBoard board)
	{
		state.setBoard((RectangularStrategyBoard) board);
	}
	
	protected RectangularStrategyBoard getBoard() {
		return state.getBoard();
	}

}
