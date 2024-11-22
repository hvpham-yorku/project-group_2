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
        showDbButton = new JButton("Show Inventory");
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
                JOptionPane.showMessageDialog(searchButton, "You searched for : " + textArea.getText()+"");
                ResultSet rs = databaseManager.getBooks();
                //JFrame frame = new JFrame(); //will see what to do with this.

                String booksFound[][] = new String[25][4];

                try{
                    int i =0;
                    int numBooksFound = 0;

                    while (rs.next()){
                        // for debugging JOptionPane.showMessageDialog(searchButton, "this is what rs.getString(2) returns :" + rs.getString(2));

                        if (rs.getString(2).equals(textArea.getText())){
                            booksParser(rs, booksFound, i);
                            numBooksFound++;
                            i++;
                        }

                    }
                    JOptionPane.showMessageDialog(searchButton, "Number of Books Found: " + numBooksFound);

                }catch (SQLException f){
                    throw new RuntimeException(f);
                }
                //display jTable
                displayJTable(booksFound);

            }
        });
        showDbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = databaseManager.getBooks();
                //JFrame f = new JFrame();

                String books[][] = null;

                try  {
                    //rs.afterLast();
                    books = new String[25][4]; //might have to change later on, right now this is hardcoded ******@@@@@@@@@@@@

                    //rs = databaseManager.getRs(); // moves cursor to front of the result set object - read api
                    int i = 0;
                    while (rs.next()) { //TODO change this into static method

                        booksParser(rs, books, i);
                        i++;

                        //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //display here
                displayJTable(books);



            }
        });
    }
    public static void displayJTable(String books[][]){
        String[] columnNames = {"id", "book name", "isbnNumber", "Inventory"};

        JFrame f = new JFrame();
        JTable j = new JTable(books, columnNames);
        JScrollPane sp = new JScrollPane(j);
        f.setTitle("All Books");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(sp);
        f.setSize(500, 500);
        f.setVisible(true);

    }
    // it will parse the books Table, use this to make changes so call this method only once a
    // and everything is updated TODO TELL NOAH @#@#@#@#@!@!@
    public static void booksParser(ResultSet rs, String books[][], int i) throws SQLException{

        books[i][0] = rs.getString(1); // id
        books[i][1] = rs.getString(2); // name of book
        books[i][2] = rs.getString(3); // isbn
        if (rs.getString(4) == null)
            books[i][3] = "0";
        else
            books[i][3] = rs.getString(4); // # of inv

    }
    //using for test
//    public static void main(String[] args) {
//        Application app = new Application();
//        //displayJTable();
//    }
}