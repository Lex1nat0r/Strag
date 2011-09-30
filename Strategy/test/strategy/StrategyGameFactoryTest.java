package strategy;

import static org.junit.Assert.*;
import static strategy.PieceType.BOMB;

import org.junit.Test;

import strategy.alpha.AlphaStrategyGame;
import strategy.beta.BetaStrategyGame;
import strategy.common.PiecePositionAssociation;
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
		assertTrue(StrategyGameFactory.getInstance().makeDeltaStrategyGame(
				PieceConfiguration.getInstance().getInitialRedConfiguration(), 
				PieceConfiguration.getInstance().getInitialBlueConfiguration())
				instanceof DeltaStrategyGame);
	}

}
