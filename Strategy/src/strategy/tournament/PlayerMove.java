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

import strategy.common.Position;

/**
 * This class encapsulates a move. DO NOT modify this class.
 * 
 * @author gpollice
 * @version Oct 2, 2011
 */
public class PlayerMove
{
	private final Position from;
	private final Position to;
	
	public PlayerMove(Position from, Position to)
	{
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public Position getFrom()
	{
		return from;
	}

	/**
	 * @return the to
	 */
	public Position getTo()
	{
		return to;
	}
}
