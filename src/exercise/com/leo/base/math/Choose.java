package exercise.com.leo.base.math;

public class Choose {

	public static void main(String[] args) {
		
		System.out.println(binomial(5, 2));
        System.out.println(dumb(5, 2));
	}
	
	public static int dumb(int n, int k) {
	    if (k == 0 || n == k) {
	        return 1;
	    }
	    else {
	        return dumb(n-1, k-1) + dumb(n-1, k);
	    }
	}
	
	/** 
	 * Calculates n choose k (using dynamic programming)
	 *   @param n the total number to choose from (n > 0)
	 *   @param k the number to choose (0 <= k <= n)
	 *   @return n choose k (the binomial coefficient)
	 */
	 public static int binomial(int n, int k) { 
	    if (n < 2) {
	        return 1;
	    }
	    else {
	        int bin[][] = new int[n+1][n+1];        // CONSTRUCT A TABLE TO STORE

	        for (int r = 0; r <= n; r++) {          // COMPUTED VALUES
	            for (int c = 0; c <= r && c <= k; c++) {
	                if (c == 0 || c == r) {
	                    bin[r][c] = 1;              // ENTER 1 IF BASE CASE
	                }
	                else {                          // OTHERWISE, USE FORMULA
	                    bin[r][c] = bin[r-1][c-1] + bin[r-1][c];
	                }
	            }
	        }
	        return bin[n][k];                       // ANSWER IS AT bin[n][k]
	    }
	}

}
