import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginGUI extends JFrame {

    JTextField user;
    JPasswordField pass;

    public LoginGUI() {
        setTitle("Secure Login System");
        setSize(350,200);
        setLayout(new GridLayout(3,2,10,10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        user = new JTextField();
        pass = new JPasswordField();

        JButton login = new JButton("Login");

        add(new JLabel("Username:"));
        add(user);
        add(new JLabel("Password:"));
        add(pass);
        add(new JLabel(""));
        add(login);

        login.addActionListener(e -> {

            String username = user.getText();
            String password = new String(pass.getPassword());

            if(authenticate(username, password)) {

                JOptionPane.showMessageDialog(this,"Login Successful");

                new DashboardGUI().setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this,"Invalid Username or Password");
            }
        });
    }

    private boolean authenticate(String username, String password) {

        try {
            File file = new File("users.dat");

            if(!file.exists()) {
                FileWriter fw = new FileWriter(file);
                fw.write("admin:1234\n");
                fw.close();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null) {

                String[] parts = line.split(":");

                if(parts.length == 2) {
                    if(parts[0].equals(username) && parts[1].equals(password)) {
                        br.close();
                        return true;
                    }
                }
            }

            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
