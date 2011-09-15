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

import strategy.StrategyException;

/**
 * Test case for the AlphaStrategy game.
 * 
 * @author gpollice, Andrew Hurle
 * @version Aug 29, 2011
 */
public class AlphaStrategyGameTest
{
	private AlphaStrategyGame game;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()
	{
		game = new AlphaStrategyGame();
		game.initialize();
	}

	@Test
	public void testStartingConfiguration() throws StrategyException
	{
		assertEquals(SCOUT, game.getPieceAt(0, 0).getType());
		assertEquals(RED, game.getPieceAt(0, 0).getColor());
		assertEquals(FLAG, game.getPieceAt(0, 1).getType());
		assertEquals(RED, game.getPieceAt(0, 1).getColor());
		assertEquals(FLAG, game.getPieceAt(1, 0).getType());
		assertEquals(BLUE, game.getPieceAt(1, 0).getColor());
		assertEquals(SCOUT, game.getPieceAt(1, 1).getType());
		assertEquals(BLUE, game.getPieceAt(1, 1).getColor());
		assertEquals(false, game.isOver());
		assertEquals(null, game.getWinner());
	}
	
	@Test(expected=StrategyException.class)
	public void testGetPieceBoundsHeightOverflow() throws StrategyException
	{
		game.getPieceAt(3, 0);
	}
	
	@Test(expected=StrategyException.class)
	public void testGetPieceBoundsHeightUnderflow() throws StrategyException
	{
		game.getPieceAt(-1, 0);
	}
	
	@Test(expected=StrategyException.class)
	public void testGetPieceBoundsWidthOverflow() throws StrategyException
	{
		game.getPieceAt(0, 3);
	}
	
	@Test(expected=StrategyException.class)
	public void testGetPieceBoundsWidthUnderflow() throws StrategyException
	{
		game.getPieceAt(0, -1);
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOntoOwnSpace() throws StrategyException
	{
		game.move(0, 0, 0, 0);
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOntoFriendlyPiece() throws StrategyException
	{
		game.move(0, 0, 0, 1);
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOutOfTurn() throws StrategyException
	{
		game.move(1, 1, 0, 1);
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingUnmovablePiece() throws StrategyException
	{
		game.move(0, 1, 1, 1);
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingDiagonally() throws StrategyException
	{
		game.move(0, 0, 1, 1);
	}
	
	@Test
	public void testRedWinning() throws StrategyException
	{
		game.move(0, 0, 1, 0);
		assertEquals(SCOUT, game.getPieceAt(1, 0).getType());
		assertEquals(RED, game.getPieceAt(1, 0).getColor());
		assertEquals(null, game.getPieceAt(0, 0));
		assertEquals(true, game.isOver());
		assertEquals(RED, game.getWinner());
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingAfterWinning() throws StrategyException
	{
		game.move(0, 0, 1, 0);
		game.move(1, 1, 0, 1);
	}
	
}
