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
package strategy.playeratcahgsr.delta;

import static org.junit.Assert.*;
import org.junit.*;
import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.playeratcahgsr.common.StrategyGame;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PiecePositionAssociation;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.StrategyGameFactory;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;
import static strategy.playeratcahgsr.common.PieceType.*;

/**
 *  This is a set of test cases for the DeltaStrategyGame that
 *  students can get started with.
 *  
 * @author gpollice
 * @version Sep 29, 2011
 */
public class StartDeltaStrategyGameTest
{
	private PiecePositionAssociation[] initialRedConfiguration;
	private PiecePositionAssociation[] initialBlueConfiguration;

	private PieceType[] redPieces = {
			BOMB, 		SERGEANT, 	MINER, 		FLAG, 		MAJOR, 		// (0, 0)..(0, 4)
			BOMB, 		BOMB, 		CAPTAIN, 	BOMB, 		MINER, 		// (0, 5)..(0, 9)
			SPY, 		COLONEL, 	LIEUTENANT, CAPTAIN, 	SCOUT, 		// (1, 0)..(1, 4)
			SERGEANT, 	MAJOR, 		SERGEANT, 	COLONEL, 	LIEUTENANT, // (1, 5)..(1, 9)
			CAPTAIN, 	SERGEANT, 	MARSHAL, 	MINER, 		LIEUTENANT, // (2, 0)..(2, 4)
			CAPTAIN, 	GENERAL, 	SCOUT, 		LIEUTENANT, MINER, 		// (2, 5)..(2, 9)
			MAJOR, 		SCOUT, 		SCOUT, 		SCOUT, 		BOMB, 		// (3, 0)..(3, 4)
			MINER, 		SCOUT, 		BOMB, 		SCOUT, 		SCOUT		// (3, 5)..(3, 9)
	};

	private PieceType[] bluePieces = {
			SCOUT, 		SCOUT, 		SERGEANT, 	MINER, 		SCOUT, 		// (6, 0)..(6, 4)
			SCOUT, 		CAPTAIN, 	MINER, 		SCOUT, 		SCOUT, 		// (6, 5)..(6, 9)
			LIEUTENANT, MAJOR, 		CAPTAIN, 	BOMB, 		LIEUTENANT, // (7, 0)..(7, 4)
			LIEUTENANT, COLONEL, 	SCOUT, 		MAJOR, 		CAPTAIN, 	// (7, 5)..(7, 9)
			MINER, 		SPY, 		SCOUT, 		CAPTAIN, 	SERGEANT, 	// (8, 0)..(8, 4)
			BOMB, 		BOMB, 		BOMB, 		MARSHAL, 	SERGEANT, 	// (8, 5)..(8, 9)
			MAJOR, 		GENERAL, 	SERGEANT, 	MINER, 		COLONEL, 	// (9, 0)..(9, 4)
			BOMB, 		FLAG, 		BOMB, 		MINER, 		LIEUTENANT	// (9, 5)..(9, 9)
	};

	/**
	 * Set up the initial configuration for the game. This is the board we start with:
	 * 
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  MAJ  |  GEN  |  SGT  |  MNR  |  COL  |   B   |   F   |   B   |  MNR  |  LT   |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  MNR  |  SPY  |  SCT  |  CPT  |  SGT  |   B   |   B   |   B   |  MSL  |  SGT  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  LT   |  MAJ  |  CPT  |   B   |  LT   |  LT   |  COL  |  SCT  |  CPT  |  SGT  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  SCT  |  SCT  |  SGT  |  MNR  |  SCT  |  SCT  |  CPT  |  MNR  |  SCT  |  SCT  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |       |       |   X   |  X    |       |       |   X   |   X   |       |       |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |       |       |   X   |  X    |       |       |   X   |   X   |       |       |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  MAJ  |  SCT  |  SCT  |  SCT  |   B   |  MNR  |  SCT  |   B   |  SCT  |  SCT  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  CPT  |  SGT  |  MSL  |  MNR  |  LT   |  CPT  |  GEN  |  SCT  |  LT   |  MNR  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |  SPY  |  COL  |  LT   |  CPT  |  SCT  |  SGT  |  MAJ  |  SGT  |  COL  |  LT   |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 * |       |       |       |       |       |       |       |       |       |       |
	 * |   B   |  SGT  |  MNR  |   F   |  MJR  |   B   |   B   |  CPT  |   B   |  MNR  |
	 * |       |       |       |       |       |       |       |       |       |       |
	 * +-------+-------+-------+-------+-------+-------+-------+-------+-------+-------+
	 */
	@Before
	public void setUp()
	{
		// set up red
		initialRedConfiguration = new PiecePositionAssociation[40];
		int ix = 0;
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 10; col++) {
				initialRedConfiguration[ix] = new PiecePositionAssociation(
						new Piece(redPieces[ix], PlayerColor.RED), new Position(row,
								col));
				ix++;
			}
		}

		// set up blue
		initialBlueConfiguration = new PiecePositionAssociation[40];
		ix = 0;
		for (int row = 6; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				initialBlueConfiguration[ix] = new PiecePositionAssociation(
						new Piece(bluePieces[ix], PlayerColor.BLUE), new Position(
								row, col));
				ix++;
			}
		}
	}

	/**
	 * Test #1: make sure that the factory method returns a valid game.
	 */
	@Test
	public void testCanMakeDeltaStrategyGame()
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
		assertTrue(game instanceof DeltaStrategyGame);
	}
	
	/**
	 * Test #2: if there is no Blue flag in the starting configuration, there
	 * should be some sort of an exception.
	 */
	@Test(expected=java.lang.Exception.class)
	public void testMissingBlueFlag()
	{
		initialBlueConfiguration[36] = new PiecePositionAssociation(
				new Piece(BOMB, PlayerColor.BLUE),
				new Position(9, 6));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
	}
	
	/**
	 * Test #3: make sure that a piece cannot move onto a choke point.
	 */
	@Test(expected=StrategyException.class)
	public void testPieceCannotMoveOntoChokePoint() throws StrategyException
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
		game.move(new Position(3, 2), new Position(4, 2));
	}
	
	/**
	 * Test #4: make sure that a scout cannot move over choke points.
	 */
	@Test(expected=StrategyException.class)
	public void testScoutCannotMoveOverChokePoint() throws StrategyException
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
		game.move(new Position(3, 2), new Position(6, 2));
	}
	
	/**
	 * Test #5: make sure that a simple movement works properly.
	 */
	@Test
	public void testSimplePieceMove() throws StrategyException
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
		assertEquals(new Piece(MAJOR, PlayerColor.RED),
				game.move(new Position(3, 0), new Position(4, 0)));
		assertEquals(Piece.NULL_PIECE, game.getPieceAt(new Position(3, 0)));
		assertEquals(new Piece(MAJOR, PlayerColor.RED), 
				game.getPieceAt(new Position(4, 0)));
	}
}