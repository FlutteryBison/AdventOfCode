class Coordinate {
  int x, y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
  public Coordinate(Coordinate pos)
  {
    this.x = pos.x;
    this.y = pos.y;
  }
  public void add(int x, int y)
  {
    this.x +=x;
    this.y +=y;
  }
  public void set(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  public void set(Coordinate pos)
  {
    this.x = pos.x;
    this.y = pos.y;
  }

  /**
   * Retruns the absalute distance between this and pos
   * @param pos
   * @return
   */
  public Coordinate absDist(Coordinate pos)
  {
    Coordinate dist = new Coordinate(this);
    dist.x -=pos.x;
    dist.y -= pos.y;
    dist.x = Math.abs(dist.x);
    dist.y = Math.abs(dist.y);
    return dist;
  }
}