package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


/*
authenticationUI.java
joptionpane window which take username password and checks with Table "loginInformation" on postgres

application.java
what to do here:
create databaseManager
and create and show the authenticator UI, once user is authenticated (from authentication.java) , it switches to the mainUI

change this to mainUI.java

 */
public class ApplicationAdmin extends Application
{
    private JButton returnButton;



    ApplicationAdmin(String username)
    {
        super(username);
        returnButton = new JButton("Return");
        frame.add(returnButton);

        returnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                databaseManager.returnBook(textArea.getText(), getUsername());
            }
        });
    }

}