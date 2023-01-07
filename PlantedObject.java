import java.io.Serializable;
import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

public class PlantedObject implements Serializable
{
    public BufferedImage image[];
    public int selectedImageIndex = 0;
    public String name;
    public int x, y;
    public long plantTime;
    public int moveCount=0;
    public int nextPhase=1;
    long timeLapsed=0;

    public void growFlower()
    {
        if(selectedImageIndex<3)
        {
            selectedImageIndex++;
        }
    }

    public void moveFlower()
    {
        if(selectedImageIndex == 3)
        {
            selectedImageIndex = 4;
        }
        else
        {
            selectedImageIndex = 3;
        }
    }

    public void dyingFlower()
    {
        selectedImageIndex = 5;
    }
    
    public void draw(Graphics2D g2, GameScreen gs)
    {
        g2.drawImage(image[selectedImageIndex], x, y, gs.tileSize, gs.tileSize, null);
    }
}
