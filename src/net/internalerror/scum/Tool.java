package net.internalerror.scum;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.*;
import java.nio.file.WatchKey;
import java.util.*;

public class Tool extends BorderPane { // NOSONAR

    ObservableList<Waypoint> waypoints = FXCollections.observableArrayList();


    public Tool() {
        Image img = null;
        try {
            img = new Image("net/internalerror/scum/image.jpg", 1000, 1000, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert img != null;
        ImageView imageView = new ImageView();
        imageView.setImage(img);

        imageView.setOnMouseClicked(mouseEvent -> createWaypoint(mouseEvent.getX(), mouseEvent.getY()));

        ListView<Waypoint> list = new ListView<>();
        list.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Waypoint waypoint, boolean empty) {
                super.updateItem(waypoint, empty);
                if (empty || waypoint == null || waypoint.getName() == null) {
                    setText("");
                    return;
                }
                setText(waypoint.getName());
            }
        });

        list.setItems(waypoints);

        list.setMaxSize(400, 1000);
        list.setPrefSize(400, 1000);
        list.setMinSize(400, 1000);

        setLeft(list);
        setCenter(imageView);

        HBox topButtonbar = new HBox();
        Button btn_Save = new Button("Save");

        btn_Save.setOnAction(actionEvent -> save());
        topButtonbar.getChildren().add(btn_Save);
        setTop(topButtonbar);
        load();
    }

    public void createWaypoint(double x, double y) {
        WaypointDialog dialog = new WaypointDialog(x, y);
        dialog.setAction(() -> addWaypoint(dialog.getWaypoint()));
        dialog.display();
    }

    private void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

    public void save() {
        StringBuilder xml = new StringBuilder();
        xml.append("<waypoints>");

        waypoints.forEach(waypoint -> {
            String part = waypoint.toXML();
            xml.append(part.replace("\n", "").replace("\t", ""));
        });

        xml.append("</waypoints>");
        try {

            File file = new File("waypoints.xml");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(xml.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        File f = new File("waypoints.xml");
        if (!f.exists()) return;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            StringBuilder builder = new StringBuilder();
            ;
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            String content = builder.toString();
            content = content.replace("<waypoints>", "").replace("</waypoints>", "");
            String[] split = content.split("((?=<waypoint>))");
            for (String s : split) {
                Waypoint waypoint = Waypoint.fromXML(s);
                waypoints.add(waypoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
