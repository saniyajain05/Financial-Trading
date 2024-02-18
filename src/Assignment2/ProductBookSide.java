package Assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ProductBookSide {
    private final BookSide side;
    private final HashMap<Price, ArrayList<Order>> bookEntries;

    public ProductBookSide(BookSide side) {
        if (side == null) {
            throw new IllegalArgumentException("Side cannot be null.");
        }

        this.side = side;
        this.bookEntries = new HashMap<Price, ArrayList<Order>>();
    }

    public OrderDTO add(Order o) {

        System.out.println("ADD: " + o.getSide() + ": "+o.getUser()+ " order: " + o.getSide() + " "+ o.getProduct()+ " at " + o.getPrice() + ", Orig Vol: " + o.getOriginalVolume() + ", Rem Vol: " + o.getRemainingVolume() + ", Fill Vol: "+ o.getFilledVolume() + ", CXL Vol: "+ o.getCancelledVolume()+ ", ID: "+ o.getId() );

        if (!bookEntries.containsKey(o.getPrice())) {
            bookEntries.put(o.getPrice(), new ArrayList<>());
        }
        ArrayList<Order> ordersForPrice = bookEntries.get(o.getPrice());
        ordersForPrice.add(o);
        //System.out.println(bookEntries.toString());
        return new OrderDTO(o.getId(), o.getProduct(), o.getPrice(),o.getSide(),o.getId(),o.getOriginalVolume(),o.getRemainingVolume(),o.getCancelledVolume(),o.getFilledVolume());

    }

    public OrderDTO cancel(String orderId) throws OrderNotFoundException{




        for (Price price : bookEntries.keySet()) {
            ArrayList<Order> ordersAtPrice = bookEntries.get(price);
            for (Order o : ordersAtPrice) {
                //System.out.println(o.toString());
                //System.out.println(o.getId());
                //System.out.println(orderId);
                if (o.getId().equals(orderId)) {
                    //System.out.println(ordersAtPrice.toString());
                    ordersAtPrice.remove(o);

                    o.setCancelledVolume(o.getCancelledVolume() + o.getRemainingVolume());
                    o.setRemainingVolume(0);

                    System.out.println("CANCEL:" + o.getSide() + " Order: " + orderId + " Cxl Qty: "+ o.getCancelledVolume());


                    if (ordersAtPrice.isEmpty()) {
                        bookEntries.remove(price);
                    }
                    return new OrderDTO(o.getId(), o.getProduct(), o.getPrice(),o.getSide(),o.getId(),o.getOriginalVolume(),o.getRemainingVolume(),o.getCancelledVolume(),o.getFilledVolume());

                }

                }
            }


        //throw new OrderNotFoundException("Order not found");
        return null;


    }

    public Price topOfBookPrice() {
        if (side == BookSide.BUY && !bookEntries.isEmpty()) {
            //System.out.println(bookEntries.toString());
            Price highestPrice = bookEntries.keySet().iterator().next(); // Highest price for BUY side
            for (Price price : bookEntries.keySet()) {
                try {
                    if (price.greaterThan(highestPrice)) {
                        highestPrice = price;
                    }
                } catch (InvalidPriceOperation e) {
                    System.out.println("Something went wrong");
                }

            }
            return highestPrice;
        } else if (side == BookSide.SELL && !bookEntries.isEmpty()) {

            Price lowestPrice = bookEntries.keySet().iterator().next();
            for (Price price : bookEntries.keySet()) {
                try {
                    if (price.lessThan(lowestPrice)) {
                        lowestPrice = price;
                    }
                } catch (InvalidPriceOperation e) {
                    System.out.println("Something went wrong");
                }
            }
            return lowestPrice;
        }
        return null;

    }

    public int topOfBookVolume() {
        Price topPrice = topOfBookPrice();
        if (topPrice != null) {
            int totalVolume = 0;
            for (Order order : bookEntries.get(topPrice)) {
                totalVolume += order.getRemainingVolume();
            }
            return totalVolume;
        }
        return 0;
    }

    public void tradeOut(Price price, int volume) {
        {
            int remainingVolume = volume;
            ArrayList<Order> ordersAtPrice = bookEntries.get(price);

                while(remainingVolume>0){
                    Order order = ordersAtPrice.get(0);
                    if (order.getRemainingVolume() <= remainingVolume) {
                        ordersAtPrice.remove(order);
                        int orderRemainingVolume = order.getRemainingVolume();
                        order.setFilledVolume(order.getFilledVolume() + orderRemainingVolume);
                        int remeberVolume = order.getRemainingVolume();
                        order.setRemainingVolume(0);
                        remainingVolume -= orderRemainingVolume;
                        System.out.println("\tFILL: ("+ side + " "+  remeberVolume + ") " + order.toString()  );
                    }
                    else{

                        order.setFilledVolume(order.getFilledVolume()+remainingVolume);
                        int remeberVolume = order.getFilledVolume();
                        order.setRemainingVolume(order.getRemainingVolume()-remainingVolume);
                        System.out.println("\tPARTIAL FILL: " + "("+ side + " "+  remeberVolume + ") " + order.toString() );
                        remainingVolume = 0;
                    }
                }
                if(ordersAtPrice.isEmpty()){
                    bookEntries.remove(price);
                }


        }
    }

    /*@Override
    public String toString() {
        String returnString = "";
        returnString += "Side: " + side + "\n";
        for (Price price : bookEntries.keySet()) {
            returnString += "\t\tPrice: " + price.toString() + "\n" ;
            ArrayList<Assignment2.Order> ordersAtPrice = bookEntries.get(price);
            for (Assignment2.Order order : ordersAtPrice) {
               returnString += "\t\t\t"+ order.toString() + "\n";
            }
        }

        return returnString;
    }*/

/*
    @Override
    public String toString() {
        String returnString = "";
        returnString += "Side: " + side + "\n";
        if(side==BookSide.BUY){
            HashMap<Price,ArrayList<Order>> temp = bookEntries;

            while(!temp.isEmpty()){
                //for (Assignment2.Price price : temp.keySet()) {
                Price price = this.topOfBookPrice();
                returnString += "\t\tAssignment2.Price: " + price.toString() + "\n" ;
                ArrayList<Order> ordersAtPrice = bookEntries.get(price);
                for (Order order : ordersAtPrice) {
                    returnString += "\t\t\t"+ order.toString() + "\n";
                }
                temp.remove(price);
            }}

        else{
            HashMap<Price,ArrayList<Order>> temp = new HashMap<Price,ArrayList<Order>>();
            while(!temp.isEmpty()) {
                //for (Assignment2.Price price : temp.keySet()) {
                Price price = this.topOfBookPrice();
                temp.put(price, bookEntries.get(price));
            }
            for (Price price : temp.keySet()) {

                returnString += "\t\tPrice: " + price.toString() + "\n" ;
                ArrayList<Order> ordersAtPrice = bookEntries.get(price);
                for (Order order : ordersAtPrice) {
                    returnString += "\t\t\t"+ order.toString() + "\n";
                }
                temp.remove(price);
            }}

        return returnString;
    }*/

    @Override
    public String toString() {
        String returnString = "";
        returnString += "Side: " + side + "\n";

        if(bookEntries.isEmpty()){
            returnString+= "\t<Empty>\n";
        }
        if(side == BookSide.BUY){
        List<Price> keys = new ArrayList<Price>(bookEntries.keySet());
        Collections.sort(keys,Collections.reverseOrder());
        //System.out.println(keys.toString());
        for (Price price: keys) {
            returnString += "\tPrice: " + price.toString() + "\n" ;
            ArrayList<Assignment2.Order> ordersAtPrice = bookEntries.get(price);
            for (Assignment2.Order order : ordersAtPrice) {
                returnString += "\t\t"+ order.toString() + "\n";
            }}
        }

        if(side == BookSide.SELL){
            ArrayList<Price> keys = new ArrayList<Price>(bookEntries.keySet());
            Collections.sort(keys);
            for (Price price: keys) {
                returnString += "\tPrice: " + price.toString() + "\n" ;
                ArrayList<Assignment2.Order> ordersAtPrice = bookEntries.get(price);
                for (Assignment2.Order order : ordersAtPrice) {
                    returnString += "\t\t"+ order.toString() + "\n";
                }}
        }
        return returnString;
    }
}


