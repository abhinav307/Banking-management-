import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(AuthFrame parent) {
        setLayout(null);
        setBounds(0, 40, 400, 460);

        JLabel emailLabel = new JLabel("Email Address");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("Password");
        JPasswordField passField = new JPasswordField();
        JCheckBox rememberMe = new JCheckBox("Remember Me");
        JButton forgotPass = new JButton("Forgot Password?");
        JButton loginButton = new JButton("Login");
        JLabel prompt = new JLabel("Don't have an account?");
        JButton registerNow = new JButton("Registration now");

        emailLabel.setBounds(50, 30, 300, 20);
        emailField.setBounds(50, 55, 300, 30);
        passLabel.setBounds(50, 95, 300, 20);
        passField.setBounds(50, 120, 300, 30);
        rememberMe.setBounds(50, 155, 150, 20);
        forgotPass.setBounds(220, 155, 130, 20);
        loginButton.setBounds(50, 190, 300, 40);
        prompt.setBounds(90, 250, 160, 20);
        registerNow.setBounds(250, 245, 130, 30);

        forgotPass.setBorderPainted(false);
        forgotPass.setContentAreaFilled(false);
        forgotPass.setForeground(Color.BLUE);
        forgotPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPass.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPass.addActionListener(e -> parent.showForgotPanel());

        registerNow.setBorderPainted(false);
        registerNow.setContentAreaFilled(false);
        registerNow.setForeground(Color.BLUE);
        registerNow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerNow.addActionListener(e -> parent.showRegisterPanel());

        loginButton.setBackground(new Color(59, 89, 152));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passField.getPassword());

            if (UserStore.login(email, password)) {
                // store current email in frame so other panels can use it
                parent.tempEmail = email;
                JOptionPane.showMessageDialog(parent, "Login successful!");
                parent.showDashboardPanel();          // show dashboard

            } else {
                JOptionPane.showMessageDialog(parent, "Invalid credentials.");
            }
        });

        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passField);
        add(rememberMe);
        add(forgotPass);
        add(loginButton);
        add(prompt);
        add(registerNow);
    }
}