import java.io.IOException;
import javax.imageio.ImageIO;

public class Rose extends GameObject
{
    public Rose()
    {
        selectedImageIndex = 0;
        name = "Rose";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Flowers/Rose.png"));
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