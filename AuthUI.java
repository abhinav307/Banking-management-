import javax.swing.*;

public class AuthUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthFrame frame = new AuthFrame();
            frame.setVisible(true);
        });
    }
}