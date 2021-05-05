package org.campus02.ecom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemoApp {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            Socket client = serverSocket.accept();

            EcommerceLogic ecommerceLogic = new EcommerceLogic(client);
            ecommerceLogic.run();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
