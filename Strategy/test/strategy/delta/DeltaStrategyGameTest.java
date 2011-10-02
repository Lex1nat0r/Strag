/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: gpollice
 *******************************************************************************/
package strategy.delta;

import static org.junit.Assert.*;
import org.junit.*;
import strategy.*;
import strategy.common.PiecePositionAssociation;
import static strategy.PieceType.*;

/**
 * 
 * @author gpollice
 * @version Sep 29, 2011
 */
public class DeltaStrategyGameTest
{

	private PiecePositionAssociation[] redConfig;
	private PiecePositionAssociation[] blueConfig;
	private DeltaStrategyGame game;
	
	@Before
	public void setUp(){
		redConfig = PieceConfiguration.getInstance().getInitialRedConfiguration();
		blueConfig = PieceConfiguration.getInstance().getInitialBlueConfiguration();
		game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				redConfig, blueConfig);
	}
	
	@Test
	public void testInitialState()
	{
		assertEquals(new Piece(FLAG, PlayerColor.RED), game.getPieceAt(new Position(0, 3)));
		assertEquals(Piece.WATER_PIECE, game.getPieceAt(new Position(5, 2)));
		assertEquals(Piece.WATER_PIECE, game.getPieceAt(new Position(5, 6)));
		assertNull(game.getWinner());
		assertFalse(game.isGameOver());
		assertEquals( "jngmcbfbrt\n"
					+ "ryspgbbbrg\n"
					+ "tjpbttcsjp\n"
					+ "ssgrssprss\n"
					+ "NNWWNNWWNN\n" 
					+ "NNWWNNWWNN\n"
					+ "jsssbrsbss\n"
					+ "pgmrtpnstr\n"
					+ "yctpsgjgct\n"
					+ "bgrfjbbpbr\n", game.getBoard().toString());
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testMissingBlueFlag()
	{
		PiecePositionAssociation initialBlueConfiguration[] = {new PiecePositionAssociation(
				new Piece(BOMB, PlayerColor.BLUE),
				new Position(9, 6))};
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				redConfig, initialBlueConfiguration);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testWrongColorInInitialConfiguration()
	{
		redConfig[2] = new PiecePositionAssociation(new Piece(BOMB, PlayerColor.BLUE), 
				new Position(1, 1));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testWrongNumberOfBombsInInitialConfiguration()
	{
		redConfig[39] = new PiecePositionAssociation(new Piece(BOMB, PlayerColor.RED), 
				new Position(3, 9));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testNoRedPieceInNeutralStartingZoneInInitialConfiguration()
	{
		redConfig[39] = new PiecePositionAssociation(new Piece(SCOUT, PlayerColor.RED), 
				new Position(4, 9));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testNoBluePieceInNeutralStartingZoneInInitialConfiguration()
	{
		blueConfig[39] = new PiecePositionAssociation(new Piece(LIEUTENANT, PlayerColor.BLUE), 
				new Position(4, 9));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testNoPieceOutsideGameBoardInInitialConfiguration()
	{
		blueConfig[39] = new PiecePositionAssociation(new Piece(LIEUTENANT, PlayerColor.BLUE), 
				new Position(9, 10));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testNoOverlappingRedPieces()
	{
		redConfig[39] = new PiecePositionAssociation(new Piece(SCOUT, PlayerColor.RED), 
				new Position(0, 0));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testNoOverlappingBluePieces()
	{
		blueConfig[39] = new PiecePositionAssociation(new Piece(LIEUTENANT, PlayerColor.BLUE), 
				new Position(6, 0));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(redConfig, blueConfig);
	}
	
	@Test
	public void testInitializingAgainDoesntBreak() throws StrategyException
	{
		game.initializeGame(redConfig, blueConfig);
		game.move(new Position(3,1), new Position(4,1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveRedScoutOntoWater() throws StrategyException
	{
		game.move(new Position(3,2), new Position(4,2));
	}
}
