/**
 * 
 */
package strategy.common;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import strategy.Piece;
import strategy.StrategyException;

/**
 * @author alextc, andrewdhurle
 *
 */
public class BoardIterator implements Iterator<Piece> {

	private Piece next;
	private Piece[][] pieces;
	private int row;
	private int col;
	
	/**
	 * 
	 */
	public BoardIterator(Piece board[][]) {
		pieces = board;
		row = 0;
		col = 0;
		next = pieces[0][0];
	}
	
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Piece next() {
		Piece current = next;
		
		if (col >= pieces[row].length - 1) {
			col = 0;
			row++;
		}
		else {
			col++;
		}
		
		if (row >= pieces.length) {
			next = null;
		}
		else {
			next = pieces[row][col];
		}
		
		return current;
	}

	@Override
	//this should never be called as the size of the board is immutable
	public void remove() {
		return;
		
	}
	
	

}
