package coretask.mystrbuild;

import java.util.List;

public class RepositoryStringBuilder {
    private final List<SnapshotStringBuilder> snapshotStringBuilderList;
    private int id = 0;
    public RepositoryStringBuilder(List<SnapshotStringBuilder> list) {
        snapshotStringBuilderList = list;
    }

    public void add(SnapshotStringBuilder snapshotStringBuilder) {
        snapshotStringBuilderList.add(snapshotStringBuilder);
        id++;
    }

    public SnapshotStringBuilder getPenultimate() {
        if (id == 1) {
            return snapshotStringBuilderList.get(0);
        }
        id--;
        return snapshotStringBuilderList.get(id - 1);
    }
}
