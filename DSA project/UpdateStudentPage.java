import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UpdateStudentPage extends JFrame implements ActionListener {

    JTextField txtRollSearch, txtName, txtFather, txtDept, txtSemester, txtGpa;
    JButton btnSearch, btnUpdate, btnBack;
    static Stack<String> studentStack;

    public UpdateStudentPage(Stack<String> stack) {
        studentStack = stack;

        setTitle("Update Student Record");
        setSize(700, 750);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 245, 255));

        JLabel lblTitle = new JLabel("Update Student Details", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
        lblTitle.setBounds(150, 40, 400, 40);
        add(lblTitle);

        int y = 120, gap = 50;

        addLabel("Enter Roll No to Search:", 100, y);
        txtRollSearch = addTextField(300, y);
        y += gap;

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnSearch.setBounds(300, y, 120, 35);
        btnSearch.addActionListener(this);
        add(btnSearch);
        y += gap + 10;

        addLabel("Full Name:", 100, y);
        txtName = addTextField(300, y);
        y += gap;

        addLabel("Father's Name:", 100, y);
        txtFather = addTextField(300, y);
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

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnUpdate.setBounds(200, 600, 120, 40);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnBack.setBounds(360, 600, 120, 40);
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
        if (e.getSource() == btnSearch) {
            String rollNo = txtRollSearch.getText().trim();
            boolean found = false;

            for (String record : studentStack) {
                String[] parts = record.split(",");
                if (parts.length == 6 && parts[0].equalsIgnoreCase(rollNo)) {
                    txtName.setText(parts[1]);
                    txtFather.setText(parts[2]);
                    txtDept.setText(parts[3]);
                    txtSemester.setText(parts[4]);
                    txtGpa.setText(parts[5]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Record not found!");
                clearFields();
            }

        } else if (e.getSource() == btnUpdate) {
            String rollNo = txtRollSearch.getText().trim();
            String newName = txtName.getText().trim();
            String newFather = txtFather.getText().trim();
            String newDept = txtDept.getText().trim();
            String newSem = txtSemester.getText().trim();
            String newGpa = txtGpa.getText().trim();

            if (rollNo.isEmpty() || newName.isEmpty() || newDept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill required fields!");
                return;
            }

            Stack<String> tempStack = new Stack<>();
            boolean updated = false;

            while (!studentStack.isEmpty()) {
                String record = studentStack.pop();
                String[] parts = record.split(",");

                if (parts.length == 6 && parts[0].equalsIgnoreCase(rollNo)) {
                    record = rollNo + "," + newName + "," + newFather + "," + newDept + "," + newSem + "," + newGpa;
                    updated = true;
                }

                tempStack.push(record);
            }

            // reverse stack back
            while (!tempStack.isEmpty()) {
                studentStack.push(tempStack.pop());
            }

            if (updated) {
                saveToFile();
                JOptionPane.showMessageDialog(this, "Record Updated Successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Roll No not found!");
            }

        } else if (e.getSource() == btnBack) {
            dispose();
            new DashboardPage();
        }
    }

    private void saveToFile() {
        try (FileWriter fw = new FileWriter("students.txt")) {
            for (String record : studentStack) {
                fw.write(record + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtFather.setText("");
        txtDept.setText("");
        txtSemester.setText("");
        txtGpa.setText("");
    }
}
