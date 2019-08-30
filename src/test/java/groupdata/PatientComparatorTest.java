package groupdata;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import groupdata.Patient.Key;

public class PatientComparatorTest {
    private PatientComparator comparator;

    @Before
    public void setUp() throws Exception {
        comparator = new PatientComparator();
    }

    @Test
    public void testCompareNull() {
        Key[] keys = {Key.id};
        comparator.setKeysToCompare(keys);
        assertTrue(comparator.compare(null, null) != 0);
    }
    
    @Test
    public void testCompareId() {
        Key[] keys = {Key.id};
        comparator.setKeysToCompare(keys);
        
        Patient p0 = new Patient("PID1,POND^DAN,M,19890224");
        Patient p1 = new Patient("PID1,POND^DAN,M,19890224");
        Patient p2 = new Patient("PID2,POND^AMY,F,19890225");
        assertTrue(comparator.compare(p0, p1) == 0);
        assertTrue(comparator.compare(p1, p2) < 0);
        assertTrue(comparator.compare(p2, p1) > 0);
    }
    
    @Test
    public void testCompareCompositeKeys() {
        Key[] keys = {Key.lastName, Key.firstName};
        comparator.setKeysToCompare(keys);
        
        Patient p0 = new Patient("PID1,POND^DAN,M,19590224");
        Patient p1 = new Patient("PID1,POND^DAN^JR,M,19890224");
        Patient p2 = new Patient("PID2,POND^AMY,F,19890225");
        assertTrue(comparator.compare(p0, p1) == 0);
        assertTrue(comparator.compare(p2, p1) < 0);
    }
}
