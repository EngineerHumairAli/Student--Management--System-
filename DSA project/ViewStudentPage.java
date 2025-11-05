import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Stack;

public class ViewStudentPage extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewStudentPage(Stack<String> studentStack) {
        setTitle("View Students");
        setSize(700, 750);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table headings
        String[] columns = {"Roll No", "Name", "Father Name", "Department", "Semester", "Last GPA"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        table.setRowHeight(25);

        // Add data from stack
        for (String record : studentStack) {
            String[] parts = record.split(",");
            if (parts.length == 6) {
                model.addRow(parts);
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
