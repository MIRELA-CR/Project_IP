package Objects;

//FACTORY METHOD
/*Objects.ObjCreator este o clasa abstracta cu rolul de Creator, iar CreateBall si CreateTube devin
clase mostenite si implementate cu noi caracteristici dupa sablonul creational Factory Method*/

import java.awt.*;


public interface ObjCreator {
    Color getColor ();
    void paintComponent(Graphics g);


    public class ColorFactory {
        public ObjCreator colorObj(String obj) {
            if (obj == null || obj.isEmpty())
                return null;
            switch (obj) {
                case "BALL":
                    return new Ball();
                case "TUBE":
                    return new Tube();
                default:
                    throw new IllegalArgumentException("Unknown object" + obj);
            }
        }
    }

    public class PaintFactory {
        public ObjCreator paintObj(String obj) {
            if (obj == null || obj.isEmpty())
                return null;
            switch (obj) {
                case "BALL":
                    return new Ball();
                case "TUBE":
                    return new Tube();
                default:
                    throw new IllegalArgumentException("Unknown object" + obj);
            }

        }
    }

}

