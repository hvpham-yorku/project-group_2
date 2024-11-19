package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageView extends JFrame {
        public JPanel panelMain;
        private JLabel userLabel;
        private JLabel passLabel;
        private JLabel firstNameLabel;
        private JLabel lastNameLabel;
        private JTextField first_name;
        private JTextField last_name;
        private JPasswordField password;
        private JTextField username;
        private JButton registerClick;
        private JButton loginClick;
        private JButton showDatabaseButton;
        private DatabaseManager databaseManager;

        private static LoginController loginController;
        private UserService userService;

        public HomepageView() {

        }
        public void start() {
            panelMain = new JPanel();

            userLabel = new JLabel("Homepage");


            // Set up layout for panelMain
            panelMain.setLayout(new FlowLayout());  // Simple layout for positioning components


            panelMain.add(userLabel);



            // Set up JFrame
            this.setContentPane(panelMain);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("HOMEPAGE");
            this.setSize(400, 300); // Set the window size
            this.setVisible(true);

            databaseManager = new DatabaseManager();






        }


}

