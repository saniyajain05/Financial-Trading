package financial_trading;

import java.util.HashMap;
import java.util.Map;

public class PriceFactory {

    private static final Map<Integer, Price> priceMemory = new HashMap<>();

    public static Price makePrice(int value) {

        if (priceMemory.containsKey(value)) {

            return priceMemory.get(value);
        } else {

            Price price = new Price(value);
            priceMemory.put(value, price);
            return price;
        }
    }

    public static Price makePrice(String value) {

        int finalValue = 0;

        value = value.replaceAll("[$,\\s]+", "");

        if (value.contains(".")){
            value = value.replace(".","");
            finalValue = Integer.parseInt(value);
        }
        else {
            try{
                finalValue = Integer.parseInt(value) * 100;

            }catch (Exception e){
                System.out.println(e);
            }
            
        }
        if (priceMemory.containsKey(finalValue)) {
            return priceMemory.get(finalValue);
        } else {
            Price newPrice = new Price(finalValue);
            priceMemory.put(finalValue, newPrice);
            return newPrice;
        }
    }
}
