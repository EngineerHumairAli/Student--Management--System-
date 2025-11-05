import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DeleteStudentPage extends JFrame implements ActionListener {

    JTextField txtRoll;
    JButton btnDelete, btnBack;
    static Stack<String> studentStack;

    public DeleteStudentPage(Stack<String> stack) {
        studentStack = stack;

        setTitle("Delete Student - Student Management System");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 230, 230));

        JLabel lblTitle = new JLabel("Delete Student Record", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
        lblTitle.setBounds(150, 30, 400, 40);
        add(lblTitle);

        JLabel lblRoll = new JLabel("Enter Roll No to Delete:");
        lblRoll.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblRoll.setBounds(120, 120, 220, 30);
        add(lblRoll);

        txtRoll = new JTextField();
        txtRoll.setBounds(350, 120, 200, 30);
        add(txtRoll);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnDelete.setBounds(200, 220, 120, 40);
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setBounds(360, 220, 120, 40);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            String roll = txtRoll.getText().trim();

            if (roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Roll Number!");
                return;
            }

            boolean found = false;
            Stack<String> tempStack = new Stack<>();

            // Search and remove record from stack
            while (!studentStack.isEmpty()) {
                String record = studentStack.pop();
                String[] parts = record.split(",");
                if (parts.length >= 1 && parts[0].equalsIgnoreCase(roll)) {
                    found = true;
                    continue; // skip the record to delete
                }
                tempStack.push(record);
            }

            // Restore remaining records
            while (!tempStack.isEmpty()) {
                studentStack.push(tempStack.pop());
            }

            // Rewrite file without deleted record
            try (FileWriter fw = new FileWriter("students.txt")) {
                for (String rec : studentStack) {
                    fw.write(rec + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Record Deleted Successfully!");
                txtRoll.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Record not found!");
            }

        } else if (e.getSource() == btnBack) {
            dispose();
            new DashboardPage();
        }
    }
}
