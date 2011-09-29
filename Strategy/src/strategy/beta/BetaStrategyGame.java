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
 *    gpollice
 *******************************************************************************/
package strategy.beta;

import strategy.*;
import strategy.common.RectangularStrategyBoard;

/**
 * Implementation of the BetaStrategy game.
 * @author gpollice, Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 11, 2011
 */
public class BetaStrategyGame implements StrategyGame
{
	
	private BetaRulesStrategy rules;
	private final GameState state;
	private final BetaInitializationStrategy init;
	
	public BetaStrategyGame() {
		this(false);
	}
	
	/**
	 * Constructs a BetaStrategyGame.
	 * If playerPlacePiece is true then the player can place pieces.
	 * 
	 * @param playerPlacePiece
	 */
	public BetaStrategyGame(boolean playerPlacePiece) {
		state = new GameState();
		if (!playerPlacePiece) {
			init = new RandomInitializationStrategy(state);
		}
		else {
			init = new ByPieceInitializationStrategy(state);
		}
		rules = new BetaRulesStrategy(state);
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
		return rules.makeMove(source, destination);
	}
	
	/**
	 * Place Piece piece at Position position on this board.
	 * 
	 * @param position The position to place the Piece at
	 * @param piece The Piece to place on the board
	 * @throws StrategyException if a player is attempting to place a piece incorrectly
	 */
	public void playerPlacePiece(Position position, Piece piece) throws StrategyException {
		init.playerPlacePiece(position, piece);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(this == other) {
			return true;
		}
		if (other instanceof BetaStrategyGame) {
			final BetaStrategyGame that = (BetaStrategyGame) other;
			return state.equals(that.getState());
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + state.hashCode();
		return result;
	}
	
	protected BetaRulesStrategy getRules() {
		return rules;
	}
	
	protected GameState getState() {
		return state;
	}
	
	/**
	 * @see BetaRulesStrategy#setBoard
	 * @param board
	 */
	protected void setBoard(RectangularStrategyBoard board) {
		state.setBoard(board);
	}
	
	protected RectangularStrategyBoard getBoard() {
		return state.getBoard();
	}
	
}
