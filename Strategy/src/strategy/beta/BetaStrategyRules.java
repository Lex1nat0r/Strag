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
import java.util.Set;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.RulesStrategy;
import strategy.StrategyException;
import strategy.common.RectangularStrategyBoard;

/**
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 14, 2011
 */
public class BetaStrategyRules implements RulesStrategy {

	private PlayerColor winnerColor;
	private PlayerColor turnColor;
	private RectangularStrategyBoard board;
	private int numMoves;
	private boolean playerCanPlacePiece;
	private Set<Piece> placedPieces;
	private boolean isOver;
	
	/* 
	 * @see strategy.RulesStrategy#getWinner()
	 */
	@Override
	public PlayerColor getWinner() {
		return winnerColor;
	}

	/* 
	 * @see strategy.RulesStrategy#initialize()
	 */
	@Override
	public void initialize() throws StrategyException {
		winnerColor = null;
		board = new RectangularStrategyBoard(6, 6);
		isOver = false;
		numMoves = 0;
		turnColor = PlayerColor.RED;
		board.initializeBoard();
		if (!playerCanPlacePiece) {
			//placePiecesByColor(PlayerColor.RED);
			//placePiecesByColor(PlayerColor.BLUE);
			placedPieces = null;
		}
		else {
			placedPieces = new HashSet<Piece>();
		}

	}

	/* 
	 * @see strategy.RulesStrategy#makeMove(strategy.Position, strategy.Position)
	 */
	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(source.equals(destination)) {
			throw new StrategyException("destination must be different from source");
		}
		if(!board.isOccupied(source)) {
			throw new StrategyException("source must be occupied by a piece");
		}
		if(isOver){
			throw new StrategyException("Game is already over");
		}
		
		//change turn/dont double move
		if(board.getPieceAt(source).getColor() != turnColor){
			throw new StrategyException("not your turn");
		}
		
		if(turnColor == PlayerColor.RED){
			turnColor=PlayerColor.BLUE;
		}
		else{
			turnColor=PlayerColor.RED;
		}
		
		final Piece sourcePiece = board.getPieceAt(source);
		final Piece destinationPiece = board.getPieceAt(destination);
		
		final int distance = board.getDistance(source, destination); //checks for diagonal
		final int range = sourcePiece.getType().getRange();
		if(range >= 0 && distance > range) {
			throw new StrategyException("Cannot move piece farther than its range");
		}
		
		if (sourcePiece.getColor().equals(destinationPiece.getColor())) {
			throw new StrategyException("Cannot move onto a friendly piece");
		}

		if (board.isOccupiedSpaceBetweenPositions(source, destination)) {
			throw new StrategyException("Cannot move through occupied spaces");
		}

		BattleResult result = BattleResult.VICTORY;
		if (board.isOccupied(destination)) {
			result = resolveBattle(sourcePiece, destinationPiece);
		}

		switch (result) {
		case VICTORY:
			board.putPieceAt(destination, sourcePiece);
			board.putPieceAt(source, Piece.NULL_PIECE);
			break;
		case DRAW:
			board.putPieceAt(destination, Piece.NULL_PIECE);
			// fallthrough
		case DEFEAT:
			board.putPieceAt(source, Piece.NULL_PIECE);
			break;
		}
		//if moves>10 end game
		numMoves++;
		if(numMoves >= 10){
			isOver=true;
		}
		return board.getPieceAt(destination);
	}

	/* 
	 * @see strategy.RulesStrategy#resolveBattle(strategy.Piece, strategy.Piece)
	 */
	@Override
	public BattleResult resolveBattle(Piece attacker, Piece defender) {
		//this should be checked first
		isOver = false;
		
		if(defender.getType().equals(PieceType.FLAG)){
			isOver = true;
			winnerColor=attacker.getColor();
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
	
	@Override
	public boolean isOver()
	{
		return winnerColor != null;
	}

}
