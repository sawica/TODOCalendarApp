import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CalendarTable extends JPanel {

    int startWeek;
    public CalendarTable(CreateCalendar calendar){
      //  CreateCalendar calendar = new CreateCalendar(2021, 11);
        startWeek = calendar.startINWeek;;
        setLayout(new GridLayout(6,7));

        //System.out.println(calendar.totalWeeks);

        for (int i = 0; i < 7; i++){
            Label label = new Label();
            label.setText(calendar.week.charAt(i)+"");
            add(label);
        }
        System.out.println("dwa");
        for ( int i = 0; i < calendar.totalWeeks; i++){
            for( int j=0; j<7; j++){
                Label label = new Label();
        //        JButton label = new JButton();
                startWeek++;
                if( startWeek < 1 || startWeek > calendar.days) {
                    //label.setEnabled(true);
                    label.setText(" ");
                }
                else label.setText(""+ startWeek);
                add(label);
            }
        }
    }
}
