package strategy.playeratcahgsr;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PiecePositionAssociation;
import strategy.common.PlayerColor;
import strategy.playeratcahgsr.common.ComparablePlayerMove;
import strategy.playeratcahgsr.common.MoveType;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;
import strategy.tournament.MoveResult;
import strategy.tournament.PlayerMove;

public class StrategyPlayerImplTest {

	private StrategyPlayerImpl redPlayer;
	private StrategyPlayerImpl bluePlayer;
	private DeltaStrategyGame redGame;
	private DeltaStrategyGame blueGame;
	
	private ComparablePlayerMove moveToEmptySpace;
	private ComparablePlayerMove moveOutOfBounds;
	private ComparablePlayerMove moveToWater;
	private ComparablePlayerMove moveToFriendlyPiece;
	
	private Map<PieceType, Integer> redUnknowns;
	private Map<PieceType, Integer> blueUnknowns;
	
	private List<ComparablePlayerMove> moveList;
	
	@Before
	public void setUp() throws Exception {
		redPlayer = new StrategyPlayerImpl(PlayerColor.RED);
		redGame = redPlayer.getGame();
		bluePlayer = new StrategyPlayerImpl(PlayerColor.BLUE);
		blueGame = bluePlayer.getGame();
		redUnknowns = redPlayer.getUnknowns();
		blueUnknowns = bluePlayer.getUnknowns();
		
		moveToEmptySpace = new ComparablePlayerMove(new Position(3,0), new Position(4,0), redUnknowns, redGame);
		moveOutOfBounds = new ComparablePlayerMove(new Position(3,0), new Position(3,-1), redUnknowns, redGame);
		moveToWater = new ComparablePlayerMove(new Position(3,2), new Position(4,2), redUnknowns, redGame);
		moveToFriendlyPiece = new ComparablePlayerMove(new Position(3,0), new Position(3,1), redUnknowns, redGame);
		
		ComparablePlayerMove[] temp = {moveOutOfBounds, moveToWater, moveToEmptySpace, moveToFriendlyPiece};
		moveList = Arrays.asList(temp);
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
	
	@Test
	public void testGetTopMoves() {
		Collections.sort(moveList);
		Collections.reverse(moveList);
		List<ComparablePlayerMove> top = redPlayer.getTopMoves(moveList);
		assertEquals(1, top.size());
		assertEquals(MoveType.VALID, top.get(0).getType());
	}
	
	@Test
	public void testDetectScouts() {
		MoveResult result = new MoveResult(new PlayerMove((new Position(6,1)).convert(), (new Position(4,1)).convert()),
				null, null);
		redPlayer.detectScouts(result);
		assertEquals(new Piece(PieceType.SCOUT, PlayerColor.BLUE), redGame.getPieceAt(new Position(4,1)));
		result = new MoveResult(new PlayerMove((new Position(6,0)).convert(), (new Position(5,0)).convert()),
				null, null);
		redPlayer.detectScouts(result);
		assertEquals(Piece.NULL_PIECE, redGame.getPieceAt(new Position(5,0)));
	}
	
	@Test
	public void testPlayerMove() {
		redPlayer.move(null);
		bluePlayer.move(null);
	}
	
	@Test
	public void testRedMakeMove() {
		redPlayer.move(null);
		MoveResult result = new MoveResult(new PlayerMove((new Position(6,0)).convert(), (new Position(5,0)).convert()),
				null, null);
		PlayerMove redMove = redPlayer.move(result);
		ComparablePlayerMove comp = new ComparablePlayerMove(Position.convert(redMove.getFrom()), Position.convert(redMove.getTo()), redUnknowns, 
				redGame);
		assertEquals(MoveType.VALID, comp.getType());
		assertEquals(Piece.UNKNOWN_PIECE, redGame.getBoard().getPieceAt(new Position(5,0)));
	}
	
	@Test
	public void testBlueReceiveMove() {
		MoveResult result = new MoveResult(new PlayerMove((new Position(3,0)).convert(), (new Position(4,0)).convert()),
				null, null);
		PlayerMove blueMove = bluePlayer.move(result);
		ComparablePlayerMove comp = new ComparablePlayerMove(Position.convert(blueMove.getFrom()), Position.convert(blueMove.getTo()), blueUnknowns, 
				blueGame);
		assertEquals(MoveType.VALID, comp.getType());
		assertEquals(Piece.UNKNOWN_PIECE, blueGame.getBoard().getPieceAt(new Position(4,0)));
	}

}
