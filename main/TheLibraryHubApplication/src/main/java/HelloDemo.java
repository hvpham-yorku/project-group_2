import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloDemo extends JFrame{
    public JPanel panelMain;
    private JLabel Test;
    private JTextField textName;
    private JButton btnClick;
    private JButton showDatabaseButton;
    private DatabaseManager databaseManager;

    public HelloDemo() {

        panelMain = new JPanel();
        Test = new JLabel("Enter your name:");
        textName = new JTextField(20);  // 20 columns wide
        btnClick = new JButton("Click me");
        showDatabaseButton = new JButton("Show Database");

        // Set up layout for panelMain
        panelMain.setLayout(new FlowLayout());  // Simple layout for positioning components
        panelMain.add(Test);
        panelMain.add(textName);
        panelMain.add(btnClick);
        panelMain.add(showDatabaseButton);

        databaseManager = new DatabaseManager("books");

        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnClick,textName.getText()+"Hello I'm amazing.");
            }
        });
        showDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = databaseManager.getRs();
                JFrame f = new JFrame();

                String books[][] = null;

                try  {
                    //rs.afterLast();
                    books = new String[25][3];
                    //rs = databaseManager.getRs(); // moves cursor to front of the result set object - read api
                    int i = 0;
                    while (rs.next()) {

                        books[i][0] = rs.getString(1);

                        books[i][1] = rs.getString(2);
                        books[i][2] = rs.getString(3);
                        i++;

                        //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String[] columnNames = {"id", "book name", "isbnNumber"};

                JTable j = new JTable(books, columnNames);
                JScrollPane sp = new JScrollPane(j);
                f.setTitle("All Books");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(sp);
                f.setSize(500, 500);
                f.setVisible(true);



            }
        });
    }

    public static void main(String[] args) {
        HelloDemo h =  new HelloDemo();
        h.setContentPane(h.panelMain);
        h.setTitle("BABOYEE");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
