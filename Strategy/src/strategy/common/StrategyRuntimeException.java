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
 * Runtime exception to be thrown when an irrecoverable error occurs and
 * no exception is declared as being thrown.
 * @author gpollice
 * @version Oct 2, 2011
 */
public class StrategyRuntimeException extends RuntimeException
{
	/**
	 * Constructor that takes a message
	 * @param msg the message associated with the exception
	 */
	public StrategyRuntimeException(String msg)
	{
		super(msg);
	}
}
