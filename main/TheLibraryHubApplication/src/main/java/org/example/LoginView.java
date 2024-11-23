package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class LoginView extends JFrame{
    public JPanel panelMain;
    private JLabel userLabel;
    private JLabel passLabel;
    private JPasswordField password;
    private JTextField username;
    private JButton registerClick;
    private JButton loginClick;
    private DatabaseManager databaseManager;
    private KeyListener kl;
    //private


    private static LoginController loginController;
    private UserService userService;

    public LoginView(LoginController loginController) {
        LoginView.loginController = loginController;
    }
    public void start() {
        panelMain = new JPanel();
        userLabel = new JLabel("Username:");
        username = new JTextField(20);  // 20 columns wide
        passLabel = new JLabel("Password:");
        password = new JPasswordField(20);  // 20 columns wide
        loginClick = new JButton("Login");
        registerClick = new JButton("Register");

        loginClick.setPreferredSize(new Dimension(100, 30));
        registerClick.setPreferredSize(new Dimension(100, 30));

        // Set up layout for panelMain
        panelMain.setLayout(new FlowLayout());  // Simple layout for positioning components
        panelMain.add(userLabel);
        panelMain.add(username);
        panelMain.add(passLabel);
        panelMain.add(password);
        panelMain.add(loginClick);
        panelMain.add(registerClick);

        kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean success = loginController.onLoginButtonClick(username.getText(), String.valueOf(password.getPassword()));

                    if (success){
                        dispose();
                        Application homeView = new Application();
                        //homeView.start();
                    }
                }

            }
            public void keyReleased(KeyEvent e) {

            }
        };
        username.addKeyListener(kl);
        password.addKeyListener(kl);

        databaseManager = new DatabaseManager();

        loginClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = loginController.onLoginButtonClick(username.getText(), String.valueOf(password.getPassword()));

                if (success){
                    dispose();
                    Application homeView = new Application();
                    //homeView.start();
                }

            }

        });

        registerClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                RegisterView registerView = new RegisterView(loginController);
                registerView.start();
            }
        });

        //panelMain.setPreferredSize(new Dimension(300, 222));

    }



}
