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
		//this will throw an exception once DeltaStrategyGame is implemented
		//will need to pass in proper PiecePositionAssociation arrays
		PiecePositionAssociation[] initialBlueConfiguration = {new PiecePositionAssociation(
				new Piece(BOMB, PlayerColor.BLUE),
				new Position(9, 6))};
		PiecePositionAssociation[] initialRedConfiguration = {new PiecePositionAssociation(
				new Piece(BOMB, PlayerColor.RED),
				new Position(2, 6))};
		assertTrue(StrategyGameFactory.getInstance().makeDeltaStrategyGame(initialRedConfiguration, initialBlueConfiguration)
				instanceof DeltaStrategyGame);
	}

}
