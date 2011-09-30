package strategy;

import static org.junit.Assert.*;

import org.junit.Test;

import strategy.alpha.AlphaStrategyGame;
import strategy.beta.BetaStrategyGame;
import strategy.delta.DeltaStrategyGame;

public class StrategyGameFactoryTest {

	@Test
	public void testMakeAlphaStrategyGame() {
		assertTrue(StrategyGameFactory.getInstance().makeAlphaStrategyGame()
				instanceof AlphaStrategyGame);
	}

	@Test
	public void testMakeBetaStrategyGame() {
		assertTrue(StrategyGameFactory.getInstance().makeBetaStrategyGame()
				instanceof BetaStrategyGame);
	}

	@Test
	public void testMakeDeltaStrategyGame() {
		//this will throw an exception once DeltaStrategyGame is implemented
		//will need to pass in proper PiecePositionAssociation arrays
		assertTrue(StrategyGameFactory.getInstance().makeDeltaStrategyGame(null, null)
				instanceof DeltaStrategyGame);
	}

}
