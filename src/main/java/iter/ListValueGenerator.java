package iter;

import java.util.List;

public class ListValueGenerator implements Generator {

    private final List<String> values;
    private int current;

    public ListValueGenerator(List<String> values) {
        this.values = values;
        this.current = 0;
    }

    @Override
    public void populateValue(Result result) {
        if (isCurrentValid()) {
            result.addToResult(values.get(current));
        }
    }

    @Override
    public boolean hasNext() {
        return current + 1 < values.size();
    }

    @Override
    public void prepareNext() {
        this.current++;
    }

    @Override
    public void reset() {
        this.current = 0;
    }

    private boolean isCurrentValid() {
        return current >= 0 && current < values.size();
    }

    @Override
    public long getCountEstimate() {
        return values.size();
    }
}
