import org.example.User;
import org.example.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        userRepository = new UserRepository();

        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User("John", "Doe", "john123", "password");

        when(mockStatement.executeUpdate()).thenReturn(1);

        userRepository.createUser(user); // This will print output, which we won't assert here.

        verify(mockStatement, times(1)).setString(1, "John");
        verify(mockStatement, times(1)).setString(2, "Doe");
        verify(mockStatement, times(1)).setString(3, "john123");
        verify(mockStatement, times(1)).setString(4, "password");
        verify(mockStatement, times(1)).setString(5, "usr"); // Default admin password for regular users
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testGetUserByUsername() throws Exception {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("firstName")).thenReturn("Jane");
        when(mockResultSet.getString("lastName")).thenReturn("Doe");
        when(mockResultSet.getString("username")).thenReturn("jane123");
        when(mockResultSet.getString("password")).thenReturn("pass789");

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        User user = userRepository.getUserByUsername("jane123");

        assertNotNull(user);
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane123", user.getUsername());
        assertEquals("pass789", user.getPassword());
    }

    @Test
    void testValidateUser() throws Exception {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("firstName")).thenReturn("Alice");
        when(mockResultSet.getString("lastName")).thenReturn("Smith");
        when(mockResultSet.getString("username")).thenReturn("alice123");
        when(mockResultSet.getString("password")).thenReturn("mypassword");

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        User user = userRepository.validateUser("alice123", "mypassword");

        assertNotNull(user);
        assertEquals("Alice", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("alice123", user.getUsername());
        assertEquals("mypassword", user.getPassword());
    }

    @Test
    void testValidateUser_InvalidCredentials() throws Exception {
        when(mockResultSet.next()).thenReturn(false);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        User user = userRepository.validateUser("invalidUser", "wrongPass");

        assertNull(user);
    }
}
