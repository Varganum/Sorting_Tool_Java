package sorting;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static String[] typeOfDataAndSorting = new String[2];

    private static final String SORTING_TYPE_MARKER = "-sortingType";
    private static final String DATA_TYPE_MARKER = "-dataType";
    private static final String SORT_NATURAL_PARAMETER = "natural";
    private static final String SORT_BY_COUNT_PARAMETER = "byCount";
    private static final String LONG_TYPE_PARAMETER = "long";
    private static final String LINE_TYPE_PARAMETER = "line";
    private static final String WORD_TYPE_PARAMETER = "word";

    public static void main(final String[] args) {

        boolean isArgumentsCorrect;

        isArgumentsCorrect = parseArguments(args);

        //System.out.println(typeOrOperationChosen);

        if (isArgumentsCorrect) {
            switch (typeOfDataAndSorting[1]) {
                case LONG_TYPE_PARAMETER: {
                    operateLongs(typeOfDataAndSorting[0]);
                    break;
                }
                case LINE_TYPE_PARAMETER: {
                    operateLines(typeOfDataAndSorting[0]);
                    break;
                }
                case WORD_TYPE_PARAMETER: {
                    operateWords(typeOfDataAndSorting[0]);
                    break;
                }
                default:
                    System.out.println("Something is wrong.");
            }
        }
    }



    private static void operateWords(String sortingType) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, Long> wordsSortedNaturally = new TreeMap<>(Comparator.naturalOrder());

        long newQuantity;
        int wordsCounter = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            wordsCounter++;

            if (wordsSortedNaturally.containsKey(word)) {
                newQuantity = wordsSortedNaturally.get(word) + 1;
                wordsSortedNaturally.put(word, newQuantity);
            } else {
                wordsSortedNaturally.put(word, 1L);
            }
        }

        //Output results according to sorting type chosen
        System.out.println("Total words: " + wordsCounter + ".");

        if (Main.SORT_NATURAL_PARAMETER.equals(sortingType)) {
            System.out.print("Sorted data: ");
            for (String word : wordsSortedNaturally.keySet()) {
                System.out.print(" " + word);
            }
        } else if (Main.SORT_BY_COUNT_PARAMETER.equals(sortingType)) {
            //create Set and sort it by number of occurrences
            Set<String> wordsSortedByCount = wordsSortedNaturally.keySet().stream().sorted(Comparator.comparingLong(wordsSortedNaturally::get)).collect(Collectors.toCollection(LinkedHashSet::new));

            //output sorted byCount elements
            //System.out.println();
            for (String word : wordsSortedByCount) {
                System.out.println(word + ": " + wordsSortedNaturally.get(word) + " time(s), " +
                        Math.round(100 * (double) wordsSortedNaturally.get(word) / wordsCounter) + "%");
            }
        }
    }

    private static void operateLines(String sortingType) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, Long> linesSortedNaturally = new TreeMap<>(Comparator.naturalOrder());
        long newQuantity;
        int linesCounter = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            linesCounter++;

            if (linesSortedNaturally.containsKey(line)) {
                newQuantity = linesSortedNaturally.get(line) + 1;
                linesSortedNaturally.put(line, newQuantity);
            } else {
                linesSortedNaturally.put(line, 1L);
            }
        }

        //Output lines according to sorting types
        System.out.println("Total lines: " + linesCounter + ".");

        if (Main.SORT_NATURAL_PARAMETER.equals(sortingType)) {
            System.out.println("Sorted data:");
            for (String line : linesSortedNaturally.keySet()) {
                for (long i = 0; i < linesSortedNaturally.get(line); i++) {
                    System.out.println(line);
                }
            }
        } else if (Main.SORT_BY_COUNT_PARAMETER.equals(sortingType)) {
            //create Set and sort it by number of occurrences
            Set<String> linesSortedByCount = linesSortedNaturally.keySet().stream().sorted(Comparator.comparingLong(linesSortedNaturally::get)).collect(Collectors.toCollection(LinkedHashSet::new));

            //output sorted byCount elements
            for (String line : linesSortedByCount) {
                System.out.println(line + ": " + linesSortedNaturally.get(line) + " time(s), " +
                        (int) (100 * linesSortedNaturally.get(line) / linesCounter) + "%");
            }
        }

    }

    private static void operateLongs(String sortingType) {

        Scanner scanner = new Scanner(System.in);
        TreeMap<Long, Long> numbers = new TreeMap<>();
        long newQuantity;
        int numbersCounter = 0;
        String nextString = null;
        long number;

        while (scanner.hasNext()) {
            try {
                nextString = scanner.next();
                number = Long.parseLong(nextString);
                numbersCounter++;

                if (numbers.containsKey(number)) {
                    newQuantity = numbers.get(number) + 1;
                    numbers.put(number, newQuantity);
                } else {
                    numbers.put(number, 1L);
                }
            } catch (NumberFormatException exception) {
                System.out.println("\"" + nextString + "\" is not a long. It will be skipped.");
            }
        }

        //Output results according to sorting type chosen
        System.out.println("Total numbers: " + numbersCounter + ".");

        if (Main.SORT_NATURAL_PARAMETER.equals(sortingType)) {
            System.out.print("Sorted data:");
            for (Long num : numbers.keySet()) {
                for (long i = 0; i < numbers.get(num); i++) {
                    System.out.print(" " + num);
                }
            }
        } else if (Main.SORT_BY_COUNT_PARAMETER.equals(sortingType)) {
            //create Set and sort it by number of occurrences
            Set<Long> numbersSortedByCount = numbers.keySet().stream().sorted(Comparator.comparingLong(numbers::get)).collect(Collectors.toCollection(LinkedHashSet::new));

            //output sorted byCount elements
            //System.out.println();
            for (Long num : numbersSortedByCount) {
                System.out.println(num + ": " + numbers.get(num) + " time(s), " +
                        Math.round(100 * (double) numbers.get(num) / numbersCounter) + "%");
            }
        }

    }

    /* Method for parsing program arguments (Stage 4 of project).
    Returns array with 2 element:
    result[0] - sorting type: "natural" or "byCount";
    result[1] - data type: numbers, words or lines.
    If sorting type is not passed then result[0] = "natural".
    If data type is not passed then result[1] = "words".
     */
    private static boolean parseArguments(String[] args) {

        boolean isArgumentsCorrect = true;
        String[] result = new String[2];
        ArrayList<String> allArguments = new ArrayList<>();
        Collections.addAll(allArguments, args);

        if (allArguments.contains(Main.SORTING_TYPE_MARKER)) {
            if (allArguments.contains(Main.SORT_NATURAL_PARAMETER)) {
                result[0] = Main.SORT_NATURAL_PARAMETER;
            } else if (allArguments.contains(Main.SORT_BY_COUNT_PARAMETER)) {
                result[0] = Main.SORT_BY_COUNT_PARAMETER;
            } else {
                isArgumentsCorrect = false;
                System.out.println("No sorting type defined!");
            }
        } else {
            result[0] = Main.SORT_NATURAL_PARAMETER;
        }

        if (allArguments.contains(Main.DATA_TYPE_MARKER)) {
            if (allArguments.contains(Main.LINE_TYPE_PARAMETER)) {
                result[1] = Main.LINE_TYPE_PARAMETER;
            } else if (allArguments.contains(Main.LONG_TYPE_PARAMETER)) {
                result[1] = Main.LONG_TYPE_PARAMETER;
            } else if (allArguments.contains(Main.WORD_TYPE_PARAMETER)) {
                result[1] = Main.WORD_TYPE_PARAMETER;
            } else {
                isArgumentsCorrect = false;
                System.out.println("No data type defined!");
            }
        } else {
            result[1] = Main.WORD_TYPE_PARAMETER;
        }
        Main.typeOfDataAndSorting = result;

        for (String arg : allArguments) {
            if (arg.matches("-.*") &&
                    (!Main.DATA_TYPE_MARKER.equals(arg) & !Main.SORTING_TYPE_MARKER.equals(arg))) {
                System.out.println("\"" + arg + "\" is not a valid parameter. It will be skipped.");
            }
        }

        return isArgumentsCorrect;
    }

    /*
        //Methods from stages 1 - 3 of project//

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

    */

}
