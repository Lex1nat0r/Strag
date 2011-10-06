package strategy;

import static org.junit.Assert.*;
import static strategy.playeratcahgsr.common.PieceType.BOMB;

import org.junit.Test;

import strategy.alpha.AlphaStrategyGame;
import strategy.playeratcahgsr.beta.BetaStrategyGame;
import strategy.playeratcahgsr.common.PiecePositionAssociation;
import strategy.playeratcahgsr.common.StrategyGameFactory;
import strategy.playeratcahgsr.delta.DeltaStrategyGame;

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
