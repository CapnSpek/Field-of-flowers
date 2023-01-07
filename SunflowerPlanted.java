import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SunflowerPlanted extends PlantedObject
{
    public SunflowerPlanted()
    {
        //selectedImageIndex = 0;
        image = new BufferedImage[6];
        name = "SunflowerPlanted";
        try
        {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sown.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Budding.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sunflower1.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sunflower2.png"));
            image[4] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sunflower3.png"));
            image[5] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sunflower4.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}