import java.util.HashMap;
import java.util.ArrayList;

public class UserStore {
    public static class User {
        public String name;
        public String email;
        public String password;
        public String question;
        public String answer;

        public double balance;
        public int cwlScore;
        public ArrayList<String> activity;

        // Optional fields for dashboard and future use
        public String username = "";
        public String phoneNumber = "";
        public String address = "";
        public String annualBenefits = "$12,000";

        public User(String name, String email, String password, String question, String answer) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.question = question;
            this.answer = answer;

            // Default financial values
            this.balance = 5000.0;
            this.cwlScore = 750;
            this.activity = new ArrayList<>();
        }
    }

    private static final HashMap<String, User> users = new HashMap<>();

    // Register a new user
    public static boolean register(String name, String email, String password, String question, String answer) {
        if (users.containsKey(email)) return false;
        users.put(email, new User(name, email, password, question, answer));
        return true;
    }

    // Login validation
    public static boolean login(String email, String password) {
        User user = users.get(email);
        return user != null && user.password.equals(password);
    }

    // Check if user exists
    public static boolean exists(String email) {
        return users.containsKey(email);
    }

    // Verify security question and answer
    public static boolean verifySecurity(String email, String question, String answer) {
        User user = users.get(email);
        return user != null && user.question.equals(question) && user.answer.equalsIgnoreCase(answer.trim());
    }

    // Reset password
    public static boolean resetPassword(String email, String newPassword) {
        User user = users.get(email);
        if (user != null) {
            user.password = newPassword;
            return true;
        }
        return false;
    }

    // Get user object (used by DashboardPanel)
    public static User getUser(String email) {
        return users.get(email);
    }
}