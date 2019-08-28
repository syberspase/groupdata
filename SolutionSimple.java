package groupdata;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

/**
 * Simple solution
 *
 */
public class SolutionSimple {
    /**
     * Sorts result set in place.
     * @param resultSet
     * @return
     */
	public static String[] groupResultSetByName(String[] resultSet) {
	    Arrays.sort(resultSet, (a,b)->{
	        String[] nameA = a.split(",")[1].toLowerCase().split("\\^");
	        String[] nameB = b.split(",")[1].toLowerCase().split("\\^");
	        return (nameA[0]+nameA[1]).compareTo((nameB[0]+nameB[1]));
	    });
		return resultSet;
	}
	
	/**
	 * For local testing.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] input = {
			"PID1,POND^AMY,F,19890224",
			"PID2,WILLIAMS^RORY,M,19881102",
			"PID3,POND^AMY,F,19890224",
			"PID4,POND^AMY,F,20010911"
		};
		String[] expected = {
			"PID1,POND^AMY,F,19890224",
			"PID3,POND^AMY,F,19890224",
			"PID4,POND^AMY,F,20010911",
            "PID2,WILLIAMS^RORY,M,19881102"
		};
		assertArrayEquals(expected, groupResultSetByName(input));
	}
}
