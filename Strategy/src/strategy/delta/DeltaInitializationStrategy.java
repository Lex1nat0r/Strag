/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 *******************************************************************************/

package strategy.delta;

import strategy.GameState;
import strategy.InitializationStrategy;
import strategy.Piece;
import strategy.PieceType;
import strategy.Position;
import static strategy.PieceType.*;
import strategy.PlayerColor;
import strategy.common.PiecePositionAssociation;
import strategy.common.RectangularStrategyBoard;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * Defines the initialization method for the DeltaStrategyGame
 * 
 * @author Alex Thortnon-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class DeltaInitializationStrategy extends InitializationStrategy {

	private static final int width = 10;
	private static final int height = 10;
	private static final int deploymentZoneSize = 4;
	private final DeploymentZone redDeploymentZone;
	private final DeploymentZone blueDeploymentZone;
	private final Hashtable<Piece, Integer> pieceCounter;
	private final Hashtable<PieceType, Integer> expectedPieceCount;
	private final Set<Position> occupiedPositions;
	
	public DeltaInitializationStrategy(GameState state) {
		super(state);
		redDeploymentZone = new DeploymentZone(0, deploymentZoneSize - 1, PlayerColor.RED);
		blueDeploymentZone = new DeploymentZone(height - deploymentZoneSize, height - 1,
				PlayerColor.BLUE);
		pieceCounter = new Hashtable<Piece, Integer>();
		expectedPieceCount = new Hashtable<PieceType, Integer>();
		occupiedPositions = new HashSet<Position>();
		makeExpectedPieceCount();
	}

	@Override
	public void initialize() {
		state.setBoard(new RectangularStrategyBoard(width, height));
		state.setNumMoves(0);
		state.setWinner(null);
		state.setTurn(PlayerColor.RED);
		pieceCounter.clear();
		occupiedPositions.clear();
	}
	
	/**
	 * Initialize (reset) the game.
	 * Clears the game board, then places all the pieces
	 * from startingRedPieces and startingBluePieces on 
	 * the board, assuming the configurations are correct.
	 * Should be able to reset the board at any time.
	 * 
	 * @param startingRedPieces the red Pieces to place on the board when it is initialized 
	 * @param startingBluePieces the blue Pieces to place on the board when it is initialized
	 */
	public void initialize(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		initialize();
		countPieces(startingRedPieces, PlayerColor.RED);
		countPieces(startingBluePieces, PlayerColor.BLUE);
		
		checkPieces();
		checkPositions(startingRedPieces, redDeploymentZone);
		checkPositions(startingBluePieces, blueDeploymentZone);
		
		for (PiecePositionAssociation i : startingRedPieces) {
			state.getBoard().putPieceAt(i.getPosition(), i.getPiece());
		}
		
		for (PiecePositionAssociation i : startingBluePieces) {
			state.getBoard().putPieceAt(i.getPosition(), i.getPiece());
		}
	}
	
	private void makeExpectedPieceCount() {
		expectedPieceCount.put(FLAG, 1);
		expectedPieceCount.put(MARSHAL, 1);
		expectedPieceCount.put(GENERAL, 1);
		expectedPieceCount.put(COLONEL, 2);
		expectedPieceCount.put(MAJOR, 3);
		expectedPieceCount.put(CAPTAIN, 4);
		expectedPieceCount.put(LIEUTENANT, 4);
		expectedPieceCount.put(SERGEANT, 4);
		expectedPieceCount.put(MINER, 5);
		expectedPieceCount.put(SCOUT, 8);
		expectedPieceCount.put(SPY, 1);
		expectedPieceCount.put(BOMB, 6);
	}
	
	private void countPieces(PiecePositionAssociation[] piecePositions, PlayerColor color) {
		for (PiecePositionAssociation i : piecePositions) {
			if (!pieceCounter.containsKey(i.getPiece())) {
				pieceCounter.put(i.getPiece(), 0);
			}
			if (i.getPiece().getColor().equals(color)) {
				pieceCounter.put(i.getPiece(), pieceCounter.get(i.getPiece()) + 1);
			}
			else {
				throw new RuntimeException("Wrong piece color");
			}
		}
	}
	
	private void checkPieces() {
		if(pieceCounter.size() != expectedPieceCount.size() * 2) {
			throw new RuntimeException("Incorrect number of unique pieces given");
		}
		
		final Enumeration<Piece> keys = pieceCounter.keys();
		
		while(keys.hasMoreElements()) {
			Piece elem = keys.nextElement();
			int expected = expectedPieceCount.get(elem.getType());
			int actual = pieceCounter.get(elem);
			if(expected != actual) {
				throw new RuntimeException("Incorrect number of " + elem + " - expected "
						+ expected + ", actually " + actual);
			}
			
		}
	}
	
	private void checkPositions(PiecePositionAssociation[] pieces, DeploymentZone zone) {
		
		for(PiecePositionAssociation i : pieces) {
			if(!zone.hasRow(i.getPosition().getRow())) {
				throw new RuntimeException(i + " outside of " + zone);
			}
			
			if(occupiedPositions.contains(i.getPosition())) {
				throw new RuntimeException(i + " overlaps with another piece");
			}
			else {
				occupiedPositions.add(i.getPosition());
			}
		}
		
	}

}
