package game.entity.platform;

import city.cs.engine.*;
import game.entity.Player;
import game.levels.Level;
import org.jbox2d.common.Vec2;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class Platform extends StaticBody {
    protected AttachedImage attachedImg;
    protected final Sensor sensor;

    public Platform(Level world, Shape shape) {
        super(world, shape);

//         setAlwaysOutline(true);

        sensor = new Sensor(this, shape);
        sensor.addSensorListener(new SensorListener() {
            final Player p = world.getPlayer();

            @Override
            public void beginContact(SensorEvent e) {
                p.setDoubleJumpsLeft(Player.MAX_DOUBLE_JUMPS);

                if (e.getContactBody() instanceof Player && (p.getCurrentState() == Player.State.FALLING || p.getCurrentState() == Player.State.JUMPING)) {
                    p.setCurrentState(Player.State.IDLE);
                }
            }

            @Override
            public void endContact(SensorEvent e) {
                if (e.getContactBody() instanceof Player) {
                    p.setCurrentState(Player.State.JUMPING);
                }
            }
        });
    }

    public void changeImage(BodyImage newImage) {
        removeAttachedImage(this.attachedImg);
        removeAllImages();
        this.attachedImg = this.addImage(newImage);
    }

    public void fillPlatform(BodyImage image, float scale, float rotation, float halfWidth, float halfHeight) {
        int numImages = (int) ((halfWidth * 2) / scale);
        float start = -(halfWidth) + (scale / 2);

        for (int i = 0; i < numImages; i++) {
            new AttachedImage(this, image, scale, rotation, new Vec2(start, 0));
            start += scale;
        }

        // System.out.println("start: " + start);
        // System.out.println("scale: " + scale);
        // System.out.println("images: " + numImages);
    }

    public void fillPlatformVertical(BodyImage image, float scale, float rotation, float halfWidth, float halfHeight) {
        int numImages = (int) ((halfHeight * 2) / scale);
        System.out.println(numImages);
        float start = halfHeight - (scale / 2);

        for (int i = 0; i < numImages; i++) {
            new AttachedImage(this, image, scale, rotation, new Vec2(0, start));
            start -= scale;
        }

        // System.out.println("start: " + start);
        // System.out.println("scale: " + scale);
        // System.out.println("images: " + numImages);
    }

    public AttachedImage getAttachedImg() {
        return attachedImg;
    }

    public void setAttachedImg(AttachedImage attachedImg) {
        this.attachedImg = attachedImg;
    }
}
