package Objects;

import Objects.ObjCreator;

import java.awt.*;

public class Ball implements ObjCreator {
    Ball() {}
    private String ballName;
    private Color ballColor;

    public Ball(int ballIndex, Color ballColor){

        this.ballName = "Objects.Ball " + ballIndex;
        this.ballColor = ballColor;
    }

    public Color getColor() {
        return this.ballColor;
    }

    public String getName() {
        return this.ballName;
    }

    public void paintComponent(Graphics g) {

    }
}
