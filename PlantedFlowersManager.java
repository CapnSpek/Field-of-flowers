import java.util.Random;
public class PlantedFlowersManager
{
    GameScreen gs;
    int probSetup[];
    int sumNumbers;
    Random rand;

    public PlantedFlowersManager(GameScreen gs)
    {
        this.gs = gs;
        probSetup = new int[15];
        sumNumbers = 0;
        rand = new Random();
        rand.setSeed(123456789);
        probabilitySetup();
    }

    void probabilitySetup()
    {
        double temp;
        for(int i=0;i<15;i++)
        {
            temp = 15*Math.exp((-1)*0.1*i);
            sumNumbers = sumNumbers + (int)temp;
            probSetup[i] = sumNumbers;
        }
    }

    public boolean plantFlower(int row, int col, int index)
    {
        int indr = row-4;
        int indc = col-3;
        if(indr<0 || indr>9)
        return false;
        if(indc<0 || indc>9)
        return false;

        if(gs.plantedObj[indr][indc] != null)
        {
            return false;
        }

        switch(index)
        {
            case 0:
            gs.plantedObj[indr][indc] = new RosePlanted();
            break;
            case 1:
            gs.plantedObj[indr][indc] = new LotusPlanted();
            break;
            case 2:
            gs.plantedObj[indr][indc] = new LilyPlanted();
            break;
            case 3:
            gs.plantedObj[indr][indc] = new SunflowerPlanted();
            break;
        }
        gs.plantedObj[indr][indc].x = col*gs.tileSize;
        gs.plantedObj[indr][indc].y = row*gs.tileSize;
        gs.plantedObj[indr][indc].plantTime = System.currentTimeMillis();
        return true;
    }

    public void pollinate(int iindex, int jindex, String name)
    {
        int randr, randc;
        int indr=0, indc=0;
        randr = rand.nextInt(sumNumbers);
        randc = rand.nextInt(sumNumbers);
        
        for(int i=0;i<15;i++)
        {
            if(randr<probSetup[i])
            {
                indr = i;
                break;
            }
        }
        for(int i=0;i<15;i++)
        {
            if(randc<probSetup[i])
            {
                indc = i;
                break;
            }
        }
        if(randr%2!=0)
        {
            indr = -1*indr;
        }
        if(randc%2!=0)
        {
            indc = -1*indc;
        }
        iindex = iindex+indr;
        jindex = jindex+indc;
        if(iindex<0 || iindex>15)
        return;
        if(jindex<0 || jindex>15)
        return;

        int type=0;
        switch(name)
        {
            case "RosePlanted":
            type = 0;
            break;
            case "LotusPlanted":
            type = 1;
            break;
            case "LilyPlanted":
            type = 2;
            break;
            case "SunflowerPlanted":
            type = 3;
            break;
        }

        plantFlower(iindex+4, jindex+3, type);
    }

    public void killFlower(int i, int j)
    {
        gs.plantedObj[i][j] = null;
    }

    public void update()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(gs.plantedObj[i][j]!=null)
                {
                    gs.plantedObj[i][j].timeLapsed = System.currentTimeMillis() - gs.plantedObj[i][j].plantTime;
                    if(gs.plantedObj[i][j].timeLapsed > 10000)
                    {
                        killFlower(i, j);
                    }
                    else if(gs.plantedObj[i][j].timeLapsed > 8000)
                    {
                        gs.plantedObj[i][j].dyingFlower();
                    }
                    else if(gs.plantedObj[i][j].timeLapsed > 4000)
                    {
                        if(gs.plantedObj[i][j].moveCount == 20)
                        {
                            gs.plantedObj[i][j].moveFlower();
                            pollinate(i, j, gs.plantedObj[i][j].name);
                            gs.plantedObj[i][j].moveCount = 0;
                        }
                        gs.plantedObj[i][j].moveCount++;
                    }
                    else
                    {
                        if(gs.plantedObj[i][j].timeLapsed/1000 == gs.plantedObj[i][j].nextPhase)
                        {
                            gs.plantedObj[i][j].growFlower();
                            gs.plantedObj[i][j].nextPhase++;
                        }
                    }
                }
            }
        }
    }
}