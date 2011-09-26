package strategy.delta;

import strategy.Piece;
import strategy.PlayerColor;
import strategy.Position;
import strategy.StrategyGame;
import strategy.common.PiecePositionAssociation;
import strategy.StrategyBoard;

public class DeltaStrategyGame implements StrategyGame {

	public DeltaStrategyGame(PiecePositionAssociation[] startingRedPieces, PiecePositionAssociation[] startingBluePieces) {
		
	}
	
	@Override
	public Piece getPieceAt(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerColor getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Piece move(Position source, Position destination) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method is used for testing purposes only.
	 * 
	 * @param board the board to set
	 */
	protected void setBoard(StrategyBoard board)
	{
		// Set the board that you use to the board supplied. This might
		// be implemented as simply as the following if you have a field
		// called "board."
		// this.board = (RectangularStrategyBoard)board;
	}

}
