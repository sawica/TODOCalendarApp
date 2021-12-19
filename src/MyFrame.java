import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;

public class MyFrame extends JFrame {
    public MyFrame() throws FileNotFoundException {
        super(("TODO Calendar App"));

        CreateCalendar calendar = new CreateCalendar(2021, 11);

        setLayout(new FlowLayout());

        //CalendarSelect calendarSelect = new CalendarSelect(calendar);
        //add(calendarSelect);

        CalendarView calPanel = new CalendarView(calendar);
        add(calPanel);

        TodoPanel todoPanel = new TodoPanel(calPanel);
        add(todoPanel);

        ButtonPanel buttonPanel = new ButtonPanel();
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
