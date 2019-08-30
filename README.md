SolutionSimple: simple solution by sorting lastName+firstName in place
SolutionStandard: sample client of how to use this program.
```java
    String[] records = { 
            "PID1,POND^AMY,F,19890224", 
            "PID2,WILLIAMS^RORY,M,19881102", 
            "PID3,POND^AMY,F,19890224",
            "PID4,POND^AMY,F,20010911" };
    Patient.Key[] groupByKeys = { Key.lastName, Key.firstName };
    Patient.Key[] orderByKeys = { Key.id };
    PatientComparator groupBy = new PatientComparator(groupByKeys);
    PatientComparator orderBy = new PatientComparator(orderByKeys);
    PatientResultSet prs = new PatientResultSet(records);
    prs.groupBy(groupBy, orderBy);
    System.out.println(prs);
```

How to run it:
git clone this project then
import it to IDE(e.g. Eclipse or IntellJ), run JUnit tests from IDE
or
run "mvn test" from command line

PatientResultSetTest is the full test.