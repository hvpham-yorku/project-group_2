package org.example;
import javax.swing.*;
import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;

import static org.example.Main.*;

public class DatabaseManager {
    private Connection con;
    private Statement st;
    //private String databaseName; its actually tableName but not needed anymore; better functionality
    private ResultSet rs;
/*
DatabaseManager
dont need to tableName


create different function for each table
 */
    public DatabaseManager() {
        try {
            this.con = DriverManager.getConnection(urlDatabase, usernameDatabase, passwordDatabase);
            this.st = this.con.createStatement();
            //this.databaseName = tableName;

        }catch (SQLException e){
            System.out.println(e);
        }
    }

    //get books
    public ResultSet getBooks() {
        try{
            this.rs = this.st.executeQuery("select * from books");

        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.rs;
    }
//TODO remove method not implementing do to time constraints
    public ResultSet getBooksInventory(){
        try {
            this.rs = this.st.executeQuery("select * from book_inventory");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.rs;
    }

    public void checkOut(ArrayList<String> listOfAddedBooks, String username) {
        System.out.println(username);
        //boolean checkOut = true;

        for (int i = 0; i < listOfAddedBooks.size(); i++)
        {
            String sql = "UPDATE books SET checked_out = true, current_book_user = ?, checked_out_date = CURRENT_TIMESTAMP, due_date = CURRENT_TIMESTAMP + INTERVAL '2 weeks' WHERE name = ?";
            //String sql_2 = "UPDATE book_inventory SET quantity_left = quantity_left - 1 WHERE name = ?";
            //String sql_3 = "UPDATE users SET checked_out_books = array_append(checked_out_books, ?) where username = ?";

            try {
                PreparedStatement pstmt = this.con.prepareStatement(sql);

                //updates book table
                pstmt.setString(1, username); // setting user
                pstmt.setString(2, listOfAddedBooks.get(i)); //should be book name
                pstmt.executeUpdate();

                // need to check quantity of books NOT anymore, deleted due to time constraints.
//                PreparedStatement pstmt2 = this.con.prepareStatement(sql_2);
//                pstmt2.setString(1, username);
//                pstmt2.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "You have checked out "+ listOfAddedBooks.toString() + " and they are due in 2 weeks from today. ");
    }

    public void returnBook(String bookName, String username) {
        System.out.println("Logged in : "+ username);

        String sql = "UPDATE books SET checked_out = false, current_book_user = NULL, checked_out_date = NULL, due_date = NULL WHERE name = ?";
        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);

            pstmt.setString(1, bookName); // name of book
            pstmt.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(null, "You have returned the book: "+ bookName + ".\n Thank you.");


    }



}
