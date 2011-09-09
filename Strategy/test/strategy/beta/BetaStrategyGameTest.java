package strategy.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import strategy.StrategyException;

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
	@Ignore
	public void testBetaStrategyGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitializeGamePlacesCorrectNumberOfPieces() {
		assertEquals(24, game.getNumPiecesOnBoard());
	}
	
	@Test
	public void testInitializeGameCreatesRandomArrangementOfPieces() throws StrategyException {
		BetaStrategyGame secondGame = new BetaStrategyGame();
		secondGame.initializeGame();
		assertFalse(secondGame.equals(game));
	}

	@Test
	@Ignore
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testIsGameOver() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetPieceAt() {
		fail("Not yet implemented");
	}

}
