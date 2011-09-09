package strategy.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the BetaStrategy game.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 8, 2011
 */
public class BetaStrategyGameTest {

	private BetaStrategyGame game;
	
	@Before
	public void setUp() throws Exception {
		game = new BetaStrategyGame();
		game.initializeGame();
	}

	@Test
	public void testBetaStrategyGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitializeGamePlacesCorrectNumberOfPieces() {
		assertEquals(24, game.getNumPiecesOnBoard());
	}
	
	@Test
	public void testInitializeGameCreatesRandomArrangementOfPieces() {
		BetaStrategyGame secondGame = new BetaStrategyGame();
		secondGame.initializeGame();
		assertFalse(secondGame.equals(game));
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGameOver() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPieceAt() {
		fail("Not yet implemented");
	}

}
