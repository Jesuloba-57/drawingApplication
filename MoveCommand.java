import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class MoveCommand extends Command {
    private Vector itemList;
    private Point startPoint;
    private Point endPoint;

    public MoveCommand() {
        itemList = new Vector();
        Enumeration enumeration = model.getSelectedItems();
        while (enumeration.hasMoreElements()) {
            Item item = (Item)(enumeration.nextElement());
            itemList.add(item);
            item.translate(deltaX, deltaY);
        }
    }

    public boolean setPoints(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        if (itemList != null) {
//           model.translate(item, startPoint, endPoint);
            //item.translate()
            return true;
        }
        return false;
    }

    int deltaX = endPoint.x - startPoint.x;
    int deltaY = endPoint.y - startPoint.y;

    public boolean undo() {
        model.moveSelected(item, endPoint, startPoint);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public void execute() {
        model.moveSelected(item, startPoint, endPoint);
    }
}
