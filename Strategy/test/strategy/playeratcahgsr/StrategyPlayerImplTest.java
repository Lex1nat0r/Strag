package strategy.playeratcahgsr;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PiecePositionAssociation;
import strategy.common.PlayerColor;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;

public class StrategyPlayerImplTest {

	private StrategyPlayerImpl redPlayer;
	private StrategyPlayerImpl bluePlayer;
	private DeltaStrategyGame redGame;
	private DeltaStrategyGame blueGame;
	
	@Before
	public void setUp() throws Exception {
		redPlayer = new StrategyPlayerImpl(PlayerColor.RED);
		redGame = redPlayer.getGame();
		bluePlayer = new StrategyPlayerImpl(PlayerColor.BLUE);
		blueGame = bluePlayer.getGame();
	}
	
	@Test
	public void testInitialRedState() {
		assertEquals(new Piece(PieceType.FLAG, PlayerColor.RED),
				redGame.getPieceAt(new Position(0,0)));
		assertEquals(new Piece(PieceType.SCOUT, PlayerColor.RED),
				redGame.getPieceAt(new Position(3,0)));
		assertEquals(new Piece(PieceType.BOMB, PlayerColor.RED),
				redGame.getPieceAt(new Position(3,9)));
		assertEquals(new Piece(PieceType.LIEUTENANT, PlayerColor.RED),
				redGame.getPieceAt(new Position(0,9)));
		
		assertEquals(Piece.UNKNOWN_PIECE, redGame.getPieceAt(new Position(6,0)));
		assertEquals(Piece.UNKNOWN_PIECE, redGame.getPieceAt(new Position(9,0)));
		assertEquals(Piece.UNKNOWN_PIECE, redGame.getPieceAt(new Position(9,9)));
		assertEquals(Piece.UNKNOWN_PIECE, redGame.getPieceAt(new Position(6,9)));
	}
	
	@Test
	public void testInitialBlueState() {
		assertEquals(new Piece(PieceType.SCOUT, PlayerColor.BLUE),
				blueGame.getPieceAt(new Position(6,0)));
		assertEquals(new Piece(PieceType.FLAG, PlayerColor.BLUE),
				blueGame.getPieceAt(new Position(9,0)));
		assertEquals(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE),
				blueGame.getPieceAt(new Position(9,9)));
		assertEquals(new Piece(PieceType.BOMB, PlayerColor.BLUE),
				blueGame.getPieceAt(new Position(6,9)));
		
		assertEquals(Piece.UNKNOWN_PIECE, blueGame.getPieceAt(new Position(0,0)));
		assertEquals(Piece.UNKNOWN_PIECE, blueGame.getPieceAt(new Position(3,0)));
		assertEquals(Piece.UNKNOWN_PIECE, blueGame.getPieceAt(new Position(3,9)));
		assertEquals(Piece.UNKNOWN_PIECE, blueGame.getPieceAt(new Position(0,9)));
	}
	
	@Test
	public void testGetRedStartingConfiguration() {
		PiecePositionAssociation[] redStart = redPlayer.getStartingConfiguration();
		assertTrue(redStart instanceof strategy.common.PiecePositionAssociation[]);
		assertEquals(PlayerColor.RED, redStart[0].getPiece().getColor());
	}
	
	@Test
	public void testGetBlueStartingConfiguration() {
		PiecePositionAssociation[] blueStart = bluePlayer.getStartingConfiguration();
		assertTrue(blueStart instanceof strategy.common.PiecePositionAssociation[]);
		assertEquals(PlayerColor.BLUE, blueStart[0].getPiece().getColor());
	}

}
