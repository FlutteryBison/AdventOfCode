package loc;

import java.util.Objects;

public class Point {
    public int x,y;

    

    public Point(Point p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p)
    {
        return Math.sqrt(Math.pow(x-p.x, 2) + Math.pow(y-p.y,2));
    }
    public double distance(int x, int y)
    {
        return Math.sqrt(Math.pow(x-this.x, 2) + Math.pow(y-this.y,2));
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    
}

