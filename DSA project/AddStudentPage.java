import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AddStudentPage extends JFrame implements ActionListener {

    JTextField txtName, txtFather, txtRoll, txtDept, txtSemester, txtGpa;
    JButton btnSave, btnBack;

    static Stack<String> studentStack = new Stack<>();

    public AddStudentPage() {
        setTitle("Add Student - Student Management System");
        setSize(700, 750);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 240, 250));

        JLabel lblTitle = new JLabel("Add New Student", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
        lblTitle.setBounds(150, 40, 400, 40);
        add(lblTitle);

        int y = 120; // starting y position
        int gap = 50;

        addLabel("Full Name:", 100, y); 
        txtName = addTextField(300, y); 
        y += gap;

        addLabel("Father's Name:", 100, y); 
        txtFather = addTextField(300, y); 
        y += gap;

        addLabel("Roll No:", 100, y); 
        txtRoll = addTextField(300, y); 
        y += gap;

        addLabel("Department:", 100, y); 
        txtDept = addTextField(300, y); 
        y += gap;

        addLabel("Semester:", 100, y); 
        txtSemester = addTextField(300, y); 
        y += gap;

        addLabel("Last Semester GPA:", 100, y); 
        txtGpa = addTextField(300, y); 
        y += gap;

        // Buttons
        btnSave = new JButton("Save");
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnSave.setBounds(200, 550, 120, 40);
        btnSave.addActionListener(this);
        add(btnSave);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setBounds(360, 550, 120, 40);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lbl.setBounds(x, y, 180, 30);
        add(lbl);
    }

    private JTextField addTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 250, 30);
        add(txt);
        return txt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String name = txtName.getText().trim();
            String father = txtFather.getText().trim();
            String roll = txtRoll.getText().trim();
            String dept = txtDept.getText().trim();
            String sem = txtSemester.getText().trim();
            String gpa = txtGpa.getText().trim();

            if (name.isEmpty() || roll.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }

            String record = roll + "," + name + "," + father + "," + dept + "," + sem + "," + gpa;
            studentStack.push(record);

            try (FileWriter fw = new FileWriter("students.txt", true)) {
                fw.write(record + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            clearFields();

        } else if (e.getSource() == btnBack) {
            dispose();
            new DashboardPage();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtFather.setText("");
        txtRoll.setText("");
        txtDept.setText("");
        txtSemester.setText("");
        txtGpa.setText("");
    }
}
