import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

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



}