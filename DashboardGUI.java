import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {
        setTitle("Secure Vault Dashboard");
        setSize(500,300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel title = new JLabel("🔐 Secure Vault System", JLabel.CENTER);

        JButton openEncrypt = new JButton("Open Encryption Tool");

        openEncrypt.addActionListener(e -> {
            new FileEncryptorGUI().setVisible(true);
        });

        add(title, BorderLayout.NORTH);
        add(openEncrypt, BorderLayout.CENTER);
    }
}
