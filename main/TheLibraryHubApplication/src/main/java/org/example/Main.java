package org.example;

import javax.swing.*;

public class Main {
    //change this depending on your own system settings
    static LoginController loginController = LoginController.getInstance();
    static String urlDatabase = "jdbc:postgresql://127.0.0.1:5432/libraryTest";
    static String usernameDatabase = "postgres";
    static String passwordDatabase = "Kanwarjot@123";

    public static void main(String[] args) {

        // Start the first Login View
        LoginView h = new LoginView(loginController);
        h.start();

        h.setContentPane(h.panelMain);
        h.setTitle("BABOYEE");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Application app = new Application();
    }
}
