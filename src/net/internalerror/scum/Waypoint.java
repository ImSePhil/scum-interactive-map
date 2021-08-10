package net.internalerror.scum;


import java.nio.file.WatchKey;
import java.util.Arrays;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Waypoint extends Observable {
    private int x, y;
    private String name;

    private static final String namePattern = "<name>(.*?)</name>";
    private static final String xPattern = "<x>(.*?)</x>";
    private static final String yPattern = "<y>(.*?)</y>";


    public Waypoint(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Waypoint() {

    }

    public static Waypoint fromXML(String xml) {
        Waypoint waypoint = new Waypoint();
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(namePattern, Pattern.DOTALL);
        matcher = pattern.matcher(xml);
        if (matcher.find()) waypoint.setName(matcher.group(1));
        pattern = Pattern.compile(xPattern, Pattern.DOTALL);
        matcher = pattern.matcher(xml);
        if (matcher.find()) waypoint.setX(Integer.parseInt(matcher.group(1)));
        pattern = Pattern.compile(yPattern, Pattern.DOTALL);
        matcher = pattern.matcher(xml);
        if (matcher.find()) waypoint.setY(Integer.parseInt(matcher.group(1)));


        return waypoint;
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
