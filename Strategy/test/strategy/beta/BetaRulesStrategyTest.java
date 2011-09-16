package strategy.beta;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.StrategyException;
import strategy.common.RectangularStrategyBoard;

/**
 * Test case for the BetaRulesStrategy game.
 * 
 * @author gpollice, Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Aug 29, 2011
 */
public class BetaRulesStrategyTest {

	private BetaRulesStrategy rules;
	private BetaRulesStrategy movementTestRules;
	private BetaRulesStrategy battleTestRules;
	private BetaRulesStrategy playerPlaceTestRules;
	private BetaRulesStrategy gameOverRules;
	
	@Before
	public void setUp() throws Exception {
		rules = new BetaRulesStrategy();
		
		movementTestRules = new BetaRulesStrategy();
		RectangularStrategyBoard movementTestBoard = new RectangularStrategyBoard(6, 6);
		movementTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(3,1), new Piece(PieceType.FLAG, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,4), new Piece(PieceType.BOMB, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,5), new Piece(PieceType.SPY, PlayerColor.RED));
		movementTestRules.setBoard(movementTestBoard);
		
		battleTestRules = new BetaRulesStrategy();
		RectangularStrategyBoard battleTestBoard = new RectangularStrategyBoard(6, 6);
		battleTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.LIEUTENANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,0), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(1,1), new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,2), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,2), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,3), new Piece(PieceType.BOMB, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,3), new Piece(PieceType.MINER, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,4), new Piece(PieceType.MARSHAL, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,4), new Piece(PieceType.SPY, PlayerColor.RED));
		battleTestRules.setBoard(battleTestBoard);
		
		gameOverRules = new BetaRulesStrategy();
		//put a scout and flag next to each other to test a win
		RectangularStrategyBoard gameOverTestBoard = new RectangularStrategyBoard(6, 6);
		gameOverTestBoard.putPieceAt(new Position(4,4), new Piece(PieceType.SCOUT, PlayerColor.RED));
		gameOverTestBoard.putPieceAt(new Position(4,5), new Piece(PieceType.FLAG, PlayerColor.BLUE));
		gameOverTestBoard.putPieceAt(new Position(5,4), new Piece(PieceType.SCOUT, PlayerColor.BLUE));
		gameOverTestBoard.putPieceAt(new Position(5,5), new Piece(PieceType.FLAG, PlayerColor.RED));
		gameOverRules.setBoard(gameOverTestBoard);
		
		playerPlaceTestRules = new BetaRulesStrategy(true);
	}

	@Test
	public void testInitialState() {
		assertFalse(rules.isOver());
		assertEquals(null, rules.getWinner());
	}

	@Test
	public void testInitializePlacesCorrectNumberOfPieces() {
		assertEquals(24, rules.getNumPiecesOnBoard());
	}
	
	@Test
	public void testInitializeCreatesRandomArrangementOfPieces() throws StrategyException {
		BetaRulesStrategy secondRules = new BetaRulesStrategy();
		assertFalse(secondRules.equals(rules));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveToSamePosition() throws StrategyException {
		movementTestRules.makeMove(new Position(0,0), new Position(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveFromUnoccupiedPosition() throws StrategyException {
		movementTestRules.makeMove(new Position(5,5), new Position(5,4));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveFromPositionOutOfBounds() throws StrategyException {
		movementTestRules.makeMove(new Position(6,5), new Position(5,5));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveToPositionOutOfBounds() throws StrategyException {
		movementTestRules.makeMove(new Position(0,5), new Position(0,6));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveDiagonally() throws StrategyException {
		movementTestRules.makeMove(new Position(0,0), new Position(1,1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveImmovablePiece() throws StrategyException {
		movementTestRules.makeMove(new Position(3,1), new Position(3,2));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveOutOfRange() throws StrategyException {
		movementTestRules.makeMove(new Position(0,5), new Position(2,5));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveOntoFriendlyPiece() throws StrategyException {
		movementTestRules.makeMove(new Position(0,5), new Position(0,4));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveThroughOccupiedSpace() throws StrategyException {
		//there's a flag between 0,1 and 5,1
		movementTestRules.makeMove(new Position(0,1), new Position(5,1));
	}
	
	@Test
	public void testMoveInfiniteRangePiece() throws StrategyException {
		Piece originalPiece = movementTestRules.getPieceAt(new Position(0,0));
		Piece destinationPiece = movementTestRules.makeMove(new Position(0,0), new Position(5,0));
		assertEquals(originalPiece, destinationPiece);
		assertEquals(originalPiece, movementTestRules.getPieceAt(new Position(5,0)));
		assertFalse(movementTestRules.getBoard().isOccupied(new Position(0,0)));
	}
	
	@Test
	public void testMoveDefaultRangePiece() throws StrategyException {
		Piece originalPiece = movementTestRules.getPieceAt(new Position(0,5));
		Piece destinationPiece = movementTestRules.makeMove(new Position(0,5), new Position(1,5));
		assertEquals(originalPiece, destinationPiece);
		assertEquals(originalPiece, movementTestRules.getPieceAt(new Position(1,5)));
		assertFalse(movementTestRules.getBoard().isOccupied(new Position(0,5)));
	}
	
	@Test
	public void testBattleLieutenantAttacksSergeantAndWins() throws StrategyException {
		Piece lieutenant = battleTestRules.getPieceAt(new Position(0,0));
		Piece returnedPiece = battleTestRules.makeMove(new Position(0,0), new Position(1,0));
		assertEquals(lieutenant, returnedPiece);
		assertEquals(lieutenant, battleTestRules.getPieceAt(new Position(1,0)));
		assertFalse(battleTestRules.getBoard().isOccupied(new Position(0,0)));
	}
	
	@Test
	public void testBattleSergeantAttacksLieutenantAndLoses() throws StrategyException {
		Piece lieutenant = battleTestRules.getPieceAt(new Position(1,1));
		Piece returnedPiece = battleTestRules.makeMove(new Position(0,1), new Position(1,1));
		assertEquals(lieutenant, returnedPiece);
		assertEquals(lieutenant, battleTestRules.getPieceAt(new Position(1,1)));
		assertFalse(battleTestRules.getBoard().isOccupied(new Position(0,1)));
	}
	
	@Test
	public void testBattleSergeantAttacksSergeantAndDraws() throws StrategyException {
		Piece returnedPiece = battleTestRules.makeMove(new Position(0,2), new Position(1,2));
		assertEquals(Piece.NULL_PIECE, returnedPiece);
		assertEquals(Piece.NULL_PIECE, battleTestRules.getPieceAt(new Position(1,2)));
		assertFalse(battleTestRules.getBoard().isOccupied(new Position(0,2)));
	}
	
	@Test
	public void testBattleMinerAttacksBombAndWins() throws StrategyException {
		Piece miner = battleTestRules.getPieceAt(new Position(0,3));
		Piece returnedPiece = battleTestRules.makeMove(new Position(0,3), new Position(1,3));
		assertEquals(miner, returnedPiece);
	}
	
	@Test
	public void testBattleSpyAttacksMarshalAndWins() throws StrategyException {
		Piece spy = battleTestRules.getPieceAt(new Position(0,4));
		Piece returnedPiece = battleTestRules.makeMove(new Position(0,4), new Position(1,4));
		assertEquals(spy, returnedPiece);
	}
	
	@Test
	public void testPlayerCanPlacePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(0, 0), scout);
		assertEquals(scout, playerPlaceTestRules.getPieceAt(new Position(0, 0)));
		
	}

	@Test (expected=StrategyException.class)
	public void testPlayerPlacePieceWhenPlayerNotAllowedToPlacePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		rules.playerPlacePiece(new Position(0, 0), scout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerPlacePieceInOccupiedSpace() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(0, 0), scout);
		Piece marshal = new Piece(PieceType.MARSHAL, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(0, 0), marshal);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceMultipleOfSamePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(0, 0), scout);
		Piece anotherScout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(1, 0), anotherScout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceRedPieceOutsideSetupZone() throws StrategyException {
		Piece redScout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestRules.playerPlacePiece(new Position(5,5), redScout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceBluePieceOutsideSetupZone() throws StrategyException {
		Piece blueScout = new Piece(PieceType.SCOUT, PlayerColor.BLUE);
		playerPlaceTestRules.playerPlacePiece(new Position(0,0), blueScout);
	}
	
	@Test 
	public void testRedCapsBlueFlag() throws StrategyException {
		Piece scout = gameOverRules.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverRules.makeMove(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverRules.getPieceAt(new Position(4,5)));
		assertFalse(gameOverRules.getBoard().isOccupied(new Position(4,4)));
	}

	@Test
	public void testisOver() throws StrategyException {
		Piece scout = gameOverRules.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverRules.makeMove(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverRules.getPieceAt(new Position(4,5)));
		assertFalse(gameOverRules.getBoard().isOccupied(new Position(4,4)));
		assertEquals(true,gameOverRules.isOver());
	}

	@Test
	public void testGetWinner() throws StrategyException {
		//red should win
		Piece scout = gameOverRules.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverRules.makeMove(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverRules.getPieceAt(new Position(4,5)));
		assertFalse(gameOverRules.getBoard().isOccupied(new Position(4,4)));
		assertEquals(PlayerColor.RED,gameOverRules.getWinner());
	}
	
	@Test (expected=StrategyException.class)
	public void testMoveAfterOver() throws StrategyException {
		gameOverRules.makeMove(new Position(4,4), new Position(4,5));
		gameOverRules.makeMove(new Position(4,5), new Position(4,4));
	}
	
	@Test
	public void testMorethanFiveMoves() throws StrategyException {
		gameOverRules.makeMove(new Position(4,4), new Position(4,3));
		gameOverRules.makeMove(new Position(5,4), new Position(5,3));
		gameOverRules.makeMove(new Position(4,3), new Position(4,4));
		gameOverRules.makeMove(new Position(5,3), new Position(5,4));
		gameOverRules.makeMove(new Position(4,4), new Position(4,3));
		gameOverRules.makeMove(new Position(5,4), new Position(5,3));
		gameOverRules.makeMove(new Position(4,3), new Position(4,4));
		gameOverRules.makeMove(new Position(5,3), new Position(5,4));
		gameOverRules.makeMove(new Position(4,4), new Position(4,3));
		gameOverRules.makeMove(new Position(5,4), new Position(5,3));
		assertEquals(true,gameOverRules.isOver());
	}
	
	@Test (expected=StrategyException.class)
	public void testDoubleMove() throws StrategyException {
		gameOverRules.makeMove(new Position(4,4), new Position(4,3));
		gameOverRules.makeMove(new Position(4,3), new Position(4,4));
	}
	
	@Test (expected=StrategyException.class)
	public void testBlueFirst() throws StrategyException {
		gameOverRules.makeMove(new Position(5,4), new Position(5,3));
	}

	@Test
	public void testGetPieceAt() throws StrategyException {
		//this method just delegates to the board, where it is more thoroughly tested
		//just a very basic test here
		assertTrue(rules.getPieceAt(new Position(0,0)) instanceof Piece);
	}
	
	@Test
	public void testEqualsObject() throws StrategyException {
		RectangularStrategyBoard board = new RectangularStrategyBoard(6, 6);
		rules.setBoard(board);
		BetaRulesStrategy mockRules = new BetaRulesStrategy();
		RectangularStrategyBoard mockBoard = new RectangularStrategyBoard(6, 6);
		mockRules.setBoard(mockBoard);
		assertTrue(rules.equals(rules));
		assertTrue(rules.equals(mockRules));
		assertFalse(rules.equals(movementTestRules));
		assertFalse(rules.equals(null));
		assertFalse(rules.equals(new Object()));
	}

}
