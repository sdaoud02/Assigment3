package mru.tsc.view;

import java.util.Scanner;
import mru.tsc.model.Toy;
import java.util.List;

public class View {
    private Scanner input;

    public View() {
        input = new Scanner(System.in);
    }
    
    /**
	 * Display welcome message
	 */
    
    public void welcome() {
        System.out.println("**********************************************");
        System.out.println("*    WELCOME TO TOY STORE COMPANY!    *");
        System.out.println("**********************************************");
    }

    /**
	 * Display main menu and get user option
	 * will show the menu once, and if the user enters a wrong answer it will have them try again
	 * with the prompt "Enter option" until the input is valid
	 * @return option as int from 1-4
	 */
    
    
    
    public int mainMenu() {
    	int option = -1;
        boolean validInput = false;
        
          
        System.out.println("\nHow We May Help You?");
        System.out.println("(1) Search Inventory and Purchase Toy");
        System.out.println("(2) Add New Toy");
        System.out.println("(3) Remove Toy");
        System.out.println("(4) Save & Exit");
        System.out.print("\nEnter Option: ");
        
        // will continue to loop until the user inputs a valid options
        while (!validInput) {
            if (input.hasNextInt()) {
                option = input.nextInt(); // checks for an int
                input.nextLine(); // newline char
                if (option >= 1 && option <= 4) { // has to be an option between 1-4
                    validInput = true; // exit loop if valid input
                } else {
                    showInvalidOption();
                    System.out.print("\nEnter Option: ");
                }
            } else {
                showInvalidNumberInput();
                input.next();
                System.out.print("\nEnter Option: ");
                
            }
        } 
        return option;
    }

    /**
 	 * Display search  menu and get user option has to be valid though
 	 * @return user option between 1-5
 	 */
    public int showSearchMenu() {
    	int option = -1;
    	
    	
        System.out.println("\nFind Toys With:");
        System.out.println("(1) Serial Number(SN)");
        System.out.println("(2) Toy Name");
        System.out.println("(3) Type");
        System.out.println("(4) Gift Suggestion (Bonus)");
        System.out.println("(5) Back to Main Menu");
        System.out.print("Enter Option: ");
        
        while (option < 1 || option > 5) { // will loop through for valid input
            if (input.hasNextInt()) { // checks for an int
                option = input.nextInt();
                input.nextLine();
                if (option < 1 || option > 5) { // checks if option is valid
                    showInvalidOption();
                    System.out.print("\nEnter Option: "); 
                }
            } else {
                showInvalidNumberInput();
                input.next();
                System.out.print("\nEnter Option: ");
            }
        }
       
        return option;
    }

    /**
     * The input methods. Will get the serial number from the user
     * @return a string serial number
     */
    public String getSerialNumberInput() {
        System.out.print("Enter Serial Number: ");
        return input.nextLine();
    }

    // get the toy name from the user
    public String getToyNameInput() {
        System.out.print("Enter Toy Name: ");
        return input.nextLine();
    }

    // get the toy brand from the user
    public String getBrandInput() {
        System.out.print("Enter Toy Brand: ");
        return input.nextLine();
    }

   
    /**
     * get the toy price from the user 
     * makes sure that it is a valid value
     * @return price as a double
     */
    public double getPriceInput() {
        System.out.print("Enter Toy Price: ");
        while (!input.hasNextDouble()) {
            showInvalidNumberInput();
            input.next();
        }
        double price = input.nextDouble();
        input.nextLine();
        return price;
    }

    /**
     * get the Available Counts from the user
     * ensures that it is a valid int number
     * @return Available Counts 
     */
    public int getAvailableCountInput() {
        System.out.print("Enter Available Counts: ");
        while (!input.hasNextInt()) {
            showInvalidNumberInput();
            input.next();
        }
        int count = input.nextInt();
        input.nextLine();
        return count;
    }
    /**
     * gets the Appropriate Age from the user
     * ensures that it is a valid int number
     * @return Appropriate Age as an int
     */

    public int getAgeAppropriateInput() {
        System.out.print("Enter Appropriate Age: ");
        while (!input.hasNextInt()) {
            showInvalidNumberInput();
            input.next();
        }
        int age = input.nextInt();
        input.nextLine();
        return age;
    }
    
    /**
     * gets the user to input the min players 
     * @return players as an int
     */
    public int getMinPlayersInput() {
        System.out.print("Enter Minimum Number of Players: ");
        while (!input.hasNextInt()) {
            showInvalidNumberInput();
            input.next();
        }
        int players = input.nextInt();
        input.nextLine();
        return players;
    }

    /**
     * gets the user to enter the max num of plays
     * @return players as an int
     */ 
    public int getMaxPlayersInput() {
        System.out.print("Enter Maximum Number of Players: ");
        while (!input.hasNextInt()) {
            showInvalidNumberInput();
            input.next();
        }
        int players = input.nextInt();
        input.nextLine();
        return players;
    }

    /**
     * gets the user to enter the designer names
     * @return a string designer name
     */
    public String getDesignersInput() {
        System.out.print("Enter Designer Names (Use ',' to separate names): ");
        return input.nextLine();
    }

