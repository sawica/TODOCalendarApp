import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* ComboBoxDemo2.java requires no other files. */
public class CalendarSelect extends JPanel implements ActionListener {
    static JFrame frame;

    public CreateCalendar calendar;
    public JLabel result;
    public String month;
    public Integer YEAR;
    public Integer MONTH;
    public JComboBox monthsList;
    public JComboBox<Integer> yearsList;
    public JButton createCalButton;
    public String[] months;
    public Integer[] YEARS;

    public CalendarSelect(CreateCalendar calendar) {
        months = calendar.months;
        YEARS = calendar.YEARS;

        MONTH = 11;
        YEAR = 2021;
        month = months[MONTH];

        monthsList = new JComboBox(months);
        yearsList = new JComboBox<Integer>(YEARS);

        monthsList.setEditable(true);
        monthsList.setSelectedItem(month);
        monthsList.addActionListener(this);
        yearsList.setEditable(true);
        yearsList.setSelectedItem(YEAR);
        yearsList.addActionListener(this);

        result = new JLabel(month + " " + YEAR);


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

        createCalButton = new JButton("create calendar");
        createCalButton.setForeground(Color.black);
        createCalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createCalButton.addActionListener(this);

        selectPanel.add(createCalButton);
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
            System.out.println(MONTH);
        }
        else if (source == yearsList){
            JComboBox combo = (JComboBox) source;
            YEAR = (Integer) combo.getSelectedItem();
            System.out.println(YEAR);
        }
        else if (source == createCalButton){
            calendar = new CreateCalendar(YEAR, MONTH);
           // System.out.println(YEAR + " " + month);
            result.setText(month + " " + YEAR);
            CalendarView xxx=(CalendarView) getParent().getParent();

            xxx.calendarPanel.remove(xxx.calendarTable);
            xxx.calendarTable = new CalendarTable(calendar);
            xxx.calendarPanel.add(xxx.calendarTable);
           System.out.println(xxx.calendarTable+"  ccc");
        }

    }


}