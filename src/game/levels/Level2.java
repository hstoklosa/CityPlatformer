package game.levels;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.Game;
import game.entity.Portal;
import game.entity.enemy.ShootingEnemy;
import game.entity.enemy.SimpleEnemy;
import game.entity.pickup.ChestPickup;
import game.entity.pickup.CoinPickup;
import game.entity.platform.LiftPlatform;
import game.entity.platform.Platform;
import game.entity.platform.TrampolinePlatform;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class Level2 extends Level {
    public static final BodyImage TILE = new BodyImage("data/images/platform/purple_tile.png");

    public Level2(Game game) {
        super(game, new ImageIcon("./data/images/bg1.png").getImage());

        // Make a ground platform
        Shape groundShape = new BoxShape(DEFAULT_TILE_SCALE * 7, DEFAULT_TILE_SCALE / 2);
        Platform ground = new Platform(this, groundShape);
        ground.setPosition(new Vec2(0f, -12f));
        ground.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE * 7, DEFAULT_TILE_SCALE / 2);

        // Suspended platform 1
        Shape platformShape1 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform1 = new Platform(this, platformShape1);
        platform1.setPosition(new Vec2(-17f, 5f));
        platform1.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 2
        Shape platformShape2 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform2 = new Platform(this, platformShape2);
        platform2.setPosition(new Vec2(5f, 5f));
        platform2.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 3
        Shape platformShape3 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform3 = new Platform(this, platformShape3);
        platform3.setPosition(new Vec2(17f, 5f));
        platform3.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 4
        Shape platformShape4 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform4 = new Platform(this, platformShape4);
        platform4.setPosition(new Vec2(17f, -5f));
        platform4.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 5
        Shape platformShape5 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform5 = new Platform(this, platformShape4);
        platform5.setPosition(new Vec2(-3f, -3f));
        platform5.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 6
        Shape platformShape6 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform6 = new Platform(this, platformShape4);
        platform6.setPosition(new Vec2(-3f, -3f));
        platform6.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Portal to the next level
        Portal portal = new Portal(this, new Vec2(17f, -1f));

        // Lift platform
        LiftPlatform lift = new LiftPlatform(this, new Vec2(-12f, 6.25f), LiftPlatform.Type.HORIZONTAL, 12);
        lift.addImage(Level.BROWN_PLATFORM);

        // Trampoline
        TrampolinePlatform tp = new TrampolinePlatform(this);
        tp.setPosition(new Vec2(11, -9));
    }

    @Override
    public void populate() {
        this.getPlayer().setPosition(new Vec2(-18f, 8f));

        // Shooting Enemy
        new ShootingEnemy(this, new Vec2(5, 8));
        new ShootingEnemy(this, new Vec2(-3, 0));
        new SimpleEnemy(this, new Vec2(-3, -9));
        new CoinPickup(this, new Vec2(-18, -9));
        new CoinPickup(this, new Vec2(-15, -9));
        new ChestPickup(this, new Vec2(17, 7.5f));
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
