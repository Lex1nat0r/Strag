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

import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;

/**
 * Describes a move.  ComparablePlayerMoves can be compared with each other to
 * determine which move is better.
 * 
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 */
public class ComparablePlayerMove implements Comparable<ComparablePlayerMove> {

	private final Position from;
	private final Position to;
	private DeltaStrategyGame game;
	private MoveType type;

	/**
	 * @param from The Position this move is from
	 * @param to The Position this move is going to
	 * @param board The game that this movement is occurring on
	 */
	public ComparablePlayerMove(Position from, Position to, DeltaStrategyGame game) {
		this.from = from;
		this.to = to;
		this.game = game;
		setType();
	}
	
	private void setType() {
		boolean isValid = false;
		try {
			game.validateMove(from, to);
			isValid = true;
		} catch(Exception e) {
			System.err.print('\0');
		}
		if(isValid) {
			type = MoveType.VALID;
		}
		else {
			type = MoveType.INVALID;
		}
	}
	
	@Override
	public int compareTo(ComparablePlayerMove other) {
		return type.compareTo(other.getType());
	}

	/**
	 * @return the from
	 */
	public Position getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public Position getTo() {
		return to;
	}
	
	/**
	 * @return The type
	 */
	public MoveType getType() {
		return type;
	}

}
