import java.io.Serializable;
import java.nio.file.*;

public class SaveData implements Serializable
{
    int playerX, playerY;
    boolean playerRose, playerLily, playerLotus, playerSunflower;
    int playerDirection;
    int objs[];
    int plantedObjX[][];
    int plantedObjY[][];
    long plantedObjElapsedTime[][];
    int plantedObjSelectedImage[][];
    int plantedObjType[][];
    int plantedObjNextPhase[][];

    SaveData(GameScreen gs)
    {
        //load player
        playerX = gs.player.x;
        playerY = gs.player.y;
        playerRose = gs.player.rose;
        playerLily = gs.player.lily;
        playerLotus = gs.player.lotus;
        playerSunflower = gs.player.sunflower;
        switch(gs.player.direction)
        {
            case "up":
            playerDirection = 0;
            break;
            case "down":
            playerDirection = 1;
            break;
            case "left":
            playerDirection = 2;
            break;
            case "right":
            playerDirection = 3;
            break;
        }

        //load objects
        objs = new int[gs.obj.length];
        for(int i=0;i<gs.obj.length;i++)
        {
            if(gs.obj[i]!=null)
            {
                objs[i]=1;
            }
            else
            {
                objs[i]=-1;
            }
        }

        //load planted objects
        plantedObjX = new int[10][10];
        plantedObjY = new int[10][10];
        plantedObjElapsedTime = new long[10][10];
        plantedObjSelectedImage = new int[10][10];
        plantedObjType = new int[10][10];
        plantedObjNextPhase = new int[10][10];

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(gs.plantedObj[i][j]!=null)
                {
                    plantedObjX[i][j] = gs.plantedObj[i][j].x;
                    plantedObjY[i][j] = gs.plantedObj[i][j].y;
                    plantedObjElapsedTime[i][j] = gs.plantedObj[i][j].timeLapsed;
                    plantedObjSelectedImage[i][j] = gs.plantedObj[i][j].selectedImageIndex;
                    switch(gs.plantedObj[i][j].name)
                    {
                        case "RosePlanted":
                        plantedObjType[i][j] = 0;
                        break;
                        case "LotusPlanted":
                        plantedObjType[i][j] = 1;
                        break;
                        case "LilyPlanted":
                        plantedObjType[i][j] = 2;
                        break;
                        case "SunflowerPlanted":
                        plantedObjType[i][j] = 3;
                        break;
                    }
                }
                else
                {
                    plantedObjX[i][j] = -1;
                    plantedObjY[i][j] = -1;
                    plantedObjElapsedTime[i][j] = -1;
                    plantedObjSelectedImage[i][j] = -1;
                    plantedObjType[i][j] = -1;
                }
            }
        }
    }

    public void load(GameScreen gs)
    {
        String file_path = "SaveData/saveGame.ser";
        Path path = Paths.get(file_path);
        if (Files.notExists(path))
        {
            return;
        }

        //load player
        gs.player.x = playerX;
        gs.player.y = playerY;
        gs.player.rose = playerRose;
        gs.player.lotus = playerLotus;
        gs.player.lily = playerLily;
        gs.player.sunflower = playerSunflower;
        switch(playerDirection)
        {
            case 0:
            gs.player.direction = "up";
            break;
            case 1:
            gs.player.direction = "down";
            break;
            case 2:
            gs.player.direction = "left";
            break;
            case 3:
            gs.player.direction = "right";
            break;
        }

        //load objects
        for(int i=0;i<objs.length;i++)
        {
            if(objs[i]==-1)
            {
                gs.obj[i]=null;
            }
        }

        //load planted objects
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(plantedObjType[i][j]!=-1)
                {
                    switch(plantedObjType[i][j])
                    {
                        case 0:
                        gs.plantedObj[i][j] = new RosePlanted();
                        break;
                        case 1:
                        gs.plantedObj[i][j] = new LotusPlanted();
                        break;
                        case 2:
                        gs.plantedObj[i][j] = new LilyPlanted();
                        break;
                        case 3:
                        gs.plantedObj[i][j] = new SunflowerPlanted();
                        break;
                    }
                    gs.plantedObj[i][j].x = plantedObjX[i][j];
                    gs.plantedObj[i][j].y = plantedObjY[i][j];
                    gs.plantedObj[i][j].selectedImageIndex = plantedObjSelectedImage[i][j];
                    gs.plantedObj[i][j].timeLapsed = plantedObjElapsedTime[i][j];
                }
                else
                {
                    gs.plantedObj[i][j] = null;
                }
            }
        }
        gs.resumeGame();
    }
}