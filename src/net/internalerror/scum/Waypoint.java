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

    /**
     * <waypoint>
     * <name>Bunker</name>
     * <x>192.0</x>
     * <y>123.23</y>
     * </waypoint>
     * {
     * name: "Bunker"
     * x: "123.123"
     * y: "232.232"
     * }
     */

    public String toXML() {
        String xml = "<waypoint>\n\t<name>valueName</name>\n\t<x>valueX</x>\n\t<y>valueY</y>\n</waypoint>";
        xml = xml.replace("valueName", name);
        xml = xml.replace("valueX", x + "");
        xml = xml.replace("valueY", y + "");


        return xml;
    }
}
