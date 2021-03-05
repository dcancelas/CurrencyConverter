import org.ini4j.Wini;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static int port;

    public static void main(String[] args) throws IOException {
        readConfigFile();
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

    public static void readConfigFile() throws IOException {
        File file = new File("server.ini");
        file.createNewFile();
        Wini ini = new Wini(file);
        port = ini.get("server", "port", int.class);
    }
}
