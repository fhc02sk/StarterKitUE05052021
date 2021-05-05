package org.campus02.ecom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemoApp {

    public static void main(String[] args) throws IOException {

       try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("server started...");
            while (true) {
                System.out.println("waiting for client...");
                Socket client = serverSocket.accept(); // BLOCKING

                System.out.println("client connected...");
                EcommerceLogic ecommerceLogic = new EcommerceLogic(client);

                Thread th = new Thread(ecommerceLogic);
                th.start(); // !!!! NICHT ecommerceLogic.run(); aufrufen

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
