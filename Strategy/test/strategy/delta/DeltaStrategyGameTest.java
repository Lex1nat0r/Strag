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
	
	@Before
	public void setUp(){
		redConfig = PieceConfiguration.getInstance().getInitialRedConfiguration();
		blueConfig = PieceConfiguration.getInstance().getInitialBlueConfiguration();
	}
	
	@Test
	public void testCanMakeDeltaStrategyGame()
	{
		StrategyGame game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				redConfig, blueConfig);
		assertEquals(new Piece(FLAG, PlayerColor.RED), game.getPieceAt(new Position(0, 3)));
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
}
