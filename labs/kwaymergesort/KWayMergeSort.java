package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {
	
	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		
		// Following just copies the input as the answer
		//
		// You must replace the loop below with code that performs
		// a K-way merge sort, placing the result in ans
		//
		// The web page for this assignment provides more detail.
		//
		// Use the ticker as you normally would, to account for
		// the operations taken to perform the K-way merge sort.
		//
		
		// if input is a single Integer, just return it
		if(n == 1) return input;
		int subSize = n / K;
		
		// recursively split input into K subarrays and store in subArrays(one row for one subarray)
		Integer[][] subArrays = new Integer[K][subSize];
		for (int i = 0; i < K; ++i) {
			Integer[] subArray = new Integer[subSize];
			System.arraycopy(input, i*subSize, subArray, 0, subArray.length);
			subArrays[i] = kwaymergesort(K, subArray, ticker);
			ticker.tick();
		}
		
		// merge the K subarrays two by two
		int numArray = K, size = n / K;
		while(numArray > 1) {
			// compare and merge two subarrays into one row in 'mergedSubArrays'
			Integer[][] mergedSubArrays = new Integer[numArray/2][size*2];
			for (int i = 0; i < mergedSubArrays.length; ++i) {
				// k for index of the 1st subarray, l for index of the 2nd subarray
				int k = 0, l = 0;
				for (int j = 0; j < mergedSubArrays[0].length; ++j{
					// directly merge when subarray is an single Integer
					if(size == 1) {
						mergedSubArrays[i][0] = Math.min(subArrays[i*2+1][l], subArrays[i*2][k]);
						mergedSubArrays[i][1] = Math.max(subArrays[i*2+1][l], subArrays[i*2][k]);
						break;
					}
					// when elements in the 1st subarray are used up (pick those in 2nd)
					if(k > size-1) {
						mergedSubArrays[i][j] = subArrays[i*2+1][l];
						l++;
					}
					// when elements in the 2nd subarray are used up (pick those in 1st)
					else if(l > size-1) {
						mergedSubArrays[i][j] = subArrays[i*2][k];
						k++;
					}
					// if both aren't used up, pick the smaller one from them
					else if(subArrays[i*2+1][l] < subArrays[i*2][k]) {
						mergedSubArrays[i][j] = subArrays[i*2+1][l];
						l++;
					}
					else{
						mergedSubArrays[i][j] = subArrays[i*2][k];
						k++;
					}
				}
			}
			// clear the 'subArrays' and fill in 'mergedSubArrays' for iteration
			subArrays = null;
			subArrays = new Integer[numArray/2][size*2];
			System.arraycopy(mergedSubArrays, 0, subArrays, 0, mergedSubArrays.length);
			numArray /= 2;
			size *= 2;
		}

		// store the merged result in 'mergedArray'
		Integer[] mergedArray = new Integer[n];
		System.arraycopy(mergedSubArrays[0], 0, mergedArray, 0, mergedArray.length); 
		return mergedArray;
	}
}
