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
 *    Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 *******************************************************************************/
package strategy.beta;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import strategy.GameState;
import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.RulesStrategy;
import strategy.StrategyBoard;
import strategy.StrategyException;
import strategy.common.RectangularStrategyBoard;

/**
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 15, 2011
 */
public class BetaRulesStrategy extends RulesStrategy {

	private int numMoves;
	private boolean playerCanPlacePiece;
	private Set<Piece> placedPieces;
	
	/**
	 * Default constructor. Initializes the game.
	 * Players are not allowed to place their pieces.  They are randomized instead.
	 * @throws StrategyException 
	 */
	public BetaRulesStrategy(GameState state)
	{
		this(state, false);
	}
	
	/**
	 * Constructor that accepts a boolean indicating whether players
	 * can place their pieces in this game or not.
	 * 
	 * @param state The GameState this should modify
	 * @param playerPlacePiece true if players can place pieces on the board where they choose
	 */
	public BetaRulesStrategy(GameState state, boolean playerPlacePiece) {
		super(state);
		state.setBoard(new RectangularStrategyBoard(6, 6));
		playerCanPlacePiece = playerPlacePiece;
		initialize();
	}
	
	@Override
	public void initialize() {
		state.setBoard(new RectangularStrategyBoard(6, 6));
		numMoves = 0;
		state.setWinner(null);
		state.setTurn(PlayerColor.RED);
		if (!playerCanPlacePiece) {
			placePiecesByColor(PlayerColor.RED);
			placePiecesByColor(PlayerColor.BLUE);
			placedPieces = null;
		}
		else {
			placedPieces = new HashSet<Piece>();
		}

	}
	

	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(source.equals(destination)) {
			throw new StrategyException("destination must be different from source");
		}
		if(source.isDiagonal(destination)) {
			throw new StrategyException("Pieces cannot move diagonally");
		}
		if(!state.getBoard().isOccupied(source)) {
			throw new StrategyException("source must be occupied by a piece");
		}
		if(isOver()){
			throw new StrategyException("Game is already over");
		}
		
		//change turn/dont double move
		if(state.getBoard().getPieceAt(source).getColor() != state.getTurn()){
			throw new StrategyException("not your turn");
		}
		
		if(state.getTurn() == PlayerColor.RED){
			state.setTurn(PlayerColor.BLUE);
		}
		else{
			state.setTurn(PlayerColor.RED);
		}
		
		final Piece sourcePiece = state.getBoard().getPieceAt(source);
		final Piece destinationPiece = state.getBoard().getPieceAt(destination);
		
		final int distance = state.getBoard().getDistance(source, destination);
		final int range = sourcePiece.getType().getRange();
		if(range >= 0 && distance > range) {
			throw new StrategyException("Cannot move piece farther than its range");
		}
		
		if (sourcePiece.getColor().equals(destinationPiece.getColor())) {
			throw new StrategyException("Cannot move onto a friendly piece");
		}

		if (state.getBoard().isOccupiedSpaceBetweenPositions(source, destination)) {
			throw new StrategyException("Cannot move through occupied spaces");
		}

		BattleResult result = BattleResult.VICTORY;
		if (state.getBoard().isOccupied(destination)) {
			result = resolveBattle(sourcePiece, destinationPiece);
		}

		switch (result) {
		case VICTORY:
			state.getBoard().putPieceAt(destination, sourcePiece);
			state.getBoard().putPieceAt(source, Piece.NULL_PIECE);
			break;
		case DRAW:
			state.getBoard().putPieceAt(destination, Piece.NULL_PIECE);
			// fallthrough
		case DEFEAT:
			state.getBoard().putPieceAt(source, Piece.NULL_PIECE);
			break;
		}
		//if moves>10 end game
		numMoves++;
		if(numMoves >= 10){
			state.setWinner(PlayerColor.BLUE);
		}
		return state.getBoard().getPieceAt(destination);
	}

	@Override
	public BattleResult resolveBattle(Piece attacker, Piece defender) {
		if(defender.getType().equals(PieceType.FLAG)){
			state.setWinner(attacker.getColor());
			return BattleResult.VICTORY;
		}
		if(attacker.getType().equals(PieceType.MINER)
				&& defender.getType().equals(PieceType.BOMB)) {
			return BattleResult.VICTORY;
		}
		if(attacker.getType().equals(PieceType.SPY)
				&& defender.getType().equals(PieceType.MARSHAL)) {
			return BattleResult.VICTORY;
		}
		
		if(attacker.getType().getRank() < defender.getType().getRank()) {
			return BattleResult.VICTORY;
		}
		if(attacker.getType().getRank() == defender.getType().getRank()) {
			return BattleResult.DRAW;
		}
		return BattleResult.DEFEAT;
	}
	
	/**
	 * Get the number of pieces of both colors currently on the board
	 * 
	 * @return numPieces
	 */
	public int getNumPiecesOnBoard() {
		int numPieces = 0;
		final Iterator<Piece> iter = state.getBoard().iterator();
		
		while(iter.hasNext()) {
			if (!iter.next().equals(Piece.NULL_PIECE)) {
				numPieces++;
			}
		}
		
		return numPieces;
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
	
	/**
	 * Place Piece piece at Position position on this board.
	 * 
	 * @param position The position to place the Piece at
	 * @param piece The Piece to place on the board
	 * @throws StrategyException if a player is attempting to place a piece incorrectly
	 */
	public void playerPlacePiece(Position position, Piece piece) throws StrategyException {
		if (!playerCanPlacePiece) {
			throw new StrategyException("Player not allowed to place Piece");
		}
		if (state.getBoard().isOccupied(position)) {
			throw new StrategyException("Player not allowed to place Piece in an occupied space");
		}
		if (!placedPieces.add(piece)) {
			throw new StrategyException("That Piece has already been placed");
		}
		if (piece.getColor() == PlayerColor.RED && position.getRow() > 1) {
			throw new StrategyException("Given position is outside RED player's setup zone");
		}
		if (piece.getColor() == PlayerColor.BLUE && position.getRow() < 4) {
			throw new StrategyException("Given position is outside BLUE player's setup zone");
		}
		state.getBoard().putPieceAt(position, piece);
	}
	
	/**
	 * Set the current StrategyBoard to the given StrategyBoard
	 * 
	 * @param board
	 */
	protected void setBoard(StrategyBoard board)
	{
		state.setBoard((RectangularStrategyBoard) board);
	}
	
	/**
	 * Return this BetaStrategyGame's RectangularStrategyBoard
	 * 
	 * @return board
	 */
	protected RectangularStrategyBoard getBoard() {
		return state.getBoard();
	}
	
	/**
	 * Accessor for getting a piece at a specific row and column.
	 * 
	 * @param pos
	 *            the Position of the piece
	 * @return the piece at the specified row and column
	 */
	public Piece getPieceAt(Position pos) {
		return state.getBoard().getPieceAt(pos);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(this == other) {
			return true;
		}
		if (other instanceof BetaRulesStrategy) {
			final BetaRulesStrategy that = (BetaRulesStrategy) other;
			return isOver() == that.isOver() && getWinner() == that.getWinner()
					&& state.getBoard().equals(that.getBoard());
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + state.getBoard().hashCode();
		result = prime * result + numMoves;
		result = prime * result + (playerCanPlacePiece ? 0 : 1);
		result = prime * result + placedPieces.hashCode();
		return result;
	}

}
