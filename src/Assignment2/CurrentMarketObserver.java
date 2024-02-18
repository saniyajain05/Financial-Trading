package Assignment2;

public interface CurrentMarketObserver {

    void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide);
}
