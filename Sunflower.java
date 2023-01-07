import java.io.IOException;
import javax.imageio.ImageIO;

public class Sunflower extends GameObject
{
    public Sunflower()
    {
        selectedImageIndex = 0;
        name = "Sunflower";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Flowers/Sunflower.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        collision = true;

        solidArea.x = 5;
        solidArea.y = 5;
    }
}