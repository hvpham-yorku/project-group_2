package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
authenticationUI.java
joptionpane window which take username password and checks with Table "loginInformation" on postgres

application.java
what to do here:
create databaseManager
and create and show the authenticator UI, once user is authenticated (from authentication.java) , it switches to the mainUI

change this to mainUI.java

 */
public class Application
{
    private JFrame frame;
    private JLabel nameLabel;
    private JButton showDbButton;
    private JButton searchButton;
    private JTextField textArea;
    private DatabaseManager databaseManager;

    Application()
    {
        databaseManager = new DatabaseManager();
        frame = new JFrame();
        nameLabel = new JLabel("Enter Name:");
        showDbButton = new JButton("Show Database");
        searchButton = new JButton("Search");
        textArea = new JTextField();
        frame.setLayout(new GridLayout(2, 2));
        frame.add(nameLabel);
        frame.add(textArea);
        frame.add(showDbButton);
        frame.add(searchButton);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(searchButton,textArea.getText()+"");
                ResultSet rs = databaseManager.getBooks();
                JFrame frame = new JFrame(); //will see what to do with this.

                String booksFound[][] = new String[25][4];

                try{
                    int i = 0;

                    while (rs.getString(2) == textArea.getText()){
                        booksFound[i][0] = rs.getString(1); // id

                        booksFound[i][1] = rs.getString(2); // name of book
                        booksFound[i][2] = rs.getString(3); // isbn
                        if (rs.getString(4) == null)
                            booksFound[i][3] = "0";
                        else
                            booksFound[i][3] = rs.getString(4); // # of inv
                        i++;
                    }
                }catch (SQLException f){
                    throw new RuntimeException(f);
                }


            }
        });
        showDbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = databaseManager.getBooks();
                JFrame f = new JFrame();

                String books[][] = null;

                try  {
                    //rs.afterLast();
                    books = new String[25][4]; //might have to change later on, right now this is hardcoded ******@@@@@@@@@@@@

                    //rs = databaseManager.getRs(); // moves cursor to front of the result set object - read api
                    int i = 0;
                    while (rs.next()) {

                        books[i][0] = rs.getString(1); // id

                        books[i][1] = rs.getString(2); // name of book
                        books[i][2] = rs.getString(3); // isbn
                        if (rs.getString(4) == null)
                            books[i][3] = "0";
                        else
                            books[i][3] = rs.getString(4); // # of inv
                        i++;

                        //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String[] columnNames = {"id", "book name", "isbnNumber", "Inventory"};

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
    public static void displayJTable(){
        
    }
}