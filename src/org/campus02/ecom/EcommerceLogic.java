package org.campus02.ecom;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class EcommerceLogic implements Runnable {

    private Socket socket;

    public EcommerceLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {

            BasketAnalyzer analyzer = null;
            String command;
            while ((command = br.readLine()) != null) {

                System.out.println("client: " + command);

                //"OpenFile data/buyings.json"
                String[] parts = command.split(" ");
                // parts[0] == OpenFile
                // parts[1] == path

                if (parts[0].equals("OpenFile") && parts.length == 2){
                    ArrayList<BasketData> baskets = BasketDataLoader.load(parts[1]);
                    analyzer = new BasketAnalyzer(baskets);

                    bw.write("<< basket data loaded with " + baskets.size() + " entries >>");
                    bw.newLine();
                    bw.flush();
                } else if (parts[0].equals("GetEveryNth") && parts.length == 2){

                } else if (parts[0].equals("GetStats") && parts.length == 1) {

                } else if (parts[0].equals("EXIT") && parts.length == 1) {

                } else {
                    // Ungültige Befehle
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFileException e) {
            e.printStackTrace();
        }
    }
}
