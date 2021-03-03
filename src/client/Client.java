package client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.decimal4j.util.DoubleRounder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public JFXTextField ammountTextField;
    public JFXComboBox<String> currency1ComboBox;
    public JFXComboBox<String> currency2ComboBox;
    public JFXButton convertButton;
    public Label currency1Label;
    public Label currency2Label;

    public static String ip = "localhost";
    public static int port = 4242;

    @FXML
    public void initialize() {
        for (int i = 0; i < Currencies.allCurrencies.size(); i++) {
            currency1ComboBox.getItems().add(Currencies.allCurrencies.get(i));
            currency2ComboBox.getItems().add(Currencies.allCurrencies.get(i));
        }
    }

    public void convertCurrency(ActionEvent actionEvent) {
        try {
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión con el servidor");

            InetSocketAddress addr = new InetSocketAddress(ip, port);
            clienteSocket.connect(addr);

            DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());

            double ammount = Double.parseDouble(ammountTextField.getText());
            String currency1 = currency1ComboBox.getValue();
            String currency2 = currency2ComboBox.getValue();

            dos.writeDouble(ammount);
            dos.writeUTF(currency1);
            dos.writeUTF(currency2);
            System.out.println("\nDivisas enviadas");

            double result = DoubleRounder.round(dis.readDouble(), 2);
            System.out.println("\nResultado: " + result);
            currency1Label.setText(ammount + " " + currency1 + " =");
            currency2Label.setText(result + " " + currency2);

            System.out.println("\nCerrando la conexion con el servidor\n");
            clienteSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToOptions(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("options/options.fxml"));
        Stage primaryStage = Main.getPrimaryStage();
        //System.out.println(primaryStage.getWidth() + " " + primaryStage.getHeight());
        primaryStage.setScene(new Scene(root, primaryStage.getWidth() - 16, primaryStage.getHeight() - 39));
    }
}
