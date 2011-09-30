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
	private PiecePositionAssociation[] initialRedConfiguration;
	private PiecePositionAssociation[] initialBlueConfiguration;

	private PieceType[] redPieces = {
			BOMB, SERGEANT, MINER, FLAG, MAJOR, 			// (0, 0)..(0, 4)
			BOMB, BOMB, CAPTAIN, BOMB, MINER, 				// (0, 5)..(0, 9)
			SPY, COLONEL, LIEUTENANT, CAPTAIN, SCOUT, 		// (1, 0)..(1, 4)
			SERGEANT, MAJOR, SERGEANT, COLONEL, LIEUTENANT, // (1, 5)..(1, 9)
			CAPTAIN, SERGEANT, MARSHAL, MINER, LIEUTENANT, // (2, 0)..(2, 4)
			CAPTAIN, GENERAL, SCOUT, LIEUTENANT, MINER, 	// (2, 5)..(2, 9)
			MAJOR, SCOUT, SCOUT, SCOUT, BOMB, 				// (3, 0)..(3, 4)
			MINER, SCOUT, BOMB, SCOUT, SCOUT				// (3, 5)..(3, 9)
	};

	private PieceType[] bluePieces = {
			SCOUT, SCOUT, SERGEANT, MINER, SCOUT, 			// (6, 0)..(6, 4)
			SCOUT, CAPTAIN, MINER, SCOUT, SCOUT, 			// (6, 5)..(6, 9)
			LIEUTENANT, MAJOR, CAPTAIN, BOMB, LIEUTENANT, 	// (7, 0)..(7, 4)
			LIEUTENANT, COLONEL, SCOUT, MAJOR, CAPTAIN, 	// (7, 5)..(7, 9)
			MINER, SPY, SCOUT, CAPTAIN, SERGEANT, 			// (8, 0)..(8, 4)
			BOMB, BOMB, BOMB, MINER, SERGEANT, 				// (8, 5)..(8, 9)
			MAJOR, GENERAL, SERGEANT, MINER, COLONEL, 		// (9, 0)..(9, 4)
			BOMB, FLAG, BOMB, MINER, LIEUTENANT				// (9, 5)..(9, 9)
	};

	/**
	 * @throws java.lang.Exception
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

	@Test
	public void testCanMakeDeltaStrategyGame()
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
		assertEquals(new Piece(FLAG, PlayerColor.RED), game.getPieceAt(new Position(0, 3)));
	}
	
	@Ignore
	@Test(expected=java.lang.Exception.class)
	public void testMissingBlueFlag()
	{
		initialBlueConfiguration[36] = new PiecePositionAssociation(
				new Piece(BOMB, PlayerColor.BLUE),
				new Position(9, 6));
		StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				initialRedConfiguration, initialBlueConfiguration);
	}
}
