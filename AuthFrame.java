import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {
    JPanel loginPanel, registerPanel, forgotPanel, securityPanel, resetPanel, dashboardPanel;
    JButton loginTab, registerTab;
    public String tempEmail = "";

    public AuthFrame() {
        setTitle("User Authentication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Wider for dashboard layout
        setLocationRelativeTo(null);
        setLayout(null);

        loginTab = new JButton("Login");
        registerTab = new JButton("Registration");
        loginTab.setBounds(0, 0, 400, 40);
        registerTab.setBounds(400, 0, 400, 40);

        loginTab.addActionListener(e -> showLoginPanel());
        registerTab.addActionListener(e -> showRegisterPanel());

        add(loginTab);
        add(registerTab);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegistrationPanel(this);
        forgotPanel = new ForgotPasswordPanel(this);
        securityPanel = new SecurityQuestionPanel(this);
        resetPanel = new ResetPasswordPanel(this);
        dashboardPanel = new DashboardPanel(this);

        add(loginPanel);
        add(registerPanel);
        add(forgotPanel);
        add(securityPanel);
        add(resetPanel);
        add(dashboardPanel);

        showLoginPanel();
    }

    private void hideAll() {
        loginPanel.setVisible(false);
        registerPanel.setVisible(false);
        forgotPanel.setVisible(false);
        securityPanel.setVisible(false);
        resetPanel.setVisible(false);
        dashboardPanel.setVisible(false);
        loginTab.setVisible(true);
        registerTab.setVisible(true);
    }

    public void showLoginPanel() {
        hideAll();
        loginPanel.setVisible(true);
        loginTab.setBackground(Color.WHITE);
        registerTab.setBackground(Color.LIGHT_GRAY);
    }

    public void showRegisterPanel() {
        hideAll();
        registerPanel.setVisible(true);
        loginTab.setBackground(Color.LIGHT_GRAY);
        registerTab.setBackground(Color.WHITE);
    }

    public void showForgotPanel() {
        hideAll();
        forgotPanel.setVisible(true);
    }

    public void showSecurityQuestionPanel() {
        hideAll();
        securityPanel.setVisible(true);
    }

    public void showResetPasswordPanel() {
        hideAll();
        resetPanel.setVisible(true);
    }

    public void showDashboardPanel() {
        hideAll();
        dashboardPanel.setVisible(true);
        loginTab.setVisible(false);
        registerTab.setVisible(false);
    }
}