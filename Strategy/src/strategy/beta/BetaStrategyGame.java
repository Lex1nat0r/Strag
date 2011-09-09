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
package strategy.beta;

import java.util.Iterator;
import java.util.Random;

import strategy.*;
import strategy.common.RectangularStrategyBoard;

/**
 * Implementation of the BetaStrategy game.
 * @author gpollice
 * @version Aug 1, 2011
 */
public class BetaStrategyGame implements StrategyGame
{
	private RectangularStrategyBoard board;
	
	/**
	 * Default constructor. Initializes the game.
	 */
	public BetaStrategyGame()
	{
		board = new RectangularStrategyBoard(6, 6);
		board.initializeBoard();
	}
	
	@Override
	public void initializeGame() throws StrategyException
	{
		placePiecesByColor(PlayerColor.RED);
		placePiecesByColor(PlayerColor.BLUE);
	}
	
	@Override
	public Piece move(Position source, Position destination) throws StrategyException
	{
		return null;
	}

	@Override
	public boolean isGameOver()
	{
		return false;
	}

	@Override
	public PlayerColor getWinner()
	{
		return null;
	}

	@Override
	public Piece getPieceAt(Position position)
	{
		return null;
	}
	
	/**
	 * Get the number of pieces of both colors currently on the board
	 * 
	 * @return numPieces
	 */
	public int getNumPiecesOnBoard() {
		int numPieces = 0;
		final Iterator<Piece> iter = board.iterator();
		
		while(iter.hasNext()) {
			if (!iter.next().equals(Piece.NULL_PIECE)) {
				numPieces++;
			}
		}
		
		return numPieces;
	}
	
	private void placePiecesByColor(PlayerColor pieceColor) throws StrategyException {
		randomlyPlacePiece(PieceType.MARSHAL, pieceColor);
		randomlyPlacePiece(PieceType.GENERAL, pieceColor);
		randomlyPlacePiece(PieceType.COLONEL, pieceColor);
		randomlyPlacePiece(PieceType.MAJOR, pieceColor);
		randomlyPlacePiece(PieceType.CAPTAIN, pieceColor);
		randomlyPlacePiece(PieceType.LIEUTENANT, pieceColor);
		randomlyPlacePiece(PieceType.SERGEANT, pieceColor);
		randomlyPlacePiece(PieceType.MINER, pieceColor);
		randomlyPlacePiece(PieceType.SCOUT, pieceColor);
		randomlyPlacePiece(PieceType.SPY, pieceColor);
		randomlyPlacePiece(PieceType.BOMB, pieceColor);
		randomlyPlacePiece(PieceType.FLAG, pieceColor);
	}
	
	private void randomlyPlacePiece(PieceType type, PlayerColor color) throws StrategyException {
		final Random randGen = new Random();
		final Piece tempPiece = new Piece(type, color);
		Position randPos = null;
		
		
		if (color == PlayerColor.RED) {
			 randPos = new Position(randGen.nextInt(2), randGen.nextInt(board.getNumCols()));
			while (board.isOccupied(randPos)) {
				randPos = new Position(randGen.nextInt(2), randGen.nextInt(board.getNumCols()));
			}
		}
		else {
			randPos = new Position((board.getNumRows() - 1) - randGen.nextInt(2), 
					randGen.nextInt(board.getNumCols()));
			while (board.isOccupied(randPos)) {
				randPos = new Position((board.getNumRows() - 1) - randGen.nextInt(2), 
						randGen.nextInt(board.getNumCols()));
			}
		}
			
		board.putPieceAt(randPos, tempPiece);
	}
	
	/**
	 * Set the current StrategyBoard to the given StrategyBoard
	 * 
	 * @param board
	 */
	protected void setBoard(StrategyBoard board)
	{
		
	}

}
