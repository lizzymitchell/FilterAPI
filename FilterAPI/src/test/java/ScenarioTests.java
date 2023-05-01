import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScenarioTests {
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

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();

        boolean matches = filter.matches(user, queryItemList);
        assertTrue(matches); // Filter should match.
        // currently failing as have not written and hooked up float equality match service
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

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();

        boolean matches = filter.matches(user, queryItemList);
        assertFalse(matches); // Filter should not match.
        // currently failing as have not written and hooked up float equality match service
    }

    @Test
    void test_single_query_field_not_present() throws Exception {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
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

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
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

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
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

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
        boolean matches = filter.matches(user,nestedQueryItemList);
        assertTrue(matches);
    }

    @Test
    void test_unbalanced_query_returns_true(){
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");

        List<QueryItem> queryList1 = new ArrayList<QueryItem>();
        queryList1.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Joe"));
        queryList1.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Frog"));

        QueryItem nestedItem = new QueryItem();
        nestedItem.SubQueryItemList = queryList1;
        nestedItem.Operator = Operator.AND;

        List<QueryItem> queryList2 = new ArrayList<QueryItem>();
        queryList2.add(nestedItem);
        queryList2.add((new QueryItem(Operator.AND, "surname", Equality.EQ, "Bloggs")));

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
        boolean matches = filter.matches(user,queryList2);
        assertTrue(matches);
    }

    @Test
    void test_unbalanced_query_returns_false(){
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");
        user.put("surname", "Bloggs");

        List<QueryItem> queryList1 = new ArrayList<QueryItem>();
        queryList1.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Joe"));
        queryList1.add(new QueryItem(Operator.OR, "firstname", Equality.EQ, "Frog"));

        QueryItem nestedItem = new QueryItem();
        nestedItem.SubQueryItemList = queryList1;
        nestedItem.Operator = Operator.AND;

        List<QueryItem> queryList2 = new ArrayList<QueryItem>();
        queryList2.add(nestedItem);
        queryList2.add((new QueryItem(Operator.AND, "surname", Equality.EQ, "Pie")));

        EqualityMatchServiceInjector injector = new StringEqualityMatchServiceInjector();
        Filter filter = injector.getFilter();
        boolean matches = filter.matches(user,queryList2);
        assertFalse(matches);
    }
}