    /**
     * gets Classification of figures
     * @return string
     */
    public String getClassificationInput() {
        System.out.print("Enter Classification (Action, Doll, Historic): ");
        return input.nextLine();
    }

    // Animal inputs
    public String getMaterialInput() {
        System.out.print("Enter Material: ");
        return input.nextLine();
    }

    public String getSizeInput() {
        System.out.print("Enter Size (S, M, L): ");
        return input.nextLine();
    }

    // Puzzle inputs
    public String getPuzzleTypeInput() {
        System.out.print("Enter Puzzle Type (Mechanical, Cryptic, Logic, Trivia, Riddle): ");
        return input.nextLine();
    }

    // Gift Suggestion inputs
    public String getAgeFilterInput() {
        System.out.print("Enter Age (or press Enter to skip): ");
        return input.nextLine();
    }

    public String getTypeFilterInput() {
        System.out.print("Enter Type (Figure, Animal, Puzzle, BoardGame) or press Enter: ");
        return input.nextLine();
    }

    public String getPriceRangeInput() {
        System.out.print("Enter Price Range (min-max) or press Enter: ");
        return input.nextLine();
    }

    // Display methods
    public void showSearchResults(List<Toy> toys) {
        System.out.println("\nHere are the search results:");
        for (int i = 0; i < toys.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + toys.get(i));
        }
        System.out.println("(" + (toys.size() + 1) + ") Back To Search Menu");
    }

    public void showGiftSuggestions(List<Toy> suggestions) {
        System.out.println("\nGift Suggestions:");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + suggestions.get(i));
        }
    }

    public void showToyDetails(Toy toy) {
        System.out.println("This Item Found:");
        System.out.println(toy);
    }

    // Success messages
    public void showTransactionSuccess() {
        System.out.println("The Transaction Successfully Terminated!");
    }

    public void showNewToyAdded() {
        System.out.println("New Toy Added!");
    }

    public void showItemRemoved() {
        System.out.println("Item Removed!");
    }

    public void showToysSaved() {
        System.out.println("Toys saved successfully!");
    }

    public void showSavingData() {
        System.out.println("\nSaving Data Into Database...");
    }

    public void showThankYou() {
        System.out.println("************ THANKS FOR VISITING US! ************");
    }

    // Error messages
    public void showSerialNumberExists() {
        System.out.println("A Toy With This Serial Number Already Exists! Try Again.");
    }

    public void showSerialNumberLengthError() {
        System.out.println("The Serial Number's length MUST be 10 digits! Try again.");
    }

    public void showSerialNumberDigitsError() {
        System.out.println("The Serial Number should only contain digits! Try again.");
    }

    public void showNoToysFound() {
        System.out.println("No toys found matching your criteria.");
    }

    public void showOutOfStock() {
        System.out.println("Sorry, this toy is out of stock.");
    }

    public void showToyNotFound() {
        System.out.println("Toy with the given serial number was not found.");
    }

    public void showInvalidOption() {
        System.out.println("This is not a valid option!. Try again..");
    }

    public void showInvalidNumberInput() {
        System.out.println("This is Not an Integer Number! Try again.");
    }

    public void showInvalidPriceRangeFormat() {
        System.out.println("Invalid price range format. Use format: min-max");
    }

    public void showMinPriceGreaterThanMax() {
        System.out.println("Minimum price cannot be greater than maximum price.");
    }

    public void showInvalidAge() {
        System.out.println("Invalid age. Skipping age filter.");
    }

    public void showInvalidType() {
        System.out.println("Invalid type. Please enter Figure, Animal, Puzzle, or BoardGame.");
    }

    public void showFileReadError(String message) {
        System.out.println("Error reading from toys.txt: " + message);
    }

    public void showFileWriteError(String message) {
        System.out.println("Error writing to toys.txt: " + message);
    }

    public void showRemovalCanceled() {
        System.out.println("Removal canceled.");
    }

    public void showPressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
    }

    public void showReturningToSearchMenu() {
        System.out.println("Returning to Search Menu...");
    }

    // Purchase choice
    public int getPurchaseChoice(int maxOptions) {
        System.out.print("Enter option number to purchase: ");
        int choice = -1;
        while (choice < 1 || choice > maxOptions + 1) {
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice < 1 || choice > maxOptions + 1) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + (maxOptions + 1) + ".");
                }
            } else {
                showInvalidNumberInput();
                input.next();
            }
        }
        return choice;
    }

    public int getGiftPurchaseChoice(int maxOptions) {
        System.out.print("Enter option number to purchase or 0 to go back: ");
        int choice = -1;
        while (choice < 0 || choice > maxOptions) {
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice < 0 || choice > maxOptions) {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + maxOptions + ".");
                }
            } else {
                showInvalidNumberInput();
                input.next();
            }
        }
        return choice;
    }

    public String getRemoveConfirmation() {
        System.out.print("Do you want to remove it (Y/N)? ");
        return input.nextLine().trim().toLowerCase();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}