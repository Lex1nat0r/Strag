package strategy.playeratcahgsr.common;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import strategy.PieceConfiguration;
import strategy.common.PlayerColor;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;

public class ComparablePlayerMoveTest {

	private ComparablePlayerMove moveToEmptySpace;
	private ComparablePlayerMove moveOutOfBounds;
	private ComparablePlayerMove moveToWater;
	private ComparablePlayerMove moveToFriendlyPiece;
	
	private DeltaStrategyGame game;
	
	private List<ComparablePlayerMove> moveList;
	
	@Before
	public void setUp() throws Exception {
		game = new DeltaStrategyGame(
				PieceConfiguration.getInstance().getInitialRedConfiguration(),
				PieceConfiguration.getInstance().getInitialBlueConfiguration());

		moveToEmptySpace = new ComparablePlayerMove(new Position(3,0), new Position(4,0), game);
		moveOutOfBounds = new ComparablePlayerMove(new Position(3,0), new Position(3,-1), game);
		moveToWater = new ComparablePlayerMove(new Position(3,2), new Position(4,2), game);
		moveToFriendlyPiece = new ComparablePlayerMove(new Position(3,0), new Position(3,1), game);
		
		ComparablePlayerMove[] temp = {moveOutOfBounds, moveToWater, moveToEmptySpace, moveToFriendlyPiece};
		moveList = Arrays.asList(temp);
		Collections.sort(moveList);
		Collections.reverse(moveList);
	}
	
	@Test
	public void testSorted() {
		assertEquals(moveToEmptySpace, moveList.get(0));
	}
	
	@Test
	public void testTypes() {
		assertEquals(MoveType.VALID, moveToEmptySpace.getType());
		assertEquals(MoveType.INVALID, moveOutOfBounds.getType());
		assertEquals(MoveType.INVALID, moveToWater.getType());
		assertEquals(MoveType.INVALID, moveToFriendlyPiece.getType());
	}
	
	@Test
	public void testMoveToUnknownPiece() {
		game.setPlayerAsUnknown(PlayerColor.BLUE);
		ComparablePlayerMove moveToUnknownPiece = new ComparablePlayerMove(new Position(3, 1),
				new Position(6, 1), game);
		assertEquals(MoveType.VALID, moveToUnknownPiece.getType());
	}
	
	@Test
	public void testBattleVictory() {
		game.getBoard().putPieceAt(new Position(4, 0), new Piece(PieceType.SCOUT, PlayerColor.BLUE));
		ComparablePlayerMove moveToWeakerPiece = new ComparablePlayerMove(new Position(3, 0), 
				new Position(4, 0), game);
		assertEquals(MoveType.ATTACK_VICTORY, moveToWeakerPiece.getType());
	}

	@Test
	public void testBattleDefeat() {
		game.getBoard().putPieceAt(new Position(4, 0), new Piece(PieceType.GENERAL, PlayerColor.BLUE));
		ComparablePlayerMove moveToWeakerPiece = new ComparablePlayerMove(new Position(3, 0), 
				new Position(4, 0), game);
		assertEquals(MoveType.ATTACK_DEFEAT, moveToWeakerPiece.getType());
	}
	
	@Test
	public void testBattleDraw() {
		game.getBoard().putPieceAt(new Position(4, 0), new Piece(PieceType.MAJOR, PlayerColor.BLUE));
		ComparablePlayerMove moveToWeakerPiece = new ComparablePlayerMove(new Position(3, 0), 
				new Position(4, 0), game);
		assertEquals(MoveType.ATTACK_DRAW, moveToWeakerPiece.getType());
	}
	
	@Test
	public void testBattleVictoryRankDifference() {
		game.getBoard().putPieceAt(new Position(0,0), new Piece(PieceType.MARSHAL, PlayerColor.RED));
		game.getBoard().putPieceAt(new Position(0,1), new Piece(PieceType.GENERAL, PlayerColor.BLUE));
		game.getBoard().putPieceAt(new Position(1,0), new Piece(PieceType.COLONEL, PlayerColor.BLUE));
		ComparablePlayerMove moveToGeneral = new ComparablePlayerMove(new Position(0, 0), 
				new Position(0, 1), game);
		ComparablePlayerMove moveToColonel = new ComparablePlayerMove(new Position(0, 0), 
				new Position(1, 0), game);
		
		assertTrue(moveToGeneral.compareTo(moveToColonel) > 0);
		assertTrue(moveToColonel.compareTo(moveToGeneral) < 0);
		assertTrue(moveToGeneral.compareTo(moveToGeneral) == 0);
	}

}
