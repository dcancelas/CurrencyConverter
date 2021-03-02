package client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.decimal4j.util.DoubleRounder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {

    public JFXTextField ammountTextField;
    public JFXComboBox<String> currency1ComboBox;
    public JFXComboBox<String> currency2ComboBox;
    public JFXButton convertButton;
    public Label currency1Label;
    public Label currency2Label;

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

            InetSocketAddress addr = new InetSocketAddress("localhost", 4242);
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
}
