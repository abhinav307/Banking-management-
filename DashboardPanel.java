import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardPanel extends JPanel {
    private AuthFrame parent;
    private JLabel nameLabel, emailLabel, balanceLabel, cwlLabel;
    private JTextArea activityArea;
    private JPanel homePanel, loanPanel;

    public DashboardPanel(AuthFrame parent) {
        this.parent = parent;
        setLayout(null);
        setBounds(0, 0, 800, 600);

        // Sidebar
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(new Color(30, 45, 60));
        sidebar.setBounds(0, 0, 200, 600);

        JLabel profilePic = new JLabel("👤", SwingConstants.CENTER);
        profilePic.setFont(new Font("Arial", Font.PLAIN, 40));
        profilePic.setBounds(50, 30, 100, 60);

        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(25, 100, 150, 20);

        emailLabel = new JLabel("", SwingConstants.CENTER);
        emailLabel.setForeground(Color.LIGHT_GRAY);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        emailLabel.setBounds(10, 120, 180, 20);

        String[] menuItems = {
            "Home", "Current Balance", "Withdraw", "Deposit", "Loan Eligibility", "CWL Editor"
        };

        int y = 160;
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setBounds(20, y, 160, 30);
            btn.setFocusPainted(false);
            btn.setActionCommand(item);
            btn.addActionListener(e -> handleMenuClick(e.getActionCommand()));
            sidebar.add(btn);
            y += 40;
        }

        sidebar.add(profilePic);
        sidebar.add(nameLabel);
        sidebar.add(emailLabel);
        add(sidebar);

        // Home Panel
        homePanel = new JPanel(null);
        homePanel.setBounds(200, 0, 600, 600);
        add(homePanel);

        JLabel greeting = new JLabel("", SwingConstants.LEFT);
        greeting.setFont(new Font("Arial", Font.BOLD, 18));
        greeting.setBounds(20, 20, 400, 30);
        homePanel.add(greeting);

        JPanel personalPanel = new JPanel(null);
        personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        personalPanel.setBounds(20, 70, 250, 100);
        JLabel fullNameLabel = label("", 120, 20);
        JLabel emailValueLabel = label("", 120, 50);
        personalPanel.add(label("Full Name:", 10, 20));
        personalPanel.add(fullNameLabel);
        personalPanel.add(label("Email:", 10, 50));
        personalPanel.add(emailValueLabel);
        homePanel.add(personalPanel);

        JPanel financialPanel = new JPanel(null);
        financialPanel.setBorder(BorderFactory.createTitledBorder("Financial Overview"));
        financialPanel.setBounds(300, 70, 250, 100);
        balanceLabel = label("$0.00", 140, 20);
        cwlLabel = label("0/850", 140, 50);
        financialPanel.add(label("Current Balance:", 10, 20));
        financialPanel.add(balanceLabel);
        financialPanel.add(label("Credit Score (CIBIL):", 10, 50));
        financialPanel.add(cwlLabel);
        homePanel.add(financialPanel);

        JPanel activityPanel = new JPanel(null);
        activityPanel.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        activityPanel.setBounds(20, 180, 530, 150);
        activityArea = new JTextArea();
        activityArea.setEditable(false);
        activityArea.setBounds(10, 20, 510, 120);
        activityPanel.add(activityArea);
        homePanel.add(activityPanel);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(500, 20, 90, 30);
        logoutBtn.addActionListener(e -> {
            parent.tempEmail = "";
            parent.showLoginPanel();
        });
        homePanel.add(logoutBtn);

        // Loan Panel (initially hidden)
        loanPanel = new JPanel(null);
        loanPanel.setBounds(200, 0, 600, 600);
        loanPanel.setVisible(false);
        add(loanPanel);

        JLabel loanTitle = new JLabel("Loan Eligibility", SwingConstants.CENTER);
        loanTitle.setFont(new Font("Arial", Font.BOLD, 20));
        loanTitle.setBounds(0, 20, 600, 30);
        loanPanel.add(loanTitle);

        JTextPane loanArea = new JTextPane();
        loanArea.setContentType("text/html");
        loanArea.setEditable(false);
        loanArea.setFont(new Font("Arial", Font.PLAIN, 14));
        loanArea.setBounds(50, 70, 500, 400);
        loanPanel.add(loanArea);

        // Refresh on show
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                UserStore.User user = UserStore.getUser(parent.tempEmail);
                if (user != null) {
                    greeting.setText("Welcome, " + user.name + "!");
                    nameLabel.setText(user.name.toUpperCase());
                    emailLabel.setText(user.email);
                    fullNameLabel.setText(user.name);
                    emailValueLabel.setText(user.email);
                    balanceLabel.setText("$" + user.balance);
                    cwlLabel.setText(user.cwlScore + "/850");

                    if (user.activity == null || user.activity.isEmpty()) {
                        activityArea.setText("No recent activity.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (String entry : user.activity) {
                            sb.append("- ").append(entry).append("\n");
                        }
                        activityArea.setText(sb.toString());
                    }

                    // Update loan panel
                    int score = user.cwlScore;
                    StringBuilder eligible = new StringBuilder("<b>You are eligible for:</b><br>");
                    StringBuilder notEligible = new StringBuilder("<b>You are not eligible for:</b><br>");

                    if (score >= 750) {
                        eligible.append("- Personal Loan<br>- Credit Card<br>- Business Loan<br>- Education Loan<br>- Loan Against Property (LAP)<br>- Home Loan<br>- Car / Two-Wheeler Loan<br>- Gold Loan<br>");
                    } else if (score >= 700) {
                        eligible.append("- Home Loan<br>- Car / Two-Wheeler Loan<br>- Gold Loan<br>");
                        notEligible.append("- Personal Loan<br>- Credit Card<br>- Business Loan<br>- Education Loan<br>- Loan Against Property (LAP)<br>");
                    } else if (score >= 650) {
                        eligible.append("- Home Loan<br>- Gold Loan<br>");
                        notEligible.append("- Personal Loan<br>- Credit Card<br>- Business Loan<br>- Education Loan<br>- Loan Against Property (LAP)<br>- Car / Two-Wheeler Loan<br>");
                    } else if (score >= 300) {
                        eligible.append("- Gold Loan<br>");
                        notEligible.append("- Personal Loan<br>- Credit Card<br>- Business Loan<br>- Education Loan<br>- Loan Against Property (LAP)<br>- Home Loan<br>- Car / Two-Wheeler Loan<br>");
                    } else {
                        notEligible.append("- All loan types<br>");
                    }

                    loanArea.setText(eligible.toString() + "<br>" + notEligible.toString());
                }
            }
        });
    }

    private JLabel label(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 20);
        return lbl;
    }

    private void handleMenuClick(String command) {
        UserStore.User user = UserStore.getUser(parent.tempEmail);
        switch (command) {
            case "Home":
                homePanel.setVisible(true);
                loanPanel.setVisible(false);
                break;
            case "Loan Eligibility":
                homePanel.setVisible(false);
                loanPanel.setVisible(true);
                break;
            case "Current Balance":
                JOptionPane.showMessageDialog(this, "Your current balance is $" + user.balance);
                break;
            case "Withdraw":
                String withdrawStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(withdrawStr);
                    if (amount > 0 && amount <= user.balance) {
                        user.balance -= amount;
                        user.activity.add("Withdrawn $" + amount);
                        JOptionPane.showMessageDialog(this, "Withdrawn $" + amount);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient Funds.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                }
                break;
            case "Deposit":
                String depositStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(depositStr);
                    if (amount > 0) {
                        user.balance += amount;
                        user.activity.add("Deposited $" + amount);
                        JOptionPane.showMessageDialog(this, "Deposited $" + amount);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                }
                                break;
            case "CWL Editor":
                String newScoreStr = JOptionPane.showInputDialog(this, "Enter new CWL score (300–850):");
                try {
                    int newScore = Integer.parseInt(newScoreStr);
                    if (newScore >= 300 && newScore <= 850) {
                        user.cwlScore = newScore;
                        JOptionPane.showMessageDialog(this, "CWL score updated to " + newScore);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Score must be between 300 and 850.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                }
                break;
        }
    }

    public void refresh() {
        setVisible(false);
        setVisible(true);
    }
}
               