package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Creando socket servidor");
        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.println("Realizando el bind");

        System.out.println("Aceptando conexiones");

        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("Conexión recibida\n");

            Thread t = new ClientHandler(s);
            t.start();
        }
    }
}
