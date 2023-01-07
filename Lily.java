import java.io.IOException;
import javax.imageio.ImageIO;

public class Lily extends GameObject
{
    public Lily()
    {
        selectedImageIndex = 0;
        name = "Lily";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Flowers/Lily.png"));
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