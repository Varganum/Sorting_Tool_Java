package sorting;

import java.util.*;


public class Main {

    private static final String SORT_INTEGER_OPERATION = "-sortIntegers";
    private static final String DATA_TYPE_MARKER = "-dataType";
    private static final String LONG_TYPE_PARAMETER = "long";
    private static final String LINE_TYPE_PARAMETER = "line";
    private static final String WORD_TYPE_PARAMETER = "word";

    public static void main(final String[] args) {

        String typeOrOperationChosen = determineTypeChosen(args);

        //System.out.println(typeOrOperationChosen);

        switch (typeOrOperationChosen) {
            case SORT_INTEGER_OPERATION: {sortIntegers(); break;}
            case LONG_TYPE_PARAMETER:    {operateLongs(); break;}
            case LINE_TYPE_PARAMETER:    {operateLines(); break;}
            case WORD_TYPE_PARAMETER:    {operateWords(); break;}
            default: System.out.println("Something is wrong.");
        }
    }

    private static void sortIntegers() {

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
        System.out.print("Sorted data:");

        for (Long num : numbers.keySet()) {
            for (long i = 0; i < numbers.get(num); i++) {
                System.out.print(" " + num);
            }
        }
    }

    private static void operateWords() {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, Long> words = new TreeMap<>(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        long newQuantity;
        int wordsCounter = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            wordsCounter++;

            if (words.containsKey(word)) {
                newQuantity = words.get(word) + 1;
                words.put(word, newQuantity);
            } else {
                words.put(word, 1L);
            }
        }
        System.out.println("Total words: " + wordsCounter + ".");
        System.out.println("The longest word: " + words.lastKey() + " (" +
                words.get(words.lastKey()) + " time(s), " +
                (int) (100 * words.get(words.lastKey()) / wordsCounter) + "%).");
    }

    private static void operateLines() {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, Long> lines = new TreeMap<>(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        long newQuantity;
        int linesCounter = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            linesCounter++;

            if (lines.containsKey(line)) {
                newQuantity = lines.get(line) + 1;
                lines.put(line, newQuantity);
            } else {
                lines.put(line, 1L);
            }
        }
        System.out.println("Total lines: " + linesCounter + ".");
        System.out.println("The longest line:\n" + lines.lastKey() + "\n(" +
                lines.get(lines.lastKey()) + " time(s), " +
                (int) (100 * lines.get(lines.lastKey()) / linesCounter) + "%).");
    }

    private static void operateLongs() {

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
                numbers.get(numbers.lastKey()) + " time(s)," +
                (int) (100 * numbers.get(numbers.lastKey()) / numbersCounter) + "%).");
    }

    private static String determineTypeChosen(String[] args) {
        String result;
        if (Arrays.stream(args).toList().contains(Main.SORT_INTEGER_OPERATION)) {
            result = Main.SORT_INTEGER_OPERATION;
        } else if (args.length > 0 && args[0].equals(Main.DATA_TYPE_MARKER)) {
            result = args[1];
        } else {
            result = Main.WORD_TYPE_PARAMETER;
        }
        return result;
    }
}
