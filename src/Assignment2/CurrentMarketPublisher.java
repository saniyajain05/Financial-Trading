package Assignment2;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentMarketPublisher {

    private static CurrentMarketPublisher instance;
    private HashMap<String, ArrayList<CurrentMarketObserver>> filters;

    private CurrentMarketPublisher() {
        filters = new HashMap<>();
    }


    public static CurrentMarketPublisher getInstance() {
        if (instance == null) {
            instance = new CurrentMarketPublisher();
        }
        return instance;
    }

    void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {

        if(filters.containsKey(symbol)) {
            ArrayList<CurrentMarketObserver> currentMarketObservers = filters.get(symbol);
            filters.get(symbol).add(cmo);
        }


        else{
            ArrayList<CurrentMarketObserver> currentMarketObservers = new ArrayList<CurrentMarketObserver>();
            filters.put(symbol, currentMarketObservers);
            filters.get(symbol).add(cmo);
        }


    }

    void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {

        if(filters.containsKey(symbol)){

            ArrayList<CurrentMarketObserver> currentMarketObservers = filters.get(symbol);
            currentMarketObservers.remove(cmo);

        }


    }

    void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {

        if (!filters.containsKey(symbol)) {
            return;
        }


        ArrayList<CurrentMarketObserver> currentMarketObservers = filters.get(symbol);


        for (CurrentMarketObserver currentMarketObserver : currentMarketObservers) {
            currentMarketObserver.updateCurrentMarket(symbol, buySide, sellSide);
        }
    }
}

