package groupdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Patient value object.
 */
public class Patient {
    public static enum Key {id, lastName, firstName, middleName, sex, dob};
    private Map<Key, String> props;
    private String formattedValues;

    /**
     * Constructor with formatted string.
     * 
     * @param str Format: "PID1,POND^AMY,F,19890224"
     */
    public Patient(String formattedValues) {
        this.formattedValues = formattedValues;
        props = new HashMap<>();
        String re = "[.*&[\\^,]]";
        String[] fields = formattedValues.split(re);
        boolean hasMiddleName = fields.length == 6 ? true : false;
        
        // Iterate enum type in the order they are declared
        int index = 0;
        for (Key key : Key.values()) {
            if (key == Key.middleName && !hasMiddleName)
                props.put(key, "");
            else
                props.put(key, fields[index++]);
        }
    }
    
    /**
     * Getter
     * @return
     */
    public Map<Key, String> getProps() {
        return props;
    }

    @Override
    public String toString() {
        return formattedValues;
    }
}
