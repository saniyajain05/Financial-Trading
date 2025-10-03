package Assignment2;

import java.util.HashMap;

public class User implements CurrentMarketObserver {
    private String userId;
    private HashMap<String, OrderDTO> orders;

    private HashMap<String, CurrentMarketSide[]> currentMarkets;

    public User(String userId) {
        setUserId(userId);
        orders = new HashMap<String, OrderDTO>();
        currentMarkets = new HashMap<String, CurrentMarketSide[]>();
    }


    private void setUserId(String userId) {
        if (userId != null && userId.matches("^[A-Z]{3}$")) {
            this.userId = userId;
        } else {
            System.out.println("Invalid user.");
            this.userId = null;
        }

    }

    public String getUserId() {
        return userId;
    }

    public void addOrder(OrderDTO o) {
        if (o != null) {
            orders.put(o.id, o);
        } else {
            System.out.println("Invalid.");
        }
    }

    public boolean hasOrderWithRemainingQty() {
        for (OrderDTO order : orders.values()) {
            if (order.remainingVolume > 0) {
                return true;
            }
        }
        return false;
    }

    public OrderDTO getOrderWithRemainingQty() {
        for (OrderDTO order : orders.values()) {
            if (order.remainingVolume > 0) {
                return order;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        String returnString = "";
        returnString += "User Id: " + userId + "\n";
        for (String product : orders.keySet()) {
            OrderDTO order = orders.get(product);
            returnString += "\t" + "Product: " + order.product + ", Price: " + order.price.toString() + ", Original Volume: " + order.originalVolume +
                    ", Remaining Volume: " + order.remainingVolume + ", Filled Volume: " + order.filledVolume + ", User: " + userId + ", Side: " + order.side +
                    ", Id: " + order.id + "\n";
        }

        return returnString;

    }

    @Override
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {

        CurrentMarketSide[] marketSides = new CurrentMarketSide[2];
        marketSides[0] = buySide;
        marketSides[1] = sellSide;



        currentMarkets.put(symbol, marketSides);

    }

    public String getCurrentMarkets() {
        String returnString = "";
        for (String symbol : currentMarkets.keySet()){
         returnString += symbol + " " +  currentMarkets.get(symbol)[0].toString() + " - " + currentMarkets.get(symbol)[1].toString() + "\n";

    }
        return returnString;
}
}


