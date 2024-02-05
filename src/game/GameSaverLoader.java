package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.entity.enemy.ShootingEnemy;
import game.entity.enemy.SimpleEnemy;
import game.entity.pickup.ChestPickup;
import game.entity.pickup.CoinPickup;
import game.entity.pickup.MelonPickup;
import game.entity.pickup.Pickup;
import game.levels.*;
import org.jbox2d.common.Vec2;

import java.io.*;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class GameSaverLoader {
    private String fileName = "saves.txt";

    /**
     * Saves the state of the current level into a text file.
     * <p>
     * This implementation iterates over Static & Dynamic bodies in the world and saves necessary information into a text file using FileWriter object.
     *
     * @param  level the level object where the player is currently residing
     * @throws IOException if an input or output exception occurred
     *
     */
    public static void save(Level level) throws IOException {
        FileWriter writer = null;

        try {
            writer = new FileWriter("saves.txt", false); // overwrite the text file
            writer.write(level.getPlayer().getName() + "," + level.getLevelName() + "\n");

            for (DynamicBody body : level.getDynamicBodies()) {
                if (body instanceof Player) {
                    Player player = level.getPlayer();
                    writer.write("Player" + "," + player.getPosition().x  + "," + player.getPosition().y + "," + player.getHealth() + "," + player.getCoins() + "\n");
                }

                if (body instanceof SimpleEnemy) {
                    Enemy enemy = (Enemy) body;
                    writer.write("SimpleEnemy" + "," + enemy.getPosition().x + "," + enemy.getPosition().y + "," + enemy.getHealth() + "\n");
                }

                if (body instanceof ShootingEnemy) {
                    Enemy enemy = (Enemy) body;
                    writer.write("ShootingEnemy" + "," + enemy.getPosition().x + "," + enemy.getPosition().y + "," + enemy.getHealth() + "\n");
                }
            }

            for (StaticBody body : level.getStaticBodies()) {
                if (body instanceof MelonPickup) {
                    Pickup pickup = (Pickup) body;
                    writer.write("Melon" + "," + pickup.getPosition().x + "," + pickup.getPosition().y + "\n");
                }

                if (body instanceof CoinPickup) {
                    Pickup pickup = (Pickup) body;
                    writer.write("Coin" + "," + pickup.getPosition().x + "," + pickup.getPosition().y + "\n");
                }

                if (body instanceof ChestPickup) {
                    ChestPickup pickup = (ChestPickup) body;
                    writer.write("Chest" + "," + pickup.getPosition().x + "," + pickup.getPosition().y + "," + pickup.getHits() + "\n");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * Loads the state of the current level from a text file.
     * <p>
     * This implementation uses FileReader and BufferReader object to read the text file with previously saved information about the current level. This is used to create the appropriate entities in the world with the necessary attributes.
     *
     * @param  game the main game's entry point to access most methods
     * @return Level the Level object related to the information saved in the designated text file
     * @throws IOException if an input or output exception occurred
     */
    public static Level load(Game game) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        Level level = null;

        try {
            fr = new FileReader("saves.txt");
            reader = new BufferedReader(fr);

            String line = reader.readLine();
            String[] levelInfo = line.split(",");

            if (levelInfo[1] != null) {
                String levelName = levelInfo[1];

                if (levelName.equals("Level1"))
                    level = new Level1(game);

                if (levelName.equals("Level2"))
                    level = new Level2(game);

                if (levelName.equals("Level3"))
                    level = new Level3(game);
            }

            level.getPlayer().setName(levelInfo[0]);
            line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(",");
                String entity = tokens[0];

                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);

                switch (entity) {
                    case "Player" -> {
                        Player player = level.getPlayer();
                        player.setHealth(Integer.parseInt(tokens[3]));
                        player.setCoins(Integer.parseInt(tokens[4]));
                        player.setPosition(new Vec2(x, y));
                    }
                    case "SimpleEnemy" -> {
                        SimpleEnemy se = new SimpleEnemy(level, new Vec2(x, y));
                        se.setHealth(Integer.parseInt(tokens[3]));
                    }
                    case "ShootingEnemy" -> {
                        ShootingEnemy se = new ShootingEnemy(level, new Vec2(x, y));
                        se.setHealth(Integer.parseInt(tokens[3]));
                    }
                    case "Melon" -> {
                        new MelonPickup(level, new Vec2(x, y));
                    }
                    case "Coin" -> {
                        new CoinPickup(level, new Vec2(x, y));
                    }
                    case "Chest" -> {
                        ChestPickup cp = new ChestPickup(level, new Vec2(x, y));
                        cp.setHits(Integer.parseInt(tokens[3]));
                    }
                    default -> {}
                }

                // process next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null)
                reader.close();

            if (fr != null)
                fr.close();
        }

        return level;
    }
}
