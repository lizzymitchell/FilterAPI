public class StringEqualityMatchServiceInjector implements EqualityMatchServiceInjector {
    @Override
    public Filter getFilter() {
        return new Filter(new StringEqualityMatchService());
    }
}
