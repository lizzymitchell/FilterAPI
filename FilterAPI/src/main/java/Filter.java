import java.util.List;
import java.util.Map;

public class Filter {

    private List<QueryItem> QueryItemList;

    public Filter(List<QueryItem> queryItemList) {
        QueryItemList = queryItemList;
    }

    public boolean matches(Map<String, String> user) {
        return true;
    }

}
