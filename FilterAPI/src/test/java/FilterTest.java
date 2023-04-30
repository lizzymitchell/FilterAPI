import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    @Test
    void test_example_usage_filter_matches() throws Exception {

        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "35");

        // Create a filter which matches all administrators older than 30:
        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));
        queryItemList.add(new QueryItem(Operator.AND, "age", Equality.GT, "30"));

        Filter filter = new Filter(queryItemList);

        boolean matches = filter.matches(user);
        assertTrue(matches); // Filter should match.
    }

    @Test
    void test_example_usage_filter_does_not_match() throws Exception {

        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");
        user.put("role", "administrator");
        user.put("age", "25");

        // Create a filter which matches all administrators older than 30:
        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));
        queryItemList.add(new QueryItem(Operator.AND, "age", Equality.GT, "30"));

        Filter filter = new Filter(queryItemList);

        boolean matches = filter.matches(user);
        assertFalse(matches); // Filter should not match.
    }

    @Test
    void test_empty_query_item_list_throws_exception() {

        List<QueryItem> queryItemList = new ArrayList<QueryItem>(); // empty query item list

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Filter(queryItemList);
        });

        String expectedMessage = "At least one QueryItem is required";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}