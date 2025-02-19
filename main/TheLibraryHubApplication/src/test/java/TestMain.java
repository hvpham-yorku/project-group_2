import org.example.Application;
import org.example.ApplicationAdmin;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// This class is created to test everything in the project to find bugs etc. This is not completed yet, last updated December 10, 2024.
public class TestMain {

    @Test
    void testApplicationCreation() {
        //user creation

        Application app = new Application("testUser");
        assertEquals("testUser", app.getUsername());
    }

    @Test
    void testAdminCreation() {
        ApplicationAdmin app = new ApplicationAdmin("testAdmin");
        assertEquals("testAdmin", app.getUsername());


    }
}
