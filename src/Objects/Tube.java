package Objects;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Stack;

public class Tube extends JComponent implements ObjCreator {

    Tube() {}
    private String tubeName;
    /*private final int TUBE_COORD = 1;
    private final int X_COORD = 1;
    private final int RECT_HEIGHT = 232;
    private final int RECT_WIDTH = 58;
    private final int Y_COORD = 174;*/
    private Color tubeColor;

    private Stack<Ball> ballList;

    public Tube(int tubeIndex) {
        this.ballList = new Stack();
        this.tubeName = "Objects.Tube " + tubeIndex;
        Dimension tDim = new Dimension(60, 234);
        this.setMaximumSize(tDim);
        this.setMinimumSize(tDim);
        this.setPreferredSize(tDim);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    public String getName() {
        return "Objects.Tube ";
    }

    public void setTubeColor(Color c) {
        this.tubeColor = c;
    }

    public void setBallList(Stack list) {
        this.ballList = list;
    }

    public Stack<Ball> getBallList() {
        return this.ballList;
    }

    public void addBall(Ball ball) {
        this.ballList.push(ball);
    }

    public Ball getBall() {
        Ball tBall = null;
        if (!this.ballList.isEmpty()) {
            tBall = (Ball) this.ballList.pop();
        }

        return tBall;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(this.tubeColor);
        g2d.drawRect(1, 1, 58, 232);
        if (!this.ballList.isEmpty()) {
            int yCoord = 174;
            Iterator var4 = this.ballList.iterator();

            while (var4.hasNext()) {
                Ball tBall = (Ball) var4.next();
                if (tBall != null) {
                    g2d.setColor(tBall.getColor());
                    g2d.fillOval(1, yCoord, 58, 57);
                    yCoord -= 58;
                }
            }
        }

        g2d.dispose();
    }
}
