package Objects;

//FACTORY METHOD
/*ObjCreator este o clasa abstracta cu rolul de Creator de tip “BALL” si “TUBE”.
        Aceasta cuprinde metodele getColor() si paintComponent(), iar Ball si Tube devin clase cu metodele mentionate intr-o forma implementata,
        dar si cu noi caracteristici unice, diferite unele de altele, pe care clasele nu le au in comun.*/

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

