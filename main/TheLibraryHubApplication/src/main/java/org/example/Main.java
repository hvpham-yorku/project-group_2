package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    //change this depending on your own system settings TODO make this into automated settings for sprint2
    static LoginController loginController = LoginController.getInstance();

    //TODO for the TA! :change to your local settings
    static String urlDatabase = "jdbc:postgresql://127.0.0.1:5432/libraryTest";
    static String usernameDatabase = "postgres";
    static String passwordDatabase = "Kanwarjot@123";
// test
    public static void main(String[] args) {

        // Start the first Login View
        LoginView h = new LoginView(loginController);
        h.start();

        h.setContentPane(h.panelMain);
        h.setTitle("Login");
        h.setSize(250,250);
        h.setLocationRelativeTo(null); //centre

        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Application app = new Application();
    }


}
