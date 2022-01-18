import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.DefaultTableRenderer;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.*;

public class TodoCalendarApp2 {
    static Table<String> createCalendar(int year, int month){
        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        int days = calendar.getActualMaximum(calendar.DATE);
        int totalWeeks = calendar.getActualMaximum(calendar.WEEK_OF_MONTH);
        int startINWeek = calendar.get(calendar.DAY_OF_WEEK) -7;
        final Table<String> table = new Table<>("S", "M", "T", "W", "T", "F", "S");
        table.setCellSelection(true);

        table.setVisibleRows(totalWeeks+1);
        final DefaultTableRenderer<String> tableRenderer = new DefaultTableRenderer<>();
        final TableModel<String> model = table.getTableModel();

        for ( int i = 0; i <= totalWeeks; i++){
            model.addRow(" "," "," "," "," "," "," ");
            for( int j=0; j<7; j++){
                startINWeek++;
                if( startINWeek < 1 || startINWeek > days) model.setCell(j, i, " ");
                else model.setCell(j, i, ""+ startINWeek);
            }

        }
        table.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Beginning));
        table.withBorder(Borders.singleLineBevel());
        return table;

    }
    static void writer(List<CheckBox> todoList, String month, int year){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/todoList"+month+year+".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(CheckBox component : todoList){
            if(!component.isChecked()) {
                if (writer != null) writer.println(component.getLabel());
            }
        }
        if(writer!= null) writer.close();

    }
    static void printTodoList(List<CheckBox> todoList, String month, int year, Panel todoPanel) throws FileNotFoundException {
        File file = new File("src/todoList"+month+year+".txt");
        todoList.removeAll(todoList);
        if(file.exists()){
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                todoList.add(new CheckBox(read.nextLine()));
            }
        }
        todoPanel.removeAllComponents();
        Label label = new Label("TODO "+month+" "+year+" list");
        todoPanel.addComponent(label);
        todoPanel.addComponent(new EmptySpace(new TerminalSize(30, 1)));
        for(Component component : todoList){
            todoPanel.addComponent(component);
        }

    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        final MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Calendar Application");
        window.setHints(Collections.singletonList(Window.Hint.FIXED_SIZE));
        window.setFixedSize(new TerminalSize(70, 20));

        Panel leftPanel = new Panel();
        leftPanel.setPreferredSize(new TerminalSize(2, 20));
        Panel calendarPanel = new Panel();
        calendarPanel.addComponent(new EmptySpace(new TerminalSize(23, 1)));
        int month =  0;
        int year = 2022;
        CreateCalendar calendar = new CreateCalendar(year, month);
        Panel tablePanel = new Panel();
        Table<String> table;
        List<CheckBox> todoList = new ArrayList<>();
        Panel todoPanel = new Panel();
        table = createCalendar(year, month);
        tablePanel.removeAllComponents();
        table.addTo(tablePanel);
        ComboBox<String> monthList = new ComboBox<String>(calendar.months);
        ComboBox<Integer> yearList = new ComboBox<Integer>(calendar.YEARS);

        monthList.setSelectedIndex(month);
        yearList.setSelectedItem(year);
        printTodoList(todoList, monthList.getSelectedItem(), yearList.getSelectedItem(), todoPanel);



        Button createCalendar = new Button("create calendar", () -> {
            int xmonth = monthList.getSelectedIndex();
            String xmonthString = monthList.getSelectedItem();
            int xyear = yearList.getSelectedItem();
            Table<String> xtable;
            xtable = createCalendar(xyear, xmonth);
            tablePanel.removeAllComponents();
            xtable.addTo(tablePanel);
            try {
                printTodoList(todoList, xmonthString, xyear, todoPanel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        Panel datePanel = new Panel();
        datePanel.addComponent(monthList);
        datePanel.addComponent(yearList);
        datePanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        datePanel.addComponent(createCalendar);
        datePanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        calendarPanel.addComponent(datePanel);
        calendarPanel.addComponent(tablePanel);

        Panel buttonPanel = new Panel();



        Panel todoTextPanel = new Panel();
        Panel rightPanel = new Panel();
        rightPanel.setPreferredSize(new TerminalSize(28, 20));
        rightPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        rightPanel.withBorder(Borders.doubleLine());
        rightPanel.addComponent(new EmptySpace( new TerminalSize(30, 1)));

        Panel rightButtonPanel = new Panel();
        rightButtonPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        rightButtonPanel.withBorder(Borders.singleLineBevel());
        rightButtonPanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        rightButtonPanel.addComponent(new Button("New TODO", new Runnable() {
            @Override
            public void run() {
                BasicWindow todoWindow = new BasicWindow("add new TODO");
                todoWindow.setHints(Collections.singletonList(Window.Hint.FIXED_SIZE));
                todoWindow.setFixedSize(new TerminalSize(10, 8));
                todoWindow.setPosition(new TerminalPosition(3, 5));
                TextBox todoText = new TextBox();
                todoTextPanel.removeAllComponents();
                todoTextPanel.addComponent(todoText);

                todoTextPanel.addComponent(new Button("Add", new Runnable() {
                    @Override
                    public  void run() {
                        todoList.add(new CheckBox(todoText.getText()));
                        todoText.setText("");
                        todoWindow.close();
                        for (Component component: todoList){
                            todoPanel.addComponent(component);
                        }
                    }
                }));
                todoWindow.setComponent(todoTextPanel);
                textGUI.addWindow(todoWindow);
                textGUI.waitForWindowToClose(todoWindow);
            }
        }));
        rightButtonPanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        rightButtonPanel.addComponent(new Button("Save TODO", () -> {
            writer(todoList, monthList.getSelectedItem(), yearList.getSelectedItem());
        }));
        rightButtonPanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        rightButtonPanel.addComponent(new Button("Refresh TODO", () -> {
            writer(todoList, monthList.getSelectedItem(), yearList.getSelectedItem());
            try {
                printTodoList(todoList, monthList.getSelectedItem(), yearList.getSelectedItem(), todoPanel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }));
        rightButtonPanel.addComponent(new EmptySpace(new TerminalSize(8, 1)));
        rightButtonPanel.addComponent(new Button("Remove TODO", () -> {
            todoList.removeIf(CheckBox::isChecked);
            writer(todoList, monthList.getSelectedItem(), yearList.getSelectedItem());
            try {
                printTodoList(todoList, monthList.getSelectedItem(), yearList.getSelectedItem(), todoPanel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }));
        for(Component component : todoList){
            todoPanel.addComponent(component);
        }

        rightButtonPanel.addComponent(new EmptySpace(new TerminalSize(8, 9)));
        rightButtonPanel.addComponent(new Button("Close", window::close));

        buttonPanel.addTo(rightPanel);
        todoPanel.addTo(rightPanel);
        Panel rrightPanel = new Panel();
        leftPanel.setPreferredSize(new TerminalSize(2, 20));
        window.setComponent(Panels.horizontal(leftPanel,
                calendarPanel,
                rightPanel, rightButtonPanel, rrightPanel));
        textGUI.addWindow(window);
        textGUI.waitForWindowToClose(window);
        screen.stopScreen();
    }
}