import java.util.EnumSet;
import java.util.Set;

public class StringEqualityMatchService extends EqualityMatchServiceBaseClass {
    @Override
    public boolean Match(Equality equality, String userValue, String queryValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Equality> supportedEqualities() {
        return EnumSet.of( Equality.EQ, Equality.NE );
    }
}
