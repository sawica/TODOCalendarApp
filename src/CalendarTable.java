import javax.swing.*;
import java.awt.*;

public class CalendarTable extends JPanel {

    int startWeek;
    Label[] calendarFields =new Label[42];
    public CalendarTable(CreateCalendar calendar){
      //  CreateCalendar calendar = new CreateCalendar(2021, 11);
        startWeek = calendar.startINWeek;;
        setLayout(new GridLayout(7,7));

        for (int i = 0; i < 7; i++){
            Label label = new Label();
            label.setText(calendar.week.charAt(i)+"");
            add(label);
        }
        //System.out.println("dwa");
        for ( int i = 0; i < 6; i++){
            for( int j=0; j<7; j++){
                Label label = new Label();
                calendarFields[i*7+j]=label;
                add(label);
            }
        }
        writeCalendar(calendar);

    }
    public void writeCalendar(CreateCalendar calendar){
        startWeek = calendar.startINWeek;

        //TODO: źle wyświetla kalendarz, ma 9 kolumn

        for ( int i = 0; i < 6; i++){
            for( int j=0; j<7; j++){
                Label field = calendarFields[i*7+j];
                startWeek++;
                if( startWeek < 1 || startWeek > calendar.days) {
                    field.setText(" ");
                }
                else field.setText(""+ startWeek);
            }
        }
    }
}
