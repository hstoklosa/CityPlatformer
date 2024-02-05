package game.levels;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.Game;
import game.entity.Player;
import game.entity.Portal;
import game.entity.enemy.SimpleEnemy;
import game.entity.pickup.CoinPickup;
import game.entity.pickup.MelonPickup;
import game.entity.platform.LiftPlatform;
import game.entity.platform.Platform;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class Level1 extends Level {

    public static final BodyImage TILE = new BodyImage("data/images/platform/green_tile.png");

    public Level1(Game game) {
        super(game, new ImageIcon("./data/images/bg3.png").getImage());

        // Make a ground platform
        Shape groundShape = new BoxShape(DEFAULT_TILE_SCALE * 7, DEFAULT_TILE_SCALE / 2);
        Platform ground = new Platform(this, groundShape);
        ground.setPosition(new Vec2(0f, -12f));
        ground.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE * 7, DEFAULT_TILE_SCALE / 2);

        // Suspended platform 1
        Platform platform1 = new Platform(this, new BoxShape(DEFAULT_TILE_SCALE, DEFAULT_TILE_SCALE / 2));
        platform1.setPosition(new Vec2(-15f, 5));
        platform1.rotate(-0.1f);
        platform1.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, -0.02f, Level.DEFAULT_TILE_SCALE, DEFAULT_TILE_SCALE / 2);

        // Suspended platform 2
        Shape platformShape2 = new BoxShape(Level.DEFAULT_TILE_SCALE * 3, Level.DEFAULT_TILE_SCALE / 2);
        Platform platform2 = new Platform(this, platformShape2);
        platform2.setPosition(new Vec2(11f, 5f));
        platform2.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE * 3, Level.DEFAULT_TILE_SCALE / 2);

        // Lift platform (vertical)
        Platform liftPlatform = new LiftPlatform(this, new Vec2(-4.5f, -10f), LiftPlatform.Type.VERTICAL, 17);
        new AttachedImage(liftPlatform, BROWN_PLATFORM, 1f, 0f, new Vec2(0, 0));

        // Portal to the next level
        Portal portal = new Portal(this, new Vec2(18f, 9f));

        // Invisible walls
        Platform wall = new Platform(this, new BoxShape(0.5f, 20f));
        wall.setPosition(new Vec2(-20.5f, 5f));

        Platform wall2 = new Platform(this, new BoxShape(0.5f, 20f));
        wall2.setPosition(new Vec2(20.5f, 5f));

        Platform wall3 = new Platform(this, new BoxShape(0.5f, 21f));
        wall3.setPosition(new Vec2(0, 25f));
        wall3.rotate(1.57f);
    }

    @Override
    public void populate() {
        this.getPlayer().setPosition(new Vec2(11, -10));
        Player.SPAWN_SOUND.play();

        // load enemies
        new SimpleEnemy(this, new Vec2(-14f, -9f));
        new SimpleEnemy(this, new Vec2(-4f, -9f));
        new SimpleEnemy(this, new Vec2(-15f, 10f));
        new SimpleEnemy(this, new Vec2(4f, 9f));

        // load pickups
        new MelonPickup(this, new Vec2(-8.5f, 7f));
        new CoinPickup(this, new Vec2(10f, -10f));
        new CoinPickup(this, new Vec2(5f, -8.5f));
        new CoinPickup(this, new Vec2(13f, 8f));
        new CoinPickup(this, new Vec2(10f, 8f));
        new CoinPickup(this, new Vec2(13f, 11f));
        new CoinPickup(this, new Vec2(10f, 11f));
    }

    @Override
    public String getLevelName() {
        return "Level1";
    }
}
