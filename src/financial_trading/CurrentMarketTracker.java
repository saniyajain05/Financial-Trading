package financial_trading;

public class CurrentMarketTracker {

    private static CurrentMarketTracker instance;
    public static CurrentMarketTracker getInstance() {
        if (instance == null) {
            instance = new CurrentMarketTracker();
        }
        return instance;
    }

    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume){
        Price marketWidth;

        if (buyPrice == null){
             marketWidth = PriceFactory.makePrice(0);
             buyPrice = PriceFactory.makePrice(0);
        }
        else if (sellPrice == null) {
            marketWidth = PriceFactory.makePrice(0);
            sellPrice = PriceFactory.makePrice(0);
        }
        else{

            try {
             marketWidth = sellPrice.subtract(buyPrice);
             //sellPrice = PriceFactory.makePrice(0);
        } catch (InvalidPriceOperation e) {
             marketWidth = PriceFactory.makePrice(0);

        }
    }
        CurrentMarketSide buySide  = new CurrentMarketSide( buyPrice, buyVolume);
        CurrentMarketSide sellSide  = new CurrentMarketSide( sellPrice, sellVolume);


        System.out.println("*********** Current Market ***********\n" + "*" +  symbol  + " " +  buyPrice + "x" +  buyVolume + "–" +  sellPrice  + "x" +  sellVolume  + "[" + marketWidth +"] \n" + "**************************************" );


        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buySide, sellSide);
    }
}
