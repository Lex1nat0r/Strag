package strategy.playeratcahgsr.common;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import strategy.PieceConfiguration;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;

public class ComparablePlayerMoveTest {

	private ComparablePlayerMove moveToEmptySpace;
	private ComparablePlayerMove moveOutOfBounds;
	private ComparablePlayerMove moveToWater;
	private ComparablePlayerMove moveToFriendlyPiece;
	
	private List<ComparablePlayerMove> moveList;
	
	@Before
	public void setUp() throws Exception {
		DeltaStrategyGame game = new DeltaStrategyGame(
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

}
