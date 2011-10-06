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
package strategy.playeratcahgsr.common;

import static org.junit.Assert.*;

import org.junit.*;
import strategy.*;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.RectangularStrategyBoard;
import static strategy.common.PlayerColor.*;
import static strategy.playeratcahgsr.common.PieceType.*;

/**
 * Test cases for the RectangularBoard class
 * @author gpollice
 * @version Aug 1, 2011
 */
public class RectangularStrategyBoardMasterTest
{
	private RectangularStrategyBoard board;
	
	@Before
	public void setup()
	{
		board = new RectangularStrategyBoard(8, 8);
	}
	
	@Test
	public void testInitialRectangularBoardIsEmpty() throws Exception
	{
		for (Piece p : board) {
			assertTrue(p == null || p == Piece.NULL_PIECE);
		}
	}

	@Test
	public void testPlacePieceOnEmptySpace() throws Exception
	{
		board.putPieceAt(new Position(0, 0), new Piece(MAJOR, RED));
		assertEquals(new Piece(MAJOR, RED), board.getPieceAt(new Position(0, 0)));
	}
	
	@Test
	public void testPlacePieceOnOccupiedSpace() throws Exception
	{
		board.putPieceAt(new Position(0, 0), new Piece(MAJOR, RED));
		board.putPieceAt(new Position(0, 0), new Piece(COLONEL, BLUE));
		assertEquals(new Piece(COLONEL, BLUE), board.getPieceAt(new Position(0, 0)));
	}
	
	@Test
	public void testSpaceIsOccupied() throws Exception
	{
		assertFalse(board.isOccupied(new Position(0, 0)));
		board.putPieceAt(new Position(0, 1), new Piece(MAJOR, RED));
		assertTrue(board.isOccupied(new Position(0, 1)));
	}
	
	@Test
	public void testDistanceToSelf() throws Exception
	{
		Position c = new Position(3, 3);
		assertEquals(0, board.getDistance(c, c));
	}
	
	@Test
	public void testDistanceToNextRow() throws Exception
	{
		Position c1 = new Position(3, 3);
		Position c2 = new Position(4, 3);
		assertEquals(1, board.getDistance(c1, c2));
	}
	
	@Test
	public void testDistanceToNextColumn() throws Exception
	{
		Position c1 = new Position(3, 3);
		Position c2 = new Position(3, 4);
		assertEquals(1, board.getDistance(c1, c2));
	}	
}