import java.util.EnumSet;

public enum Equality {
    EQ,
    NE,
    GT,
    GTE,
    LT,
    LTE,
    REGEX;

    public static EnumSet<Equality> GetNumericalEqualities() {
        return EnumSet.of(GT, GTE, LT, LTE);
    }
}
