import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Main class
 *
 * @author lucian.davidescu
 */
public class MainClass {

    private static final String YES_WORD = "synonyms";
    private static final String NO_WORD = "different";
    private static final int LEFT_POSITION = 0;
    private static final int RIGHT_POSITION = 1;
    private static final String SPLIT_REGEXP = " ";
    public static final String INPUT_FILE = "input.in";
    public static final String OUTPUT_FILE = "output.out";


    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        try {
            Path inputPath = mainClass.getPathToInputFile();
            if (inputPath != null) {
                Path outputPath = mainClass.getPathToOutputFile();
                if (outputPath != null) {
                    long startingTime = System.currentTimeMillis();
                    mainClass.solveProblem(inputPath, outputPath);
                    System.out.println("Finished generating output file in: " +
                            ((System.currentTimeMillis() - startingTime) / 1000) + " sec");
                } else {
                    System.out.println("No output file present. Please create output.out under 'src'");
                }

            } else {
                System.out.println("No input file present. Please add input.io under 'src'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void solveProblem(Path inputFile, Path outputFile) throws Exception {
        BufferedReader reader = Files.newBufferedReader(inputFile);
        String line = reader.readLine();
        int T = parseInt(line);

        while (T > 0) {
            line = reader.readLine();
            int N = parseInt(line);
            int synonimCounter = 0;
            Map<String, Integer> Nmap = new HashMap<>();
            while (N > 0) {
                line = reader.readLine();
                String[] currentLine = line.split(SPLIT_REGEXP);
                String left = getLeftValue(currentLine);
                String right = getRightValue(currentLine);
                if (Nmap.get(right) != null && Nmap.get(left) != null
                        && !Nmap.get(right).equals(Nmap.get(left))) {
                    if (Nmap.get(left) < Nmap.get(right)) {
                        updateOldMapValues(Nmap, Nmap.get(right), Nmap.get(left));
                        Nmap.put(left, Nmap.get(left));
                        Nmap.put(right, Nmap.get(left));
                    } else {
                        updateOldMapValues(Nmap, Nmap.get(left), Nmap.get(right));
                        Nmap.put(left, Nmap.get(right));
                        Nmap.put(right, Nmap.get(right));
                    }
                } else if (Nmap.get(left) != null) {
                    Nmap.put(right, Nmap.get(left));
                } else if (Nmap.get(right) != null) {
                    Nmap.put(left, Nmap.get(right));
                } else {
                    Nmap.put(left, synonimCounter);
                    Nmap.put(right, synonimCounter);
                    synonimCounter++;
                }
                N--;
            }
            line = reader.readLine();
            int Q = parseInt(line);
            while (Q > 0) {
                line = reader.readLine();
                String[] currentLine = line.split(SPLIT_REGEXP);
                String left = getLeftValue(currentLine);
                String right = getRightValue(currentLine);
                if (isSynonym(left, right, Nmap)) {
                    writeToFile(outputFile, YES_WORD);
                } else {
                    writeToFile(outputFile, NO_WORD);
                }
                Q--;
            }
            T--;
        }
    }

    private boolean isSynonym(String left, String right, Map<String, Integer> Nmap) throws Exception {
        if (left.equals(right)
                || (Nmap.get(left) != null && Nmap.get(right) != null && Nmap.get(left).equals(Nmap.get(right)))) {
            return true;
        }
        return false;
    }

    private String getLeftValue(String[] currentLine) {
        return currentLine[LEFT_POSITION].toLowerCase();
    }

    private String getRightValue(String[] currentLine) {
        return currentLine[RIGHT_POSITION].toLowerCase();
    }

    private void updateOldMapValues(Map<String, Integer> map, Integer value, Integer updatedValue) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                entry.setValue(updatedValue);
            }
        }
    }

    public Path getPathToInputFile() throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(INPUT_FILE);
        if (url != null) {
            return Paths.get(url.toURI());
        }
        return null;
    }

    public Path getPathToOutputFile() throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(OUTPUT_FILE);
        if (url != null) {
            return Paths.get(url.toURI());
        }
        return null;
    }

    public void writeToFile(Path path, String textToWrite) throws Exception {
        Files.write(
                path,
                (textToWrite + "\r\n").getBytes(),
                StandardOpenOption.APPEND);
    }

    public void clearFile(Path path) throws Exception {
        Files.write(
                path,
                ("").getBytes(),
                StandardOpenOption.WRITE);
    }
}
