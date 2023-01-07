import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class Pause
{
    BufferedImage pauseBackground;
    GameScreen gs;
    InputTaker handle;
    long lastTime;
    Font maruMonica;

    public Pause(GameScreen gs, InputTaker handle)
    {
        this.gs = gs;
        this.handle = handle;
        try
        {
            
            InputStream is = getClass().getResourceAsStream("/Fonts/MP16SC.ttf");
            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/Background/PauseFinal.png"));  
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        
        if(handle.r==true)
        {
            gs.resumeGame();
        }
    }

    public int getXforCentredText(String text, Graphics g2)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gs.screenWidth/2 - length/2;
        return x;
    }

    public void drawPauseScreen(Graphics2D g2)
    {
        g2.drawImage(pauseBackground, 0, 0, gs.tileSize*gs.maxScreenCol, gs.tileSize*gs.maxScreenRow, null);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        String text = "Paused";
        int x = getXforCentredText(text, g2);
        int y = gs.tileSize*5;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        text = "Press 'r' to resume";
        x = getXforCentredText(text, g2);
        y = gs.tileSize*7;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
}