package groupdata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Patient value object.
 */
public class Patient extends Record{
    public static enum Key {
        id, lastName, firstName, middleName, sex, dob
    };
    private String[] keys;
    private Map<String, String> props;

    /**
     * Constructor with formatted string.
     * 
     * @param str Format: "PID1,POND^AMY,F,19890224"
     */
    public Patient(String formattedValues) {
        keys = Arrays.stream(Key.values()).map(Enum::name).toArray(String[]::new);
        setRawValue(formattedValues);
        setRegexDelim("[.*&[\\^,]]");
        setKeys(keys);
        props = this.parse("middleName");
    }

    /**
     * Getter
     * 
     * @return
     */
    public Map<String, String> getProps() {
        return props;
    }

    @Override
    public String toString() {
        return getRawValue();
    }
}
