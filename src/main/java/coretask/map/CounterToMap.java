package coretask.map;

import java.util.HashMap;
import java.util.Map;

public class CounterToMap<T> {
    public Map<T, Integer> countOfElements(T[] elements) {
        Map<T, Integer> result = new HashMap<>();
        for (T element : elements) {
            if (result.containsKey(element)) {
                result.computeIfPresent(element, (k, v) ->
                        v + 1);
            } else {
                result.putIfAbsent(element, 1);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        CounterToMap<Integer> counterToMap = new CounterToMap<>();
        Integer[] numbers = {1, 3, 1, 4, 1, 3};
        Map<Integer, Integer> result = counterToMap.countOfElements(numbers);
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.println("Ключ - " + entry.getKey() + " " + "Значение - " + entry.getValue());
        }
    }
}
