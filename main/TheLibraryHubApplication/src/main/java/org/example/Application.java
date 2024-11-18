package org.example;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application
{
    private JFrame frame;
    private JLabel nameLabel;
    private JButton showDbButton;
    private JButton enterButton;
    private JTextField textArea;
    private DatabaseManager databaseManager;

    Application()
    {
        databaseManager = new DatabaseManager("books");
        frame = new JFrame();
        nameLabel = new JLabel("Enter Name:");
        showDbButton = new JButton("Show Database");
        enterButton = new JButton("Click Me");
        textArea = new JTextField();
        frame.setLayout(new GridLayout(2, 2));
        frame.add(nameLabel);
        frame.add(textArea);
        frame.add(showDbButton);
        frame.add(enterButton);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(enterButton,textArea.getText()+"Hello I'm amazing.");
            }
        });
        showDbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = databaseManager.getRs();
                JFrame f = new JFrame();

                String books[][] = null;

                try  {
                    //rs.afterLast();
                    books = new String[25][3]; //might have to change later on, right now this is hardcoded

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
}