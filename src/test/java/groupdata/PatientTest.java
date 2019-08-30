package groupdata;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import groupdata.Patient.Key;

public class PatientTest {

    @Test
    public void testFullName() {
        String record = "PID1,POND^DAN,M,19890224";
        Patient p = new Patient(record);
        Map<String, String> props = p.getProps();
        assertEquals("PID1", props.get(Key.id.name()));
        assertEquals("POND", props.get(Key.lastName.name()));
        assertEquals("DAN", props.get(Key.firstName.name()));
        assertEquals("", props.get(Key.middleName.name()));
        assertEquals("M", props.get(Key.sex.name()));
        assertEquals("19890224", props.get(Key.dob.name()));
    }
    
    @Test
    public void testLastFirstName() {
        String record = "PID1,POND^DAN^JR,M,19890224";
        Patient p = new Patient(record);
        Map<String, String> props = p.getProps();
        assertEquals("PID1", props.get(Key.id.name()));
        assertEquals("POND", props.get(Key.lastName.name()));
        assertEquals("DAN", props.get(Key.firstName.name()));
        assertEquals("JR", props.get(Key.middleName.name()));
        assertEquals("M", props.get(Key.sex.name()));
        assertEquals("19890224", props.get(Key.dob.name()));
    }

}
