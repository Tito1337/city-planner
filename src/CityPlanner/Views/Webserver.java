package CityPlanner.Views;

import CityPlanner.Model.Activity;
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
     * @param port TCP où le seveur se met en écoute
     * @param trip instance du trip qui sera affiché lors de requêtes web
     */
    public Webserver(int port, Trip trip) {
        this.port = port;
        this.trip = trip;
    }

    /**
     * "Main" du serveur web
     */
    public void run() {
        try {
            // Lancer l'écoute sur le port programmé
            ServerSocket server = new ServerSocket(port);

            // Créer un ClientTask pour chaque requête au serveur
            while(true)  {
                Socket socket = server.accept();
                new Thread(new ClientTask(socket)).start();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * ClientTask est un runnable appelé à chaque nouvelle requête web.
     * Il gère la requête du client et génère une page web résumant les informations du Trip en mémoire.
     */
    private class ClientTask implements Runnable {
        Socket socket;

        /**
         * Constructeur
         * @param socket où envoyer la réponse HTTP
         */
        public ClientTask(Socket socket) {
            this.socket = socket;
        }

        /**
         * "Main" de ce runnable
         */
        public void run() {
            try {
                InputStream input  = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                // Génération du HTML
                String html = "<!DOCTYPE html>\r\n"+
                        "<html>\r\n"+
                        "<head>\r\n"+
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"+
                        "   <title>Votre City Trip</title>\r\n"+
                        "</head>\r\n"+
                        "<body>\r\n";

                if(trip.getCity() != null && trip.getTag() != null) {
                    html += "<h1>Séjour à "+trip.getCity()+"</h1>\r\n";
                    html += "<p>Du "+trip.getStartDate()+" au "+trip.getEndDate()+"\r\n";
                    html += "<p>Voyage pour "+trip.getPersonNumber()+" personnes sur le thème "+trip.getTag()+"</p>\r\n";

                    html += "<h2>Activités choisies</h2>\r\n";

                    for(Activity a: trip.getActivities()) {
                        if(a.getSelected()) {
                            html += "<h3>"+a.getName()+"</h3>\r\n";
                            html += "<p>"+a.getDescription()+"</p>\r\n";
                            html += "<p><b>Durée</b> "+a.getDuration()+" heures, <b>Prix</b> "+a.getPrice()+" €, <b>Ouverture</b> "+a.getOpen()+", <b>Adresse</b> "+a.getAddress()+"</p>\r\n";
                        }
                    }
                } else {
                    html += "<h1>Encodage incomplet</h1>\r\n";
                }

                html += "</body>\r\n</html>";

                // Envoi au client
                output.write(("HTTP/1.0 200 OK\r\n"+
                              "Connection: Close\r\n"+
                              "\r\n"+
                              html).getBytes());

                // Cleanup
                output.close();
                input.close();
            } catch (Exception e) {
                System.err.printf("ERREUR serveur web : %s\n", e);
                e.printStackTrace();
            }
        }
    }
}