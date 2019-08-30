package groupdata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Patient ResultSet, can retrieve data by group
 */
public class PatientResultSet {
    private List<Patient> resultSet;
    List<PatientGroup> groups = new ArrayList<>();

    /**
     * Default Constructor.
     */
    public PatientResultSet() {
        resultSet = new ArrayList<Patient>();
    }

    /**
     * Constructor with formatted records.
     * 
     * @param records formatted records
     */
    public PatientResultSet(String[] records) {
        resultSet = new ArrayList<Patient>();
        for (String record : records) {
            addPatient(new Patient(record));
        }
    }

    /**
     * Adds patient to result set.
     * 
     * @param patient
     * @return
     */
    public PatientResultSet addPatient(Patient patient) {
        resultSet.add(patient);
        return this;
    }

    /**
     * Groups and order result set by comparators. 
     * If no group comparator, sort resultSet by order comparator.
     * 
     * @param groupBy comparator to group
     * @param orderBy comparator to order in group
     * @return
     */
    public PatientResultSet groupBy(Comparator<Patient> groupBy, Comparator<Patient> orderBy) {
        // Re-group
        groups.clear();

        // if no group comparator, sort resultSet by order comparator.
        if (groupBy == null) {
            resultSet.sort(orderBy);
            return this;
        }

        resultSet.sort(groupBy);

        int sequence = 0;
        Patient prevPatient = null;
        PatientGroup prevGroup = null;
        for (Patient patient : resultSet) {
            if (groups.isEmpty() || groupBy.compare(patient, prevPatient) != 0) {
                PatientGroup group = new PatientGroup(sequence++);
                groups.add(group);
                if (prevGroup != null) {
                    prevGroup.orderBy(orderBy);
                }
                prevGroup = group;
            }
            prevGroup.addPatient(patient);
            prevPatient = patient;
        }
        // order last group
        prevGroup.orderBy(orderBy);
        return this;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");

        if (groups.isEmpty()) {
            for (Patient patient : resultSet) {
                sj.add(patient.toString());
            }
        } else {
            for (PatientGroup pg : groups) {
                sj.add(pg.toString());
            }
        }

        return sj.toString();
    }

    /**
     * For local testing.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Patient p1 = new Patient("PID1,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID2,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID3,POND^AMY,M,19890224");

        Patient.Key[] groupByKeys = { Patient.Key.lastName, Patient.Key.firstName };
        Patient.Key[] orderByKeys = { Patient.Key.id };
        PatientComparator groupBy = new PatientComparator(groupByKeys);
        PatientComparator orderBy = new PatientComparator(orderByKeys);
        PatientResultSet pg = new PatientResultSet();
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        pg.groupBy(groupBy, orderBy);

        System.out.println(pg);
    }
}
