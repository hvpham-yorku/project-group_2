package org.example;
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

//    public void checkOut(ArrayList<String> inCartBooks){
//        // TODO update checkout duedate checkoutoutdate currentbookuser
//
//            for (int i = 0; i < inCartBooks.size(); i++)
//            {
//            String sql = "UPDATE books SET checked_out = true, current_book_user = ?, checked_out_date = CURRENT_TIMESTAMP, due_date = CURRENT_TIMESTAMP + INTERVAL '2 weeks' WHERE name = ?";
//            String sql_2 = "UPDATE book_inventory SET quantity_left = quantity_left - 1 WHERE name = ?";
//            String sql_3 = "UPDATE users SET checked_out_books = array_append(checked_out_books, ?) where username = ?";
//
//            try {
//                PreparedStatement pstmt = this.con.prepareStatement(sql);
//
//                pstmt.setString(1, user.getUsername());
//                pstmt.setInt(2, inCartBooks.get(i));
//
//                pstmt.executeUpdate();
//
//                PreparedStatement pstmt_2 = this.con.prepareStatement(sql_2);
//                pstmt_2.setInt(1, inCartBooks.get(i));
//                pstmt_2.executeUpdate();
//
//                PreparedStatement pstmt_3 = this.con.prepareStatement(sql_3);
//                pstmt_3.setInt(1, id);
//                pstmt_3.setString(2, user.getUsername());
//                pstmt_3.executeUpdate();
//
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//            }
//    }


}
