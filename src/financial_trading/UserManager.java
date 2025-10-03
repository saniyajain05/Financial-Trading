package financial_trading;

import java.util.HashMap;
import java.util.Random;

public class UserManager {
    private static UserManager instance;
    private HashMap<String, User> users;

    private UserManager() {
        users = new HashMap<String, User>();
    }


    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }


    public void init(String[] usersIn) {
        for (String userId : usersIn) {
            users.put(userId, new User(userId));
        }
    }


    public User getRandomUser() {
        if (users.isEmpty()) {
            return null;  
        }
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        return users.values().toArray(new User[0])[randomIndex];
    }


    public void addToUser(String userId, OrderDTO order) {
        User user = users.get(userId);
        if (user != null) {
            user.addOrder(order);
        }
    }


    @Override
    public String toString() {

            String returnString = "";
            for (User user : users.values()) {
                returnString += user.toString() + "\n";
            }
            return returnString;
        }

    public User getUser(String id){
        return users.get(id);
    }

}
