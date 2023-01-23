import java.util.ArrayList;
import java.util.Iterator;

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