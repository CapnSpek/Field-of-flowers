import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;

public class Player extends Entity
{
    GameScreen gs;
    InputTaker handle;
    boolean rose, sunflower, lily, lotus;
    long lastTime;
    int keyLimiter=200;

    public Player(GameScreen gs, InputTaker handle)
    {
        lastTime = System.currentTimeMillis();
        rose = sunflower = lily = lotus = false;
        this.gs = gs;
        this.handle = handle;
        solidArea = new Rectangle(12, 15, gs.tileSize*1/2, gs.tileSize*1/2);
        solidAreaDefX = solidArea.x;
        solidAreaDefY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        x = 8*gs.tileSize;
        y = 15*gs.tileSize;   
        speed = 3;
        default_speed = speed;
        direction = "up";
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/WalkingPlayer/Farmer_right_2.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void update()
    {
        boolean check = false;
        if(handle.esc == true)
        {
            gs.backToTitleScreen();
        }
        if(handle.z == true)
        {
            if(System.currentTimeMillis()-lastTime>=keyLimiter)
            if(rose==true)
            {
                placeFlower(0);
                lastTime = System.currentTimeMillis();
            }
        }
        if(handle.x == true)
        {
            if(System.currentTimeMillis()-lastTime>=keyLimiter)
            if(lotus==true)
            {
                placeFlower(1);
                lastTime = System.currentTimeMillis();
            }
        }
        if(handle.c == true)
        {
            if(System.currentTimeMillis()-lastTime>=keyLimiter)
            if(lily==true)
            {
                placeFlower(2);
                lastTime = System.currentTimeMillis();
            }
        }
        if(handle.v == true)
        {
            if(System.currentTimeMillis()-lastTime>=keyLimiter)
            if(sunflower==true)
            {
                placeFlower(3);
                lastTime = System.currentTimeMillis();
            }
        }
        if(handle.shift == true)
        {
            speed+=2;
        }
        if(handle.p == true)
        {
            gs.pauseGame();
        }
        if(handle.up == true)
        {
            direction = "up";         
            spriteCounter++;
            check = true;
        }
        else if(handle.down == true)
        {
            direction = "down";         
            spriteCounter++;
            check = true;
        }
        else if(handle.left == true)
        {
            direction = "left";          
            spriteCounter++;
            check = true;
        }
        else if(handle.right == true)
        {
            direction = "right";           
            spriteCounter++;
            check = true;
        }

        collisionOn = false;
        gs.detector.checkTile(this);

       int objectIndex = gs.detector.checkObject(this);
       pickUpObject(objectIndex);

        if(check == true)
        if(collisionOn == false)
        {
            switch(direction)
            {
                case "up":
                if(y>0)
                y -= speed;
                break;
                case "down":
                if(y<(gs.maxScreenCol-1)*gs.tileSize)
                y += speed;
                break;
                case "left":
                if(x>0)
                x -= speed;
                break;
                case "right":
                if(x<(gs.maxScreenRow-1)*gs.tileSize)
                x += speed;
                break;
            }
        }
        if(spriteCounter>4)
        {
            if(spriteNum == 1)
            {
                spriteNum = 2;
            }
            else if(spriteNum == 2)
            {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        speed = default_speed;
    }

    public void pickUpObject(int index)
    {
        if(index==-1)
        return;

        String objectName = gs.obj[index].name;
        gs.playSE(0);
        switch(objectName)
        {
            case "Rose":
            rose = true;
            break;
            case "Lotus":
            lotus = true;
            break;
            case "Lily":
            lily = true;
            break;
            case "Sunflower":
            sunflower = true;
            break;
        }
        gs.obj[index] = null;
    }

    public void placeFlower(int index)
    {
        int middle_x = x+(gs.tileSize/2);
        int middle_y = y+(gs.tileSize/2);

        int middle_col = middle_x/gs.tileSize;
        int middle_row = middle_y/gs.tileSize;

        if(middle_col < 3 || middle_col > 12)
        return;
        if(middle_row < 4 || middle_row >13)
        return;

        gs.plantFlower(middle_row, middle_col, index);
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
            if(spriteNum == 1)
            {
                image = up1;
            }
            else if(spriteNum == 2)
            {
                image = up2;
            }
            break;
            case "down":
            if(spriteNum == 1)
            {
                image = down1;
            }
            else if(spriteNum == 2)
            {
                image = down2;
            }
            break;
            case "left":
            if(spriteNum == 1)
            {
                image = left1;
            }
            else if(spriteNum == 2)
            {
                image = left2;
            }
            break;
            case "right":
            if(spriteNum == 1)
            {
                image = right1;
            }
            else if(spriteNum == 2)
            {
                image = right2;
            }
            break;
        }
        g2.drawImage(image, x, y, gs.tileSize, gs.tileSize, null);
    }
}