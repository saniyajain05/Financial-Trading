package Assignment2;

import java.util.HashMap;
import java.util.Random;

    public class TrafficSim {
        private static HashMap<String, Double> basePrices = new HashMap<String, Double>();
        private static Random random = new Random();
        private static ProductManager productManager = ProductManager.getInstance();

        public static void runSim() throws DataValidationException, OrderNotFoundException {
            UserManager userManager= UserManager.getInstance();
            String[] userIds = new String[]{"ANN", "BOB", "CAT", "DOG", "EGG"};
            userManager.init(userIds);

            User user1 = userManager.getUser("ANN");
            User user2 = userManager.getUser("BOB");
            User user3 = userManager.getUser("CAT");
            User user4 = userManager.getUser("DOG");
            User user5 = userManager.getUser("EGG");

            CurrentMarketPublisher currentMarketPublisher = CurrentMarketPublisher.getInstance();
            currentMarketPublisher.subscribeCurrentMarket("WMT",user1);
            currentMarketPublisher.subscribeCurrentMarket("TGT",user1);
            currentMarketPublisher.subscribeCurrentMarket("TGT",user2);
            currentMarketPublisher.subscribeCurrentMarket("TSLA",user2);
            currentMarketPublisher.subscribeCurrentMarket("AMZN",user3);
            currentMarketPublisher.subscribeCurrentMarket("TGT",user3);
            currentMarketPublisher.subscribeCurrentMarket("WMT",user3);
            currentMarketPublisher.subscribeCurrentMarket("TSLA",user4);
            currentMarketPublisher.subscribeCurrentMarket("WMT",user5);


            ProductManager productManager = ProductManager.getInstance();
            productManager.addProduct("WMT");
            productManager.addProduct("TGT");
            productManager.addProduct("AMZN");
            productManager.addProduct("TSLA");

            basePrices.put("WMT", 140.98);
            basePrices.put("TGT", 174.76);
            basePrices.put("AMZN", 102.11);
            basePrices.put("TSLA", 196.81);




            currentMarketPublisher.unSubscribeCurrentMarket("TGT", user2);


            for (int i = 0; i < 10000; i++){
                System.out.println((i + 1)+")");

                User randomUser = userManager.getRandomUser();
                if((Math.random() < 0.9)){
                    String randomProduct = productManager.getRandomProduct();

                    int randomIndex = random.nextInt(2);
                    BookSide[] sideList = new BookSide[]{BookSide.BUY,BookSide.SELL};
                    BookSide side = sideList[randomIndex];

                    int orderVolume = (int) (25 + (Math.random() * 300));

                    orderVolume = (int) Math.round(orderVolume / 5.0) * 5;

                    Price randomPrice = getPrice(randomProduct,side);

                    Order order = new Order(randomUser.getUserId(),randomProduct,randomPrice,orderVolume, side);

                    OrderDTO orderDTO = productManager.addOrder(order);

                    randomUser.addOrder(orderDTO);

                }
                else{
                    //System.out.println(randomUser.hasOrderWithRemainingQty());
                    if (randomUser.hasOrderWithRemainingQty()){

                        OrderDTO randomOrderDTO = randomUser.getOrderWithRemainingQty();

                        OrderDTO cancelledOrderDTO = productManager.cancel(randomOrderDTO);

                        if(cancelledOrderDTO != null){

                            randomUser.addOrder(cancelledOrderDTO);
                        }


                    }

                }

            }

            //System.out.println("ProductBooks: \n\n" + productManager.toString());
            //System.out.println("Users: \n\n" +userManager.toString());
            System.out.println("ANN" + ":\n" + user1.getCurrentMarkets());
            System.out.println("BOB" + ":\n" + user2.getCurrentMarkets());
            System.out.println("CAT" + ":\n" + user3.getCurrentMarkets());
            System.out.println("DOG" + ":\n" + user4.getCurrentMarkets());
            System.out.println("EGG" + ":\n" + user5.getCurrentMarkets());


            currentMarketPublisher.unSubscribeCurrentMarket("WMT",user1);
            currentMarketPublisher.unSubscribeCurrentMarket("TGT",user1);
            currentMarketPublisher.unSubscribeCurrentMarket("TSLA",user2);
            currentMarketPublisher.unSubscribeCurrentMarket("AMZN",user3);
            currentMarketPublisher.unSubscribeCurrentMarket("TGT",user3);
            currentMarketPublisher.unSubscribeCurrentMarket("WMT",user3);
            currentMarketPublisher.unSubscribeCurrentMarket("TSLA",user4);
            currentMarketPublisher.unSubscribeCurrentMarket("WMT",user5);


        }



        private static Price getPrice(String symbol, BookSide side) {

            double basePrice = basePrices.get(symbol);

            double priceWidth = 0.02; double startPoint = 0.01; double tickSize = 0.1;

            double gapFromBase = basePrice * priceWidth;
            double priceVariance = gapFromBase * (Math.random());

            //double priceToTick  = 0 ;


            if(side == BookSide.BUY) {
                double priceToUse = basePrice * (1 - startPoint);
                priceToUse += priceVariance;
                double priceToTick = Math.round(priceToUse * 1 / tickSize) / 10.0;
                priceToTick = priceToTick * 100;
                Price price = PriceFactory.makePrice((int)priceToTick);
                return price;
            }
            else{
                double priceToUse = basePrice * (1 + startPoint);
                priceToUse -= priceVariance;
                double priceToTick = Math.round(priceToUse * 1/ tickSize) / 10.0;
                priceToTick = priceToTick * 100;
                Price price = PriceFactory.makePrice((int)priceToTick);
                return price;
            }




        }


    }


