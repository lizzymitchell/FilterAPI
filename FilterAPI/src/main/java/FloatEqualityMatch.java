import java.util.EnumSet;
import java.util.Set;

public class FloatEqualityMatch extends EqualityMatchBaseClass {

    @Override
    public boolean Match(Equality equality, String userValue, String queryValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Equality> supportedEqualities() {
        return EnumSet.of( Equality.GT, Equality.GTE, Equality.LT, Equality.LTE );
    }
}
