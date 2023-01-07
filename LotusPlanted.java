import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LotusPlanted extends PlantedObject
{
    public LotusPlanted()
    {
        //selectedImageIndex = 0;
        image = new BufferedImage[6];
        name = "LotusPlanted";
        try
        {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Sown.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Budding.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lotus1.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lotus2.png"));
            image[4] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lotus3.png"));
            image[5] = ImageIO.read(getClass().getResourceAsStream("/FlowersPlanted/Lotus4.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}