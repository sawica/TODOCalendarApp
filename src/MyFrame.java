import javax.swing.*;
import java.io.FileNotFoundException;

public class MyFrame extends JFrame {

    public MyFrame() throws FileNotFoundException {
        super(("TODO Calendar App"));

//        CreateCalendar calendar = new CreateCalendar(2021, 11);
//
//        setLayout(new FlowLayout());
//
//        //CalendarSelect calendarSelect = new CalendarSelect(calendar);
//        //add(calendarSelect);
//
//        calPanel = new CalendarView(calendar);
//        add(calPanel);
//
//        todoPanel = new TodoPanel(calPanel);
//        add(todoPanel);
//
//        buttonPanel = new ButtonPanel();
//        add(buttonPanel);
        add(new MyView());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
