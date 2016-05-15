package CityPlanner.Views;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tito on 15/05/16.
 */
public class Webserver implements Runnable {
    int port;

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
                long time = System.currentTimeMillis();
                output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                        "Coucou" + " - " +
                        time +
                        "").getBytes());
                output.close();
                input.close();
                System.out.println("Request processed: " + time);
            } catch (Exception e) {
                //report exception somewhere.
                e.printStackTrace();
            }
        }
    }
}