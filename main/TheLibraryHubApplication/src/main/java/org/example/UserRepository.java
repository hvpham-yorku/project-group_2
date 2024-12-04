package org.example;

import java.sql.*;

import static org.example.Main.*;


public class UserRepository {

    public void createUser(User user) {
        String sql = "INSERT INTO users (firstName, lastName, username, password, class) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


                pstmt.setString(1, user.getFirstName());

                pstmt.setString(2, user.getLastName());

                pstmt.setString(3, user.getUsername());

                pstmt.setString(4, user.getPassword());

                pstmt.setString(5, user.getAdminPassword());

                pstmt.executeUpdate();

                System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = parseUser(rs);

                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(urlDatabase, usernameDatabase , passwordDatabase);

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = parseUser(rs);

                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    
    private User parseUser(ResultSet rs) throws SQLException {


        User user = null;

           user = new User(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("username"),
                rs.getString("password"));
           
        return user;
    }


}
