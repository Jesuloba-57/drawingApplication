import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private MoveCommand moveCommand;
    private UndoManager undoManager;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        this.undoManager = undoManager;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        moveCommand = new MoveCommand();
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
        undoManager.beginCommand(moveCommand);
    }

    private class MouseHandler extends MouseAdapter {
        private Point startPoint;

        public void mousePressed(MouseEvent event) {
            startPoint = View.mapPoint(event.getPoint());
        }

        public void mouseReleased(MouseEvent event) {
            Point endPoint = View.mapPoint(event.getPoint());
            if (moveCommand.setPoints(startPoint, endPoint)) {
                drawingPanel.removeMouseListener(this);
                undoManager.endCommand(moveCommand);
            } else {
                undoManager.cancelCommand();
            }
        }
    }
}
