package iter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ResultsIterator implements Iterator<Result> {

    private final List<Generator> generators;
    private boolean veryLastUsed = false;

    public ResultsIterator(Generator... generators) {
        this.generators = Arrays.asList(generators);
    }

    public long getCountEstimate() {
        long res = 0;
        for (Generator g : generators) {
            if (res == 0) {
                res = g.getCountEstimate();
            } else {
                res *= g.getCountEstimate();
            }
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return !veryLastUsed || hasAnyGeneratorNext();
    }

    private boolean hasAnyGeneratorNext() {
        return generators.stream().anyMatch(Generator::hasNext);
    }

    @Override
    public Result next() {
        if (veryLastUsed) {
            return null;
        }

        Result result = new Result();
        for (int i = 0; i < generators.size(); i++) {
            Generator g = generators.get(i);
            g.populateValue(result);
            if (isLast(i)) {
                if (g.hasNext()) {
                    g.prepareNext();
                } else if (!hasAnyGeneratorNext()) {
                    veryLastUsed = true;
                } else {
                    resetFrom(i);
                }
            }
        }
        return result;
    }

    private boolean isLast(int index) {
        return index == generators.size() - 1;
    }

    private void resetFrom(int index) {
        Generator g = generators.get(index);
        if (!g.hasNext()) {
            int prev = index - 1;
            if (prev > -1) {
                g.reset();
                Generator prevG = generators.get(prev);
                if (prevG.hasNext()) {
                    prevG.prepareNext();
                } else {
                    resetFrom(prev);
                }
            }
        }
    }
}
