import java.util.*;
import static java.lang.System.out;

/**
 * Fair and Square
 * NOTE: Good design practices are mostly ignored in programming contests.
 * @author DavidJennings
 *
 */
public class GCJ2013QualRoundProblemC {
	private Scanner in = new Scanner(System.in);
	private TreeSet<Long> pdSet;
	private static final int LIMIT = 10000000; // allows for palindromes up to 10^14
	
	public static void main(String args[]) {
		try {
			(new GCJ2013QualRoundProblemC()).goGo();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goGo() {
		buildPalindromeSet();
		
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			Long A = in.nextLong();
			Long B = in.nextLong();
			int count = 0;
			for (Long pal : pdSet.subSet(A, true, B, true)) {
				count++;
			}
			out.println("Case #" + (i + 1) + ": " + count);
		}
	}
	
	private boolean testSquare(Long pal) {
		double sqrt = Math.sqrt(pal);
		if (sqrt % 1 == 0) {
			// also check if square is palindrome
			if (isPalindrome(String.valueOf((long)sqrt))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPalindrome(String val) {
		StringBuffer buf = new StringBuffer(val);
		return val.equals(buf.reverse().toString());
	}
	
	private void buildPalindromeSet() {
		pdSet = new TreeSet<Long>();
		for (long i = 1; i < 10; i++) {
			if (testSquare(i)) {
				pdSet.add(i);
			}
		}
		
		for (int i = 1; i < LIMIT; i++) {
			String half = String.valueOf(i);
			StringBuffer buf = new StringBuffer(half);
			String reverse = buf.reverse().toString();
			Long even = Long.valueOf(half + reverse);
			if (testSquare(even)) {
				pdSet.add(even);
			}
			if (i < (LIMIT / 10)) {
				for (char c = '0'; c <= '9'; c++) {
					Long odd = Long.valueOf(half + c + reverse);
					if (testSquare(odd)) {
						pdSet.add(odd);
					}
				}
			}
		}
	}
}
