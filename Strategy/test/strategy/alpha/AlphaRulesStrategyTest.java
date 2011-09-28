package strategy.alpha;


import static org.junit.Assert.assertEquals;
import static strategy.PieceType.FLAG;
import static strategy.PieceType.SCOUT;
import static strategy.PlayerColor.BLUE;
import static strategy.PlayerColor.RED;

import org.junit.Before;
import org.junit.Test;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.StrategyException;

/**
 * Test case for the AlphaRulesStrategy class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 16, 2011
 */
public class AlphaRulesStrategyTest {

	private AlphaRulesStrategy game;
	
	@Before
	public void setUp() throws Exception {
		game = new AlphaRulesStrategy();
		game.initialize();
	}
	
	@Test
	public void testStartingConfiguration() throws StrategyException
	{
		assertEquals(SCOUT, game.getPieceAt(new Position(0, 0)).getType());
		assertEquals(RED, game.getPieceAt(new Position(0, 0)).getColor());
		assertEquals(FLAG, game.getPieceAt(new Position(0, 1)).getType());
		assertEquals(RED, game.getPieceAt(new Position(0, 1)).getColor());
		assertEquals(FLAG, game.getPieceAt(new Position(1, 0)).getType());
		assertEquals(BLUE, game.getPieceAt(new Position(1, 0)).getColor());
		assertEquals(SCOUT, game.getPieceAt(new Position(1, 1)).getType());
		assertEquals(BLUE, game.getPieceAt(new Position(1, 1)).getColor());
		assertEquals(false, game.isOver());
		assertEquals(null, game.getWinner());
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsHeightOverflow()
	{
		game.getPieceAt(new Position(3, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsHeightUnderflow()
	{
		game.getPieceAt(new Position(-1, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsWidthOverflow()
	{
		game.getPieceAt(new Position(0, 3));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceBoundsWidthUnderflow()
	{
		game.getPieceAt(new Position(0, -1));
	}

	@Test(expected=StrategyException.class)
	public void testMovingOntoOwnSpace() throws StrategyException
	{
		game.makeMove(new Position(0, 0), new Position(0, 0));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOntoFriendlyPiece() throws StrategyException
	{
		game.makeMove(new Position(0, 0), new Position(0, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingOutOfTurn() throws StrategyException
	{
		game.makeMove(new Position(1, 1), new Position(0, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingUnmovablePiece() throws StrategyException
	{
		game.makeMove(new Position(0, 1), new Position(1, 1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingDiagonally() throws StrategyException
	{
		game.makeMove(new Position(0, 0), new Position(1, 1));
	}
	
	@Test
	public void testRedWinning() throws StrategyException
	{
		game.makeMove(new Position(0, 0), new Position(1, 0));
		assertEquals(SCOUT, game.getPieceAt(new Position(1, 0)).getType());
		assertEquals(RED, game.getPieceAt(new Position(1, 0)).getColor());
		assertEquals(Piece.NULL_PIECE, game.getPieceAt(new Position(0, 0)));
		assertEquals(true, game.isOver());
		assertEquals(RED, game.getWinner());
	}
	
	@Test(expected=StrategyException.class)
	public void testMovingAfterWinning() throws StrategyException
	{
		game.makeMove(new Position(0, 0), new Position(1, 0));
		game.makeMove(new Position(1, 1), new Position(0, 1));
	}
}
