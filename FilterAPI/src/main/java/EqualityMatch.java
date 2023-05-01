import java.util.Set;

public interface EqualityMatch {
    public boolean Match(Equality equality, String userValue, String queryValue);
    public Set<Equality> supportedEqualities();

    public boolean ValidEquality(Equality equality);
}
