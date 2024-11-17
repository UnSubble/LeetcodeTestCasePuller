import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String TARGET_URL = "https://leetcode.com/graphql?query=" +
            "{question(titleSlug: \"%s\"){codeSnippets{code}}}";

    private static void printExceptionAndExit(Scanner scanner, Exception e) {
        System.err.println(e.getMessage());
        System.err.println("Press enter to exit...");
        scanner.next();
        System.exit(1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        String sourceCode = "";

        boolean success = false;
        while (!success) {

            System.out.print("Name of the problem to be pulled: ");
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Problem name cannot be empty!");
                continue;
            }

            String targetProblem = input.trim().toLowerCase().replaceAll("\\s", "-");
            try {

                @SuppressWarnings("deprecation")
                URL url = new URL(String.format(TARGET_URL, targetProblem));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                try (InputStream stream = connection.getInputStream()) {
                    String content = new String(stream.readAllBytes());
                    Pattern pattern = Pattern.compile("(\"},\\{\"code\":\")");
                    Matcher matcher = pattern.matcher(content);

                    @SuppressWarnings("unused")
                    boolean found = matcher.find();
                    int startIndex = matcher.end();
                    @SuppressWarnings("unused")
                    boolean nextLang = matcher.find();
                    int endIndex = matcher.start();

                    sourceCode = content.substring(startIndex, endIndex);
                    sourceCode = sourceCode.replaceAll("\\\\n", "\n");
                }

            } catch (IOException | IllegalStateException e) {
                printExceptionAndExit(scanner, e);
            }

            success = true;
        }

        success = false;
        while (!success) {
            System.out.print("Path to create the file: ");
            String stringPath = scanner.nextLine();
            Path path = Path.of(stringPath).toAbsolutePath();
            if (Files.notExists(path)) {
                System.out.println("No such path was found!");
                continue;
            }
            path = path.resolveSibling(input.trim().replaceAll("\\s", "") + ".java");
            try {
                if (Files.notExists(path))
                    Files.createFile(path);

                try (OutputStream stream = Files.newOutputStream(path, StandardOpenOption.WRITE)) {
                    stream.write(sourceCode.getBytes());
                    stream.flush();
                    stream.write('\n');
                    stream.flush();
                }
            } catch (IOException e) {
                printExceptionAndExit(scanner, e);
            }
            success = true;
        }

    }
}