import java.util.List;
import java.util.Map;

public class Filter implements FilterConsumer {

    public Filter() {
    }

    private EqualityMatchService equalityMatchService;

    // I want to inject a list of all the services here, not sure how to do that
    // possibly with spring - instructions say not to do this though!
    public Filter(EqualityMatchService equalityMatchService) {
        this.equalityMatchService = equalityMatchService;
    }

    public boolean ListIsMatch(List<QueryItem> list) {
        if (list.size() ==0) {
            return false;
        }

        // all matches are true
        if (list.stream().allMatch(
                queryItem -> queryItem.Matches == true
        )) {
            return true;
        }

        // if any of the matches are true and an OR, the whole group of queries is a match
        if (list.stream().anyMatch(
                queryItem -> queryItem.Operator == Operator.OR && queryItem.Matches == true)) {
            return true;
        }

        return false;
    }

    public boolean matches(Map<String, String> user, QueryItem queryItem) {
        if (queryItem.SubQueryItemList != null && queryItem.SubQueryItemList.size() > 0) {
            return matches(user, queryItem.SubQueryItemList);
        }
        else {
            return matchItem(user, queryItem);
        }
    }

    @Override
    public boolean matchItem(Map<String, String> user, QueryItem queryItem){
        if (!user.containsKey(queryItem.Field)) {
            return false;
        }

        String userValue = user.get(queryItem.Field);

        // INJECT EQUALITY SERVICES, SO WE CAN HAVE MULTIPLE DIFFERENT ONES HERE
        // THEN WE CAN HANDLE MORE TYPES OF EQUALITY WITHOUT ALTERING THE CODE
        // OPEN / CLOSED PRINCIPLE
        // DEPENDENCY INVERSION PRINCIPLE

        return equalityMatchService.MatchValue(queryItem.Equality, userValue, queryItem.Value);
    }

    public boolean matches(Map<String, String> user, List<QueryItem> queryItemList) {
         for (final QueryItem queryItem : queryItemList) {
             queryItem.Matches = matches(user, queryItem);
         }
         return ListIsMatch(queryItemList);
    }
}
