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
 * This abstract class defines the behavior expected of all versions 
 * of the initialization rules for Strategy.
 * Implementations are responsible for getting the GameState into the proper starting state.
 * 
 * @author Alex Thortnon-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 29, 2011
 */
public abstract class InitializationStrategy {

	protected GameState state;
	
	/**
	 * Constructs a new InitializationStrategy with the given GameState
	 * 
	 * @param state The GameState this InitializationStrategy should modify 
	 */
	protected InitializationStrategy(GameState state) {
		this.state = state;
	}
	
	/**
	 * Initialize (reset) the game.
	 * Clears the game board.
	 * Should be able to reset the board at any time.
	 * 
	 */
	public abstract void initialize();
	
}
