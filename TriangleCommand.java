import java.awt.*;
import java.text.*;
public class TriangleCommand extends Command {
    private Triangle triangle;
    private int pointCount;

    public TriangleCommand() {
        this(null);
        pointCount = 0;
    }

    public TriangleCommand(Point point) {
        this(point, null);
        pointCount = 1;
    }

    public TriangleCommand(Point point1, Point point2) {
        this(point1, point2, null);
        pointCount = 2;
    }

    public TriangleCommand(Point point1, Point point2, Point point3) {
        triangle = new Triangle(point1, point2, point3);
        pointCount = 3;
    }

    public void setTriPoint(Point point) {
        if (++pointCount == 1) {
            triangle.setPoint1(point);
        } else if (pointCount == 2) {
            triangle.setPoint2(point);

        } else if (pointCount == 3) {
            triangle.setPoint3((point));
        }
    }
    public void execute() {
        model.addItem(triangle);
    }
    public boolean undo() {
        model.removeItem(triangle);
        return true;
    }
    public boolean redo() {
        execute();
        return true;
    }
    public boolean end() {
//        if (triangle.getPoint3() == null){
//
//        }
        return triangle.getPoint1() != null && triangle.getPoint2() != null && triangle.getPoint3() != null;
    }
}
