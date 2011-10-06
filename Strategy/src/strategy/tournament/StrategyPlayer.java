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
package strategy.tournament;

import strategy.*;
import strategy.common.PiecePositionAssociation;

/**
 * The StrategyPlayer interface defines the behavior required to get input and
 * output from an entity playing a game of Strategy. The entity might be a human
 * player who enters moves through the keyboard or a GUI, or a computer player
 * that possesses artificial intelligence to allow it to play a game.
 * @author gpollice
 * @version Sep 4, 2011
 */
public interface StrategyPlayer
{
	/**
	 * The game driver calls this method to get the player's starting
	 * configuration.
	 * 
	 * @return array of PiecePositionAssociation objects that define
	 * 	where the player's pieces should be placed on the board at the beginning
	 * 	of the game.
	 */
	PiecePositionAssociation[] getStartingConfiguration();
	
	/**
	 * <p>
	 * Determine your next move based upon the updated information provided.
	 * </p><p>
	 * If you are the Red player, the first time you receive this message, the
	 * <tt>gameUpdate</tt> argument will be null. After that, it will reflect
	 * new information that tells you what your opponent did in response to your
	 * last move and what new information you have learned. Specifically, the contents
	 * of each field will be:
	 * <ul>
	 * <li> 
	 * 		The <tt>oponentsLastMove</tt> provides the source and destination positions of 
	 * 		the opponent's move. 
	 * </li><li>
	 * 		If the opponent attacked you or you attacked an opponent, then the 
	 * 		<tt>myLastTarget</tt> field will tell you which opponent's piece was involved 
	 * 		in the attack. If you did not attack, this field will be null.
	 * </li><li>
	 * 		If the opponent attacked you then <tt>opponentsAttacker</tt> tells you which
	 * 		piece the opponent attacked you with.
	 * </li>
	 * </ul>
	 * 
	 * @param gameUpdate updated information based upon your last move and the opponent's
	 * 	response
	 * @return your next move
	 */
	PlayerMove move(MoveResult gameUpdate);
}
