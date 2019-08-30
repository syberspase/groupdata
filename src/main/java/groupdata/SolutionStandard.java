package groupdata;

import groupdata.Patient.Key;

/**
 * Test client
 *
 */
public class SolutionStandard {

    public static void main(String[] args) {
        String[] records = { 
                "PID1,POND^AMY,F,19890224", 
                "PID2,WILLIAMS^RORY,M,19881102", 
                "PID3,POND^AMY,F,19890224",
                "PID4,POND^AMY,F,20010911" };
        Patient.Key[] groupByKeys = { Key.lastName, Key.firstName };
        Patient.Key[] orderByKeys = { Key.id };
        PatientComparator groupBy = new PatientComparator(groupByKeys);
        PatientComparator orderBy = new PatientComparator(orderByKeys);
        PatientResultSet prs = new PatientResultSet(records);
        prs.groupBy(groupBy, orderBy);
        System.out.println(prs);
    }
}
