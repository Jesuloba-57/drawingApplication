import java.awt.*;
import java.text.*;
import java.util.List;

public class PolygonCommand extends Command {
    private Polygon polygon;
    private int pointCount;

    public PolygonCommand(List<Point> polygonPoints) {
        polygon = new Polygon(polygonPoints);
        pointCount = polygonPoints.size();
    }

    public void setPoint(Point point) {
//        if (polygon.getLen() == 1){
//            Line line = new Line(point, point);
//            model.addItem(line);
//        }
        polygon.setPoint(point);
    }
    public void setFinal(Point point){
        polygon.setFinalPoint(point);
    }
    public void execute() {
        model.addItem(polygon);
    }
    public boolean undo() {
        model.removeItem(polygon);
        return true;
    }
    public boolean redo() {
        execute();
        return true;
    }
    public boolean end() {
        return polygon.notEmpty();
    }
}