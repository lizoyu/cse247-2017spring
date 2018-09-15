package rabinkarp;

import java.util.ArrayList;
import timing.Ticker;

public class RK {
	
	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	
	private int h;
	private int constPow;
	private ArrayList<Character> window;
	

	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		this.h = 0;
		this.constPow = 1;
		for( int i = 0; i < m; ++i ){
			// manually calculate the power
			this.constPow *= 31;
			// to avoid overflow
			this.constPow %= 511;
		}
		this.window = new ArrayList<Character>();
		for( int i = 0; i < m; ++i ){
			window.add('\u0000');
		}
	}
	

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
		// get the last char that just left the window 
		int quitChar = (int) window.get(0);
		window.remove(0);
		h = ((31*h - constPow*quitChar + d) % 511 + 511) % 511;
		// add the new char to the window
		window.add(d);
		return h;
	}
}
