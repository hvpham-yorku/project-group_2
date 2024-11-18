package org.example;

import java.sql.*;


public class UserRepository {

    public void createUser(User user) {
        String sql = "INSERT INTO users (firstName, lastName, username, password) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


                pstmt.setString(1, user.getFirstName());

                pstmt.setString(2, user.getLastName());

                pstmt.setString(3, user.getUsername());

                pstmt.setString(4, user.getPassword());

                pstmt.executeUpdate();

                System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

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

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");

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

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");

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
    
    public void updateUser(User user, String role) {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, password = ? WHERE username = ?";
    
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());

            pstmt.setString(2, user.getLastName());

            pstmt.setString(3, user.getPassword()); 

            pstmt.setString(4, role); 

            pstmt.setString(5, user.getUsername());
            
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
    
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    
    // Method to update password
    public void updateUserProfile(User user) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/librarytest", "noahvukosa" , "1234");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getUsername());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User password updated successfully.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
