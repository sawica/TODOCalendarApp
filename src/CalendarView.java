import javax.swing.*;
import java.awt.*;

import static javax.swing.Box.createRigidArea;

public class CalendarView extends JPanel {
    static JFrame frame;
    String month;
    Integer YEAR;
    CalendarTable calendarTable;
    JPanel calendarPanel;

    public CalendarView(CreateCalendar calendar){

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
        calendarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        CalendarSelect calendarSelect = new CalendarSelect(calendar);
        calendarPanel.add(calendarSelect);

        //CalendarTable
        calendarTable = new CalendarTable(calendar);
        calendarPanel.add(calendarTable);
        add(calendarPanel);


    }
    
}
