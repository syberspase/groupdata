package groupdata;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * Patient group
 */
public class PatientGroup {
    private int id;
    private Queue<Patient> patients = null;
    
    /**
     * Constructor with group id
     * @param id
     */
    public PatientGroup(int id) {
        this.id = id;
        patients = new ArrayDeque<>();
    }
    
    /**
     * Adds patient to group
     * @param patient
     * @return
     */
    public PatientGroup addPatient(Patient patient) {
        patients.add(patient);
        return this;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add(id+":");
        for (Patient p : patients) {
            sj.add(p.toString());
        }
        return sj.toString();
    }
    
    /**
     * For local testing
     * @param args
     */
    public static void main(String[] args) {
        Patient p1 = new Patient("PID1,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID1,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^BOB^SR,M,19890224");
        
        PatientGroup pg = new PatientGroup(1);
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        System.out.println(pg);
    }
}
