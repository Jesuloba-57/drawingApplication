import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends Item {
    private List<Point> vertices;
    private boolean finalpoint = false;

    public Polygon(List<Point> vertices) {
        this.vertices = new ArrayList<>(vertices);
    }

    public void setPoint(Point point){
        vertices.add(point);
    }
    public void setFinalPoint(Point point){
        vertices.add(point);
        finalpoint = true;
    }

    public boolean notEmpty(){
        return !vertices.isEmpty();
    }

    public boolean includes(Point point) {
        for (Point vertex : vertices) {
            if (distance(point, vertex) < 10.0) {
                return true;
            }
        }
        return false;
    }

    public void render(UIContext uiContext) {
//        if (vertices.size() == 1){
//
//        }
//        if (vertices.size() < 3) {
//            // A polygon needs at least 3 vertices to be valid
//            return;
//        }

        for (int i = 0; i < vertices.size() - 1; i++) {
            uiContext.drawLine(vertices.get(i), vertices.get(i + 1));
        }

        // Connect the last and first vertices to close the polygon
        if (finalpoint)
            uiContext.drawLine(vertices.get(vertices.size() - 1), vertices.get(0));
    }

    // other methods...

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Polygon composed of vertices:\n");
        for (Point vertex : vertices) {
            stringBuilder.append(vertex).append("\n");
        }
        return stringBuilder.toString();
    }

    public int getLen() {
        return vertices.size();
    }
}
