package financial_trading;

class InvalidPriceOperation extends Exception {
    public InvalidPriceOperation(String message) {
        super(message);
    }
}