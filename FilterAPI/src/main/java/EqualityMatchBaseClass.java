import java.util.Set;

abstract class EqualityMatchBaseClass implements EqualityMatch {

    @Override
    abstract public Set<Equality> supportedEqualities();

    @Override
    public boolean ValidEquality(Equality equality) {
        return supportedEqualities().contains(equality);
    }

}
