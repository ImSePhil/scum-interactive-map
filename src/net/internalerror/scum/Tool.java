package net.internalerror.scum;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.nio.file.WatchKey;
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
    }


    public void createWaypoint(double x, double y) {
        WaypointDialog dialog = new WaypointDialog(x,y);
        dialog.setAction(() -> addWaypoint(dialog.getWaypoint()));
        dialog.display();
    }

    private void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

}
