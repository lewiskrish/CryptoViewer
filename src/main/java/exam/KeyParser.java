package exam;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Adapted from my SOFT2201 Assignment 2 config file parsing

/**
 * Utility class for parsing API keys from a config file.
 */
public class KeyParser {

    /**
     * Returns the input API key located in the src/main/resources/keys.json file.
     *
     * @return the parsed API key
     */
    public static String getInputKey() {
        JSONObject configJson = null;
        JSONParser jsonParser = new JSONParser();
        try {
            configJson = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/keys.json"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert configJson != null;
        return (String) configJson.get("input_key");
    }

    /**
     * Returns the output API key located in the src/main/resources/keys.json file.
     *
     * @return the parsed API key
     */
    public static String getOutputKey() {
        JSONObject configJson = null;
        JSONParser jsonParser = new JSONParser();
        try {
            configJson = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/keys.json"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert configJson != null;
        return (String) configJson.get("output_key");
    }
}
