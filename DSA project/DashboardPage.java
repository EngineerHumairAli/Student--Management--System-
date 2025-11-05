import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardPage extends JFrame implements ActionListener {

    JButton btnAdd, btnView, btnUpdate, btnDelete, btnLogout;

    public DashboardPage() {
        setTitle("Dashboard - Student Management System");
        setSize(700, 750);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel lblTitle = new JLabel("Student Management Dashboard", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
        lblTitle.setBounds(50, 50, 600, 50);
        add(lblTitle);

        // Buttons
        btnAdd = new JButton("Add Student");
        btnAdd.setBounds(250, 150, 200, 40);
        btnAdd.addActionListener(this);
        add(btnAdd);

        btnView = new JButton("View Students");
        btnView.setBounds(250, 220, 200, 40);
        btnView.addActionListener(this);
        add(btnView);

        btnUpdate = new JButton("Update Student");
        btnUpdate.setBounds(250, 290, 200, 40);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnDelete = new JButton("Delete Student");
        btnDelete.setBounds(250, 360, 200, 40);
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(250, 430, 200, 40);
        btnLogout.addActionListener(this);
        add(btnLogout);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddStudentPage();
        } else if (e.getSource() == btnView) {
            new ViewStudentPage(AddStudentPage.studentStack);
        } else if (e.getSource() == btnUpdate) {
           new UpdateStudentPage(AddStudentPage.studentStack);

        } else if (e.getSource() == btnDelete) {
             new DeleteStudentPage(AddStudentPage.studentStack);

        } else if (e.getSource() == btnLogout) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            dispose();
            new LoginPage();
        }
    }
}
