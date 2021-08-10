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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.Optional;

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
    }

    public void createWaypoint(double x, double y) {
        WaypointDialog dialog = new WaypointDialog(x, y);
        dialog.setAction(() -> addWaypoint(dialog.getWaypoint()));
        dialog.display();
    }

    private void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

    private void save() {
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


}
