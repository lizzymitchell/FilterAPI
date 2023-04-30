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
        return true;
    }

}
