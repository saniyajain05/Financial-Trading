package financial_trading;

public class ProductBook {
    private final String product;
    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    public ProductBook(String product) {
        if (!isValidProduct(product)) {
            throw new IllegalArgumentException("Invalid");
        }

        this.product = product;
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }

    public OrderDTO add(Order order) throws DataValidationException{
        OrderDTO orderDTO = (order.getSide() == BookSide.BUY) ? buySide.add(order) : sellSide.add(order);
        tryTrade();
        updateMarket();
        return orderDTO;
    }

    public OrderDTO cancel(BookSide side, String orderId) throws OrderNotFoundException {
        OrderDTO returnOrder = (side == BookSide.BUY) ? buySide.cancel(orderId) : sellSide.cancel(orderId);
        return (returnOrder);

    }

    public void tryTrade() {
        Price topBuyPrice = buySide.topOfBookPrice();
        Price topSellPrice = sellSide.topOfBookPrice();

        while (true) {
            try {
                if (!(topBuyPrice != null && topSellPrice != null && topBuyPrice.greaterOrEqual(topSellPrice))) break;{

                int topBuyVolume = buySide.topOfBookVolume();
                int topSellVolume = sellSide.topOfBookVolume();
                int volumeToTrade = Math.min(topBuyVolume, topSellVolume);


                sellSide.tradeOut(topSellPrice, volumeToTrade);
                buySide.tradeOut(topBuyPrice, volumeToTrade);

                topBuyPrice = buySide.topOfBookPrice();
                topSellPrice = sellSide.topOfBookPrice();
                }
            }
        catch (InvalidPriceOperation e) {
            System.out.println("Something went wrong");
        }
    }
    }

    @Override
    public String toString() {
        return "Product: " + product + "\n" + buySide.toString() + sellSide.toString();
    }


    private boolean isValidProduct(String product) {
        return product.matches("[A-Z0-9.]{1,5}");
    }

    private void updateMarket(){
         CurrentMarketTracker currentMarketTracker = CurrentMarketTracker.getInstance();
         currentMarketTracker.updateMarket(product , buySide.topOfBookPrice(), buySide.topOfBookVolume(), sellSide.topOfBookPrice(), sellSide.topOfBookVolume());
    }
}
