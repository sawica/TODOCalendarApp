import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.FileNotFoundException;

import javax.swing.*;

import static javax.swing.Box.createRigidArea;
import static javax.swing.Box.createVerticalBox;

public class ButtonPanel extends JPanel implements ActionListener{
    static JFrame frame;

    private JButton newTODO;
    private JButton saveTODO;
    private JButton refreshTODO;
    private JButton removeTODO;
    private JButton close;

    public ButtonPanel() {
        newTODO = new JButton("New TODO");
        newTODO.setMinimumSize(new Dimension(400, 15));
        newTODO.setBackground(Color.ORANGE);
        saveTODO = new JButton("Save TODO");
        saveTODO.setSize(200, 30);
        saveTODO.setBackground(Color.ORANGE);
        refreshTODO = new JButton("Refresh TODO");
        refreshTODO.setSize(120, 30);
        refreshTODO.setBackground(Color.ORANGE);
        removeTODO = new JButton("Remove TODO");
        removeTODO.setSize(150, 30);
        removeTODO.setBackground(Color.ORANGE);
        close = new JButton("Close");
        close.setSize(300, 30);
        close.setBackground(Color.GRAY);
        close.setForeground(Color.RED);

        newTODO.addActionListener(this);
        saveTODO.addActionListener(this);
        refreshTODO.addActionListener(this);
        removeTODO.addActionListener(this);
        close.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        BoxLayout layout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(layout);

        buttonPanel.add(createRigidArea(new Dimension(0, 70)));
        buttonPanel.add(newTODO);
        buttonPanel.add(saveTODO);
        buttonPanel.add(refreshTODO);
        buttonPanel.add(removeTODO);
        buttonPanel.add(createRigidArea(new Dimension(0, 100)));
        buttonPanel.add(close);
        add(buttonPanel);

//        JPanel buttonPanel = new JPanel();
//        GridLayout grid = new GridLayout(7, 1, 10, 10);
//        buttonPanel.setLayout(grid);
//
//        buttonPanel.add(createRigidArea(new Dimension(0, 70)));
//
//        buttonPanel.add(newTODO);
//
//        buttonPanel.add(saveTODO);
//
//        buttonPanel.add(refreshTODO);
//
//        buttonPanel.add(removeTODO);
//
//        buttonPanel.add(createRigidArea(new Dimension(0, 100)));
//
//        buttonPanel.add(close);
//        add(buttonPanel);
//        buttonPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        buttonPanel.add(createRigidArea(new Dimension(0, 70)), gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        buttonPanel.add(newTODO, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        buttonPanel.add(saveTODO, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        buttonPanel.add(refreshTODO, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        buttonPanel.add(removeTODO, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 5;
//        buttonPanel.add(createRigidArea(new Dimension(0, 100)), gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 6;
//        buttonPanel.add(close, gbc);
//        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        MyView myView = (MyView) getParent();

        if(source == newTODO){
            try {
                new  AddTodoFrame(this.getParent());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            setBackground(Color.GREEN);
        }


        else if(source == saveTODO){
            myView.todoPanel.save();
            setBackground(Color.BLUE);
        }

        else if(source == refreshTODO){
            myView.todoPanel.writeTodoList();
            myView.todoPanel.refresh();
            setBackground(Color.RED);
        }

        else if(source == removeTODO){
            myView.todoPanel.removeItems();
            myView.todoPanel.writeTodoList();
            setBackground(Color.ORANGE);
        }


        else if(source == close){
            setBackground(Color.CYAN);
            System.exit(0);
        }

    }
}