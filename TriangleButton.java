import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TriangleButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private UndoManager undoManager;
    private TriangleCommand triangleCommand;

    public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Trianagle");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {
            if (++pointCount == 1) {
                triangleCommand = new TriangleCommand(View.mapPoint(event.getPoint()));
                undoManager.beginCommand(triangleCommand);
            }
            else if(pointCount == 2){
                triangleCommand.setTriPoint(View.mapPoint(event.getPoint()));
                view.refresh();
            }
            else if(pointCount == 3){
                triangleCommand.setTriPoint(View.mapPoint(event.getPoint()));
                drawingPanel.removeMouseListener(this);
                undoManager.endCommand(triangleCommand);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                pointCount = 0;
            }
        }
    }
}
