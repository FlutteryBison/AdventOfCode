
import java.util.Objects;

public class Point
{
    int x;
    int y;

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }
    
        
    public Point()
    {}
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Point(Point p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public boolean equals(Object o) {
        
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