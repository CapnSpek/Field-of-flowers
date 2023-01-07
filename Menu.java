import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.*;

public class Menu
{
    Font maruMonica;
    BufferedImage menuBackground;
    public int menuItem = 0;
    GameScreen gs;
    InputTaker handle;
    long lastTime;
    long keyLimiter=300;
    int loadFile = 0;

    public Menu(GameScreen gs, InputTaker handle)
    {
        String file_path = "SaveData/saveGame.ser";
        Path path = Paths.get(file_path);
        if (Files.exists(path))
        {
            loadFile = 1;
        }

        lastTime = System.currentTimeMillis();
        this.gs = gs;
        this.handle = handle;
        try
        {
            InputStream is = getClass().getResourceAsStream("/Fonts/MP16SC.ttf");
            menuBackground = ImageIO.read(getClass().getResourceAsStream("/Background/BackgroundFinal.png"));
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
        
        if(handle.up==true)
        {
            if(menuItem>0 && System.currentTimeMillis()-lastTime>=keyLimiter)
            {
                menuItem--;
                if(loadFile==0)
                {
                    menuItem--;
                }    
                lastTime = System.currentTimeMillis();
            }
            else if(menuItem==0 && System.currentTimeMillis()-lastTime>=keyLimiter)
            {
                menuItem = 2;        
                lastTime = System.currentTimeMillis();
            }
        }
        else if(handle.down==true)
        {
            if(menuItem<2 && System.currentTimeMillis()-lastTime>=keyLimiter)
            {
                menuItem++;
                if(loadFile==0)
                {
                    menuItem++;
                }
                lastTime = System.currentTimeMillis();
            }
            else if(menuItem==2 && System.currentTimeMillis()-lastTime>=keyLimiter)
            {
                menuItem = 0;
                lastTime = System.currentTimeMillis();
            }
        }
        if(handle.x==true)
        {
            menuOptions(menuItem);
        }
    }

    public void menuOptions(int menuItem)
    {
        switch(menuItem)
        {
            case 0:
            gs.playSE(0);
            reset();
            gs.gameState = 1;
            break;
            case 1:
            gs.playSE(0);
            loadGame();
            break;
            case 2:
            gs.playSE(0);
            saveGame();
            System.exit(1);
            break;
        }
    }


    public void loadGame()
    {
        SaveData save;
        try {
            FileInputStream fileIn = new FileInputStream("SaveData/saveGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            save = (SaveData) in.readObject();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         } catch (ClassNotFoundException c) {
            System.out.println("SaveData class not found");
            c.printStackTrace();
            return;
         }

         reset();
         save.load(gs);
    }

    public void saveGame()
    {
        SaveData save = new SaveData(gs);
        try {
            FileOutputStream fileOut = new FileOutputStream("SaveData/saveGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(save);
            out.close();
            fileOut.close();
         } catch (IOException i) {
            i.printStackTrace();
         }
    }

    public void reset()
    {
        gs.arrange.setObject();
        gs.plantedObj = new PlantedObject[10][10];
        gs.player = new Player(gs, handle);
    }


    public int getXforCentredText(String text, Graphics g2)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gs.screenWidth/2 - length/2;
        return x;
    }

    public void drawTitleScreen(Graphics2D g2)
    {
        g2.drawImage(menuBackground, 0, 0, gs.tileSize*gs.maxScreenCol, gs.tileSize*gs.maxScreenRow, null);
        g2.setFont(maruMonica);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        String text = "Field of flowers";
        int x = getXforCentredText(text, g2);
        int y = gs.tileSize*5;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        int tile = 7;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        text = "New game";
        x = getXforCentredText(text, g2);
        y = gs.tileSize*tile;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(menuItem==0)
        {
            g2.setColor(Color.gray);
            g2.drawString(">", x-2-gs.tileSize, y+2);
            g2.setColor(Color.white);
            g2.drawString(">", x-gs.tileSize, y);
        }
        tile++;

        if(loadFile==1)
        {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        text = "Load game";
        x = getXforCentredText(text, g2);
        y = gs.tileSize*tile;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(menuItem==1)
        {
            g2.setColor(Color.gray);
            g2.drawString(">", x-2-gs.tileSize, y+2);
            g2.setColor(Color.white);
            g2.drawString(">", x-gs.tileSize, y);
        }
        tile++;
    }
        

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        text = "Quit game";
        x = getXforCentredText(text, g2);
        y = gs.tileSize*tile;
        g2.setColor(Color.gray);
        g2.drawString(text, x-2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(menuItem==2)
        {
            g2.setColor(Color.gray);
            g2.drawString(">", x-2-gs.tileSize, y+2);
            g2.setColor(Color.white);
            g2.drawString(">", x-gs.tileSize, y);
        }
    }
}