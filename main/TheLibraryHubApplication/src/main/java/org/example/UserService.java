package org.example;


public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }

    public User handleGetUserById(int id) {
        return userRepository.getUserById(id);
    }

    public boolean handleRegisterUser(String firstName, String lastName, String userName, String password, String role) {
        String validationMessage = validateUserRegistration(firstName, lastName, userName, password, role);

        if (!validationMessage.isEmpty()) {
            return false; 
        }

        User user = userRepository.getUserByUsername(userName);

        if (user != null) {

            return false;
        }

        User newUser = null;


        newUser = new User(firstName, lastName, userName, password);

        userRepository.createUser(newUser);
        
        return true;
    }
    // Method for updating user profile (changing password only)
    public boolean handleUpdateUser(String oldUserName, String oldPassword, String newPassword) {
    	User user = userRepository.validateUser(oldUserName, oldPassword);
    	
    	if (user == null) {
    		// Old username or password is invalid
    		return false;
    	}
    	
    	// Update password with new values
  
        user.setPassword(newPassword);
        
        userRepository.updateUserProfile(user);
        
        
        return true;
    }
    
    public User handleUserLogin(String username, String password) {
        return userRepository.validateUser(username, password);
    }

    private String validateUserRegistration(String firstName, String lastName, String userName, String password, String role) {
        StringBuilder missingFields = new StringBuilder("Missing fields: ");

        if (firstName == null || firstName.trim().isEmpty()) missingFields.append("\nFirst Name, ");

        if (lastName == null || lastName.trim().isEmpty()) missingFields.append("\nLast Name, ");

        if (userName == null || userName.trim().isEmpty()) missingFields.append("\nUser Name, ");

        if (password == null || password.trim().isEmpty()) missingFields.append("\nPassword, ");

        if (role == null || role.trim().isEmpty()) missingFields.append("\nRole, ");
        

        if (missingFields.length() > "Missing fields: ".length()) {

            missingFields.setLength(missingFields.length() - 2);

            return missingFields.toString();

        } else {
            // Return empty string if no fields are missing
            return "";
        }
    }


}