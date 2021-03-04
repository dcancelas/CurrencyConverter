import org.decimal4j.util.DoubleRounder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            double ammount = dis.readDouble();
            String currency1 = dis.readUTF();
            String currency2 = dis.readUTF();

            System.out.println(currency1 + " -> " + currency2 + " (" + ammount + ")");
            double result = Converter.convert_currency(currency1, currency2, ammount);
            dos.writeDouble(DoubleRounder.round(result, 2));
            System.out.println("Resultado enviado");

            System.out.println("\nCerrando la conexión con el cliente");
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
