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
		rules = new BetaRulesStrategy(playerPlacePiece);
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
	
	/**
	 * @see RulesStrategy#playerPlacePiece
	 * @param position
	 * @param piece
	 * @throws StrategyException
	 */
	public void playerPlacePiece(Position position, Piece piece) throws StrategyException {
		rules.playerPlacePiece(position, piece);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(this == other) {
			return true;
		}
		if (other instanceof BetaStrategyGame) {
			final BetaStrategyGame that = (BetaStrategyGame) other;
			return rules.equals(that.getRules());
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + rules.hashCode();
		return result;
	}
	
	protected BetaRulesStrategy getRules() {
		return rules;
	}
	
	/**
	 * @see BetaRulesStrategy#setBoard
	 * @param board
	 */
	protected void setBoard(RectangularStrategyBoard board) {
		rules.setBoard(board);
	}
	
	protected RectangularStrategyBoard getBoard() {
		return rules.getBoard();
	}
	
	protected int getNumPiecesOnBoard() {
		return rules.getNumPiecesOnBoard();
	}
	
}
