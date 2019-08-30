package groupdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import groupdata.Patient.Key;

public class PatientResultSetTest {
    PatientComparator groupBy;
    PatientComparator orderBy;

    @Before
    public void setUp() throws Exception {
        Key[] groupByKeys = { Key.lastName, Key.firstName };
        Key[] orderByKeys = { Key.id };
        groupBy = new PatientComparator(groupByKeys);
        orderBy = new PatientComparator(orderByKeys);
    }

    @Test
    public void testGroupBy() {
        Patient p1 = new Patient("PID3,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID2,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^AMY,M,19890224");

        PatientResultSet pg = new PatientResultSet();
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        pg.groupBy(groupBy, null);
        
        String expected = "0:\n" + p3 + "\n1:\n" + p1 + "\n" + p2;
        assertEquals(expected, pg.toString());
    }
    
    @Test
    public void testOrderBy() {
        Patient p1 = new Patient("PID3,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID2,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^AMY,M,19890224");
        
        PatientResultSet pg = new PatientResultSet();
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        pg.groupBy(null, orderBy);
        
        String expected = p3 + "\n" + p2 + "\n" + p1;
        assertEquals(expected, pg.toString());
    }
    
    @Test
    public void testGroupByOrderBy() {
        Patient p1 = new Patient("PID3,POND^BOB,M,19890224");
        Patient p2 = new Patient("PID2,POND^BOB^JR,M,19890224");
        Patient p3 = new Patient("PID1,POND^AMY,M,19890224");

        PatientResultSet pg = new PatientResultSet();
        pg.addPatient(p1).addPatient(p2).addPatient(p3);
        pg.groupBy(groupBy, orderBy);
        
        String expected = "0:\n" + p3 + "\n1:\n" + p2 + "\n" + p1;
        assertEquals(expected, pg.toString());
    }
    
    @Test
    public void testFull() {
        String[] records = {
            "PID1,POND^AMY,F,19890224",
            "PID2,WILLIAMS^RORY,M,19881102",
            "PID3,POND^AMY,F,19890224",
            "PID4,CLARA^OSWALD,F,19890224",
            "PID5,POND^AMY,F,20010911",
            "PID6,CLAR^OSWALD,F,19890224",
            "PID7,POND^AMELIA,F,20010911",
            "PID8,CLARA^oswald,F,19890224",
            "PID9,TYLER^ROSE,F,20000101",
            "PID10,NOBLE^DONNA,F,19780405",
            "PID11,TYLER^ROSE,F,20000101",
            "PID12,NOBLE^DONN,F,19780405",
            "PID13,TYLER^ROSE,F,20000102",
            "PID14,CLARA^OSWALD^COLEMAN,F,19890224"
        };
        
        String expected = 
             "0:\n"
            +"PID6,CLAR^OSWALD,F,19890224\n"
            +"1:\n"
            +"PID14,CLARA^OSWALD^COLEMAN,F,19890224\n"
            +"PID4,CLARA^OSWALD,F,19890224\n"
            +"PID8,CLARA^oswald,F,19890224\n"
            +"2:\n"
            +"PID12,NOBLE^DONN,F,19780405\n"
            +"3:\n"
            +"PID10,NOBLE^DONNA,F,19780405\n"
            +"4:\n"
            +"PID7,POND^AMELIA,F,20010911\n"
            +"5:\n"
            +"PID1,POND^AMY,F,19890224\n"
            +"PID3,POND^AMY,F,19890224\n"
            +"PID5,POND^AMY,F,20010911\n"
            +"6:\n"
            +"PID11,TYLER^ROSE,F,20000101\n"
            +"PID13,TYLER^ROSE,F,20000102\n"
            +"PID9,TYLER^ROSE,F,20000101\n"
            +"7:\n"
            +"PID2,WILLIAMS^RORY,M,19881102";

        PatientResultSet pg = new PatientResultSet(records);
        pg.groupBy(groupBy, orderBy);
        
        assertEquals(expected, pg.toString());
    }

}
