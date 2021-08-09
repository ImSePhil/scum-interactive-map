package net.internalerror.scum;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WaypointDialog {

    private Waypoint waypoint;
    private Runnable saveAction;

    //Inputs
    private TextField _name;

    public WaypointDialog(double x, double y) {
        waypoint = new Waypoint();
        waypoint.setX((int) x);
        waypoint.setY((int) y);
    }

    public void display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        _name = new TextField();
        layout.getChildren().add(new Label("Name:"));
        layout.getChildren().add(_name);

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(actionEvent -> {
            waypoint.setName(_name.getText());
            saveAction.run();
            stage.close();
        });

        cancel.setOnAction(actionEvent -> stage.close());

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(save, cancel);
        layout.getChildren().add(buttons);

        Scene scene = new Scene(layout, 400, 800);
        stage.setTitle("New Waypoint");
        stage.setScene(scene);
        stage.show();
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    public void setAction(Runnable runnable) {
        this.saveAction = runnable;
    }
}
