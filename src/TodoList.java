import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TodoList {


    public LinkedList<String> todoList;
    private String month;
    private Integer year;

    public TodoList(String m, Integer y) throws FileNotFoundException {

        this.month = m;
        this.year = y;
        this.todoList = new LinkedList<>();

        readFromFile();
//        writeToConsole();

    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File("src/todoList" + month + year + ".txt");
        todoList.removeAll(todoList);
        if (file.exists()) {
            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {
                todoList.add(read.nextLine());
            }
        }
    }

    public void writeToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/todoList" + month + year + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String todo : todoList) {
            if (writer != null) {
                writer.println(todo);
            }
        }
        if (writer != null) writer.close();
    }

    public void writeToConsole() {
        System.out.println("List: ");
        for (String todo : todoList) {
            System.out.println(todo);
        }
        System.out.println();
    }

    public void removeItem(String todo) {
        todoList.remove(todo);
    }

    public void addItem(String todo) {
        todoList.add(todo);
    }

    public int len() {
      return todoList.size();
    }
}