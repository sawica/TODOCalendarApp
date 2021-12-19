import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class TodoPanel extends JPanel {
    static JFrame frame;

    public TodoPanel(CalendarView calendarSelect) {

        TodoList list = null;
        try {
            list = new TodoList(calendarSelect.month, calendarSelect.YEAR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JPanel todoPanel = new JPanel();
        todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("TODO " + calendarSelect.month + " " + calendarSelect.YEAR + " List");
        todoPanel.add(label);

        for(String todo : list.todoList){
            todoPanel.add(new JCheckBox(todo));
        }
        add(todoPanel);
    }
}
