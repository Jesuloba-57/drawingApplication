import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private UndoManager undoManager;
    private PolygonCommand polygonCommand;
    private boolean isPolygonInProgress;

    public PolygonButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        this.view = view;
        this.drawingPanel = drawingPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        isPolygonInProgress = true;
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private List<Point> polygonPoints = new ArrayList<>();
        int pointCount = polygonPoints.size();

        public void mouseClicked(MouseEvent event) {
            if (++pointCount == 1)
                {
                    polygonPoints.add(View.mapPoint(event.getPoint()));
                    polygonCommand = new PolygonCommand(new ArrayList<>(polygonPoints));
                    undoManager.beginCommand(polygonCommand);
                }
            if (SwingUtilities.isRightMouseButton(event)) {
                    // Create and end the polygon command when right-click is detected
                    polygonCommand.setFinal(View.mapPoint(event.getPoint()));
                    drawingPanel.removeMouseListener(this);
                    undoManager.endCommand(polygonCommand);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    polygonPoints.clear();
                    pointCount = 0;
                    isPolygonInProgress = false; // Stop the loop
            } else if (isPolygonInProgress) {
                // Add the clicked point to the list
                polygonCommand.setPoint(View.mapPoint(event.getPoint()));
                view.refresh();
            }
        }
    }
}
