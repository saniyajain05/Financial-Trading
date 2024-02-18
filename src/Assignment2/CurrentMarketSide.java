package Assignment2;

public class CurrentMarketSide {

    private Price price;
    private int volume;

    public CurrentMarketSide(Price price, int volume) {
        this.price = price;
        this.volume = volume;
    }

    public String toString() {
        return price.toString() + "x" + volume;
    }
}
