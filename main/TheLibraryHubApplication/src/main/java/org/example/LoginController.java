package org.example;

import javax.swing.*;

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

                return isSuccessfulLogin;
            }


    // Event Handler for Register Button
    public boolean onRegisterButtonClick(String firstName, String lastName, String username, String password, String adminpassword) {

       Boolean registered = userService.handleRegisterUser(firstName, lastName, username, password, adminpassword);

       if(registered){
           //go back to login
           JOptionPane.showMessageDialog(null, "Successfully Registered, Please login", "REGISTERED", JOptionPane.INFORMATION_MESSAGE);

       }else {
           //try again
           JOptionPane.showMessageDialog(null, "Invalid Input or user already exists. Try Again", "Invalid", JOptionPane.INFORMATION_MESSAGE);
       }

       return registered;

    };



}