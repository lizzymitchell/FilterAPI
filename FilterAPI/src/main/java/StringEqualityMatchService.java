import java.util.EnumSet;
import java.util.Set;

public class StringEqualityMatchService extends EqualityMatchServiceBaseClass {
    @Override
    public boolean MatchValue(Equality equality, String userValue, String queryValue) {
        switch(equality) {
            case EQ:
                return userValue == queryValue;
            case NE:
                return userValue != queryValue;
            default:
                throw new UnsupportedOperationException("Equality not supported");
        }
    }

    @Override
    public Set<Equality> supportedEqualities() {
        return EnumSet.of( Equality.EQ, Equality.NE );
    }
}
