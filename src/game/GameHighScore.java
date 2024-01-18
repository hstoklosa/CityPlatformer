package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameHighScore {
    private String fileName;

    public GameHighScore(String fileName) {
        this.fileName = fileName;
    }

    public static void writeHighScore(String name, int score) throws IOException {
        FileWriter writer = null;

        try {
            writer = new FileWriter("scores.txt", true);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public static HashMap<String, Integer> readScores() throws IOException {
        HashMap<String, Integer> scores = new HashMap<>();
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            System.out.println("Reading " + "scores.txt" + " ...");

            fr = new FileReader("scores.txt");
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");

                System.out.println("Name: " + tokens[0] + ", Score: " + tokens[1]);
                scores.put(tokens[0], Integer.valueOf(tokens[1]));

                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null)
                reader.close();

            if (fr != null)
                fr.close();
        }

        return scores.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
