package groupdata;

/**
 * Test client
 *
 */
public class SolutionStandard {
    public static PatientResultSet groupResultSetByName(String[] resultSet) {
        Patient.Key[] cacheOrder = {Patient.Key.lastName, Patient.Key.firstName};
        PatientResultSet prs = new PatientResultSet(new PatientComparator(cacheOrder));
        for (String record : resultSet) {
            prs.addPatient(new Patient(record));
        }
        return prs;
    }

    public static void main(String[] args) {
        String[] input = {
                "PID1,POND^AMY,F,19890224",
                "PID2,WILLIAMS^RORY,M,19881102",
                "PID3,POND^AMY,F,19890224",
                "PID4,POND^AMY,F,20010911"
            };
        System.out.println(groupResultSetByName(input));
    }
}
