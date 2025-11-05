import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame {

    JLabel lblTitle;
    Timer timer;

    public WelcomePage() {
        // Frame setup
        setTitle("Student Management System - Welcome");
        setSize(700, 750);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background color
       

        // Title label
        lblTitle = new JLabel("Welcome to Student Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(30, 150, 640, 50);
        add(lblTitle);

        // Timer (5 seconds delay)
        timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // close welcome window
                new LoginPage(); // open login page
            }
        });
        timer.setRepeats(false);
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomePage();
    }
}
