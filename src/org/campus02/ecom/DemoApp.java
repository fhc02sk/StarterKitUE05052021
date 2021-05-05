package org.campus02.ecom;

import java.util.ArrayList;

public class DemoApp {
    public static void main(String[] args) throws DataFileException {
        ArrayList<BasketData> list = BasketDataLoader.load("./data/buyings.json");

        System.out.println("list.size() = " + list.size());
        //System.out.println(list);
    }
}
