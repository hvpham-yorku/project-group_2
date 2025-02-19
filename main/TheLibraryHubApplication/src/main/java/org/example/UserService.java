package org.example;


public class UserService {
    private static final String admin = "admin"; // use this password to verify a librarian class of user.
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }


    public boolean handleRegisterUser(String firstName, String lastName, String userName, String password, String adminpassword) {

        String validationMessage = validateUserRegistration(firstName, lastName, userName, password, adminpassword); //TODO check this method if it needs anything as well

        if (!validationMessage.isEmpty()) {
            return false; 
        }

        User user = userRepository.getUserByUsername(userName);

        if (user != null) {

            return false;
        }

        User newUser = null;

        if (adminpassword.equals(admin)) {
            newUser = new User(firstName, lastName, userName, password, adminpassword); //TODO change from here make another method to create admin class / in the original method add usr class as default
        }
        else {
            newUser = new User(firstName, lastName, userName, password);
        }
        userRepository.createUser(newUser);
        
        return true;
    }
    
    public User handleUserLogin(String username, String password) {
        return userRepository.validateUser(username, password);
    }

    public String validateUserRegistration(String firstName, String lastName, String userName, String password, String adminpassword) {
        StringBuilder missingFields = new StringBuilder("Missing fields: ");

        if (firstName == null || firstName.trim().isEmpty())
            missingFields.append("\nFirst Name, ");

        if (lastName == null || lastName.trim().isEmpty())
            missingFields.append("\nLast Name, ");

        if (userName == null || userName.trim().isEmpty())
            missingFields.append("\nUser Name, ");

        if (password == null || password.trim().isEmpty())
            missingFields.append("\nPassword, ");

//        if (adminpassword == null || adminpassword.trim().isEmpty())
//            missingFields.append("\nAdmin Password, "); //TODO no idea what this does... check
//


        if (missingFields.length() > "Missing fields: ".length()) {

            missingFields.setLength(missingFields.length() - 2);

            return missingFields.toString();

        } else {
            // Return empty string if no fields are missing
            return "";
        }
    }


}