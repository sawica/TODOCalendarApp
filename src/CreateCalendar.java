import java.util.GregorianCalendar;
import java.util.List;

public class CreateCalendar {
    String week = "SMTWTFS";
    String[] months = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] years = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2025"};
    Integer[] YEARS = {2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2025};
    int startINWeek;
    int days;
    int totalWeeks;

    public CreateCalendar(int year, int month){
        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        days = calendar.getActualMaximum(calendar.DATE);
        totalWeeks = calendar.getActualMaximum(calendar.WEEK_OF_MONTH);
        startINWeek = calendar.get(calendar.DAY_OF_WEEK) - 7;
    }

}
