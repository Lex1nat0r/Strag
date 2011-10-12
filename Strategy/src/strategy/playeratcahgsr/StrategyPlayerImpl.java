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
 *    gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 *******************************************************************************/
package strategy.playeratcahgsr;

import static strategy.playeratcahgsr.common.PieceType.BOMB;
import static strategy.playeratcahgsr.common.PieceType.CAPTAIN;
import static strategy.playeratcahgsr.common.PieceType.COLONEL;
import static strategy.playeratcahgsr.common.PieceType.FLAG;
import static strategy.playeratcahgsr.common.PieceType.GENERAL;
import static strategy.playeratcahgsr.common.PieceType.LIEUTENANT;
import static strategy.playeratcahgsr.common.PieceType.MAJOR;
import static strategy.playeratcahgsr.common.PieceType.MARSHAL;
import static strategy.playeratcahgsr.common.PieceType.MINER;
import static strategy.playeratcahgsr.common.PieceType.SCOUT;
import static strategy.playeratcahgsr.common.PieceType.SERGEANT;
import static strategy.playeratcahgsr.common.PieceType.SPY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import strategy.common.PlayerColor;
import strategy.playeratcahgsr.common.BoardIterator;
import strategy.playeratcahgsr.common.ComparablePlayerMove;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PiecePositionAssociation;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.StrategyGameFactory;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;
import strategy.tournament.*;

/**
 * Defines a computer-controlled StrategyPlayer.
 *
 * @author gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Oct 5, 2011
 */
public class StrategyPlayerImpl implements StrategyPlayer
{
	private final int width = 10;
	private final int height = 10;
	private final int deploymentHeight = 4;
	
	private final PlayerColor myColor;
	private final PieceType[][] startingBluePieceTypes;
	private final PiecePositionAssociation[] startingRedConfig;
	private final PiecePositionAssociation[] startingBlueConfig;
	private final Hashtable<PieceType, Integer> unknowns;
	
	private final DeltaStrategyGame game;
	private PlayerMove lastMove;
	
	/**
	 * The starting configuration of the red player.
	 * 
	 * |		  |
	 * |SSBRMNRRCB|
	 * |RBPSYBSRSS|
	 * |BPPPJJJSTS|
	 * |FBCGGGGTTT|
	 * +----------+
	 */
	private final PieceType[][] startingRedPieceTypes = {
			{SCOUT,   SCOUT,    BOMB,       MINER,      MARSHAL,	// 3,0..3,4
			GENERAL,  MINER,    MINER,      COLONEL,    BOMB},		// 3,5..3,9
			{MINER,   BOMB,     CAPTAIN,    SCOUT,      SPY,		// 2,0..2,4
			BOMB,     SCOUT,    MINER,      SCOUT,      SCOUT},		// 2,5..2,9
			{BOMB,    CAPTAIN,  CAPTAIN,    CAPTAIN,    MAJOR,		// 1,0..1,4
			MAJOR,    MAJOR,    SCOUT,      LIEUTENANT, SCOUT},		// 1,5..1,9
			{FLAG,    BOMB,     COLONEL,    SERGEANT,   SERGEANT,	// 0,0..0,4
			SERGEANT, SERGEANT, LIEUTENANT, LIEUTENANT, LIEUTENANT}	// 0,5..0,9
	};
	
	/**
	 * @param myColor The color this StrategyPlayer should be playing as
	 */
	public StrategyPlayerImpl(PlayerColor myColor)
	{
		this.myColor = myColor;
		unknowns = new Hashtable<PieceType, Integer>();
		
		//flip the PieceType 2D array vertically to get blue config
		final List<PieceType[]> temp = Arrays.asList(startingRedPieceTypes.clone());
		Collections.reverse(temp);
		startingBluePieceTypes = (PieceType[][]) temp.toArray();
		
		startingRedConfig = makeAssociations(startingRedPieceTypes, PlayerColor.RED, 0);
		startingBlueConfig = makeAssociations(startingBluePieceTypes, PlayerColor.BLUE,
				height - deploymentHeight);
		
		game = StrategyGameFactory.getInstance().makeDeltaStrategyGame(startingRedConfig,
				startingBlueConfig);
		
		game.setPlayerAsUnknown(myColor.equals(PlayerColor.RED)
				? PlayerColor.BLUE : PlayerColor.RED);
		
		populateUnknowns();
	}
	
	private void populateUnknowns() {
		for (Piece i : game.getBoard()) {
			if (myColor.equals(i.getColor())) {
				if (!unknowns.containsKey(i.getType())) {
					unknowns.put(i.getType(), 0);
				}
				unknowns.put(i.getType(), unknowns.get(i.getType()) + 1);
			}
		}
	}
	
