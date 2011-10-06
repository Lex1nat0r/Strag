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
package strategy.common;

/**
 * Exception class for the Strategy game. All catchable exceptions will be either
 * this class or a subclass of this class.
 * 
 * @author gpollice
 * @version Aug 1, 2011
 */
public class StrategyException extends Exception
{
	/**
	 * Instances will take a message.
	 * 
	 * @param msg the message associated with the Exception.
	 */
	public StrategyException(String msg)
	{
		super(msg);
	}
}
