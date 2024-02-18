package Assignment2;

import java.util.HashMap;
import java.util.Random;

public class ProductManager {
    private static ProductManager instance;
    private final HashMap<String, ProductBook> productBooks;

    private ProductManager() {
        productBooks = new HashMap<>();
    }

    public static synchronized ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void addProduct(String symbol) {
        if (!productBooks.containsKey(symbol)) {
            productBooks.put(symbol, new ProductBook(symbol));
        }
    }

    public String getRandomProduct() {
        if (!productBooks.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(productBooks.size());
            return (String) productBooks.keySet().toArray()[index];
        }
        return null;
    }

    public OrderDTO addOrder(Order order) throws DataValidationException {
        String symbol = order.getProduct();
        if (productBooks.containsKey(symbol)) {
            ProductBook productBook = productBooks.get(symbol);
            return productBook.add(order);
        }
        return null;
    }

    public OrderDTO cancel(OrderDTO orderDTO) throws OrderNotFoundException {
        String symbol = orderDTO.product;
        if (productBooks.containsKey(symbol)) {
            ProductBook productBook = productBooks.get(symbol);
            return productBook.cancel(orderDTO.side, orderDTO.id);
        }
        System.out.println("Cancel failed.");
        return null;
    }

    @Override
    public String toString() {
        String returnString = "";

        for (ProductBook productBook : productBooks.values()) {
            returnString += productBook.toString()+ "\n";
        }

        return returnString;
    }
}
