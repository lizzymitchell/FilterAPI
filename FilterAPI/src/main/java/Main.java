import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create user resource having various properties:
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        // Create a filter which matches all administrators older than 30:
        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));
        queryItemList.add(new QueryItem(Operator.AND, "age", Equality.GT, "30"));

        Filter filter = null;
        try {
            filter = new Filter(queryItemList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assert filter.matches(user); // Filter should match.

        user.put("age", "25");
        assert !filter.matches(user); // Filter should not match.
    }
}