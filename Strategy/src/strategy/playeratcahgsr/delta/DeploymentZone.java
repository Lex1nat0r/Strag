/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 * 
 * Copyright (c) 2011 Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Author: Alex Thornton-Clark, Andrew Hurle, Gabriel Stern-Robbins
 *******************************************************************************/
package strategy.playeratcahgsr.delta;

import strategy.common.PlayerColor;

/**
 * Describes a deployment zone for a certain color.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Oct 1, 2011
 */
public class DeploymentZone {
	
	private final int minimum;
	private final int maximum;
	private final PlayerColor color;
	
	/**
	 * Create a DeploymentZone from rows minimum to maximum, inclusive,
	 * for the player of the given color.
	 * 
	 * @param minimum Minimum row covered by the zone
	 * @param maximum Maximum row covered by the zone
	 * @param color The color of the player this is for
	 */
	public DeploymentZone(int minimum, int maximum, PlayerColor color) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.color = color;
	}
	
	public int getMinimum() {
		return minimum;
	}
	
	public int getMaximum() {
		return maximum;
	}
	
	/**
	 * @param row The row to test
	 * @return true if this zone contains number, false otherwise
	 */
	public boolean hasRow(int row) {
		return row >= minimum && row <= maximum;
	}
	
	@Override
	public String toString() {
		return "DeploymentZone " + color + " from " + minimum + " to " + maximum;
	}
	
}
