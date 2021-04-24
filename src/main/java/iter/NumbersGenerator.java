package iter;

public class NumbersGenerator implements Generator {

    private final int from;
    private final int to;

    private int current;

    public NumbersGenerator(int from, int to) {
        this.from = from;
        this.to = to;

        this.current = from;
    }

    @Override
    public void populateValue(Result result) {
        result.addToResult(current);
    }

    @Override
    public boolean hasNext() {
        return current < to;
    }

    @Override
    public void prepareNext() {
        this.current++;
    }

    @Override
    public void reset() {
        this.current = from;
    }

    @Override
    public long getCountEstimate() {
        return to - from + 1;
    }
}
