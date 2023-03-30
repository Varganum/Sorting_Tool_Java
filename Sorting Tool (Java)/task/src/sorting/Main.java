package sorting;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static String[] typeOfDataAndSorting = new String[2];
    private static File inputFile;
    private static File outputFile;
    private static final String SORTING_TYPE_MARKER = "-sortingType";
    private static final String DATA_TYPE_MARKER = "-dataType";
    private static final String INPUT_FILE_MARKER = "-inputFile";
    private static final String OUTPUT_FILE_MARKER = "-outputFile";
    private static final String SORT_NATURAL_PARAMETER = "natural";
    private static final String SORT_BY_COUNT_PARAMETER = "byCount";
    private static final String LONG_TYPE_PARAMETER = "long";
    private static final String LINE_TYPE_PARAMETER = "line";
    private static final String WORD_TYPE_PARAMETER = "word";

    public static void main(final String[] args) {

        boolean isArgumentsCorrect;

        isArgumentsCorrect = parseArguments(args);

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
        Scanner scanner;
        PrintStream outputFileWriter;
        TreeMap<String, Long> wordsSortedNaturally = new TreeMap<>(Comparator.naturalOrder());

        long newQuantity;
        int wordsCounter = 0;

        scanner = getScanner();

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

        scanner.close();

        boolean isOutputFile = Objects.nonNull(Main.outputFile);

        outputFileWriter = setOutputStream(isOutputFile);

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
            for (String word : wordsSortedByCount) {
                System.out.println(word + ": " + wordsSortedNaturally.get(word) + " time(s), " +
                            Math.round(100 * (double) wordsSortedNaturally.get(word) / wordsCounter) + "%");
            }
        }

        if (Objects.nonNull(outputFileWriter)) {
            outputFileWriter.close();
        }
    }



    private static void operateLines(String sortingType) {
        Scanner scanner;
        PrintStream outputFileWriter;
        TreeMap<String, Long> linesSortedNaturally = new TreeMap<>(Comparator.naturalOrder());
        long newQuantity;
        int linesCounter = 0;

        scanner = getScanner();

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

        scanner.close();

        boolean isOutputFile = Objects.nonNull(Main.outputFile);

        outputFileWriter = setOutputStream(isOutputFile);

        //Output lines to console according to sorting types
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
                        Math.round(100 * (double) linesSortedNaturally.get(line) / linesCounter) + "%");
            }
        }

        if (Objects.nonNull(outputFileWriter)) {
            outputFileWriter.close();
        }
    }

    private static void operateLongs(String sortingType) {

        Scanner scanner;
        PrintStream outputFileWriter;
        TreeMap<Long, Long> numbers = new TreeMap<>();
        long newQuantity;
        int numbersCounter = 0;
        String nextString = null;
        long number;

        scanner = getScanner();

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

        scanner.close();

        boolean isOutputFile = Objects.nonNull(Main.outputFile);

        outputFileWriter = setOutputStream(isOutputFile);

        //Output results to console according to sorting type chosen
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
            for (Long num : numbersSortedByCount) {
                System.out.println(num + ": " + numbers.get(num) + " time(s), " +
                            Math.round(100 * (double) numbers.get(num) / numbersCounter) + "%");
            }
        }

        if (Objects.nonNull(outputFileWriter)) {
            outputFileWriter.close();
        }
    }


    private static Scanner getScanner() {
        Scanner inputScanner = null;
        if (Objects.nonNull(Main.inputFile) && Main.inputFile.exists()) {
            try {
                inputScanner = new Scanner(Main.inputFile);
            } catch (FileNotFoundException exception) {
                System.out.println("Input file is not found.");
            }
        } else {
            inputScanner = new Scanner(System.in);
        }
        return inputScanner;
    }

    private static PrintStream setOutputStream(boolean isOutputFile) {
        PrintStream result = null;
        if (isOutputFile) {
            try {
                //if output file is provided - create new PrintStream and set System out stream to this mew PrintStream
                result = new PrintStream(Main.outputFile);
                System.setOut(result);
            } catch (IOException exception) {
                System.out.println("Some troubles with output file.");
            }
        }
        return result;
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

        if (allArguments.contains(Main.INPUT_FILE_MARKER)) {
            int index = allArguments.indexOf(Main.INPUT_FILE_MARKER) + 1;
            Main.inputFile = new File(allArguments.get(index));
        }

        if (allArguments.contains(Main.OUTPUT_FILE_MARKER)) {
            int index = allArguments.indexOf(Main.OUTPUT_FILE_MARKER) + 1;
            Main.outputFile = new File(allArguments.get(index));
        }

        for (String arg : allArguments) {
            if (arg.matches("-.*") &&
                    (!Main.DATA_TYPE_MARKER.equals(arg) & !Main.SORTING_TYPE_MARKER.equals(arg) &
                            !Main.INPUT_FILE_MARKER.equals(arg) & !Main.OUTPUT_FILE_MARKER.equals(arg))) {
                System.out.println("\"" + arg + "\" is not a valid parameter. It will be skipped.");
            }
        }

        Main.typeOfDataAndSorting = result;
        return isArgumentsCorrect;
    }

}
