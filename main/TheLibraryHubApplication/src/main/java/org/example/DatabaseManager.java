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
            System.out.println(e.getMessage());
        }
        return this.rs;
    }

    public ResultSet getUsers(){
        try{
            this.rs = this.st.executeQuery("select * from users");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return this.rs;
    }

    public void checkOut(ArrayList<String> listOfAddedBooks, String username) {
        //System.out.println(username);
        //boolean checkOut = true;
        if(listOfAddedBooks.isEmpty()){
            JOptionPane.showMessageDialog(null, "No books added to the cart. Please add books to cart and try again.");
        }
        else{
            for (int i = 0; i < listOfAddedBooks.size(); i++)
            {
                String sql = "UPDATE books SET checked_out = true, current_book_user = ?, checked_out_date = CURRENT_TIMESTAMP(0), due_date = CURRENT_TIMESTAMP(0) + INTERVAL '2 weeks' WHERE name = ?";
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
                    JOptionPane.showMessageDialog(null, "You have checked out "+ listOfAddedBooks.toString() + " and they are due in 2 weeks from today. ");

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }



    }

    public void returnBook(String bookName, String username) {
        //System.out.println("Logged in : "+ username);
        if(bookName.isEmpty()){
            JOptionPane.showMessageDialog(null, "No books in the textField. Please type a book to return and try again.");
        }
        else {
            String sql = "UPDATE books SET checked_out = false, current_book_user = NULL, checked_out_date = NULL, due_date = NULL WHERE name = ?";
            try {
                PreparedStatement pstmt = this.con.prepareStatement(sql);

                pstmt.setString(1, bookName); // name of book
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "You have returned the book: "+ bookName + ".\n Thank you.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



    }

    public boolean validateAdmin(String username) {
        String sql = "SELECT class FROM users WHERE userName = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String test = rs.getString("class");
                return rs.getString("class").replace("\n", "").equals("admin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
//usr, admin, null

        return false;
    }
}
