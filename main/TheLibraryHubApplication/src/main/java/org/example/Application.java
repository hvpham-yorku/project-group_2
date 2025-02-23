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
public class Application
{
    protected JFrame frame;
    private JLabel nameLabel, cartLabel;
    private JButton searchButton, addToCartButton, checkoutButton;
    protected JTextField textArea;
    private JTextArea addToCartTextField;
    protected DatabaseManager databaseManager;
    protected String books[][] = new String[25][7]; //might have to change later on, right now this is hardcoded ******@@@@@@@@@@@@
    private String booksFound[][] = new String[25][7];
    private KeyListener kl;
    private String  cartItems = "";
    private ArrayList<String> booksAdded = new ArrayList<String>();
    private String username;

    public Application(String username) {
        databaseManager = new DatabaseManager();
        frame = new JFrame();
        nameLabel = new JLabel("Enter Name of Book:");
        cartLabel = new JLabel("Cart Items:");
        searchButton = new JButton("Search");
        addToCartButton = new JButton("Add To Cart");
        checkoutButton = new JButton("Checkout");
        //returnButton = new JButton("Return");
        addToCartTextField = new JTextArea(30,30);
        textArea = new JTextField(20);
        this.username = username;
        frame.setLayout(new FlowLayout());
        frame.add(nameLabel);
        frame.add(textArea);
        frame.add(searchButton);
        frame.add(addToCartButton);
        frame.add(cartLabel);
        frame.add(addToCartTextField);
        frame.add(checkoutButton);
        //frame.add(returnButton);
        frame.setTitle("Welcome " + this.username);
        frame.setSize(350,650);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0); // closes application
                }

            }
            public void keyReleased(KeyEvent e) {

            }
        };
        textArea.addKeyListener(kl);
        //password.addKeyListener(kl);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((textArea.getText().isEmpty()) || (!search(textArea.getText()))) { // empty or not valid name
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book name.");
                }
                else
                {

                    cartItems =  textArea.getText(); // check with database if checked_out is false - 2 lines down

                    if (checkedOutState(textArea.getText())){ //checks if true
                        JOptionPane.showMessageDialog(frame, textArea.getText() + " is already checked out.\n Please select a valid book.");
                    }
                    else if (booksAdded.contains(textArea.getText())) { // check if already added to cart
                        JOptionPane.showMessageDialog(frame, textArea.getText() + " is already in the cart.");
                    }
                    else{
                        booksAdded.add(cartItems); // has an arraylist of all the books added to cart

                        //booksToAdd = booksToAdd + cartItems + "\n" ; // adds to cart LLL:::: not needed anymore , converted to arraylist
                        //System.out.println(cartItems);
                        addToCartTextField.setText(booksAdded.toString());
                        textArea.setText("");
                    }

                }

            }
        });
        /* here we need to change books_inventory: - the inventory
         * change books table : check which user is checking out book
         * change bool checked out , timestamp due date, timestamp checkedout date, text user that checked out
         *
         */
        checkoutButton.addActionListener(new ActionListener() {
            //private String username = this.username;

            @Override
            public void actionPerformed(ActionEvent e) {
                databaseManager.checkOut(booksAdded, getUsername());
                addToCartTextField.setText("");
                booksAdded.clear();
                //all checks are performed in addtocart button, so i can just implement checkout

            }
        });
    }
    public String getUsername() {
        return this.username;
    } //check username


    protected void displayJTable(String books[][]){
        String[] columnNames = {"id", "book name", "isbnNumber", "checked_out", "due_date", "checked_out_date", "current_book_user"};

        JFrame f = new JFrame();
        JTable j = new JTable(books, columnNames);
        JScrollPane sp = new JScrollPane(j);
        f.setTitle("All Books");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(sp);
        f.setSize(800, 400);
        f.setLocationRelativeTo(null); //centre ur ass
        j.addKeyListener(new KeyListener() { //
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    f.dispose(); // hides the frame so i can search again
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        f.setVisible(true);

    }
    // it will parse the books Table, use this to make changes so call this method only once a
    // and everything is updated

    protected static void booksParser(ResultSet rs, String books[][], int i) throws SQLException{

        books[i][0] = rs.getString(1); // id
        books[i][1] = rs.getString(2); // name of book
        books[i][2] = rs.getString(3); // isbn
        books[i][3] = rs.getString(4); // checked out boolean
        if (rs.getString(5) == null) // due date
            books[i][4] = "N/A";
        else
            books[i][4] = rs.getString(5);
        if (rs.getString(6) == null) //checkout  date
            books[i][5] = "N/A";
        else
            books[i][5] = rs.getString(6);
        if (rs.getString(7) == null) // current book user
            books[i][6] = "N/A";
        else
            books[i][6] = rs.getString(7);


    }

    //using for test
//    public static void main(String[] args) {
//        Application app = new Application();
//        //displayJTable();
//    }
    private void search() {
        int i =0;
        int numBooksFound = 0;
        ResultSet rs = databaseManager.getBooks();

        try{


            while (rs.next()){
                // for debugging JOptionPane.showMessageDialog(searchButton, "this is what rs.getString(2) returns :" + rs.getString(2));

                if (rs.getString(2).equals(textArea.getText())){
                    booksParser(rs, booksFound, i);
                    numBooksFound++;
                    i++;
                }

            }
            JOptionPane.showMessageDialog(searchButton, "You searched for : " + textArea.getText()+"\n" +
                    "Number of Books found: " + numBooksFound);


        }catch (SQLException f){
            throw new RuntimeException(f);
        }
        //display jTable
        if (numBooksFound != 0)
            displayJTable(booksFound);
    }
    private boolean search(String bookToSearch){
        boolean found = false;
        ResultSet rs = databaseManager.getBooks();
        try{
            while(rs.next()){
                if (rs.getString(2).equals(bookToSearch)){
                    found = true;
                }
            }
        }catch (SQLException f){
            throw new RuntimeException(f);
        }
        return found;
    }

    // this function checks whether the book is in library or is already checked out.
    private boolean checkedOutState(String bookToSearch){
        boolean checkedOutState = true;
        ResultSet rs = databaseManager.getBooks();
        try{
            int i =0;
            while (rs.next()){
                if (rs.getString(2).equals(bookToSearch)){ // if book found
                    //JOptionPane.showMessageDialog(frame,rs.getString(4) + " : value of checkedOut");

                    if (rs.getString(4).equals("f")){
                        checkedOutState = false;
                    }

                }
            }
        }catch (SQLException g){
            throw new RuntimeException(g);
        }
        return checkedOutState;
    }

}
