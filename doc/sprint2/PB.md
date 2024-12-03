
1.	User Story 1: Register as new user and Sign in with password setup at the time of registration (Sign In / Login In)
Tasks: 
-First time user needs to register (Sign up) for the app. 
-Once signed up, Username and Password is stored in database. 
-Next time user will login the credentials set up at the time of registration. 
Description: Implement secure login functionality and set up a database to store eligible user credentials.
Objective: Research and select a secure and efficient method to implement user authentication.

2.	User Story 2: Search a book
Tasks:
-Develop search functionality: When you search name of a book, It shows the no. of results and then the names from the database. 
Description: Create a search interface to allow users to search for a book, with results fetched from the database.
Objective: Determine the best approach for integrating a database to manage book inventory and status updates and general info.

3.	User Story 3: Checking inventory- Librarian POV: Here librarians can see all the books owned by the library and check all the list of books along with their quantity and the status of books -whether they are in stock or already rented, who has the book, when is it due and other info.  
Tasks:
-Accessing all the books in the database
-Checking the status of the books: In stock or already on rent
Description: Implement a system for librarians to view all books, their quantities, and their status (in stock or rented), who has the book, when is it due and other info.  
Objective: Explore methods for efficient querying of books in the database based on user input.

4. User Story 4: Customer Checkout Book
As a library customer, I want to check out a book from the library
Tasks:
-Implement checkout functionality where a user searches and selects a book to borrow.
-Update the inventory to mark the book as checkout.
Description: Enable users to borrow books from the library by searching and selecting a book, and ensure that the inventory reflects the status of the book as checked out.
Objective: Design a user-friendly and efficient checkout process while ensuring accurate inventory updates.

6. User Story 5: User interface for librarians
As a librarian, I want an interface to view and manage library inventory.
Tasks:
-Create a dashboard for librarians to view all books and their statuses
-Allow actions such as marking books as returned and viewing overdue books.
Description: Build a librarian-specific interface for efficient inventory management, enabling librarians to view the complete list of books, their availability, and overdue information, and perform actions such as marking books as returned.
Objective: Develop an intuitive and comprehensive interface for librarians to manage library resources effectively.

8. User Story 6: Customer Return Book
As a library customer, I want to return a book so that it is available for others to borrow.
Task:
-	Implement a return functionality that updates the book’s status and removes it from the user's checkout list.
-	Add logic to handle overdue returns 
Description: Facilitate the process for customers to return borrowed books, ensuring the book’s availability status is updated in the inventory and any overdue conditions are appropriately managed.
Objective: Create a smooth return process that integrates with the library system for accurate status updates and overdue handling.

