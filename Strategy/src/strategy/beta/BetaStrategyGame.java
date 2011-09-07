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

/**
 * Implementation of the BetaStrategy game.
 * @author gpollice
 * @version Aug 1, 2011
 */
public class BetaStrategyGame implements StrategyGame
{
	/**
	 * Default constructor. Initializes the game.
	 */
	public BetaStrategyGame()
	{
		
	}
	
	@Override
	public void initializeGame()
	{
		
	}
	
	@Override
	public Piece move(Position source, Position destination) throws StrategyException
	{
		return null;
	}

	@Override
	public boolean isGameOver()
	{
		return false;
	}

	@Override
	public PlayerColor getWinner()
	{
		return null;
	}

	@Override
	public Piece getPieceAt(Position position)
	{
		return null;
	}

}
