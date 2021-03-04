package client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Options {

    public AnchorPane rootPane;
    public JFXTextField ipTextField;
    public JFXTextField portTextField1;
    public JFXButton saveButton;
    public JFXSnackbar snackbar;

    @FXML
    public void initialize() {
        ipTextField.setText(Client.ip);
        portTextField1.setText(String.valueOf(Client.port));

        snackbar = new JFXSnackbar(rootPane);
    }

    public void goToMain(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("resources/client.fxml"));
        Stage primaryStage = Main.getPrimaryStage();
        //System.out.println(primaryStage.getWidth() + " " + primaryStage.getHeight());
        primaryStage.setScene(new Scene(root, primaryStage.getWidth() - 16, primaryStage.getHeight() - 39));
    }

    public void saveSettings(ActionEvent actionEvent) {
        Client.ip = ipTextField.getText();
        Client.port = Integer.parseInt(portTextField1.getText());
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("Se han guardado los cambios")));
    }
}
