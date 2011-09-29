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
package strategy;

import strategy.common.RectangularStrategyBoard;

/**
 * Responsible for holding the state of a game (winner, turn, and board)
 * so that multiple clients can alter it easily.
 * 
 * Just a bunch of getters and setters.  Not to be confused with the "State" pattern.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 29, 2011
 */
public class GameState {

	private PlayerColor winner;
	private PlayerColor turn;
	private RectangularStrategyBoard board;
	
	/**
	 * If there is a winner, return the winner's color.
	 * 
	 * @return the winner's color if there is a winner or null if the game is not over or the 
	 * 			default winner of the specific ruleset.
	 */
	public PlayerColor getWinner() {
		return winner;
	}
	
	/**
	 * @param winner  The color to set the winner to
	 */
	public void setWinner(PlayerColor winner) {
		this.winner = winner;
	}

	/**
	 * @return true if the game is over; false otherwise.
	 */
	public boolean isOver() {
		return winner != null;
	}
	
	/**
	 * @return the color of the player whose turn it is
	 */
	public PlayerColor getTurn() {
		return turn;
	}
	
	/**
	 * @param turn The color to set the turn to
	 */
	public void setTurn(PlayerColor turn) {
		this.turn = turn;
	}
	
	/**
	 * @return The RectangularStrategyBoard for this game
	 */
	public RectangularStrategyBoard getBoard() {
		return board;
	}
	
	/**
	 * @param board  The RectangularStrategyBoard to set to
	 */
	public void setBoard(RectangularStrategyBoard board) {
		this.board = board;
	}
	
}
