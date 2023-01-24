import java.util.ArrayList;
import java.util.Iterator;

//OBSERVER//
/*Clasa Subject contine metode pentru inregistrare, stergere din memorie is actualizare a evenimentului de click al mouse-lui.
        Aceste metode sunt mai apoi implementate in clasa MovesData cu actiunile respective pentru a tine un log al schimbarilor
        (aici, cresterea numarului de click-uri facute pe ecran). Observer creaza o interfata de upate,
        folosita de CurrentMCount prin adaugarea unei unitati la numarul inregistrat de pana acum.*/

interface Subject {
    public void registerObs(Observer o);
    public void unregisterObs(Observer o);
    public void notifyObservers();
}

class MovesData implements Subject {
    int moves;

    ArrayList<Observer> observerList;

    public MovesData() {
        observerList = new ArrayList<Observer>();
    }

    @Override
    public void registerObs(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObs(Observer o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for (Iterator<Observer> it =
             observerList.iterator(); it.hasNext();)
        {
            Observer o = it.next();
            o.update(o, moves);
        }
    }

    private int getLatestMoves() {
        return 0;
    }

    public void dataChanged() {
        moves = getLatestMoves();
        notifyObservers();
    }
}

interface Observer {
    public void update( Observer o, int moves);
}

class CurrentMCount implements Observer {
    private int moves;
    public void update(Observer o, int moves) {
        this.moves = moves;
        display();
    }

    public void display() {
        System.out.println("\nAti dat click de" + moves + "ori\n");
    }
}