package iter;

public interface Generator {

    void populateValue(Result result);

    boolean hasNext();

    void prepareNext();

    void reset();

    long getCountEstimate();
}
