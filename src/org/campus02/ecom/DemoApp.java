package org.campus02.ecom;

import java.util.ArrayList;

public class DemoApp {
    public static void main(String[] args) throws DataFileException {
        ArrayList<BasketData> list = BasketDataLoader.load("./data/buyings.json", new BasketComparator());

        System.out.println("list.size() = " + list.size());
        /*
        for(int i = 0; i < 5000; i++) {
            System.out.println(list.get(i));
        }*/

        BasketAnalyzer ba = new BasketAnalyzer(list);

        //System.out.println("ba.filterBaskets(\"Amex\", 380.0, 400.0 = " + ba.filterBaskets("Amex", 380.0, 400.0));
        //System.out.println("ba.getEveryNthBasket(15000) = " + ba.getEveryNthBasket(15000));
        //System.out.println("ba.groupByProductCategory() = " + ba.groupByProductCategory().keySet());



        WeekDays weekDays = WeekDays.Monday;

        weekDays = WeekDays.Tuesday;

        if (weekDays == WeekDays.Wednesday) {
            System.out.println("PR Übungsabend");
        }
    }
}
