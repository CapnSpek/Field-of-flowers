import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LilyPlanted extends PlantedObject
{
    public LilyPlanted()
    {
        //selectedImageIndex = 0;
        image = new BufferedImage[6];
        name = "LilyPlanted";
        try
        {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sown.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Budding.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lily1.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lily2.png"));
            image[4] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lily3.png"));
            image[5] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lily4.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}