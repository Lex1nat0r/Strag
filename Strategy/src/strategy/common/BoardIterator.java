/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: gpollice, alextc, andrewdhurle
 *******************************************************************************/
package strategy.common;

import java.util.Iterator;

import strategy.Piece;

/**
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 11, 2011
 */
public class BoardIterator implements Iterator<Piece> {

	private Piece next;
	private final Piece[][] pieces;
	private int row;
	private int previousRow;
	private int col;
	private int previousCol;
	
	/**
	 * @param board The board to iterate through
	 */
	public BoardIterator(Piece board[][]) {
		pieces = board;
		row = 0;
		col = 0;
		previousRow = 0;
		previousCol = 0;
		next = pieces[0][0];
	}
	
	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Piece next() {
		final Piece current = next;
		previousCol = col;
		previousRow = row;
		
		if (col >= pieces[row].length - 1) {
			col = 0;
			row++;
		}
		else {
			col++;
		}
		
		if (row >= pieces.length) {
			next = null;
		}
		else {
			next = pieces[row][col];
		}
		
		return current;
	}

	@Override
	//this should never be called as the size of the board is immutable
	public void remove() {
	}

	/**
	 * @return the row of the last Piece returned by next()
	 */
	public int getRow() {
		return previousRow;
	}
	
	/**
	 * @return the column of the last Piece returned by next()
	 */
	public int getColumn() {
		return previousCol;
	}

}
