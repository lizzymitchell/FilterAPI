import java.util.List;

public class QueryItem {

    public QueryItem(){}

    public QueryItem(Operator operator, String field, Equality equality, String value) {
        Operator = operator;
        Field = field;
        Equality = equality;
        Value = value;
    }
    public Operator Operator;
    public String Field;
    public String Value;
    public Equality Equality;
    public boolean Matches; // not ideal that this is accessible outside of the filter class
    public List<QueryItem> SubQueryItemList;
}
