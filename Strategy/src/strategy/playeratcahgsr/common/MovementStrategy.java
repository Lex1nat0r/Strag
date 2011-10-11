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
package strategy.playeratcahgsr.common;

import strategy.common.StrategyException;

/**
 * This interface defines the behavior expected of 
 * all versions of the movement rules for Strategy.
 * Implementations are responsible for moving pieces
 * and resolving battles.
 * 
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public abstract class MovementStrategy {

	protected GameState state;
	
	/**
	 * @param state  The GameState this MovementStrategy should modify 
	 */
	protected MovementStrategy(GameState state) {
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
	 * Determines the outcome of a battle between two Pieces.
	 * Detects whether the game has been won by this battle.
	 * 
	 * @param attacker The attacking Piece
	 * @param defender The defending Piece
	 * @return VICTORY if the attacker wins, DEFEAT if the defender wins,
	 * 		DRAW if both pieces lose
	 */
	protected abstract BattleResult resolveBattle(Piece attacker, Piece defender);

}
