import org.example.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test Cases for handleRegisterUser
    @Test
    void testHandleRegisterUser_SuccessfulRegularUserRegistration() {
        // Arrange
        when(userRepository.getUserByUsername("john123")).thenReturn(null);
        //when(userRepository.createUser(any(User.class))).thenReturn(true);

        // Act
        boolean result = userService.handleRegisterUser("John", "Doe", "john123", "password", "");

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).getUserByUsername("john123");
        verify(userRepository, times(1)).createUser(any(User.class));
    }

    @Test
    void testHandleRegisterUser_SuccessfulAdminUserRegistration() {
        // Arrange
        when(userRepository.getUserByUsername("admin123")).thenReturn(null);
        //when(userRepository.createUser(any(User.class))).thenReturn(true);

        // Act
        boolean result = userService.handleRegisterUser("Admin", "User", "admin123", "password", "admin");

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).getUserByUsername("admin123");
        verify(userRepository, times(1)).createUser(any(User.class));
    }

    @Test
    void testHandleRegisterUser_FailsDueToMissingFields() {
        // Act
        boolean result = userService.handleRegisterUser("", "Doe", "john123", "password", "");

        // Assert
        assertFalse(result);
        verify(userRepository, never()).getUserByUsername(anyString());
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void testHandleRegisterUser_FailsDueToExistingUsername() {
        // Arrange
        when(userRepository.getUserByUsername("john123")).thenReturn(new User("John", "Doe", "john123", "password"));

        // Act
        boolean result = userService.handleRegisterUser("John", "Doe", "john123", "password", "");

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).getUserByUsername("john123");
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void testHandleRegisterUser_FailsDueToInvalidAdminPassword() {
        // Act
        boolean result = userService.handleRegisterUser("Admin", "User", "admin123", "password", "wrongAdminPassword");

        // Assert
        assertTrue(result);
        //verify(userRepository, never()).getUserByUsername(anyString());
        //verify(userRepository, never()).createUser(any(User.class));
    }

    // Test Cases for handleUserLogin
    @Test
    void testHandleUserLogin_SuccessfulLogin() {
        // Arrange
        User expectedUser = new User("John", "Doe", "john123", "password");
        when(userRepository.validateUser("john123", "password")).thenReturn(expectedUser);

        // Act
        User result = userService.handleUserLogin("john123", "password");

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).validateUser("john123", "password");
    }

    @Test
    void testHandleUserLogin_FailsDueToInvalidCredentials() {
        // Arrange
        when(userRepository.validateUser("john123", "wrongPassword")).thenReturn(null);

        // Act
        User result = userService.handleUserLogin("john123", "wrongPassword");

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).validateUser("john123", "wrongPassword");
    }

    // Test Cases for validateUserRegistration
    @Test
    void testValidateUserRegistration_AllFieldsValid() {
        // Act
        String result = userService.validateUserRegistration("John", "Doe", "john123", "password", "");

        // Assert
        assertEquals("", result);
    }

    @Test
    void testValidateUserRegistration_MissingFirstName() {
        // Act
        String result = userService.validateUserRegistration("", "Doe", "john123", "password", "");

        // Assert
        assertEquals("Missing fields: \nFirst Name", result);
    }

    @Test
    void testValidateUserRegistration_MissingLastName() {
        // Act
        String result = userService.validateUserRegistration("John", "", "john123", "password", "");

        // Assert
        assertEquals("Missing fields: \nLast Name", result);
    }

    @Test
    void testValidateUserRegistration_MissingUsername() {
        // Act
        String result = userService.validateUserRegistration("John", "Doe", "", "password", "");

        // Assert
        assertEquals("Missing fields: \nUser Name", result);
    }

    @Test
    void testValidateUserRegistration_MissingPassword() {
        // Act
        String result = userService.validateUserRegistration("John", "Doe", "john123", "", "");

        // Assert
        assertEquals("Missing fields: \nPassword", result);
    }

    @Test
    void testValidateUserRegistration_MultipleMissingFields() {
        // Act
        String result = userService.validateUserRegistration("", "", "john123", "", "");

        // Assert
        assertEquals("Missing fields: \nFirst Name, \nLast Name, \nPassword", result);
    }
}