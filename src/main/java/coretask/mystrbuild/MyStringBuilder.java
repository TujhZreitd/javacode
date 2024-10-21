package coretask.mystrbuild;

import java.util.ArrayList;

public class MyStringBuilder {
    private char[] array;
    private int length;
    private final RepositoryStringBuilder repositoryStringBuilder;

    public MyStringBuilder() {
        array = new char[16];
        length = 0;
        repositoryStringBuilder = new RepositoryStringBuilder(new ArrayList<>());
        repositoryStringBuilder.add(new SnapshotStringBuilder(this));
    }

    public MyStringBuilder append(String str) {
        if (str == null) {
            str = "null";
        }
        ensureCapacity(length + str.length());
        for (int i = 0; i < str.length(); i++) {
            array[length++] = str.charAt(i);
        }
        saveSnapshotStringBuilder(this);
        return this;
    }

    private void ensureCapacity(int newLength) {
        if (newLength > length) {
            int newCapacity = Math.max(array.length * 2, newLength);
            char[] newArray = new char[newCapacity];
            System.arraycopy(array, 0, newArray, 0, length);
            array = newArray;
        }
    }
    private void saveSnapshotStringBuilder(MyStringBuilder myStringBuilder) {
        SnapshotStringBuilder snapshotStringBuilder = new SnapshotStringBuilder(myStringBuilder);
        repositoryStringBuilder.add(snapshotStringBuilder);
    }

    public MyStringBuilder undo() {
        String penultimateValue = repositoryStringBuilder.getPenultimate().getSnapshotStringBuilder();
        array = new char[penultimateValue.length()];
        length = 0;
        for (int i = 0; i < penultimateValue.length(); i++) {
            array[length++] = penultimateValue.charAt(i);
        }
        return this;
    }

    @Override
    public String toString() {
        return new String(array, 0, length);
    }
}

