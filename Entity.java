import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.Rectangle;

public class Entity implements Serializable
{
    public int x, y;
    public int speed, default_speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefX, solidAreaDefY;
    public boolean collisionOn = false;
}
