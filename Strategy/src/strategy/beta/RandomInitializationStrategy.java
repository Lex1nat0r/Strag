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

package strategy.beta;

import java.util.Random;

import strategy.GameState;
import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;

/**
 * Defines the initialization method for the random piece setup
 * 
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 29, 2011
 */
public class RandomInitializationStrategy extends BetaInitializationStrategy {
	
	public RandomInitializationStrategy(GameState state) {
		super(state);
	}

	@Override
	public void initialize() {
		super.initialize();
		placePiecesByColor(PlayerColor.RED);
		placePiecesByColor(PlayerColor.BLUE);
	}
	
	private void placePiecesByColor(PlayerColor pieceColor) {
		for (PieceType type : PieceType.values()) {
			Piece pieceToPlace = new Piece(type, pieceColor);
			randomlyPlacePiece(pieceToPlace);
		}
	}
	
	private void randomlyPlacePiece(Piece pieceToPlace) {
		final Random randGen = new Random();
		Position randPos = null;
		
		if (pieceToPlace.getColor() == PlayerColor.RED) {
			do {
				randPos = new Position(randGen.nextInt(2),
						randGen.nextInt(state.getBoard().getNumCols()));
			} while (state.getBoard().isOccupied(randPos));
		}
		else {
			do {
				randPos = new Position((state.getBoard().getNumRows() - 1) - randGen.nextInt(2), 
						randGen.nextInt(state.getBoard().getNumCols()));
			} while (state.getBoard().isOccupied(randPos));
		}
			
		state.getBoard().putPieceAt(randPos, pieceToPlace);
	}

}
