import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static javax.swing.Box.createRigidArea;

public class ButtonPanel extends JPanel implements ActionListener{
    static JFrame frame;

    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton newTODO;
    private JButton saveTODO;
    private JButton refreshTODO;
    private JButton removeTODO;
    private JButton close;

    public ButtonPanel() {
        newTODO = new JButton("New TODO");
        newTODO.setSize(120, 30);
        saveTODO = new JButton("Save TODO");
        saveTODO.setSize(120, 30);
        refreshTODO = new JButton("Refresh TODO");
        refreshTODO.setSize(120, 30);
        removeTODO = new JButton("Remove TODO");
        removeTODO.setSize(120, 30);
        close = new JButton("Close");
        close.setSize(70, 30);

        newTODO.addActionListener(this);
        saveTODO.addActionListener(this);
        refreshTODO.addActionListener(this);
        removeTODO.addActionListener(this);
        close.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

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

//        if(source == greenButton)
//            setBackground(Color.GREEN);
//
//        else if(source == blueButton)
//            setBackground(Color.BLUE);
//
//        else if(source == redButton)
//            setBackground(Color.RED);
    }
}