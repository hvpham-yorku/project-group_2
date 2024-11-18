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


    public boolean onLoginButtonClick(String username, String password) {

                User user = userService.handleUserLogin(username, password);

                boolean isSuccessfulLogin = user != null;

                 if(isSuccessfulLogin){

                  JOptionPane.showMessageDialog(null, "Successful Login", "Log in Success", JOptionPane.INFORMATION_MESSAGE);

                 }else {
                  //try again
                    JOptionPane.showMessageDialog(null, "Invalid Login Input. Try Again", "Invalid", JOptionPane.INFORMATION_MESSAGE);
                  }
              /*  if (isSuccessfulLogin){
                    //Got to homepage

                    // Create a new JFrame for the new page (or window)
                    JFrame newFrame = new JFrame("Homepage");

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

               */
                return isSuccessfulLogin;
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
    public boolean onRegisterButtonClick(String firstName, String lastName, String username, String password) {

       Boolean registered = userService.handleRegisterUser(firstName, lastName, username, password);

       if(registered){
           //go back to login
           JOptionPane.showMessageDialog(null, "Successfully Registered, Please login", "REGISTERED", JOptionPane.INFORMATION_MESSAGE);

       }else {
           //try again
           JOptionPane.showMessageDialog(null, "Invalid Input or user already exists. Try Again", "Invalid", JOptionPane.INFORMATION_MESSAGE);
       }

       return registered;

    };





    public User getUserById(int userId) {
        return userService.handleGetUserById(userId);
    }

}