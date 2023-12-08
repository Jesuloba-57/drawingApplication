import java.awt.*;
import java.util.ArrayList;

public class Triangle extends Item {
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public boolean includes(Point point) {
        return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0) || (distance(point, point3)< 10.0));
    }

    public void setPoint1(Point point) {
        this.point1 = point;
    }
    public void setPoint2(Point point) {
        this.point2 = point;
    }
    public void setPoint3(Point point) {
        this.point3 = point;
    }

    public Point getPoint1() {return point1;}

    public Point getPoint2() {return point1;}

    public Point getPoint3() {return point1;}

    public void render(UIContext uiContext) {
        uiContext.drawLine(point1, point2);
        uiContext.drawLine(point2, point3);
        uiContext.drawLine(point1, point3);
    }

    public String toString() {
        return "Triangle Composed of " + point1 + ", " + point2 + "and " + point2;
    }
}
