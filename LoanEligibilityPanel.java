import javax.swing.*;

public class LoanEligibilityPanel extends JPanel {
    public LoanEligibilityPanel(UserStore.User user) {
        setLayout(null);
        setBounds(200, 0, 600, 600); // Right side panel

        JLabel title = new JLabel("Loan Eligibility", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(0, 20, 600, 30);
        add(title);

        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setBounds(50, 70, 500, 400);
        result.setFont(new Font("Arial", Font.PLAIN, 14));

        int score = user.cwlScore;
        StringBuilder eligible = new StringBuilder("✅ You are eligible for:\n");
        StringBuilder notEligible = new StringBuilder("❌ You are not eligible for:\n");

        if (score >= 750) {
            eligible.append("- Personal Loan\n- Credit Card\n- Business Loan\n- Education Loan\n- Loan Against Property (LAP)\n- Home Loan\n- Car / Two-Wheeler Loan\n- Gold Loan\n");
        } else if (score >= 700) {
            eligible.append("- Home Loan\n- Car / Two-Wheeler Loan\n- Gold Loan\n");
            notEligible.append("- Personal Loan\n- Credit Card\n- Business Loan\n- Education Loan\n- Loan Against Property (LAP)\n");
        } else if (score >= 650) {
            eligible.append("- Home Loan\n- Gold Loan\n");
            notEligible.append("- Personal Loan\n- Credit Card\n- Business Loan\n- Education Loan\n- Loan Against Property (LAP)\n- Car / Two-Wheeler Loan\n");
        } else if (score >= 300) {
            eligible.append("- Gold Loan\n");
            notEligible.append("- Personal Loan\n- Credit Card\n- Business Loan\n- Education Loan\n- Loan Against Property (LAP)\n- Home Loan\n- Car / Two-Wheeler Loan\n");
        } else {
            notEligible.append("- All loan types\n");
        }

        result.setText(eligible.toString() + "\n" + notEligible.toString());
        add(result);
    }
}