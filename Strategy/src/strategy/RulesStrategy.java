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

/**
 * This interface defines the behavior expected of all versions of the rules for Strategy.
 * 
 * @author Alex Thortnon-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 14, 2011
 */
public abstract class RulesStrategy {

	protected GameState state;
	
	/**
	 * The possible results of a battle.
	 */
	public enum BattleResult {
		DEFEAT, VICTORY, DRAW
	}
	
	/**
	 * @param state  The GameState this RulesStrategy should modify 
	 */
	protected RulesStrategy(GameState state) {
		this.state = state;
	}
	
	/**
	 * Moves the piece at source to destination.
	 * Calls resolveBattle if necessary.
	 * 
	 * @param source
	 * 			the coordinates of the moving piece
	 * @param destination
	 * 			the destination coordinates
	 * @return the Piece that is at the destination space after the move is complete
	 * @throws StrategyException
	 *             if:
	 *             <ul>
	 *             <li>There is no piece of the moving player's color on the source space</li>
	 *             <li>The piece cannot legally move to the destination</li>
	 *             </ul>
	 */
	public abstract Piece makeMove(Position source, Position destination) throws StrategyException;
	
	/**
	 * If there is a winner, return the winner's color.
	 * 
	 * @return the winner's color if there is a winner or null if the game is not over or the 
	 * 			default winner of the specific ruleset.
	 */
	public PlayerColor getWinner(){
		return state.getWinner();
	}
	
	/**
	 * Determines the outcome of a battle between two Pieces.
	 * Detects whether the game has been won by this battle.
	 * 
	 * @param attacker The attacking Piece
	 * @param defender The defending Piece
	 * @return VICTORY if the attacker wins, DEFEAT if the defender wins,
	 * 		DRAW if both pieces lose
	 */
	public abstract BattleResult resolveBattle(Piece attacker, Piece defender);
	
	/**
	 * @return true if the game is over; false otherwise.
	 */
	public boolean isOver(){
		return state.isOver();
	}

}
