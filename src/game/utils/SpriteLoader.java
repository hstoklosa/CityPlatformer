package game.utils;

import city.cs.engine.BodyImage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class SpriteLoader {
    public static BodyImage[] loadSprites(String folderPath) {
        return loadSprites(folderPath, 3);
    }

    /**
     * Short description.
     * <p>
     * Detailed description. You might describe the typical use of this method
     *
     * @param  folderPath
     * @param  height
     * @return BodyImage[]
     */
    public static BodyImage[] loadSprites(String folderPath, float height) {
        BodyImage[] sprites;

        try {
            // Retrieve folder & png images within it
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png"));

            // Sort files using the name: 0, 1, 2...
            Arrays.sort(files, Comparator.comparingInt(file -> Integer.parseInt(file.getName().replaceAll("\\D", ""))));

            // Load each sprites as BodyImage into an array
            sprites = new BodyImage[files.length];

            for (int i = 0; i < files.length; i++) {
                sprites[i] = new BodyImage(files[i].toURL(), height);
            }

            return sprites;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BodyImage[0];
    }

}
