import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    @Test
    void test_matches_empty_query() {
        Map<String, String> user = new LinkedHashMap<String, String>();
        user.put("firstname", "Joe");

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches); // a map with that value does not exist so the match should be false
    }

    @Test
    void test_matches_empty_user(){
        Map<String, String> user = new LinkedHashMap<String, String>();

        List<QueryItem> queryItemList = new ArrayList<QueryItem>();
        queryItemList.add(new QueryItem(Operator.AND, "role", Equality.EQ, "administrator"));

        Filter filter = new Filter();
        boolean matches = filter.matches(user, queryItemList);

        assertFalse(matches); // a map with that value does not exist so the match should be false
    }


    @Test
    void test_listIsMatch_empty_list_returns_false() {
        List<QueryItem> list = new ArrayList<>();
        Filter filter = new Filter();

        boolean match = filter.ListIsMatch(list);
        assertFalse(match);
    }

    @Test
    void test_ListIsMatch_all_false_and_returns_false() {

        QueryItem queryItem = new QueryItem();
        queryItem.Matches = false;
        queryItem.Operator = Operator.AND;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertFalse(match);
    }

    @Test
    void test_ListIsMatch_all_false_or_returns_false() {
        QueryItem queryItem = new QueryItem();
        queryItem.Matches = false;
        queryItem.Operator = Operator.OR;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertFalse(match);
    }

    @Test
    void test_ListIsMatch_some_true_and_returns_false() {
        QueryItem queryItem = new QueryItem();
        queryItem.Matches = false;
        queryItem.Operator = Operator.AND;
        QueryItem trueQueryItem = new QueryItem();
        trueQueryItem.Matches = true;
        trueQueryItem.Operator = Operator.AND;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);
        list.add(trueQueryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertFalse(match);
    }

    @Test
    void test_ListIsMatch_one_true_or_returns_true() {
        QueryItem queryItem = new QueryItem();
        queryItem.Matches = false;
        queryItem.Operator = Operator.AND;
        QueryItem trueQueryItem = new QueryItem();
        trueQueryItem.Matches = true;
        trueQueryItem.Operator = Operator.OR;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);
        list.add(trueQueryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertTrue(match);
    }

    @Test
    void test_ListIsMatch_all_true_and_returns_true(){
        QueryItem queryItem = new QueryItem();
        queryItem.Matches = true;
        queryItem.Operator = Operator.AND;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertTrue(match);
    }

    @Test
    void test_ListIsMatch_all_true_or_returns_true(){
        QueryItem queryItem = new QueryItem();
        queryItem.Matches = true;
        queryItem.Operator = Operator.OR;

        List<QueryItem> list = new ArrayList<>();
        list.add(queryItem);
        list.add(queryItem);

        Filter filter = new Filter();
        boolean match = filter.ListIsMatch(list);
        assertTrue(match);
    }

    @Test
    void matches() {
    }

    @Test
    void testMatches() {
    }
}