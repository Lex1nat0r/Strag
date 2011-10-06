/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2011 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Author: gpollice, Andrew Hurle
 *******************************************************************************/
package strategy.alpha;

import static org.junit.Assert.assertEquals;
import static strategy.PlayerColor.*;
import static strategy.PieceType.*;
import org.junit.*;

import strategy.Piece;
import strategy.Position;
import strategy.StrategyException;

/**
 * Test case for the AlphaStrategy game.
 * 
 * @author gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Aug 29, 2011
 */
public class AlphaStrategyGameTest
{
	private AlphaStrategyGame game;
	/**
	 * @throws StrategyException 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws StrategyException
	{
		game = new AlphaStrategyGame();
	}

	@Test
	public void testStartingConfiguration() throws StrategyException
	{
		assertEquals(SCOUT, game.getPieceAt(new Position(0, 0)).getType());
		assertEquals(RED, game.getPieceAt(new Position(0, 0)).getColor());
		assertEquals(FLAG, game.getPieceAt(new Position(0, 1)).getType());
		assertEquals(RED, game.getPieceAt(new Position(0, 1)).getColor());
		assertEquals(FLAG, game.getPieceAt(new Position(1, 0)).getType());
		assertEquals(BLUE, game.getPieceAt(new Position(1, 0)).getColor());
		assertEquals(SCOUT, game.getPieceAt(new Position(1, 1)).getType());
		assertEquals(BLUE, game.getPieceAt(new Position(1, 1)).getColor());
		assertEquals(false, game.isGameOver());
		assertEquals(null, game.getWinner());
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsHeightOverflow() throws ArrayIndexOutOfBoundsException
	{
		game.getPieceAt(new Position(3, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsHeightUnderflow() throws ArrayIndexOutOfBoundsException
	{
		game.getPieceAt(new Position(-1, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsWidthOverflow() throws ArrayIndexOutOfBoundsException
	{
		game.getPieceAt(new Position(0, 3));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsWidthUnderflow() throws ArrayIndexOutOfBoundsException
	{
		game.getPieceAt(new Position(0, -1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOntoOwnSpace() throws StrategyException
	{
		game.move(new Position(0, 0), new Position(0, 0));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOntoFriendlyPiece() throws StrategyException
	{
		game.move(new Position(0, 0), new Position(0, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOutOfTurn() throws StrategyException
	{
		game.move(new Position(1, 1), new Position(0, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingUnmovablePiece() throws StrategyException
	{
		game.move(new Position(0, 1), new Position(1, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingDiagonally() throws StrategyException
	{
		game.move(new Position(0, 0), new Position(1, 1));
	}
	
	@Test
	public void testRedWinning() throws StrategyException
	{
		game.move(new Position(0, 0), new Position(1, 0));
		assertEquals(SCOUT, game.getPieceAt(new Position(1, 0)).getType());
		assertEquals(RED, game.getPieceAt(new Position(1, 0)).getColor());
		assertEquals(Piece.NULL_PIECE, game.getPieceAt(new Position(0, 0)));
		assertEquals(true, game.isGameOver());
		assertEquals(RED, game.getWinner());
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingAfterWinning() throws StrategyException
	{
		game.move(new Position(0, 0), new Position(1, 0));
		game.move(new Position(1, 1), new Position(0, 1));
	}
	
}
