package org.example;

import javax.swing.*;
import java.sql.*;

import static org.example.Main.*;

/*
 SQL Queries that manipulate books database
 */

public class BookRepository {

    // addBook. Will add book of given name and string number
    // SQL database has trigger to handle updating book inventory
        public void addBook(String name, int isbn) {
            String sql = "INSERT INTO books (name, isbn_number, checked_out) VALUES (?, ?, false)";

            try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {


                pstmt.setString(1, name);

                pstmt.setInt(2, isbn);


                pstmt.executeUpdate();

                System.out.println("Book added");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void checkoutBook(int id, int isbn, User user) {

            String sql = "UPDATE books SET checked_out = true, current_book_user = ?, checked_out_date = CURRENT_TIMESTAMP, due_date = CURRENT_TIMESTAMP + INTERVAL '2 weeks' WHERE id = ?";
            String sql_2 = "UPDATE book_inventory SET quantity_left = quantity_left - 1 WHERE isbn_number = ?";
            String sql_3 = "UPDATE users SET checked_out_books = array_append(checked_out_books, ?) where username = ?";

            try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);

                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, user.getUsername());
                pstmt.setInt(2, id);

                pstmt.executeUpdate();

                PreparedStatement pstmt_2 = conn.prepareStatement(sql_2);
                pstmt_2.setInt(1, isbn);
                pstmt_2.executeUpdate();

                PreparedStatement pstmt_3 = conn.prepareStatement(sql_3);
                pstmt_3.setInt(1, id);
                pstmt_3.setString(2, user.getUsername());
                pstmt_3.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }


    public void returnBook(int id, int isbn, User user) {

        String sql = "UPDATE books SET checked_out = false, current_book_user = NULL, checked_out_date = NULL, due_date = NULL WHERE id = ?";
        String sql_2 = "UPDATE book_inventory SET quantity_left = quantity_left + 1 WHERE isbn_number = ?";
        String sql_3 = "UPDATE users SET checked_out_books = array_remove(checked_out_books, ?) where username = ?";

        try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            PreparedStatement pstmt_2 = conn.prepareStatement(sql_2);
            pstmt_2.setInt(1, isbn);
            pstmt_2.executeUpdate();

            PreparedStatement pstmt_3 = conn.prepareStatement(sql_3);
            pstmt_3.setInt(1, id);
            pstmt_3.setString(2, user.getUsername());
            pstmt_3.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



  /*  public static void main(String[] args) {

        // Start the first Login View
        User noah = new User("Noah", "Vukosa", "nvukosa", "1234");
        BookRepository repo = new BookRepository();

        repo.checkoutBook(1, 123456789, noah);

        //Application app = new Application();
    }
*/

}
