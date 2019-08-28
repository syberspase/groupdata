package groupdata;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * Patient ResultSet, can retrieve data by group
 */
public class PatientResultSet {
    private PriorityQueue<Patient> resultSet;
    private Comparator<Patient> comparator;
    
    /**
     * Constructor with comparator for order in result set.
     * @param comparator result set comparator
     */
    public PatientResultSet(Comparator<Patient> comparator) {
        resultSet = new PriorityQueue<Patient>(comparator);
        this.comparator = comparator;
    }
    
    /**
     * Add patient to result set.
     * @param patient
     * @return
     */
    public PatientResultSet addPatient(Patient patient) {
        resultSet.add(patient);
        return this;
    }

    /**
     * Gets result set in group
     * @return
     */
    public Stack<PatientGroup> getGroups() {
        int sequence = 0;
        Patient prevPatient = null;
        Stack<PatientGroup> groups = new Stack<>();
        for (Patient patient : resultSet) {
            if (groups.isEmpty() || comparator.compare(patient, prevPatient) != 0)
                groups.add(new PatientGroup(sequence++));
            groups.peek().addPatient(patient);
            prevPatient = patient;
        }
        return groups;
    }
    
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        Stack<PatientGroup> groups = getGroups();
        
        for (PatientGroup pg : groups) {
            sj.add(pg.toString());
        }
        return sj.toString();
    }
    
    /**
     * For local testing.
     * @param args
     */
    public static void main(String[] args) {
        Patient p1 = new Patient("PID1,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID2,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID3,POND^AMY,M,19890224");
        
        Patient.Key[] cacheOrder = {Patient.Key.lastName, Patient.Key.firstName};
        PatientResultSet pg = new PatientResultSet(new PatientComparator(cacheOrder));
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        
        System.out.println(pg);
    }
}
