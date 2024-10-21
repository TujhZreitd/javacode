package coretask.mystrbuild;

public class SnapshotStringBuilder {
    private final String snapshotStringBuilder;

    public SnapshotStringBuilder(MyStringBuilder myStringBuilder) {
        snapshotStringBuilder = myStringBuilder.toString();
    }

    public String getSnapshotStringBuilder() {
        return snapshotStringBuilder;
    }
}
