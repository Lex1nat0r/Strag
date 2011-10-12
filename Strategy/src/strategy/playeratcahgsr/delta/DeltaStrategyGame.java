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

package strategy.playeratcahgsr.delta;

import java.util.ArrayList;
import java.util.List;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.playeratcahgsr.beta.BetaMovementStrategy;
import strategy.playeratcahgsr.common.BattleResult;
import strategy.playeratcahgsr.common.StrategyGame;
import strategy.playeratcahgsr.common.GameState;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.PiecePositionAssociation;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.RectangularStrategyBoard;
import strategy.playeratcahgsr.common.StrategyBoard;
import strategy.tournament.MoveResult;
import strategy.tournament.PlayerMove;

/**
 * The main Game for DeltaStrategy. All interactions with the game go through this class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 30, 2011
 */
public class DeltaStrategyGame implements StrategyGame {

	private final GameState state;
	private final DeltaInitializationStrategy init;
	private final DeltaMovementStrategy movement;
	
	/**
	 * Constructs a DeltaStrategyGame.
	 * Calls the initializeGame method to place the Pieces in startingRedPieces
	 * and startingBluePieces on the board
	 * 
	 * @param startingRedPieces 
	 * 			the array of PiecePositionAssociations used to place the red Pieces on the board
	 * @param startingBluePieces 
	 * 			the array of PiecePositionAssociations used to place the blue Pieces on the board
	 */
	public DeltaStrategyGame(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		state = new GameState();
		init = new DeltaInitializationStrategy(state);
		movement = new DeltaMovementStrategy(state);
		initializeGame(startingRedPieces, startingBluePieces);
	}
	
	@Override
	public Piece getPieceAt(Position position) {
		return state.getBoard().getPieceAt(position);
	}

	@Override
	public PlayerColor getWinner() {
		return state.getWinner();
	}

	@Override
	public void initializeGame() {
		init.initialize();
	}
	
	/**
	 * Places the Pieces in startingRedPieces and startingBluePieces on the board
	 * 
	 * @param startingRedPieces 
	 * 		the array of PiecePositionAssociations used to place the red Pieces on the board
	 * @param startingBluePieces 
	 * 		the array of PiecePositionAssociations used to place the blue Pieces on the board 
	 */
	public void initializeGame(PiecePositionAssociation[] startingRedPieces, 
			PiecePositionAssociation[] startingBluePieces) {
		init.initialize(startingRedPieces, startingBluePieces);
	}

	@Override
	public boolean isGameOver() {
		return state.isOver();
	}

	@Override
	public Piece move(Position source, Position destination) throws StrategyException {
		return movement.makeMove(source, destination);
	}
	
	/**
	 * This method is used for testing purposes only.
	 * 
	 * @param board the board to set
	 */
	protected void setBoard(StrategyBoard board)
	{
		state.setBoard((RectangularStrategyBoard) board);
	}
	
	public RectangularStrategyBoard getBoard() {
		return state.getBoard();
	}
	
	/**
	 * Consider the given color's pieces to be unknown.
	 * 
	 * @param color the color to consider unknown
	 */
	public void setPlayerAsUnknown(PlayerColor color) {
		init.setPlayerAsUnknown(color);
	}

	/**
	 * @see DeltaMovementStrategy#validateMove(Position, Position)
	 * 
	 * @param from
	 * @param to
	 * @throws StrategyException
	 */
	public void validateMove(Position from, Position to) throws StrategyException {
		movement.validateMove(from, to);
	}

	/**
	 * Make moves on the board according to our last move and the opponent's move.
	 * 
	 * @param lastMove Our last move
	 * @param gameUpdate The opponent's last move and other info
	 * @param myColor Our color
	 * @throws StrategyException If a move is invalid
	 * @return List of Pieces whose types have been discovered
	 */
	public List<PieceType> update(PlayerMove lastMove, MoveResult gameUpdate, PlayerColor myColor)
			throws StrategyException {
		final List<PieceType> knownPieces = new ArrayList<PieceType>();
		state.setTurn(myColor);  //it should always be my turn
		if(lastMove != null) {
			if(gameUpdate.getMyLastTarget() != null) {
				if(getPieceAt(Position.convert(lastMove.getTo())).equals(Piece.UNKNOWN_PIECE)) {
					knownPieces.add(PieceType.convert(gameUpdate.getMyLastTarget()));
				}
				state.getBoard().putPieceAt( Position.convert(lastMove.getTo()),
						new Piece(PieceType.convert(gameUpdate.getMyLastTarget()), 
								myColor.equals(PlayerColor.RED) ? 
										PlayerColor.BLUE : PlayerColor.RED));
			}
			move(Position.convert(lastMove.getFrom()), Position.convert(lastMove.getTo()));

		}
		if(gameUpdate != null) {
			if(gameUpdate.getOpponentsAttacker() != null) {
				if(getPieceAt(Position.convert(gameUpdate.getOpponentsLastMove().getFrom()))
						.equals(Piece.UNKNOWN_PIECE)) {
					knownPieces.add(PieceType.convert(gameUpdate.getOpponentsAttacker()));
				}
				state.getBoard().putPieceAt(
						Position.convert(gameUpdate.getOpponentsLastMove().getFrom()),
						new Piece(PieceType.convert(gameUpdate.getOpponentsAttacker()), 
								myColor.equals(PlayerColor.RED) ? 
										PlayerColor.BLUE : PlayerColor.RED));
			}
			move(Position.convert(gameUpdate.getOpponentsLastMove().getFrom()),
					Position.convert(gameUpdate.getOpponentsLastMove().getTo()));
		}
		state.setTurn(myColor);
		
		return knownPieces;
	}

	/**
	 * @see BetaMovementStrategy#determineBattleResult(Piece, Piece)
	 * @param attacker
	 * @param defender
	 * @return the BattleResult of attacker attacking defender
	 */
	public static BattleResult determineBattleResult(Piece attacker, Piece defender) {
		return BetaMovementStrategy.determineBattleResult(attacker, defender);
	}
	
	

}
