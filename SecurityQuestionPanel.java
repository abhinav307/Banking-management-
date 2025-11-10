import javax.swing.*;
import java.awt.*;

public class SecurityQuestionPanel extends JPanel {
    public SecurityQuestionPanel(AuthFrame parent) {
        setLayout(null);
        setBounds(0, 40, 400, 460);

        JLabel questionLabel = new JLabel("Select your security question");
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
        JButton verifyButton = new JButton("Verify");

        questionLabel.setBounds(50, 80, 300, 20);
        questionBox.setBounds(50, 105, 300, 30);
        answerLabel.setBounds(50, 150, 300, 20);
        answerField.setBounds(50, 175, 300, 30);
        verifyButton.setBounds(50, 230, 300, 40);

        verifyButton.setBackground(new Color(59, 89, 152));
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setFocusPainted(false);

        verifyButton.addActionListener(e -> {
            String question = (String) questionBox.getSelectedItem();
            String answer = answerField.getText().trim();
            if (UserStore.verifySecurity(parent.tempEmail, question, answer)) {
                JOptionPane.showMessageDialog(parent, "Security answer verified");
                parent.showResetPasswordPanel();
            } else {
                JOptionPane.showMessageDialog(parent, "Incorrect answer");
            }
        });

        add(questionLabel);
        add(questionBox);
        add(answerLabel);
        add(answerField);
        add(verifyButton);
    }
}