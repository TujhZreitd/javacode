package coretask.collection;

public class SimpleFunction implements Function {
    @Override
    public Object apply(Object o) {
        return o.toString();
    }
}
