public class Sensor {
    private final int x;
    private final int y;

    private final int beaconX;
    private final int beaconY;

    private final int manhattanDistance;

    public Sensor(int x, int y, int beaconX, int beaconY)
    {
        this.x = x;
        this.y = y;
        this.beaconX = beaconX;
        this.beaconY = beaconY;
        this.manhattanDistance = manhattanDistance(x,y,beaconX,beaconY);
    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2)
    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int getX()
    {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBeaconX() {
        return beaconX;
    }
    
    public int getBeaconY() {
        return beaconY;
    }

    public int getManhattanDistance() {
        return manhattanDistance;
    }


}
