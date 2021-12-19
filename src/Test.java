import java.awt.*;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyFrame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
