package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class LoginController {

    static LoginController loginController;
    private UserService userService;

    private LoginController() {

            this.userService = new UserService(new UserRepository());

    }

    public static LoginController getInstance() {
        if (loginController == null) {
            loginController = new LoginController();
        }
        return loginController;
    }


    public void onLoginButtonClick(String username, String password) {

                User user = userService.handleUserLogin(username, password);

                boolean isSuccessfulLogin = user != null;


                if (isSuccessfulLogin){

                    // Create a new JFrame for the new page (or window)
                    JFrame newFrame = new JFrame("New Page");

                    // Set the size and other properties of the new frame
                    newFrame.setSize(400, 300);
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // Add a label or other components to the new frame
                    JLabel newPageLabel = new JLabel("Welcome to the new page!", JLabel.CENTER);
                    newFrame.add(newPageLabel);

                    // Make the new frame visible
                    newFrame.setVisible(true);

                    return;


                }
                return;
            }



  /*  public ChangeListener<? super String> onUserNameTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }
    public ChangeListener<? super String> onPasswordTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }

 */
    // Event Handler for Register Button
    public void onRegisterButtonClick() {


    };





    public User getUserById(int userId) {
        return userService.handleGetUserById(userId);
    }

}