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
package strategy.alpha;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.RulesStrategy;
import strategy.StrategyException;
import strategy.common.*;

/**
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 14, 2011
 */
public class AlphaRulesStrategy extends RulesStrategy {

	private static final int width = 2;
	private static final int height = 2;
	private final RectangularStrategyBoard board = new RectangularStrategyBoard(height, width);
	
	@Override
	public void initialize() {
		winnerColor = null;
		turnColor = PlayerColor.RED;
		board.putPieceAt(new Position(0, 0), 
				new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.RED));
		board.putPieceAt(new Position(0, 1), 
				new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.RED));
		board.putPieceAt(new Position(1, 0), 
				new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.BLUE));
		board.putPieceAt(new Position(1, 1), 
				new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.BLUE));

	}

	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(isOver()) {
			throw new StrategyException("Cannot move after game is over");
		}
		if(board.getPieceAt(source).getType() == PieceType.FLAG) {
			throw new StrategyException("Cannot move flag");
		}
		if(board.getPieceAt(source).getColor() != turnColor) {
			throw new StrategyException("Cannot move out of turn");
		}
		if(source.equals(destination)) {
			throw new StrategyException("Must move to a different space");
		}
		if(source.isDiagonal(destination)) {
			throw new StrategyException("Cannot move diagonally");
		}
		if(board.getPieceAt(source).getColor() == board.getPieceAt(destination).getColor()) {
			throw new StrategyException("Cannot move to a space containing a friendly unit");
		}
		
		resolveBattle(board.getPieceAt(source), board.getPieceAt(destination));
		board.putPieceAt(destination, board.getPieceAt(source));
		board.putPieceAt(source, Piece.NULL_PIECE);
		return board.getPieceAt(destination);
	}

	@Override
	public BattleResult resolveBattle(strategy.Piece attacker,
			strategy.Piece defender) {
		winnerColor = PlayerColor.RED;
		return BattleResult.VICTORY;
	}
	
	/**
	 * Accessor for getting a piece at a specific row and column.
	 * 
	 * @param pos
	 *            the Position of the piece
	 * @return the piece at the specified row and column
	 */
	public Piece getPieceAt(Position pos) {
		return board.getPieceAt(pos);
	}

}
