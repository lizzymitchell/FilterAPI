import java.util.Set;

public interface EqualityMatchService {
    public boolean Match(Equality equality, String userValue, String queryValue);
    public Set<Equality> supportedEqualities();

    public boolean ValidEquality(Equality equality);
}
