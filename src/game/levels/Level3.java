package game.levels;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.Game;
import game.entity.Portal;
import game.entity.enemy.ShootingEnemy;
import game.entity.enemy.SpikeEnemy;
import game.entity.pickup.CoinPickup;
import game.entity.platform.LiftPlatform;
import game.entity.platform.Platform;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class Level3 extends Level {
    public static final BodyImage TILE = new BodyImage("data/images/platform/red_tile.png");
    public static final BodyImage SUB_TILE = new BodyImage("data/images/platform/subred.png");

    public Level3(Game game) {
        super(game, new ImageIcon("./data/images/bg2.png").getImage());

        // Suspended platform
        Shape platformShape1 = new BoxShape(Level.DEFAULT_TILE_SCALE, 1.5f);
        Platform platform1 = new Platform(this, platformShape1);
        platform1.setPosition(new Vec2(-17f, -5f));
        platform1.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Lift platform
        LiftPlatform lift = new LiftPlatform(this, new Vec2(-12f, -3.75f), LiftPlatform.Type.HORIZONTAL, 14);
        lift.addImage(Level.BROWN_PLATFORM);

        // Shape platformShape2 = new BoxShape(1.5f, 0.85f);
        // Platform platform2 = new Platform(this, platformShape2);
        // platform2.setPosition(new Vec2(-15.5f, -8f));
        // new AttachedImage(platform2, SUB_TILE, 1.7f, 0f, new Vec2(-0f, 0));

        Shape subPlatformShape = new BoxShape(1.5f, Level.DEFAULT_SUBTILE_SCALE * 3); // hh = 0.80f

        Platform platform3 = new Platform(this, subPlatformShape);
        platform3.setPosition(new Vec2(-18.55f, -11f));
        platform3.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        Platform platform2 = new Platform(this, subPlatformShape);
        platform2.setPosition(new Vec2(-15.55f, -11f));
        platform2.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        new SpikeEnemy(this, new Vec2(-13f, -9.5f));
        new SpikeEnemy(this, new Vec2(-11f, -9.5f));
        new SpikeEnemy(this, new Vec2(-9f, -9.5f));
        new SpikeEnemy(this, new Vec2(-7f, -9.5f));
        new SpikeEnemy(this, new Vec2(-5f, -9.5f));
        new SpikeEnemy(this, new Vec2(-3f, -9.5f));
        new SpikeEnemy(this, new Vec2(-1f, -9.5f));
        new SpikeEnemy(this, new Vec2(1f, -9.5f));
        new SpikeEnemy(this, new Vec2(3f, -9.5f));

        Shape subPlatformShape2 = new BoxShape(1.5f, Level.DEFAULT_SUBTILE_SCALE); // hh = 0.80f

        Platform platform4 = new Platform(this, subPlatformShape2);
        platform4.setPosition(new Vec2(-12.5f, -12.2f));
        platform4.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        Platform platform5 = new Platform(this, subPlatformShape2);
        platform5.setPosition(new Vec2(-9.5f, -12.2f));
        platform5.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        Platform platform6 = new Platform(this, subPlatformShape2);
        platform6.setPosition(new Vec2(-6.5f, -12.2f));
        platform6.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        Platform platform7 = new Platform(this, subPlatformShape2);
        platform7.setPosition(new Vec2(-3.5f, -12.2f));
        platform7.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        Platform platform8 = new Platform(this, subPlatformShape2);
        platform8.setPosition(new Vec2(-0.5f, -12.2f));
        platform8.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        Platform platform9 = new Platform(this, subPlatformShape2);
        platform9.setPosition(new Vec2(2.5f, -12.2f));
        platform9.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE);

        // Suspended platform 2
        Platform platform10 = new Platform(this, platformShape1);
        platform10.setPosition(new Vec2(7.02f, -5f));
        platform10.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        Platform platform11 = new Platform(this, subPlatformShape);
        platform11.setPosition(new Vec2(5.55f, -11f));
        platform11.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        Platform platform12 = new Platform(this, subPlatformShape);
        platform12.setPosition(new Vec2(8.6f, -11f));
        platform12.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        // Suspended platform 3
        Platform platform13 = new Platform(this, platformShape1);
        platform13.setPosition(new Vec2(13f, -3.5f));
        platform13.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        Platform platform14 = new Platform(this, subPlatformShape);
        platform14.setPosition(new Vec2(11.55f, -8f));
        platform14.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        Platform platform15 = new Platform(this, subPlatformShape);
        platform15.setPosition(new Vec2(14.5f, -8f));
        platform15.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        // Suspended platform 4
        Platform platform16 = new Platform(this, platformShape1);
        platform16.setPosition(new Vec2(11f, 6.5f));
        platform16.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Suspended platform 5
        Platform platform17 = new Platform(this, platformShape1);
        platform17.setPosition(new Vec2(19.5f, -3.5f));
        platform17.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        Platform platform18 = new Platform(this, subPlatformShape);
        platform18.setPosition(new Vec2(17.25f, -8.5f));
        platform18.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        Platform platform19 = new Platform(this, subPlatformShape);
        platform19.setPosition(new Vec2(19f, -8.5f));
        platform19.fillPlatformVertical(SUB_TILE, 1.7f, 0f, 1.5f, Level.DEFAULT_SUBTILE_SCALE * 3);

        // Suspended platform 5
        Platform platform20 = new Platform(this, platformShape1);
        platform20.setPosition(new Vec2(13.5f, -3.5f));
        platform20.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE, 1.5f);

        // Make a ground platform
        Shape groundShape = new BoxShape(DEFAULT_TILE_SCALE * 6, DEFAULT_TILE_SCALE / 2);
        Platform ground = new Platform(this, groundShape);
        ground.setPosition(new Vec2(0f, -12f));
        ground.fillPlatform(TILE, Level.DEFAULT_TILE_SCALE, 0f, Level.DEFAULT_TILE_SCALE * 7, DEFAULT_TILE_SCALE / 2);

    }

    @Override
    public void populate() {
        this.getPlayer().setPosition(new Vec2(-17f, -1));

        new CoinPickup(this, new Vec2(-11f, 3.25f));
        new CoinPickup(this, new Vec2(-9f, 1.25f));
        new CoinPickup(this, new Vec2(-7f, 3.25f));
        new CoinPickup(this, new Vec2(-5f, 1.25f));
        new CoinPickup(this, new Vec2(-3f, 3.25f));
        new CoinPickup(this, new Vec2(-1f, 1.25f));
        new CoinPickup(this, new Vec2(1f, 3.25f));

        new ShootingEnemy(this, new Vec2(10.5f, 10f));
        new ShootingEnemy(this, new Vec2(7f, -3.5f));

        new Portal(this, new Vec2(18f, 0.7f));
    }

    @Override
    public String getLevelName() {
        return "Level3";
    }
}
