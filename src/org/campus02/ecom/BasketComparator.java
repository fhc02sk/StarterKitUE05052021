package org.campus02.ecom;

import java.util.Comparator;

public class BasketComparator implements Comparator<BasketData> {

    @Override
    public int compare(BasketData o1, BasketData o2) {

        int compBuyingLocation = o1.getBuyingLocation().compareTo(o2.getBuyingLocation());
        if (compBuyingLocation == 0) {
            return Double.compare(o2.getOrderTotal(), o1.getOrderTotal());
//            return o2.getOrderTotal().compareTo(o1.getOrderTotal());
        }

        return compBuyingLocation;
    }
}
