import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* ComboBoxDemo2.java requires no other files. */
public class CalendarSelect extends JPanel implements ActionListener {


    public CreateCalendar calendar;
    public JLabel result;
    public String month;
    public Integer YEAR;
    public Integer MONTH;
    public JComboBox monthsList;
    public JComboBox<Integer> yearsList;
    public String[] months;
    public Integer[] YEARS;

    public CalendarSelect(CreateCalendar calendar) {
        months = calendar.months;
        YEARS = calendar.YEARS;

        MONTH = 0;
        YEAR = 2022;
        month = months[MONTH];

        monthsList = new JComboBox(months);
        yearsList = new JComboBox<Integer>(YEARS);

        monthsList.setEditable(true);
        monthsList.setSelectedItem(month);
        monthsList.addActionListener(this);
        monthsList.setBackground(Color.cyan);

        yearsList.setEditable(true);
        yearsList.setSelectedItem(YEAR);
        yearsList.addActionListener(this);
        yearsList.setBackground(Color.CYAN);

        result = new JLabel(month + " " + YEAR);
        result.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel,
                BoxLayout.Y_AXIS));

        selectPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectPanel.add(monthsList);
        selectPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        selectPanel.add(yearsList);
        selectPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        selectPanel.add(result);
        selectPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        selectPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(selectPanel);
    }

    public void view (){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == monthsList){
            JComboBox combo = (JComboBox) source;
            MONTH = combo.getSelectedIndex();
            month = months[MONTH];
        }
        else if (source == yearsList){
            JComboBox combo = (JComboBox) source;
            YEAR = (Integer) combo.getSelectedItem();
        }

            calendar = new CreateCalendar(YEAR, MONTH);
            result.setText(month + " " + YEAR);
            CalendarView calendarView=(CalendarView) getParent().getParent();
            calendarView.calendarTable.writeCalendar(calendar);

            MyView view = (MyView) calendarView.getParent();
            view.todoPanel.writeTodoList(month, YEAR);
            view.updateUI();

    }
}