import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameObject implements Serializable
{
    public BufferedImage image;
    public int selectedImageIndex = 0;
    public String name;
    public boolean collision = true;
    public int x, y;
    public Rectangle solidArea = new Rectangle(6, 16, 10, 10);
    public int solidAreaDefX = solidArea.x;
    public int solidAreaDefY = solidArea.y;

    public void draw(Graphics2D g2, GameScreen gs)
    {
        g2.drawImage(image, x, y, gs.tileSize, gs.tileSize, null);
    }
}