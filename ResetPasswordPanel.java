import javax.swing.*;
import java.awt.*;

public class ResetPasswordPanel extends JPanel {
    public ResetPasswordPanel(AuthFrame parent) {
        setLayout(null);
        setBounds(0, 40, 400, 460);

        JLabel newPassLabel = new JLabel("Enter New Password");
        JPasswordField newPassField = new JPasswordField();
        JLabel confirmPassLabel = new JLabel("Re-enter Password");
        JPasswordField confirmPassField = new JPasswordField();
        JButton saveButton = new JButton("Save");

        newPassLabel.setBounds(50, 100, 300, 20);
        newPassField.setBounds(50, 125, 300, 30);
        confirmPassLabel.setBounds(50, 165, 300, 20);
        confirmPassField.setBounds(50, 190, 300, 30);
        saveButton.setBounds(50, 240, 300, 40);

        saveButton.setBackground(new Color(59, 89, 152));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        saveButton.addActionListener(e -> {
            String pass1 = new String(newPassField.getPassword());
            String pass2 = new String(confirmPassField.getPassword());

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(parent, "Passwords do not match");
            } else if (!isStrong(pass1)) {
                JOptionPane.showMessageDialog(parent, "Password must be at least 6 characters and include a number");
            } else {
                UserStore.resetPassword(parent.tempEmail, pass1);
                JOptionPane.showMessageDialog(parent, "Password reset successful");
                parent.showLoginPanel();
            }
        });

        add(newPassLabel);
        add(newPassField);
        add(confirmPassLabel);
        add(confirmPassField);
        add(saveButton);
    }

    private boolean isStrong(String password) {
        return password.length() >= 6 && password.matches(".*\\d.*");
    }
}