import java.util.List;
import java.util.Map;

public class Filter {

    private List<QueryItem> QueryItemList;

    public Filter(List<QueryItem> queryItemList) throws Exception {
        if (queryItemList.size() < 1) {
            throw new IllegalArgumentException("At least one QueryItem is required");
        }

        QueryItemList = queryItemList;
    }

    public boolean matches(Map<String, String> user) {

        QueryItem queryItem = QueryItemList.get(0);
        if (!user.containsKey(queryItem.Field)) {
            return false;
        }

        // i need to do something fancy and recursive
        // some kind of nested query items, so you could technically go on forever
        // also want to dependency inject the filter matching so we could add new equalities and operators later on


//        for (final QueryItem queryItem : QueryItemList) {
//
//
//        }

        return true;
    }

}
