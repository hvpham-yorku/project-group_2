package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterView extends JFrame{
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

    public RegisterView(LoginController loginController) {
        this.loginController = loginController;
    }
    public void start() {
        panelMain = new JPanel();

        userLabel = new JLabel("Username:");
        username = new JTextField(20);  // 20 columns wide
        passLabel = new JLabel("Password:");
        password = new JPasswordField(20);  // 20 columns wide
        firstNameLabel = new JLabel("First Name:");
        first_name = new JTextField(20);  // 20 columns wide
        lastNameLabel = new JLabel("Last Name:");
        last_name = new JTextField(20);  // 20 columns wide
        loginClick = new JButton("Login");
        registerClick = new JButton("Register");
        showDatabaseButton = new JButton("Show Database");


        // Set up layout for panelMain
        panelMain.setLayout(new FlowLayout());  // Simple layout for positioning components

        panelMain.add(firstNameLabel);
        panelMain.add(first_name);
        panelMain.add(lastNameLabel);
        panelMain.add(last_name);
        panelMain.add(userLabel);
        panelMain.add(username);
        panelMain.add(passLabel);
        panelMain.add(password);
        panelMain.add(registerClick);


        // Set up JFrame
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Register");
        this.setSize(300, 300); // Set the window size
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        databaseManager = new DatabaseManager();
        

        registerClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean success = loginController.onRegisterButtonClick(first_name.getText(), last_name.getText(), username.getText(), String.valueOf(password.getPassword()));

                if (success){
                    dispose();
                }
                else{

                }
            }
        });



    }


}
