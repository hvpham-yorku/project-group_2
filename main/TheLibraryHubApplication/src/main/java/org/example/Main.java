package org.example;

import javax.swing.*;

public class Main {
    static LoginController loginController = LoginController.getInstance();

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
