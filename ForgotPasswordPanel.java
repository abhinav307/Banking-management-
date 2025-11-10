import javax.swing.*;
import java.awt.*;

public class ForgotPasswordPanel extends JPanel {
    public ForgotPasswordPanel(AuthFrame parent) {
        setLayout(null);
        setBounds(0, 40, 400, 460);

        JLabel emailLabel = new JLabel("Enter your Email Address");
        JTextField emailField = new JTextField();
        JButton nextButton = new JButton("Next");

        emailLabel.setBounds(50, 100, 300, 20);
        emailField.setBounds(50, 125, 300, 30);
        nextButton.setBounds(50, 180, 300, 40);

        nextButton.setBackground(new Color(59, 89, 152));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);

        nextButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (UserStore.exists(email)) {
                parent.tempEmail = email;
                JOptionPane.showMessageDialog(parent, "Username found");
                parent.showSecurityQuestionPanel();
            } else {
                JOptionPane.showMessageDialog(parent, "Email not found");
            }
        });

        add(emailLabel);
        add(emailField);
        add(nextButton);
    }
}