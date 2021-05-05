package org.campus02.ecom;

import java.util.ArrayList;

public class DemoApp {
    public static void main(String[] args) throws DataFileException {
        ArrayList<BasketData> list = BasketDataLoader.load("./data/buyings.json", new BasketComparator());

        System.out.println("list.size() = " + list.size());

        for(int i = 0; i < 5000; i++) {
            System.out.println(list.get(i));
        }
    }
}
