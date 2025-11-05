import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginPage() {
        setTitle("Login - Student Management System");
        setSize(700, 750);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Student Management Login");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
        lblTitle.setBounds(150, 100, 500, 50);
        add(lblTitle);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblUser.setBounds(180, 200, 100, 30);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(300, 200, 200, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblPass.setBounds(180, 250, 100, 30);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(300, 250, 200, 30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnLogin.setBounds(280, 320, 120, 35);
        btnLogin.addActionListener(this);
        add(btnLogin);

        setVisible(true);
    }

      @Override
    public void actionPerformed(ActionEvent e) {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (user.equalsIgnoreCase("admin") && pass.equals("12345")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose(); // close login page
            new DashboardPage(); // open dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
        }
    }
} // ← closes class LoginPage

