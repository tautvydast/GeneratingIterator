package iter;

public class Result {

    private final StringBuilder stringBuilder;

    public Result() {
        this.stringBuilder = new StringBuilder();
    }

    public void addToResult(Object obj) {
        stringBuilder.append(obj);
    }

    public String get() {
        return stringBuilder.toString();
    }
}
