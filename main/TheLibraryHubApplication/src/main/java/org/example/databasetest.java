package org.example;
import  java.sql.*;
/*
this class is jsut a test class to see how connecting to database works, not really needed to run the application.
 */
public class databasetest {

    public static void main(String[] args) {
        System.out.println("test");

        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/libraryTest", "postgres" , "Kanwarjot@123" );
            System.out.println("Works!");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from books"); // i dont understand this line
                //st.executeUpdate() to update.
            // get elements of LibraryTest
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

            rs = st.executeQuery("select * from books where id>1");

            while (rs.next()) {
                System.out.println(rs.getBoolean(4)); // index1 = 2, index2 = Testbook2,
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

                rs.close();
            con.close();  //close connection
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

}
