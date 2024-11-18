package org.example;
import java.sql.*;
public class DatabaseManager {
    private Connection con;
    private Statement st;
    //private String databaseName; its actually tableName
    private ResultSet rs;
/*
DatabaseManager
dont need to tableName


create different function for each table
 */
    public DatabaseManager() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/libraryTest", "postgres" , "Kanwarjot@123");
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

    public void getAuthentication(){
        //TODO might be used for combining authenticator class
        //TODO this is created different from getBooks because it should be accessing another DB possibly called Authenticator

    }
}
