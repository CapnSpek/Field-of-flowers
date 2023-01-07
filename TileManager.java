import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class TileManager
{
    GameScreen gs;
    Tile[] tile;
    public String mapData[];
    public int mapTileNum[][];

    public TileManager(GameScreen gs)
    {
        this.gs = gs;
        tile = new Tile[5];
        mapData = new String[gs.maxScreenRow];
        mapTileNum = new int[gs.maxScreenCol][gs.maxScreenRow];
        readMapData();
        getTileImage();
    }

    private void readMapData()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream("/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for(int i=0;i<gs.maxScreenRow;i++)
            {
                mapData[i] = br.readLine();
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getTileImage()
    {
        try
        {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")); 
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[1].collision = true; 

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/soil.png")); 
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")); 
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        int x, y;
        for(int row=0; row<gs.maxScreenRow; row++)
        {
            for(int col=0;col<gs.maxScreenCol;col++)
            {
                x = col*gs.tileSize;
                y = row*gs.tileSize;
                int index = mapData[row].charAt(col)-'0';
                g2.drawImage(tile[index].image, x, y, gs.tileSize, gs.tileSize, null);
            }
        }
    }
}