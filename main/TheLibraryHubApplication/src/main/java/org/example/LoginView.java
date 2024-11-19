package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginView extends JFrame{
    public JPanel panelMain;
    private JLabel userLabel;
    private JLabel passLabel;
    private JPasswordField password;
    private JTextField username;
    private JButton registerClick;
    private JButton loginClick;
    private JButton showDatabaseButton;
    private DatabaseManager databaseManager;

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
        showDatabaseButton = new JButton("Show Database");


        // Set up layout for panelMain
        panelMain.setLayout(new FlowLayout());  // Simple layout for positioning components
        panelMain.add(userLabel);
        panelMain.add(username);
        panelMain.add(loginClick);
        panelMain.add(passLabel);
        panelMain.add(password);
        panelMain.add(registerClick);
        panelMain.add(showDatabaseButton);



        databaseManager = new DatabaseManager();

        loginClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               boolean success = loginController.onLoginButtonClick(username.getText(), String.valueOf(password.getPassword()));

                if (success){
                    dispose();
                    HomepageView homeView = new HomepageView();
                    homeView.start();
                }
                else{

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

        showDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = databaseManager.getBooks();
                JFrame f = new JFrame();

                String books[][] = null;

                try {
                    //rs.afterLast();
                    books = new String[25][3];
                    //rs = databaseManager.getRs(); // moves cursor to front of the result set object - read api
                    int i = 0;
                    while (rs.next()) {

                        books[i][0] = rs.getString(1);

                        books[i][1] = rs.getString(2);
                        books[i][2] = rs.getString(3);
                        i++;

                        //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String[] columnNames = {"id", "book name", "isbnNumber"};

                JTable j = new JTable(books, columnNames);
                JScrollPane sp = new JScrollPane(j);
                f.setTitle("All Books");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(sp);
                f.setSize(500, 500);
                f.setVisible(true);


            }
        });

    }


}
