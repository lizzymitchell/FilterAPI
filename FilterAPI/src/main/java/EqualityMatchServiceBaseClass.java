import java.util.Set;

abstract class EqualityMatchServiceBaseClass implements EqualityMatchService {

    @Override
    abstract public Set<Equality> supportedEqualities();

    @Override
    public boolean ValidEquality(Equality equality) {
        return supportedEqualities().contains(equality);
    }

}
