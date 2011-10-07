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
package strategy.playeratcahgsr;

import strategy.common.*;
import strategy.tournament.*;

/**
 *
 * @author gpollice
 * @version Oct 5, 2011
 */
public class StrategyPlayerImpl implements StrategyPlayer
{
	private final PlayerColor myColor;
	
	/**
	 * Default
	 * @param myColor
	 */
	public StrategyPlayerImpl(PlayerColor myColor)
	{
		this.myColor = myColor;
		// TODO: add any other initialization code to your player here
	}
	/*
	 * @see strategy.tournament.StrategyPlayer#getStartingConfiguration()
	 */
	@Override
	public PiecePositionAssociation[] getStartingConfiguration()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see strategy.tournament.StrategyPlayer#move(strategy.tournament.MoveResult)
	 */
	@Override
	public PlayerMove move(MoveResult gameUpdate)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
