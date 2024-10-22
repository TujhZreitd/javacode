package coretask.collection;

import java.util.Arrays;

public class ExampleFunction<T> {

    public Object[] functionUser(T[] array, Function function) {
        Object[] result = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = function.apply(array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Function function = new SimpleFunction();
        ExampleFunction<User> exampleFunction = new ExampleFunction();
        User[] users = {
                new User("Egor", "Yakushev"),
                new User("Anton", "Ivanov"),
                new User("Ivan", "Sidorov")};
        var res = exampleFunction.functionUser(users, function);
        Arrays.stream(res).forEach(System.out::println);
    }
}
