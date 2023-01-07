public class FieldArrange
{
    GameScreen gs;

    public FieldArrange(GameScreen gs)
    {
        this.gs = gs;
    }

    public void setObject()
    {
        gs.obj[0] = new Sunflower();
        gs.obj[0].x = 3*gs.tileSize;
        gs.obj[0].y = 15*gs.tileSize;

        gs.obj[1] = new Rose();
        gs.obj[1].x = 12*gs.tileSize;
        gs.obj[1].y = 15*gs.tileSize;

        gs.obj[2] = new Lily();
        gs.obj[2].x = 5*gs.tileSize;
        gs.obj[2].y = 15*gs.tileSize;

        gs.obj[3] = new Lotus();
        gs.obj[3].x = 10*gs.tileSize;
        gs.obj[3].y = 15*gs.tileSize;
    }
}
