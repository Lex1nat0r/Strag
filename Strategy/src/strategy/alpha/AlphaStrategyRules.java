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
import strategy.PlayerColor;
import strategy.Position;
import strategy.RulesStrategy;
import strategy.StrategyException;
import strategy.common.*;

/**
 * @author Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 * @version Sep 14, 2011
 */
public class AlphaStrategyRules implements RulesStrategy {
	
	private static final int width = 2;
	private static final int height = 2;
	private final RectangularStrategyBoard board = new RectangularStrategyBoard(height, width);
	private PlayerColor winner;

	/* 
	 * @see strategy.RulesStrategy#getWinner()
	 */
	@Override
	public PlayerColor getWinner() {
		return winner;
	}

	/* 
	 * @see strategy.RulesStrategy#initialize()
	 */
	@Override
	public void initialize() throws StrategyException {
		winner = null;
		board.putPieceAt(new Position(0, 0), new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.RED));
		board.putPieceAt(new Position(0, 1), new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.RED));
		board.putPieceAt(new Position(1, 0), new strategy.Piece(strategy.PieceType.FLAG, PlayerColor.BLUE));
		board.putPieceAt(new Position(1, 1), new strategy.Piece(strategy.PieceType.SCOUT, PlayerColor.BLUE));

	}

	/* 
	 * @see strategy.RulesStrategy#makeMove(strategy.Position, strategy.Position)
	 */
	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		winner = PlayerColor.RED;
		return board.getPieceAt(source);
	}

	@Override
	public BattleResult resolveBattle(strategy.Piece attacker,
			strategy.Piece defender) {
		return BattleResult.VICTORY;
	}

}
