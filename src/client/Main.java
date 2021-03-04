package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage pStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/client.fxml"));
        primaryStage.setTitle("Conversor de divisas");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(380);
        primaryStage.setResizable(true);
        primaryStage.show();

        pStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
