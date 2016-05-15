package CityPlanner.Views;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * La classe Webserver implémente un petit serveur web multithreadé
 * Implémenté en Runnable, donc doit être lancé par un thread
 */
public class Webserver implements Runnable {
    int port;

    /**
     * Constructeur
     * @param port
     */
    public Webserver(int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while(true)  {
                Socket socket = server.accept();
                new Thread(new ClientTask(socket)).start();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private class ClientTask implements Runnable {
        Socket socket;

        public ClientTask(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                InputStream input  = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                String html = "<html><head></head><body>Mon super serveur web</body></html>";

                output.write(("HTTP/1.0 200 OK\r\n"+
                              "Connection: Close\r\n"+
                              "\r\n"+
                              html).getBytes());

                output.close();
                input.close();
            } catch (Exception e) {
                System.err.printf("ERREUR serveur web : %s\n", e);
                e.printStackTrace();
            }
        }
    }
}