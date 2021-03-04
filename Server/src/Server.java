import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = 4242;
        System.out.println("Iniciando servidor en el puerto " + port);
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("\nAceptando conexiones");

        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("\nConexión recibida: " + s.getLocalAddress().getHostAddress());

            Thread t = new ClientHandler(s);
            t.start();
        }
    }
}
