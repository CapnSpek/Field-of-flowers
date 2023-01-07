public class CollisionDetector
 {

    GameScreen gs;

    CollisionDetector(GameScreen gs)
    {
        this.gs = gs;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gs.tileSize;
        int entityRightCol = entityRightX/gs.tileSize;
        if(entityRightCol>gs.maxScreenCol-1)
            entityRightCol = entityRightCol-1;

        int entityTopRow = entityTopY/gs.tileSize;
        int entityBottomRow = entityBottomY/gs.tileSize;
        if(entityBottomRow>gs.maxScreenRow-1)
            entityBottomRow = gs.maxScreenRow-1;

        int tileNum1=0, tileNum2=0;

        switch(entity.direction)
        {
            case "up":
            entityTopRow = (entityTopY - entity.speed) / (gs.tileSize);
            tileNum1 = gs.tileM.mapData[entityTopRow].charAt(entityLeftCol)-'0';
            tileNum2 = gs.tileM.mapData[entityTopRow].charAt(entityRightCol)-'0';
            break;
            case "down":
            entityBottomRow = (entityBottomY + entity.speed) / (gs.tileSize);
            if(entityBottomRow>gs.maxScreenRow-1)
            entityBottomRow = gs.maxScreenRow-1;
            tileNum1 = gs.tileM.mapData[entityBottomRow].charAt(entityLeftCol)-'0';
            tileNum2 = gs.tileM.mapData[entityBottomRow].charAt(entityRightCol)-'0';
            break;
            case "left":
            entityLeftCol = (entityLeftX - entity.speed) / (gs.tileSize);
            tileNum1 = gs.tileM.mapData[entityTopRow].charAt(entityLeftCol)-'0';
            tileNum2 = gs.tileM.mapData[entityBottomRow].charAt(entityLeftCol)-'0';
            break;
            case "right":
            entityRightCol = (entityRightX + entity.speed) / (gs.tileSize);
            if(entityRightCol>gs.maxScreenCol-1)
            entityRightCol = entityRightCol-1;
            tileNum1 = gs.tileM.mapData[entityTopRow].charAt(entityRightCol)-'0';
            tileNum2 = gs.tileM.mapData[entityBottomRow].charAt(entityRightCol)-'0';
            break;
        }
        if(gs.tileM.tile[tileNum1].collision == true || gs.tileM.tile[tileNum2].collision == true)
        {
            entity.collisionOn = true;
        }
    }
    public int checkObject(Entity entity)
    {
        int index = -1;

        for(int i=0;i<gs.obj.length;i++)
        {
         if(gs.obj[i] != null)
          {
            entity.solidArea.x = entity.solidArea.x + entity.x;;
            entity.solidArea.y = entity.solidArea.y + entity.y;
            gs.obj[i].solidArea.x = gs.obj[i].solidArea.x + gs.obj[i].x;
            gs.obj[i].solidArea.y = gs.obj[i].solidArea.y + gs.obj[i].y;

             switch(entity.direction)
                {
                  case "up":
                   entity.solidArea.y = entity.solidArea.y - entity.speed;
                   if(entity.solidArea.intersects(gs.obj[i].solidArea))
                    {
                        if(gs.obj[i].collision == true)
                        {
                            entity.collisionOn = true;
                        }
                        index = i;
                    }
                    break;
                    case "down":
                    entity.solidArea.y = entity.solidArea.y + entity.speed;
                    if(entity.solidArea.intersects(gs.obj[i].solidArea))
                    {
                        if(gs.obj[i].collision == true)
                        {
                            entity.collisionOn = true;
                        }
                        index = i;
                    }
                    break;
                    case "left":
                    entity.solidArea.x = entity.solidArea.x - entity.speed;
                    if(entity.solidArea.intersects(gs.obj[i].solidArea))
                    {
                        if(gs.obj[i].collision == true)
                        {
                            entity.collisionOn = true;
                        }
                        index = i;
                    }
                    break;
                    case "right":
                    entity.solidArea.x = entity.solidArea.x + entity.speed;
                    if(entity.solidArea.intersects(gs.obj[i].solidArea))
                    {
                        if(gs.obj[i].collision == true)
                        {
                            entity.collisionOn = true;
                        }
                        index = i;
                    }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefX;
                entity.solidArea.y = entity.solidAreaDefY;

                gs.obj[i].solidArea.x = gs.obj[i].solidAreaDefX;
                gs.obj[i].solidArea.y = gs.obj[i].solidAreaDefY;
          }
       }
       return index;
    }
}
