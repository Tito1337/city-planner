package CityPlanner.Views;

import CityPlanner.Model.Trip;

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
    Trip trip;

    /**
     * Constructeur
     * @param port
     */
    public Webserver(int port, Trip trip) {
        this.port = port;
        this.trip = trip;
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

                String html = "<!DOCTYPE html>\r\n"+
                        "<html>\r\n"+
                        "<head>\r\n"+
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"+
                        "   <title>Votre City Trip</title>\r\n"+
                        "</head>\r\n"+
                        "<body>\r\n";

                if(trip.getCity() != null && trip.getTag() != null) {
                    html += "<h1>Séjour à "+trip.getCity()+"</h1>";
                }

                html += "</body>\r\n</html>";

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