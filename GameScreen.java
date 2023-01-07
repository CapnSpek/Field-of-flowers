import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JFrame;

public class GameScreen extends JPanel implements Runnable
{
    //ORIGINAL
    final int originalTileSize = 16;

    //SCALE IT UP
    final int scale = 3;

    JFrame window;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    InputTaker handle = new InputTaker();
    Sound sound = new Sound();
    public CollisionDetector detector = new CollisionDetector(this);
    public FieldArrange arrange = new FieldArrange(this);
    public PlantedFlowersManager flowersManager = new PlantedFlowersManager(this);
    Thread gameThread;

    //GAMESTATE
    int gameState = 0;

    //MENU & PAUSE
    Menu menu = new Menu(this, handle);
    Pause pause = new Pause(this, handle);

    //MAP SETTING
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 30;

    //PLAYER
    Player player = new Player(this, handle);

    //FLOWER OBJECTS
    public GameObject obj[] = new GameObject[4];

    //PLANTED FLOWERS
    public PlantedObject plantedObj[][] = new PlantedObject[10][10];

    public GameScreen()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(handle);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        arrange.setObject();

        playMusic(1);
        playMusic(2);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;

        while(gameThread !=null)
        {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update()
    {
        if(gameState==1)
        {
            player.update();
            flowersManager.update();
        }
        else if (gameState==0)
        {
            menu.update();
        }
        else if(gameState==2)
        {
            pause.update();
        }
    }

    public void pauseGame()
    {
        gameState = 2;
    }

    public void resumeGame()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(plantedObj[i][j]!=null)
                {
                    plantedObj[i][j].plantTime = System.currentTimeMillis() - plantedObj[i][j].timeLapsed;
                }
            }
        }
        gameState = 1;
    }

    public void plantFlower(int middle_row, int middle_col, int index)
    {
        if(flowersManager.plantFlower(middle_row, middle_col, index) == false)
        {
            playSE(0);
        }
    }

    public void backToTitleScreen()
    {
        playSE(0);
        gameState = 0;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == 0)
        {
            menu.drawTitleScreen(g2);
        }
        else if(gameState==2)
        {
            pause.drawPauseScreen(g2);
        }
        else
        {
            tileM.draw(g2);

            for(int i=0;i<obj.length;i++)
            {
                if(obj[i]!=null)
                {
                   obj[i].draw(g2, this);
                }
            }

            for(int i=0;i<10;i++)
            {
                for(int j=0;j<10;j++)
                {
                    if(plantedObj[i][j]!=null)
                    {
                        plantedObj[i][j].draw(g2, this);
                  }
                }
            }

          player.draw(g2);
        }
        
        g2.dispose();
    }

    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic()
    {
        sound.stop();
    }

    public void playSE(int i)
    {
        sound.setFile(i);
        sound.play();
    }
}