package financial_trading;

public class OrderDTO {
    public final String user;
    public final String product;
    public final Price price;
    public final BookSide side;
    public final String id;
    public final int originalVolume;
    public final int remainingVolume;
    public final int cancelledVolume;
    public final int filledVolume;

    public OrderDTO(String user, String product, Price price, BookSide side, String id,
                    int originalVolume, int remainingVolume, int cancelledVolume, int filledVolume) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.id = id;
        this.originalVolume = originalVolume;
        this.remainingVolume = remainingVolume;
        this.cancelledVolume = cancelledVolume;
        this.filledVolume = filledVolume;
    }

    // Override the toString method
    @Override
    public String toString() {
        return user + " order: " + side + " " + product + " at " + price + ", Orig Vol: " + originalVolume +
                ", Rem Vol: " + remainingVolume + ", Fill Vol: " + filledVolume + ", CXL Vol: " + cancelledVolume +
                ", ID: " + id;
    }
}

