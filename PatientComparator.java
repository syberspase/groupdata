package groupdata;

import java.util.Comparator;

/**
 * Comparator for result set
 */
public class PatientComparator implements Comparator<Patient> {
    private Patient.Key[] keysToCompare;
    
    /**
     * Constructor with keys to compare, in that order.
     * @param keysToCompare
     */
    public PatientComparator(Patient.Key[] keysToCompare) {
        this.keysToCompare = keysToCompare;
    }

    @Override
    public int compare(Patient p1, Patient p2) {
        if (p1 == null || p2 == null)
            return -1;
        
        for (Patient.Key key : keysToCompare) {
            int diff = p1.getProps().get(key).compareToIgnoreCase(p2.getProps().get(key));
            if (diff != 0) 
                return diff;
        }
        return 0;
    }

}
