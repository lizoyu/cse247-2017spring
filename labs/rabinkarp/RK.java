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

		// loop to calculate the power to avoid overflow
		for( int i = 0; i < m; ++i ){
			this.constPow *= 31;
			this.constPow %= 511;
		}

		// initialize the window
		this.window = new ArrayList<Character>();
		for( int i = 0; i < m; ++i ){
			this.window.add('\u0000');
		}
	}
	

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return h the rolling hash
	 */
	public int nextCh(char d) {
		// get the char that just left the window 
		int quitChar = (int) this.window.get(0);

		// remove the char left and compute the rolling hash
		this.window.remove(0);
		this.h = ((31*this.h - this.constPow*quitChar + d) % 511 + 511) % 511;

		// add the new char to the window
		this.window.add(d);
		return this.h;
	}
}
