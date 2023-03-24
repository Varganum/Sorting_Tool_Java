package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Long, Long> numbers = new TreeMap<>();
        long newQuantity;
        int numbersCounter = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            numbersCounter++;
            // write your code here
            if (numbers.containsKey(number)) {
                newQuantity = numbers.get(number) + 1;
                numbers.put(number, newQuantity);
            } else {
                numbers.put(number, 1L);
            }
        }
        System.out.println("Total numbers: " + numbersCounter + ".");
        System.out.println("The greatest number: " + numbers.lastKey() + "(" +
                numbers.get(numbers.lastKey()) + " time(s)).");
    }
}
