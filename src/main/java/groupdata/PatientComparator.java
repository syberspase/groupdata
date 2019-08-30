package groupdata;

import java.util.Comparator;

/**
 * Comparator for result set
 */
public class PatientComparator implements Comparator<Patient> {
    private Patient.Key[] keysToCompare;

    /**
     * Default constructor, set with keys to compare later.
     * 
     * @param keysToCompare
     */
    public PatientComparator() {
        this.keysToCompare = new Patient.Key[0];
    }

    /**
     * Constructor with keys to compare, in that order.
     * 
     * @param keysToCompare
     */
    public PatientComparator(Patient.Key... keysToCompare) {
        this.keysToCompare = keysToCompare;
    }

    /**
     * Sets with keys to compare.
     * 
     * @param keysToCompare
     */
    public void setKeysToCompare(Patient.Key... keysToCompare) {
        this.keysToCompare = keysToCompare;
    }

    @Override
    public int compare(Patient p1, Patient p2) {
        // returns -1 if any param is null
        if (p1 == null || p2 == null)
            return -1;

        for (Patient.Key key : keysToCompare) {
            int diff = p1.getProps().get(key.name()).compareToIgnoreCase(p2.getProps().get(key.name()));
            if (diff != 0)
                return diff;
        }

        // equals
        return 0;
    }
}
