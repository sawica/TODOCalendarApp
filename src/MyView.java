import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;
import java.util.GregorianCalendar;

public class MyView extends JPanel {
    static JFrame frame;
    CalendarView calPanel;
    TodoPanel todoPanel;
    ButtonPanel buttonPanel;


    public MyView() throws FileNotFoundException {

        CreateCalendar calendar = new CreateCalendar(2022, 0);
        setLayout(new FlowLayout());

        calPanel = new CalendarView(calendar);
        add(calPanel);

        todoPanel = new TodoPanel(calendar.months[0], 2022);
        add(todoPanel);

        buttonPanel = new ButtonPanel();
        add(buttonPanel);

    }
}