	/**
	 * Create an array of PiecePositionAssociation from our 2D PieceType array.
	 * Position is inferred from the type's position in the array, and the row is offset
	 * by rowOffset.
	 */
	private PiecePositionAssociation[] makeAssociations(PieceType[][] types, PlayerColor color,
			int rowOffset) {
		final PiecePositionAssociation[] associations =
			new PiecePositionAssociation[deploymentHeight * width];
		
		int assocIndex = 0;
		for(int y = 0; y < deploymentHeight; y++) {
			for(int x = 0; x < width; x++) {
				PieceType type = types[y][x];
				Piece piece = new Piece(type, color);
				Position position = new Position(-1 * y + deploymentHeight - 1 + rowOffset, x);
				
				associations[assocIndex] = new PiecePositionAssociation(piece, position);
				assocIndex++;
			}
		}
		
		return associations;
	}
	
	/*
	 * @see strategy.tournament.StrategyPlayer#getStartingConfiguration()
	 */
	@Override
	public strategy.common.PiecePositionAssociation[] getStartingConfiguration()
	{
		return PiecePositionAssociation.convert(myColor.equals(PlayerColor.RED)
				? startingRedConfig : startingBlueConfig);
	}

	/*
	 * @see strategy.tournament.StrategyPlayer#move(strategy.tournament.MoveResult)
	 */
	@Override
	public PlayerMove move(MoveResult gameUpdate) {
		final List<PieceType> knownPieces;
		try {
			knownPieces = game.update(lastMove, gameUpdate, myColor);
		} catch(Exception e) {
			//oh poop oh poop oh poop
			throw new RuntimeException("playeratcahgsr screwed up while trying to move", e);
		}
		
		final PieceType detectedScout = detectScouts(gameUpdate);
		if (detectedScout != null) {
			knownPieces.add(detectedScout);
		}
		
		updateUnknowns(knownPieces);
		
		lastMove = getRandomBestMove();
		return lastMove;
	}
	
	private void updateUnknowns(List<PieceType> newKnowns) {
		for (PieceType i : newKnowns) {
			unknowns.put(i, unknowns.get(i) - 1);
		}
	}

	/**
	 * Puts a scout on the opponent's destination position if the distance
	 * between the source and destination is greater than one.
	 * 
	 * @param gameUpdate The gameUpdate for this move
	 * @return Scout if a scout has been detected, otherwise null
	 */
	protected PieceType detectScouts(MoveResult gameUpdate) {
		if(gameUpdate != null) {
			final PlayerMove oppMove = gameUpdate.getOpponentsLastMove();
			if(oppMove != null && game.getBoard().getDistance(Position.convert(oppMove.getFrom()),
					Position.convert(oppMove.getTo())) > 1) {
				//we know that this must be a scout
				game.getBoard().putPieceAt(Position.convert(oppMove.getTo()),
						new Piece(PieceType.SCOUT,
								myColor == PlayerColor.RED ? PlayerColor.BLUE : PlayerColor.RED));
				return PieceType.SCOUT;
			}
		}
		return null;
	}
	
	/**
	 * @return The best move to make.
	 */
	protected PlayerMove getRandomBestMove() {
		List<ComparablePlayerMove> moves = getAllMoves();
		moves = getTopMoves(moves);
		Collections.shuffle(moves);
		final ComparablePlayerMove move = moves.get(0);
		
		return new PlayerMove(move.getFrom().convert(), move.getTo().convert());
	}
	
	/**
	 * @return A list of all possible moves to be made by myColor, sorted by quality descending
	 */
	protected List<ComparablePlayerMove> getAllMoves() {
		final List<ComparablePlayerMove> allMoves = new ArrayList<ComparablePlayerMove>();
		final BoardIterator iter = (BoardIterator) game.getBoard().iterator();
		while(iter.hasNext()) {
			Piece currentPiece = iter.next();
			if(myColor.equals(currentPiece.getColor())) {
				Position currentPosition = new Position(iter.getRow(), iter.getColumn());
				Collection<Position> cardinals = currentPosition.getCardinalPositions();
				
				for(Position p : cardinals) {
					allMoves.add(new ComparablePlayerMove(currentPosition, p, unknowns, game));
				}
			}
		}
		
		Collections.sort(allMoves); //this is ascending, we want descending
		Collections.reverse(allMoves);
		return allMoves;
	}
	
	/**
	 * Show me your moves!
	 * 
	 * @param allMoves A List of moves sorted by bestness descending 
	 * @return A List of the best moves (moves can have the same bestness)
	 */
	protected static List<ComparablePlayerMove> getTopMoves(List<ComparablePlayerMove> allMoves) {
		int index = 0;
		for(ComparablePlayerMove i : allMoves) {
			if(index + 1 >= allMoves.size() || i.compareTo(allMoves.get(index + 1)) != 0) {
				break;
			}
			index++;
		}
		return allMoves.subList(0, index + 1); //second argument is exclusive
	}
	
	protected DeltaStrategyGame getGame() {
		return game;
	}
	
	public Map<PieceType, Integer> getUnknowns() {
		return unknowns;
	}

}
