package financial_trading;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.



import static Assignment2.BookSide.*;

public class Main {
    public static void main(String[] args) {


        try {
            TrafficSim. runSim();
        } catch (DataValidationException | OrderNotFoundException e) {
            System.out.println("Unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();}

        /*ProductBook pb = new ProductBook("TGT");

        try {
            System.out.println("1) --------------------------------------------------------------");
            Order o1 = new Order("AAA", "TGT", PriceFactory.makePrice(1000), 50, BUY);
            pb.add(o1);
            System.out.println(pb);
            System.out.println("2) --------------------------------------------------------------");
            Order o2 = new Order("BBB", "TGT", PriceFactory.makePrice(1000), 60, BUY);
            pb.add(o2);
            System.out.println(pb);
            System.out.println("3) --------------------------------------------------------------");
            Order o3 = new Order("CCC", "TGT", PriceFactory.makePrice(995), 70, BUY);
            pb.add(o3);
            System.out.println(pb);
            System.out.println("4) --------------------------------------------------------------");
            Order o4 = new Order("DDD", "TGT", PriceFactory.makePrice(990), 25, BUY);
            pb.add(o4);
            System.out.println(pb);
            System.out.println("5) --------------------------------------------------------------");
            Order o5 = new Order("EEE", "TGT", PriceFactory.makePrice(1010), 120, SELL);
            pb.add(o5);
            System.out.println(pb);
            System.out.println("6) --------------------------------------------------------------");
            Order o6 = new Order("EEE", "TGT", PriceFactory.makePrice(1020), 45, SELL);
            pb.add(o6);
            System.out.println(pb);
            System.out.println("7) --------------------------------------------------------------");
            Order o7 = new Order("FFF", "TGT", PriceFactory.makePrice(1025), 90, SELL);
            pb.add(o7);
            System.out.println(pb);
            System.out.println("8) --------------------------------------------------------------");
            Order o8 = new Order("AAA", "TGT", PriceFactory.makePrice(1000), 200, SELL);
            pb.add(o8);
            System.out.println(pb);
            System.out.println("9) --------------------------------------------------------------");
            Order o9 = new Order("BBB", "TGT", PriceFactory.makePrice(1010), 200, BUY);
            pb.add(o9);
            System.out.println(pb);
            System.out.println("10) --------------------------------------------------------------");
            pb.cancel(SELL, o6.getId());
            System.out.println(pb);
            System.out.println("11) --------------------------------------------------------------");
            Order o10 = new Order("CCC", "TGT", PriceFactory.makePrice(990), 95, SELL);
            pb.add(o10);
            System.out.println(pb);
            System.out.println("12) --------------------------------------------------------------"); Order o11 = new Order("DDD", "TGT", PriceFactory.makePrice(1025), 100, BUY);
            pb.add(o11);
            System.out.println(pb);
    } catch (DataValidationException | OrderNotFoundException e) {
        System.out.println("Unexpected exception occurred: " + e.getMessage());
        e.printStackTrace();}*/



    }
}





