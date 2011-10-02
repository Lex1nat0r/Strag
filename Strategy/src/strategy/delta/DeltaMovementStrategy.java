package strategy.delta;

import strategy.GameState;
import strategy.Piece;
import strategy.Position;
import strategy.StrategyException;
import strategy.beta.BetaMovementStrategy;

public class DeltaMovementStrategy extends BetaMovementStrategy {

	public DeltaMovementStrategy(GameState state) {
		super(state);
	}

	@Override
	public Piece makeMove(Position source, Position destination)
			throws StrategyException {
		if(state.getBoard().getPieceAt(destination).equals(Piece.WATER_PIECE)) {
			throw new StrategyException("cannot move onto water piece");
		}
		return super.makeMove(source, destination);
	}
	

}
