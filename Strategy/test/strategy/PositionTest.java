package strategy;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import strategy.playeratcahgsr.common.Position;

/**
 * Test case for the Position class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 8, 2011
 */
public class PositionTest {

	private Position pos;
	private Position samePos;
	
	@Before
	public void setUp() throws Exception {
		pos = new Position(0,0);
		samePos = new Position(0,0);
	}

	@Test
	public void testHashCode() {
		int hash = pos.hashCode();
		assertEquals(hash, samePos.hashCode());
		assertEquals(hash, pos.hashCode());  //test consistency
	}

	@Test
	public void testToString() {
		assertEquals("(0,0)", pos.toString());
	}

	@Test
	public void testEqualsObject() {
		Position differentPos = new Position(1,1);
		assertTrue(pos.equals(pos));
		assertTrue(pos.equals(samePos));
		assertFalse(pos.equals(differentPos));
		assertFalse(pos.equals(null));
		assertFalse(pos.equals(new Object()));
	}

}
