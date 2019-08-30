package groupdata;

import java.util.HashMap;
import java.util.Map;

public abstract class Record {
    // keys to split by regular expression delimiter
    private String[] keys;

    // regular expression delimiter to split string
    private String regexDelim;

    // raw value to be parsed
    private String rawValue;

    /**
     * Parse raw value by keys and delimiter Only allow one optional key.
     * 
     * @param optionalKey
     * @return
     */
    protected Map<String, String> parse(String optionalKey) {
        Map<String, String> props = new HashMap<>();
        String[] fields = rawValue.split(regexDelim);
        boolean hasOptionalValue = fields.length == keys.length ? true : false;

        // Iterate keys in the order they are declared
        int index = 0;
        for (String key : keys) {
            if (optionalKey != null && key.equals(optionalKey) && !hasOptionalValue)
                props.put(key, "");
            else
                props.put(key, fields[index++]);
        }
        return props;
    }

    // getters and setters below

    protected String getRawValue() {
        return rawValue;
    }

    protected void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    protected void setKeys(String[] keys) {
        this.keys = keys;
    }

    protected void setRegexDelim(String regexDelim) {
        this.regexDelim = regexDelim;
    }

}
