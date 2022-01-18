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
        }


        else if(source == saveTODO){
            myView.todoPanel.save();

        }

        else if(source == refreshTODO){
            myView.todoPanel.refresh();
        }

        else if(source == removeTODO){
            myView.todoPanel.removeItems();
        }


        else if(source == close){
            System.exit(0);
        }

    }
}