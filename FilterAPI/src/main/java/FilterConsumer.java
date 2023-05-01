import java.util.Map;

public interface FilterConsumer {
    boolean matchItem(Map<String, String> user, QueryItem queryItem);
}
