import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class toDo extends JFrame {
    private JPanel checkBoxPanel;
    private JTextField taskTextField;

    private ArrayList<JCheckBox> checkBoxes;
    private ArrayList<String> toDoList;

    public toDo() {
        // Set up the frame
        setTitle("To-Do List Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the to-do list and checkboxes
        toDoList = new ArrayList<>();
        checkBoxes = new ArrayList<>();

        // Create components
        JPanel inputPanel = new JPanel();
        taskTextField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JScrollPane scrollPane = new JScrollPane();
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        // Add components to inputPanel
        inputPanel.add(taskTextField);
        inputPanel.add(addButton);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        scrollPane.setViewportView(checkBoxPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
    }

    private void addTask() {
        String task = taskTextField.getText().trim();
        if (!task.isEmpty()) {
            JCheckBox checkBox = new JCheckBox(task);
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeTask(checkBox);
                }
            });
            checkBoxes.add(checkBox);
            toDoList.add(task);
            updateToDoList();
            taskTextField.setText("");
        }
    }

    private void removeTask(JCheckBox checkBox) {
        String taskToRemove = checkBox.getText();
        checkBoxes.remove(checkBox);
        toDoList.remove(taskToRemove);
        updateToDoList();
    }

    private void updateToDoList() {
        checkBoxPanel.removeAll();
        for (JCheckBox checkBox : checkBoxes) {
            checkBoxPanel.add(checkBox);
        }
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new toDo().setVisible(true);
            }
        });
    }
}
