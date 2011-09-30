package strategy;

import static strategy.PieceType.BOMB;
import static strategy.PieceType.CAPTAIN;
import static strategy.PieceType.COLONEL;
import static strategy.PieceType.FLAG;
import static strategy.PieceType.GENERAL;
import static strategy.PieceType.LIEUTENANT;
import static strategy.PieceType.MAJOR;
import static strategy.PieceType.MARSHAL;
import static strategy.PieceType.MINER;
import static strategy.PieceType.SCOUT;
import static strategy.PieceType.SERGEANT;
import static strategy.PieceType.SPY;
import strategy.common.PiecePositionAssociation;

public class PieceConfiguration {
	
	private PiecePositionAssociation[] initialRedConfiguration;
	private PiecePositionAssociation[] initialBlueConfiguration;
	private static final PieceConfiguration instance = new PieceConfiguration();

	public static PieceConfiguration getInstance() {
		return instance;
	}
	
	private PieceType[] redPieces = {
			BOMB, SERGEANT, MINER, FLAG, MAJOR, 			// (0, 0)..(0, 4)
			BOMB, BOMB, CAPTAIN, BOMB, MINER, 				// (0, 5)..(0, 9)
			SPY, COLONEL, LIEUTENANT, CAPTAIN, SCOUT, 		// (1, 0)..(1, 4)
			SERGEANT, MAJOR, SERGEANT, COLONEL, LIEUTENANT, // (1, 5)..(1, 9)
			CAPTAIN, SERGEANT, MARSHAL, MINER, LIEUTENANT, // (2, 0)..(2, 4)
			CAPTAIN, GENERAL, SCOUT, LIEUTENANT, MINER, 	// (2, 5)..(2, 9)
			MAJOR, SCOUT, SCOUT, SCOUT, BOMB, 				// (3, 0)..(3, 4)
			MINER, SCOUT, BOMB, SCOUT, SCOUT				// (3, 5)..(3, 9)
	};

	private PieceType[] bluePieces = {
			SCOUT, SCOUT, SERGEANT, MINER, SCOUT, 			// (6, 0)..(6, 4)
			SCOUT, CAPTAIN, MINER, SCOUT, SCOUT, 			// (6, 5)..(6, 9)
			LIEUTENANT, MAJOR, CAPTAIN, BOMB, LIEUTENANT, 	// (7, 0)..(7, 4)
			LIEUTENANT, COLONEL, SCOUT, MAJOR, CAPTAIN, 	// (7, 5)..(7, 9)
			MINER, SPY, SCOUT, CAPTAIN, SERGEANT, 			// (8, 0)..(8, 4)
			BOMB, BOMB, BOMB, MINER, SERGEANT, 				// (8, 5)..(8, 9)
			MAJOR, GENERAL, SERGEANT, MARSHAL, COLONEL, 		// (9, 0)..(9, 4)
			BOMB, FLAG, BOMB, MINER, LIEUTENANT				// (9, 5)..(9, 9)
	};
	
	private PieceConfiguration() {
		setUp();
	}
	
	public PiecePositionAssociation[] getInitialRedConfiguration() {
		return initialRedConfiguration;
	}
	
	public PiecePositionAssociation[] getInitialBlueConfiguration() {
		return initialBlueConfiguration;
	}
	
	private void setUp() {
		// set up red
		initialRedConfiguration = new PiecePositionAssociation[40];
		int ix = 0;
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 10; col++) {
				initialRedConfiguration[ix] = new PiecePositionAssociation(
						new Piece(redPieces[ix], PlayerColor.RED), new Position(row,
								col));
				ix++;
			}
		}

		// set up blue
		initialBlueConfiguration = new PiecePositionAssociation[40];
		ix = 0;
		for (int row = 6; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				initialBlueConfiguration[ix] = new PiecePositionAssociation(
						new Piece(bluePieces[ix], PlayerColor.BLUE), new Position(
								row, col));
				ix++;
			}
		}
	}
}
