package org.campus02.ecom;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
                    ArrayList<BasketData> baskets = null;
                    try {
                        baskets = BasketDataLoader.load(parts[1]);

                        analyzer = new BasketAnalyzer(baskets);

                        bw.write("<< basket data loaded with " + baskets.size() + " entries >>");
                        bw.newLine();
                        bw.flush();
                    } catch (DataFileException e) {
                        e.printStackTrace();

                        bw.write("Error on loading: " + e.getMessage());
                        bw.newLine();
                        bw.flush();
                    }

                } else if (parts[0].equals("GetEveryNth") && parts.length == 2){

                    if (analyzer == null) {
                        bw.write("<< OpenFile needs to be called first >>");
                        bw.newLine();
                        bw.flush();
                    }
                    else
                    {
                        int number = Integer.parseInt(parts[1]);
                        ArrayList<BasketData> entries = analyzer.getEveryNthBasket(number);

                        for (BasketData bd: entries) {
                            bw.write(bd.toString());
                            bw.newLine();
                        }
                        bw.write("count entries: " + entries.size());
                        bw.newLine();
                        bw.flush();
                    }

                } else if (parts[0].equals("GetStats") && parts.length == 1) {

                    if (analyzer == null) {
                        bw.write("<< OpenFile needs to be called first >>");
                        bw.newLine();
                        bw.flush();
                    }
                    else {
                        HashMap<String, ArrayList<Double>> map = analyzer.groupByProductCategory();

                        for (String productCategory: map.keySet()) {

                            ArrayList<Double> orderTotals = map.get(productCategory);
                            double sum = 0;
                            for (double value : orderTotals) {
                                sum += value;
                            }
                            sum = sum / orderTotals.size();

                            bw.write(productCategory + " - " + sum);
                            bw.newLine();
                            bw.flush();
                        }
                    }
                } else if (parts[0].equals("EXIT") && parts.length == 1) {
                    break;
                } else {
                    bw.write("<< Unknown command >>");
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
