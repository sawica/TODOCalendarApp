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
//        for (JCheckBox checkBox:checkBoxes){
//            todoPanel.remove(checkBox);
//        }
        System.out.println("remove, m: " + month + " y: " + year);
        for(JCheckBox checkBox : checkBoxes){
            if(checkBox.isSelected()){
                System.out.println(checkBox.getText());
                String temp = checkBox.getText();
                list.removeItem(temp);;
                todoPanel.remove(checkBox);
                checkBoxes.remove(checkBox);
            }
        }
        list.writeToFile();
    }
    public void refresh(){
        System.out.println("refresh");
    }
    public void save(){
        list.writeToFile();
        System.out.println("save");
    }
    public void addItem(String todo){
        System.out.println("przed add: " + list.len());
        list.addItem(todo);
        System.out.println("po add: " + list.len());
        list.writeToFile();
        JCheckBox checkBox = new JCheckBox(todo);
        checkBoxes.add(checkBox);
        todoPanel.add(checkBox);
    }
    public void clearList() {
        todoPanel.removeAll();
        checkBoxes.removeAll(checkBoxes);
        label = new JLabel();
        label.setText("TODO " + month + " " + year + " List");
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        todoPanel.add(label);
    }
}
