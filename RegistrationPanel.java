import javax.swing.*;
import java.awt.*;

public class RegistrationPanel extends JPanel {
    public RegistrationPanel(AuthFrame parent) {
        setLayout(null);
        setBounds(0, 40, 400, 520);

        JLabel nameLabel = new JLabel("Full Name");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email Address");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("Password");
        JPasswordField passField = new JPasswordField();
        JLabel confirmLabel = new JLabel("Confirm Password");
        JPasswordField confirmField = new JPasswordField();

        JLabel securityLabel = new JLabel("Security Question");
        String[] questions = {
            "When is your birthday?",
            "What is your best friend's name?",
            "What is your favourite color?",
            "What is your favourite country?",
            "What was your first school?",
            "What is your father's name?",
            "What is your mother's name?",
            "What is your girlfriend's number?",
            "What is your ex's number?"
        };
        JComboBox<String> questionBox = new JComboBox<>(questions);
        JLabel answerLabel = new JLabel("Your Answer");
        JTextField answerField = new JTextField();

        JButton registerButton = new JButton("Registration");
        JLabel prompt = new JLabel("Already have an account?");
        JButton loginNow = new JButton("Login now");

        // Positioning
        nameLabel.setBounds(50, 30, 300, 20);
        nameField.setBounds(50, 55, 300, 30);
        emailLabel.setBounds(50, 95, 300, 20);
        emailField.setBounds(50, 120, 300, 30);
        passLabel.setBounds(50, 160, 300, 20);
        passField.setBounds(50, 185, 300, 30);
        confirmLabel.setBounds(50, 225, 300, 20);
        confirmField.setBounds(50, 250, 300, 30);
        securityLabel.setBounds(50, 290, 300, 20);
        questionBox.setBounds(50, 315, 300, 30);
        answerLabel.setBounds(50, 355, 300, 20);
        answerField.setBounds(50, 380, 300, 30);
        registerButton.setBounds(50, 430, 300, 40);
        prompt.setBounds(90, 480, 160, 20);
        loginNow.setBounds(250, 475, 100, 30);

        // Styling
        loginNow.setBorderPainted(false);
        loginNow.setContentAreaFilled(false);
        loginNow.setForeground(Color.BLUE);
        loginNow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginNow.addActionListener(e -> parent.showLoginPanel());

        registerButton.setBackground(new Color(59, 89, 152));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        // Registration logic
        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String pass1 = new String(passField.getPassword());
            String pass2 = new String(confirmField.getPassword());
            String question = (String) questionBox.getSelectedItem();
            String answer = answerField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Please fill all fields.");
            } else if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(parent, "Passwords do not match.");
            } else if (!isStrong(pass1)) {
                JOptionPane.showMessageDialog(parent, "Password must be at least 6 characters and include a number.");
            } else {
                boolean success = UserStore.register(name, email, pass1, question, answer);
                if (success) {
                    JOptionPane.showMessageDialog(parent, "Registration successful!\nPlease go to Login to sign in.");
                    parent.showLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(parent, "Email already registered.");
                }
            }
        });

        // Add components
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passField);
        add(confirmLabel);
        add(confirmField);
        add(securityLabel);
        add(questionBox);
        add(answerLabel);
        add(answerField);
        add(registerButton);
        add(prompt);
        add(loginNow);
    }

    private boolean isStrong(String password) {
        return password.length() >= 6 && password.matches(".*\\d.*");
    }
}