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
package strategy.common;

import java.util.Iterator;
import strategy.*;

/**
 *
 * @author gpollice
 * @version Sep 2, 2011
 */
public class RectangularStrategyBoard implements StrategyBoard
{
	/**
	 * Constructor for a rectangular board.
	 * 
	 * @param rows
	 * @param columns
	 */
	public RectangularStrategyBoard(int rows, int columns)
	{
		
	}
	
	/*
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Piece> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see strategy.StrategyBoard#getPieceAt(strategy.Position)
	 */
	@Override
	public Piece getPieceAt(Position position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see strategy.StrategyBoard#putPieceAt(strategy.Position, strategy.Piece)
	 */
	@Override
	public void putPieceAt(Position position, Piece piece)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * @see strategy.StrategyBoard#isOccupied(strategy.Position)
	 */
	@Override
	public boolean isOccupied(Position position)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see strategy.StrategyBoard#getDistance(strategy.Position, strategy.Position)
	 */
	@Override
	public int getDistance(Position from, Position to)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Initialize the board to the starting configuration.
	 */
	public void initializeBoard()
	{
		
	}
	
	@Override
	public String toString()
	{
		return null;
	}
}
