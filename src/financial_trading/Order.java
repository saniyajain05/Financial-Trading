package financial_trading;

public class Order {

        private  String user ;
        private  String product;
        private  Price price;
        private  BookSide side;
        private  String id;
        private  int originalVolume;
        private int remainingVolume;
        private int cancelledVolume;
        private int filledVolume;

        public Order(String user, String product, Price price,  int originalVolume,BookSide side) {

            try {
                setUser(user);

            setProduct(product);
            setPrice(price);
            setSide(side);
            setID();
            setOriginalVolume(originalVolume);
            } catch (DataValidationException e) {
                throw new RuntimeException(e);
            }
            this.remainingVolume = originalVolume;
            this.cancelledVolume = 0;
            this.filledVolume = 0;
        }

    private void setUser(String user) throws DataValidationException{
        if (user != null && user.matches("^[A-Z]{3}$")) {
            this.user = user;
        } else {
            throw new DataValidationException("Invalid data");
        }
    }

    private void setProduct(String product) throws DataValidationException{
        if (product != null && product.matches("^[A-Z0-9.]{1,5}$")) {
            this.product = product;
        } else {
            throw new DataValidationException("Invalid data");
        }
    }

    private void setPrice(Price price) throws DataValidationException{
        if (price != null) {
            this.price = price;
        } else {
            throw new DataValidationException("Invalid data");
        }
    }

    private void setSide(BookSide side) throws DataValidationException{
        if (side != null) {
            this.side = side;
        } else {
            throw new DataValidationException("Invalid data");
        }
    }
        private void setID(){
            long nanotime = System.nanoTime();
            this.id =  user + product + price.toString() + nanotime;
        }

    private void setOriginalVolume(int originalVolume) throws DataValidationException{
        if (originalVolume > 0 && originalVolume < 10000) {
            this.originalVolume = originalVolume;
        } else {
            throw new DataValidationException("Invalid data");
        }
    }

    public void setRemainingVolume(int remainingVolume) {
        if (remainingVolume < 0) {
            System.out.println("Invalid side.");
            this.remainingVolume = 0;
        }
        this.remainingVolume = remainingVolume;
    }

    public void setCancelledVolume(int cancelledVolume) {
        if (cancelledVolume < 0) {
            System.out.println("Invalid side.");
            this.cancelledVolume = 0;
        }
        this.cancelledVolume = cancelledVolume;
    }

    public void setFilledVolume(int filledVolume) {
        if (filledVolume < 0) {
            System.out.println("Invalid side.");
            this.filledVolume = 0;
        }
        this.filledVolume = filledVolume;
    }

        public String getUser() {
            return user;
        }

        public String getProduct() {
            return product;
        }

        public Price getPrice() {
            return price;
        }

        public BookSide getSide() {
            return side;
        }

        public String getId() {
            return id;
        }

        public int getOriginalVolume() {
            return originalVolume;
        }

        public int getRemainingVolume() {
            return remainingVolume;
        }

        public int getCancelledVolume() {
            return cancelledVolume;
        }

        public int getFilledVolume() {
            return filledVolume;
        }



        @Override
        public String toString() {
            return user + " order: " + side + " " + product + " at " + price + ", Orig Vol: " + originalVolume +
                    ", Rem Vol: " + remainingVolume + ", Fill Vol: " + filledVolume + ", CXL Vol: " + cancelledVolume +
                    ", ID: " + id;
        }

        public OrderDTO makeTradableDTO() {
            return new OrderDTO( user,  product,  price,  side,  id,
             originalVolume,  remainingVolume,  cancelledVolume,  filledVolume);
        }


}
