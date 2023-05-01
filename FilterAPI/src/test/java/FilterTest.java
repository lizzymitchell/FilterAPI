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

        Filter filter = new Filter();

        boolean matches = filter.matches(user, queryItemList);
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

        Filter filter = new Filter();

        boolean matches = filter.matches(user, queryItemList);
        assertFalse(matches); // Filter should not match.
    }

    @Test
    void test_empty_query() {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches); // a map with that value does not exist so the match should be false
    }

    @Test
    void test_empty_user(){
        Map<String, String> user = new LinkedHashMap<String, String>();

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches); // a map with that value does not exist so the match should be false
    }

    @Test
    void test_single_query_field_not_present() throws Exception {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches); // a map with that value does not exist so the match should be false
    }

    @Test
    void test_bottom_level_or_matches() throws Exception {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "firstname", Equality.EQ, "Sam"));
        queryItemList.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Joe"));

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertTrue(matches);
    }

    @Test
    void test_bottom_level_or_no_matches() throws Exception {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "firstname", Equality.EQ, "Sam"));
        queryItemList.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Frog"));

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches);
    }

    @Test
    void test_1_level_match_matches(){
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");

        List<QueryItem> nonMatchQueryItemList = new ArrayList<QueryItem>();
        nonMatchQueryItemList.add(new QueryItem(Operator.AND, "firstname", Equality.EQ, "Sam"));
        nonMatchQueryItemList.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Frog"));

        List<QueryItem> matchQueryItemList = new ArrayList<QueryItem>();
        matchQueryItemList.add(new QueryItem(Operator.AND, "firstname", Equality.EQ, "Joe"));
        matchQueryItemList.add(new QueryItem(Operator.AND, "surname", Equality.EQ, "Bloggs"));

        QueryItem nonMatchQueryItem = new QueryItem();
        nonMatchQueryItem.SubQueryItemList = nonMatchQueryItemList;
        nonMatchQueryItem.Operator = Operator.OR;

        QueryItem matchQueryItem = new QueryItem();
        matchQueryItem.SubQueryItemList = matchQueryItemList;
        matchQueryItem.Operator = Operator.OR;

        List<QueryItem> nestedQueryItemList = new ArrayList<>();
        nestedQueryItemList.add(nonMatchQueryItem);
        nestedQueryItemList.add(matchQueryItem);

        Filter filter = new Filter();
        boolean matches = filter.matches(user,nestedQueryItemList);
        assertTrue(matches);
    }

}