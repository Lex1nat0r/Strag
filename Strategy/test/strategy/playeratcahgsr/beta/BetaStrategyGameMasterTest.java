/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2011 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Author:
 *    gpollice
 *******************************************************************************/
package strategy.playeratcahgsr.beta;

import static org.junit.Assert.*;
import static strategy.common.PlayerColor.*;

import java.util.*;
import org.junit.*;
import strategy.*;
import strategy.common.*;
import strategy.playeratcahgsr.beta.BetaStrategyGame;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;
import strategy.playeratcahgsr.common.Position;
import strategy.playeratcahgsr.common.RectangularStrategyBoard;

/**
 * Test cases for the BetaStrategy game.
 * 
 * @author gpollice
 * @version Aug 1, 2011
 */
public class BetaStrategyGameMasterTest {
    private BetaStrategyGame game;
    private RectangularStrategyBoard board;
    private final int BOARD_SIZE = 6;
    
    private final Piece
    	redMarshal = new Piece(PieceType.MARSHAL, RED),
    	blueMarshal = new Piece(PieceType.MARSHAL, BLUE),
    	redGeneral = new Piece(PieceType.GENERAL, RED),
    	blueGeneral = new Piece(PieceType.GENERAL, BLUE),
		redColonel = new Piece(PieceType.COLONEL, RED),
    	blueColonel = new Piece(PieceType.COLONEL, BLUE),
		redMajor = new Piece(PieceType.MAJOR, RED),
    	blueMajor = new Piece(PieceType.MAJOR, BLUE),
    	redCaptain = new Piece(PieceType.CAPTAIN, RED),
    	blueCaptain = new Piece(PieceType.CAPTAIN, BLUE),
    	redLieutenant = new Piece(PieceType.LIEUTENANT, RED),
    	blueLieutenant = new Piece(PieceType.LIEUTENANT, BLUE),
    	redSergeant = new Piece(PieceType.SERGEANT, RED),
    	blueSergeant = new Piece(PieceType.SERGEANT, BLUE),
    	redMiner = new Piece(PieceType.MINER, RED),
    	blueMiner = new Piece(PieceType.MINER, BLUE),
    	redScout = new Piece(PieceType.SCOUT, RED),
    	blueScout = new Piece(PieceType.SCOUT, BLUE),
    	redSpy = new Piece(PieceType.SPY, RED),
    	blueSpy = new Piece(PieceType.SPY, BLUE),
    	redBomb = new Piece(PieceType.BOMB, RED),
    	blueBomb = new Piece(PieceType.BOMB, BLUE),
    	redFlag = new Piece(PieceType.FLAG, RED),
    	blueFlag = new Piece(PieceType.FLAG, BLUE);
    
