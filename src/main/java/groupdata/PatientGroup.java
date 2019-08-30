package groupdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Patient group
 */
public class PatientGroup {
    private int id;
    private List<Patient> patients = null;

    /**
     * Constructor with group id
     * 
     * @param id
     */
    public PatientGroup(int id) {
        this.id = id;
        patients = new ArrayList<>();
    }

    /**
     * Adds patient to group
     * 
     * @param patient
     * @return
     */
    public PatientGroup addPatient(Patient patient) {
        patients.add(patient);
        return this;
    }

    /**
     * Orders patients by comparator.
     * 
     * @param comparator
     * @return
     */
    public PatientGroup orderBy(Comparator<Patient> comparator) {
        if (comparator != null)
            Collections.sort(patients, comparator);
        return this;
    }

    /**
     * Getter
     * 
     * @return list of patients
     */
    public List<Patient> getPatients() {
        return this.patients;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add(id + ":");
        for (Patient p : patients) {
            sj.add(p.toString());
        }
        return sj.toString();
    }
}
