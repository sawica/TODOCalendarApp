import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class TodoPanel extends JPanel {
    static JFrame frame;

    TodoList list;
    JLabel label;
    LinkedList<JCheckBox> checkBoxes;
    JPanel todoPanel;
    String month;
    Integer year;

    public TodoPanel(String month, Integer year) {

        checkBoxes = new LinkedList<JCheckBox>();
        todoPanel = new JPanel();
        todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
        add(todoPanel);

        writeTodoList(month, year);
    }
    public void writeTodoList(String month, Integer year){
        try {
            list = new TodoList(month, year);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.month = month;
        this.year = year;
        todoPanel.removeAll();
        checkBoxes.removeAll(checkBoxes);
        label = new JLabel();
        label.setText("TODO " + month + " " + year + " List");
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        todoPanel.add(label);
        for(String todo : list.todoList){
            JCheckBox checkBox = new JCheckBox(todo);
            checkBoxes.add(checkBox);
            todoPanel.add(checkBox);
        }

    }
    public void writeTodoList(){
        try {
            list = new TodoList(month, year);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        todoPanel.removeAll();
        checkBoxes.removeAll(checkBoxes);
        label = new JLabel();
        label.setText("TODO " + month + " " + year + " List");
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        todoPanel.add(label);
        for(String todo : list.todoList){
            JCheckBox checkBox = new JCheckBox(todo);
            checkBoxes.add(checkBox);
            todoPanel.add(checkBox);
        }

    }
    public void removeItems(){
        int checkCounter=checkBoxes.size();
        for(int i =checkCounter-1 ; i >=0; i--){
            JCheckBox checkBox = checkBoxes.get(i);
            if(checkBox.isSelected()){
                if (list.todoList.contains(checkBox.getText())) list.removeItem(checkBox.getText());;
                todoPanel.remove(checkBox);
                checkBoxes.remove(checkBox);
            }
        }
        todoPanel.updateUI();
    }
    public void refresh(){
        for(JCheckBox checkBox : checkBoxes){
            if(checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
        writeTodoList();
        todoPanel.updateUI();
    }
    public void save(){
        list.writeToFile();
    }
    public void addItem(String todo){
        list.addItem(todo);
        list.writeToFile();
        JCheckBox checkBox = new JCheckBox(todo);
        checkBoxes.add(checkBox);
        todoPanel.add(checkBox);
        todoPanel.updateUI();
    }
}
