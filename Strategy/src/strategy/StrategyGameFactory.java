package strategy;

import strategy.common.*;

import strategy.alpha.AlphaStrategyGame;
import strategy.beta.BetaStrategyGame;
import strategy.delta.DeltaStrategyGame;

public class StrategyGameFactory {

	private static StrategyGameFactory instance;
	
	public static StrategyGameFactory getInstance() {
		return instance;// returns the factory instance
	}
	public AlphaStrategyGame makeAlphaStrategyGame() throws StrategyException {
		return new AlphaStrategyGame();
	}
	
	public BetaStrategyGame makeBetaStrategyGame() throws StrategyException{
		return new BetaStrategyGame();
	}
	
	public DeltaStrategyGame makeDeltaStrategyGame(PiecePositionAssociation[] startingRedPieces,
				PiecePositionAssociation[] startingBluePieces) {
		return new DeltaStrategyGame(startingRedPieces, startingBluePieces);
	}
}
