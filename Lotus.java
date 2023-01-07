import java.io.IOException;
import javax.imageio.ImageIO;

public class Lotus extends GameObject
{
    public Lotus()
    {
        selectedImageIndex = 0;
        name = "Lotus";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Flowers/Lotus.png"));
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