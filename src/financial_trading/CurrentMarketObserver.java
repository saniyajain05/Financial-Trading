package financial_trading;

public interface CurrentMarketObserver {

    void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide);
}
