package ua.lviv.softserve.lv236.tasks.TasksGUI;

import ua.lviv.softserve.lv236.tasks.Main;
import ua.lviv.softserve.lv236.tasks.taskPack.GeneralTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kolja on 27.04.2017.
 */
public class MainWindow extends JFrame {

    private GeneralTask localTask;
    private static String dropDown;
    private JPanel generalPanel = new JPanel();
    private JComboBox taskList = new JComboBox(Main.getAllNames().toArray());
    private JTextArea descriptionField = new JTextArea();
    private JTextArea inputField = new JTextArea();
    private JTextArea resultField = new JTextArea();
    private JButton calculateButton = new JButton("Calculate");
    private JLabel selectTaskLabel = new JLabel("Select Task");
    private JLabel setInputLabel = new JLabel("Set Input");
    private JLabel resultLabel = new JLabel("Result");
    private JLabel descriptionLabel = new JLabel("Task Description");
    private JScrollPane resultScroll = new JScrollPane(resultField);

    public MainWindow() {

        //sizing
        selectTaskLabel.setPreferredSize(new Dimension(500, 20));
        taskList.setPreferredSize(new Dimension(500, 20));
        descriptionLabel.setPreferredSize(new Dimension(500, 30));
        descriptionField.setPreferredSize(new Dimension(500, 80));
        setInputLabel.setPreferredSize(new Dimension(500, 20));
        inputField.setPreferredSize(new Dimension(500, 40));
        calculateButton.setPreferredSize(new Dimension(500, 20));
        resultLabel.setPreferredSize(new Dimension(500, 20));
        resultField.setPreferredSize(new Dimension(4000, 4000));
        resultScroll.setPreferredSize(new Dimension(500, 300));

        //adding components
        generalPanel.add(selectTaskLabel);
        generalPanel.add(taskList);
        generalPanel.add(descriptionLabel);
        generalPanel.add(descriptionField);
        generalPanel.add(setInputLabel);
        generalPanel.add(inputField);
        generalPanel.add(calculateButton);
        generalPanel.add(resultLabel);
        generalPanel.add(resultScroll);

        //general options
        generalPanel.setLayout(new FlowLayout());
        generalPanel.setBackground(Color.lightGray);
        resultField.setFocusable(false);
        descriptionField.setFocusable(false);
        taskList.setSelectedIndex(-1);
        descriptionField.setLineWrap(true);
        setResizable(false);
        setContentPane(generalPanel);
        setSize(550, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //task list actions
        taskList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropDown = (String) taskList.getSelectedItem();
                for (GeneralTask task : Main.getGeneralTaskList()) {
                    if (dropDown.equalsIgnoreCase(task.getName()))
                        descriptionField.setText(task.getDescription());
                }
            }
        });

        //button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (GeneralTask task : Main.getGeneralTaskList()) {
                        if (dropDown.equalsIgnoreCase(task.getName())) {
                            localTask = task;
                            localTask.setInput(inputField.getText());
                            localTask.countResult();
                            resultField.setText(localTask.getResult());
                        }
                    }
                } catch (Exception exception) {
                    resultField.setText("Something goes wrong" + " " + exception.getMessage());
                }
            }
        });

    }

}
