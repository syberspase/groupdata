package groupdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import groupdata.Patient.Key;

public class PatientGroupTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAddPatient() {
        Patient p1 = new Patient("PID2,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID3,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^BOB^SR,M,19890224");
        
        PatientGroup pg = new PatientGroup(1);
        pg.addPatient(p1).addPatient(p2).addPatient(p3);

        String expected = "1:\n" + p1 + "\n" + p2 + "\n" + p3;
        assertEquals(expected, pg.toString());
    }
    
    @Test
    public void testOrderBy() {
        Patient p1 = new Patient("PID2,POND^BOB^SR,M,19890224");
        Patient p2 = new Patient("PID3,POND^ALEX^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^BOB,M,19890224");
        
        PatientGroup pg = new PatientGroup(1);
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        
        PatientComparator comparator = new PatientComparator(Key.id);
        pg.orderBy(comparator);
        
        String expected = "1:\n" + p3 + "\n" + p1 + "\n" + p2;
        assertEquals(expected, pg.toString());
        
        Key[] keys = {Key.lastName, Key.firstName, Key.middleName};
        comparator.setKeysToCompare(keys);
        pg.orderBy(comparator);
        
        expected = "1:\n" + p2 + "\n" + p3 + "\n" + p1;
        assertEquals(expected, pg.toString());
        
        expected = "1:\n" + p2 + "\n" + p1 + "\n" + p3;
        assertNotEquals(expected, pg.toString());
    }

}
