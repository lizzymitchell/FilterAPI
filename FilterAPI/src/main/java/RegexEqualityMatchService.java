import java.util.EnumSet;
import java.util.Set;

public class RegexEqualityMatchService extends EqualityMatchServiceBaseClass {
    @Override
    public boolean MatchValue(Equality equality, String userValue, String queryValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Equality> supportedEqualities() {
        return EnumSet.of( Equality.REGEX );
    }
}
