import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AddTodoFrame extends JFrame implements ActionListener {

    JTextField textField;
    JButton button;
    Component myView;

    public AddTodoFrame(Component comp_myView) throws FileNotFoundException {
            super(("Add TODO"));

            myView = comp_myView;
            JPanel addPanel = new JPanel();

            textField = new JTextField(10);
            textField.addActionListener(this);
            button = new JButton("Add");
            button.addActionListener(this);
            addPanel.add(textField);
            addPanel.add(button);
            add(addPanel);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pack();
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            Object source = e.getSource();
            String todo = "";
            MyView view = (MyView) myView;
            todo = textField.getText();;
            if(source == button){
                if(!todo.equals("")) {
                    view.todoPanel.addItem(todo);
                    view.todoPanel.writeTodoList();
                    textField.setText("");
                }
            }
    }

}
