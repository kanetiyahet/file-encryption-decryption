import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileEncryptorGUI extends JFrame {

    JTextField fileField;
    JPasswordField keyField;

    public FileEncryptorGUI() {

        setTitle("Encryption Tool");
        setSize(500,250);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        fileField = new JTextField(25);
        JButton browse = new JButton("Browse");

        keyField = new JPasswordField(16);

        JButton enc = new JButton("Encrypt");
        JButton dec = new JButton("Decrypt");

        add(fileField);
        add(browse);
        add(keyField);
        add(enc);
        add(dec);

        browse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
                fileField.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });

        enc.addActionListener(e -> {
            try {
                AESUtil.encrypt(new String(keyField.getPassword()),
                        new File(fileField.getText()));
                JOptionPane.showMessageDialog(this,"Encrypted!");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        });

        dec.addActionListener(e -> {
            try {
                AESUtil.decrypt(new String(keyField.getPassword()),
                        new File(fileField.getText()));
                JOptionPane.showMessageDialog(this,"Decrypted!");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        });
    }
}
