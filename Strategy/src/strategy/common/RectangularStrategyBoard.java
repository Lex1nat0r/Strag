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
 * @author gpollice, alextc, andrewdhurle
 * @version Sep 8, 2011
 */
public class RectangularStrategyBoard implements StrategyBoard
{
	
	private Piece[][] pieces;
	private int numRows;
	private int numCols;
	
	/**
	 * Constructor for a rectangular board.
	 * 
	 * @param rows
	 * @param columns
	 */
	public RectangularStrategyBoard(int rows, int columns)
	{
		pieces = new Piece[rows][columns];
		numRows = rows;
		numCols = columns;
		
	}
	
	/*
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Piece> iterator()
	{
		return new BoardIterator(pieces);
	}

	/*
	 * @see strategy.StrategyBoard#getPieceAt(strategy.Position)
	 */
	@Override
	public Piece getPieceAt(Position position)
	{
		return pieces[position.getRow()][position.getColumn()];
	}

	/*
	 * @see strategy.StrategyBoard#putPieceAt(strategy.Position, strategy.Piece)
	 */
	@Override
	public void putPieceAt(Position position, Piece piece)
	{
		pieces[position.getRow()][position.getColumn()] = piece;
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
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				pieces[i][j] = Piece.NULL_PIECE;
			}
		}
	}
	
	@Override
	public String toString()
	{
		return null;
	}
}
