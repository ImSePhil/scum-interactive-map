package net.internalerror.scum;


import java.util.Observable;

public class Waypoint extends Observable {
    private int x, y;
    private String name;

    public Waypoint(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Waypoint() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        setChanged();
        notifyObservers();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public void addY(int y) {
        this.y += y;
        setChanged();
        notifyObservers();
    }

    public void addX(int x) {
        this.x += x;
        setChanged();
        notifyObservers();
    }

    public void subY(int y) {
        this.y -= y;
        setChanged();
        notifyObservers();
    }

    public void subX(int x) {
        this.x -= x;
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}
