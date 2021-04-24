import iter.ListValueGenerator;
import iter.NumbersGenerator;
import iter.Result;
import iter.ResultsIterator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ResultsIterator it = new ResultsIterator(
                new NumbersGenerator(0, 10),
                new ListValueGenerator(List.of("a", "b", "c")));

        long generated = 0;
        while (it.hasNext()) {
            Result result = it.next();
            System.out.println(result.get());
            generated++;
        }

        System.out.println("Generated: " + generated);
        System.out.println("Estimated: " + it.getCountEstimate());
    }
}
