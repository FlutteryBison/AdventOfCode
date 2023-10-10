public class world {

  private int worldWidth = 2000;
  private int worldHeight = 2000;
  private boolean[][] visited = new boolean[worldWidth][worldHeight];
  private Knot headKnot = new Knot();


  public world()
  {
    headKnot.pos = new Coordinate(worldWidth/2,worldHeight/2);
    Knot curKnot = headKnot;
    for(int i = 0; i < 9;i++)
    {
      curKnot.next = new Knot();
      curKnot = curKnot.next;
      curKnot.pos = new Coordinate(worldWidth/2,worldHeight/2);
    }
    
    visited[headKnot.pos.x][headKnot.pos.y] = true;
  }
  public void moveLeft(int steps)
  {
    moveAll(-1, 0,steps);
  }
  public void moveRight(int steps)
  {
    moveAll(1,0,steps);
  }
  public void moveUp(int steps)
  {
    moveAll(0, -1, steps);
  }
  public void moveDown(int steps)
  {
    moveAll(0, 1, steps);
  }

  private void moveAll(int xdir,int ydir, int steps)
  {
    for(int i = 0; i < steps; i++)
    {
      moveStep(xdir, ydir, headKnot);
    }
  }
  private void moveStep(int xdir, int ydir,Knot knot)
  {
    knot.pos.add(xdir, ydir);
    Knot tail = knot.next;
    if(tail!=null){
      xdir = 0;
      ydir =0;
      if(tail.pos.absDist(knot.pos).x >1 || tail.pos.absDist(knot.pos).y >1)
      {
        int xdiff = knot.pos.x - tail.pos.x;
        xdir = roundAway0(xdiff/2f);
        int ydiff = knot.pos.y - tail.pos.y;
        ydir = roundAway0( ydiff/2f);
      
      }
      moveStep(xdir, ydir, tail);
    }
    else{
      visited[knot.pos.x][knot.pos.y] = true;
    }
  }

  public int visitedCount(){
    int acc = 0;
    for (int x = 0; x<worldWidth; x++){
      for(int y = 0; y<worldHeight;y++){
        if(visited[x][y])
        {
          acc++;
        }
      }
    }
    return acc;
  }

  public int roundAway0(double val) {
    if (val < 0) {
        return (int)Math.floor(val);
    }
    return (int)Math.ceil(val);
  }
}
