import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CountNumbers {

    public static String fileName = "numbers.txt";

    public static List<String> readFileInList(String fileName) {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Double count_numbers(String fileName) {
        List<String> numbersNotSorted = readFileInList(fileName);
        Predicate<String> numberFilter = Pattern
                .compile("^[0-9-+]*\\.+\\d*$")
                .asPredicate();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        List<String> numbersSorted = numbersNotSorted.stream()
                .filter(item -> !item.isEmpty())
                .filter(item -> !item.startsWith("#"))
                .filter(numberFilter)
                .collect(Collectors.toList());

        List<Double> numbers = numbersSorted.stream()
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        numbers.forEach(System.out::println);

        double sum = numbers.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return Double.parseDouble(decimalFormat.format(sum));
    }

    public static void main(String[] args) {
        System.out.println(count_numbers(fileName));
    }
}
