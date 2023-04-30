import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create user resource having various properties:
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        //Create a filter which matches all administrators older than 30:
        Filter filter = new Filter(); // Create a filter using your API.
        assert filter.matches(user); // Filter should match.

        //user.put("age", "25");
        //assert !filter.matches(user); // Filter should not match.
    }
}