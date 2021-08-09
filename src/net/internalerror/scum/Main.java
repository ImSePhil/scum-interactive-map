package net.internalerror.scum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scum Map Tool");

        Tool tool = new Tool();
        primaryStage.setScene(new Scene(tool));
        primaryStage.show();
    }
}