    private final Position
    	p00 = new Position(0, 0),
    	p01 = new Position(0, 1),
    	p10 = new Position(1, 0),
    	p11 = new Position(1, 1),
    	p20 = new Position(2, 0),
    	p21 = new Position(2, 1),
    	p30 = new Position(3, 0),
    	p31 = new Position(3, 1),
    	p52 = new Position(5, 2),
    	p53 = new Position(5, 3);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    	game = new BetaStrategyGame();
        game.initializeGame();
    }

    /**
     * Test #1: Ensure that there is one of each piece in the initial
     * configuration for each side and that the pieces are in the proper areas
     * of the board.
     * @throws Exception 
     */
    @Test
    public void testInitialConfigurationHasOneOfEachPieceInFirstTwoRows() throws Exception
    {

        Collection redPieces = getInitialPieces(board, RED);
        for (PieceType pt : PieceType.values()) {
        		
	            assertTrue("Checking for RED " + pt.toString(),
	                    redPieces.contains(pt));
        }
        Collection bluePieces = getInitialPieces(board, BLUE);
        for (PieceType pt : PieceType.values()) {
	            assertTrue("Checking for BLUE " + pt.toString(),
	                    bluePieces.contains(pt));
        }
    }

    /**
     * Test #2: Make sure that we get a random configuration. The odds of the
     * first three configurations being identical are miniscule and should never
     * happen. If this test fails, re-run it to make sure that it's not just an
     * aberration.
     * @throws Exception 
     */
    @Test
    public void testRandomInitialConfiguration() throws Exception
    {
    	RectangularStrategyBoard board1 = getBoardCopy();
    	game.initializeGame();
    	RectangularStrategyBoard board2 = getBoardCopy();
    	game.initializeGame();
    	RectangularStrategyBoard board3 = getBoardCopy();
        String config1 = board1.toString();
        String config2 = board2.toString();
        String config3 = board3.toString();
        assertFalse(config1.equals(config2));
        assertFalse(config1.equals(config3));
        assertFalse(config2.equals(config3));
    }

    /**
     * Test #3: make sure that there are no pieces in the middle rows.
     * @throws Exception 
     */
    @Test
    public void testInitialConfigurationHasEmptySpacesOnMiddleRows() throws Exception
    {
        for (int row = 2; row < 4; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
            	final Piece p = game.getPieceAt(new Position(row, col));
                assertTrue(p == null || p == Piece.NULL_PIECE);
            }
        }
    }

    /**
     * Test #4: make sure that the game is not marked complete after it is
     * initialized.
     */
    @Test
    public void testGameIsNotCompleteAfterInitialization()
    {
        assertFalse(game.isGameOver());
    }

    /**
     * Test #5: make sure that there is no winner before any moves are made.
     */
    @Test
    public void testNoWinnerAfterInitialization()
    {
        assertNull(game.getWinner());
    }

    /**
     * Test #6: test a basic move of a red piece to an open square.
     * @throws StrategyException
     * @throws BoardException 
     */
    @Test
    public void testMoveRedPieceToOpenSquare() throws Exception
    {
        RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redScout);
        game.setBoard(board);
        assertEquals(redScout, game.move(p00, p10));
        assertTrue(positionIsEmpty(p00));
        assertEquals(redScout,game.getPieceAt(p10));
    }

    /**
     * Test #7: test a basic move of a blue piece to an open square.
     * @throws StrategyException
     */
    @Test
    public void testMoveBluePieceToOpenSquare() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redScout);
        board.putPieceAt(p53, blueSergeant);
        game.setBoard(board);
        game.move(p00, p10);
        assertEquals(blueSergeant, game.move(p53, p52));
        assertTrue(positionIsEmpty(p53));
        assertEquals(blueSergeant,game.getPieceAt(p52));
    }

    /**
     * Test #9: make sure that the game catches an invalid move from an empty
     * position.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testMoveFromEmptyPositionIsInvalid() throws StrategyException
    {
        game.move(p20, p30);
    }

    /**
     * Test #10: make sure that the game doesn't allow the BLUE player to move
     * out of turn.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testMoveBlueWhenRedTurn() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redScout);
        board.putPieceAt(p53, blueMiner);
        game.setBoard(board);
        game.move(p53, p52);
    }

    /**
     * Test #11: make sure that the game doesn't allow the RED player to move
     * out of turn.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testMoveRedWhenBlueTurn() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redColonel);
        game.setBoard(board);
        game.move(p00, p01);
        game.move(p01, p00);
    }

    /**
     * Test #12: make sure that the game does not allow any moves after the game
     * is over.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testAttemptMoveAfterGameIsOver() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redColonel);
        board.putPieceAt(p10, blueFlag);
        board.putPieceAt(p20, redCaptain);
        game.setBoard(board);
        try {
	        game.move(p00, p10);
        } catch (StrategyException e) {
        	fail("Unexpected StrategyException");
        }
	    assertTrue(game.isGameOver());
        game.move(p20, p30);	// exception expected
    }

    /**
     * Test #13: make sure that the bomb cannot move.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testAttemptToMoveBomb() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redBomb);
		game.setBoard(board);
        game.move(p00, p01);
    }

    /**
     * Test #14: make sure the flag cannot move.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testAttemptToMoveFlag() throws Exception
    {
    	RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
        board.putPieceAt(p00, redFlag);
		game.setBoard(board);
        game.move(p00, p01);
    }

    /**
     * Test #15: make sure that the scout can move more than one position to an
     * open position.
     * @throws StrategyException
     */
    @Test
	public void testMoveScoutMoreThanOnePositionToOpenPosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redScout);
		game.setBoard(board);
		game.move(p00, p30);
	}

    /**
     * Test #16: make sure that the Captain cannot move > 1 position
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testTryToMoveCaptainMoreThanOnePosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redCaptain);
		game.setBoard(board);
		game.move(p00, p30);
    }

    /**
     * Test #17: make sure that the Miner cannot move > 1 position
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testTryToMoveMinerMoreThanOnePosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redMiner);
		game.setBoard(board);
		game.move(p00, p30);
    }

    /**
     * Test #18: make sure that you can't move a piece to the position it
     * already occupies.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testTryToMoveToTheSourcePosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redLieutenant);
		game.setBoard(board);
		game.move(p00, p00);
    }

    /**
     * Test #19: test to make sure that pieces cannot move along a diagonal path.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testAttemptDiagonalMove() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redLieutenant);
		game.setBoard(board);
		game.move(p00, p11);
    }
    
    /**
     * Test #20: test to make sure that pieces only move in a straight line.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testNonLinearMove() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p00, redScout);
		game.setBoard(board);
		game.move(p00, p53);
    }

    /**
     * Test #21: test to make sure that the Scout on (1, 1) cannot move over
     * an occupied position to an empty position.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testTryToMoveScoutAt1_1OverOccupiedPosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redScout);
		board.putPieceAt(p21, blueBomb);
		game.setBoard(board);
        game.move(p11, p31);
    }
    
    /**
     * Test #22: test to make sure a scout can move to an occupied position more than 
     * one position away.
     * @throws StrategyException
     */
    @Test
    public void testMoveScoutMoreThanOnePositionToOccupiedPosition() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redScout);
		board.putPieceAt(p31, blueSpy);
		game.setBoard(board);
        assertEquals(redScout, game.move(p11, p31));
    }

    /**
     * Test #23: test to ensure that battle works properly for the Marshal winning
     * agaist a piece of lower rank.
     * @throws StrategyException
     */
    @Test
    public void testMarshalDefeatsCaptain() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redMarshal);
		board.putPieceAt(p21, blueCaptain);
		game.setBoard(board);
        assertEquals(redMarshal, game.move(p11, p21));
    }
    
    /**
     * Test #24: test to ensure that a Bomb defeats a non-miner.
     * @throws StrategyException
     */
    @Test
    public void testBombDefeatsNonMiner() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redMajor);
		board.putPieceAt(p21, blueBomb);
		game.setBoard(board);
        assertEquals(blueBomb, game.move(p11, p21));
    }
    
    /**
     * Test #25: make sure that a Miner defeats a bomb.
     * @throws StrategyException
     */
    @Test
    public void testMinerDefeatsBomb() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redMiner);
		board.putPieceAt(p21, blueBomb);
		game.setBoard(board);
        assertEquals(redMiner, game.move(p11, p21));
    }
    
    /**
     * Test #26: after RED captures the flag, the game is over and RED is the
     * winner.
     * 
     * @throws StrategyException
     */
    @Test
    public void testRedWinsGameByCapturingTheFlag() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redSpy);
		board.putPieceAt(p21, blueFlag);
		game.setBoard(board);
        assertEquals(redSpy, game.move(p11, p21));
        assertTrue(game.isGameOver());
        assertEquals(RED, game.getWinner());
    }
    
    /**
     * Test #27: after BLUE captures the flag, the game is over and BLUE is the
     * winner.
     * 
     * @throws StrategyException
     */
    @Test
    public void testBlueWinsGameByCapturingTheFlag() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redSpy);
		board.putPieceAt(p20, redFlag);
		board.putPieceAt(p21, blueSergeant);
		game.setBoard(board);
		game.move(p11, p10);
        assertEquals(blueSergeant, game.move(p21, p20));
        assertTrue(game.isGameOver());
        assertEquals(BLUE, game.getWinner());
    }
   
    /**
     * Test #28: test to make sure that an attacking Spy defeats a Marshal.
     * @throws StrategyException
     */
    @Test
    public void testAttackingSpyDefeatsMarshal() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redSpy);
		board.putPieceAt(p21, blueMarshal);
		game.setBoard(board);
        assertEquals(redSpy, game.move(p11, p21));
    }
    
    /**
     * Test #29: test to make sure that an attacking Marshal defeats a Spy.
     * @throws StrategyException
     */
    @Test
    public void testAttackingMarshalDefeatsSpy() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redMarshal);
		board.putPieceAt(p21, blueSpy);
		game.setBoard(board);
        assertEquals(redMarshal, game.move(p11, p21));
    }
    
    /**
     * Test 30: test to make sure that when two pieces of the same rank battle, both
     * are removed from the board.
     * @throws StrategyException
     */
    @Test
    public void testBattleOfEqualsRemovesBothPieces() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redLieutenant);
		board.putPieceAt(p21, blueLieutenant);
		game.setBoard(board);
		final Piece p = game.move(p11,  p21);
        assertTrue(p == null || p == Piece.NULL_PIECE);
        assertTrue(positionIsEmpty(p11));
        assertTrue(positionIsEmpty(p21));
    }
    
    /**
     * Test 31: test to make sure that a battle cannot occur with a friendly piece.
     * @throws StrategyException
     */
    @Test(expected = strategy.common.StrategyException.class)
    public void testTryToBattleFriendlyPiece() throws Exception
	{
		RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
		board.putPieceAt(p11, redLieutenant);
		board.putPieceAt(p21, redLieutenant);
		game.setBoard(board);
		game.move(p11,  p21);
    }
    /**************************** Helper Functions 
     * @throws Exception ****************************/
    /*
     * Create a collection of the piece types of the specified color that are on
     * the board.
     */
    private Collection getInitialPieces(
            RectangularStrategyBoard board, PlayerColor color) throws Exception
    {
        List pieces = new ArrayList();
        final int rowStart = color == RED ? 0 : 4;
        // Only look at the two rows where this player's pieces are supposed to
        // be
        for (int row = rowStart; row < (rowStart + 2); row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                PlayerColor pc = game.getPieceAt(new Position(row, col))
                        .getColor();
                // If the color of the piece on the board matches the parameter,
                // add
                // the piece type to the collection
                assertTrue(pc == null || pc == color); // not the opponent's
                                                       // color
                if (pc == color) {
                    pieces.add(game.getPieceAt(new Position(row, col))
                            .getType());
                }
            }
        }
        return pieces;
    }
    
    private RectangularStrategyBoard getBoardCopy() throws Exception
    {
    	final RectangularStrategyBoard board = new RectangularStrategyBoard(BOARD_SIZE, BOARD_SIZE);
    	for (int row = 0; row < BOARD_SIZE; row++) {
    		for (int col = 0; col < BOARD_SIZE; col++) {
    			final Position p = new Position(row, col);
    			board.putPieceAt(p, game.getPieceAt(p));
    		}
    	}
    	return board;
    }
    
    private boolean positionIsEmpty(Position pos) throws Exception
    {
    	final Piece piece = game.getPieceAt(pos);
    	return piece == null || piece == Piece.NULL_PIECE;
    }
}