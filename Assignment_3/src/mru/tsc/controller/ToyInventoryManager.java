package mru.tsc.controller;

import mru.tsc.exceptions.PriceException;
import mru.tsc.exceptions.PlayerException;
import mru.tsc.model.*;
import mru.tsc.view.View;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyInventoryManager {
    private List<Toy> toys;
    private GiftSuggestion giftSuggestion;

    // constructor will initialize the empty toy list and gift suggestion
    public ToyInventoryManager() {
        toys = new ArrayList<>();
        giftSuggestion = new GiftSuggestion(toys);
    }
   
    public List<Toy> getToys() {
        return toys;
    }

    public void makeGiftSuggestion(Scanner scanner, View view) {
        giftSuggestion.suggestGift(scanner, view);
    }

    /**
     * checks to see if toy with the serial number already exists 
     * @param serialNumber checks the serial number
     * @return will return if true if the toy already exists, otherwise will return false
     */
    private boolean toyExists(String serialNumber) {
        for (Toy toy : toys) {
            if (toy.getSerialNumber().equals(serialNumber)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * loads toy from the file
     * @param view will display the view message
     */

    public void loadToysFromFile(View view) {
        try (BufferedReader reader = new BufferedReader(new FileReader("res/toys.txt"))) {
            String line;
            // reads each line from the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String serialNumber = data[0];
                String name = data[1];
                String brand = data[2];
                double price = Double.parseDouble(data[3]);
                int availableCount = Integer.parseInt(data[4]);
                int ageAppropriate = Integer.parseInt(data[5]);

                Toy toy = null;
                // creates a toy based on serial number prefix
                switch (serialNumber.charAt(0)) {
                case '0':
                case '1':
                    String classification = data[6];
                    toy = new Figure(serialNumber, name, brand, price, availableCount, ageAppropriate, classification);
                    break;
                case '2':
                case '3':
                    String material = data[6];
                    String size = data[7];
                    toy = new Animal(serialNumber, name, brand, price, availableCount, ageAppropriate, material, size);
                    break;
                case '4':
                case '5':
                case '6':
                    String puzzleType = data[6];
                    toy = new Puzzle(serialNumber, name, brand, price, availableCount, ageAppropriate, puzzleType);
                    break;
                case '7':
                case '8':
                case '9':
                    String[] playersRange = data[6].split("-");
                    int minPlayers = Integer.parseInt(playersRange[0].trim());
                    int maxPlayers = Integer.parseInt(playersRange[1].trim());
                    String designers = data[7];
                    toy = new BoardGame(serialNumber, name, brand, price, availableCount, ageAppropriate, minPlayers, maxPlayers, designers);
                    break;
                }
                // validiates toy in the inventory
                if (toy != null) {
                    toys.add(toy);
                }
            }
        } catch (IOException e) {
            view.showFileReadError(e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            view.showMessage("Error in file format: " + e.getMessage());
        }
    }

    /**
     * with the users input if valid will add a new toy to the inventory 
     * @param scanner Scans for user input
     * @param view	
     */
    public void addNewToy(Scanner scanner, View view) { 
        String serialNumber;
        String type = "";
    	// validates the users input for the serial number

        while (true) {

            serialNumber = view.getSerialNumberInput();
            
            // checks to see if there is already an existing serial number
            if (toyExists(serialNumber)) {
                view.showSerialNumberExists();
                continue;
            }
            // checks if the length is valid
            if (serialNumber.length() != 10) {
                view.showSerialNumberLengthError();
                continue;
            }
            // checks if user inputs the correct digits
            if (!serialNumber.matches("\\d+")) {
                view.showSerialNumberDigitsError();
                continue;
            }
            
            // toy type based on serial number and the first digit
            char firstDigit = serialNumber.charAt(0);
            switch (firstDigit) {
                case '0':
                case '1':
                    type = "figure";
                    break;
                case '2':
                case '3':
                    type = "animal";
                    break;
                case '4':
                case '5':
                case '6':
                    type = "puzzle";
                    break;
                case '7':
                case '8':
                case '9':
                    type = "boardgame";
                    break;
                default:
                    view.showMessage("Invalid serial number format.");
                    continue;
            }
            break;
        }

        // gets the toy info
        String name = view.getToyNameInput();
        String brand = view.getBrandInput();

        // checks for valid price from user
        double price;
        while (true) {
            price = view.getPriceInput();
            try {
                if (price < 0) {
                    throw new PriceException();
                }
                break;
            } catch (PriceException e) {
                view.showMessage(e.getMessage());
            }
        }

        // gets the inventory and age info 
        int availableCount = view.getAvailableCountInput();
        int ageAppropriate = view.getAgeAppropriateInput();

        // creates toy type based on the classification
        Toy toy = null;
        switch (type) {
            case "boardgame":
                int minPlayers = view.getMinPlayersInput();
                int maxPlayers = view.getMaxPlayersInput();
                
                // checks for valid player range
                try {
                    if (minPlayers > maxPlayers) {
                        throw new PlayerException();
                    }
                } catch (PlayerException e) {
                    view.showMessage(e.getMessage());
                    return;
                }
                String designers = view.getDesignersInput();
                toy = new BoardGame(serialNumber, name, brand, price, availableCount, ageAppropriate, minPlayers, maxPlayers, designers);
                break;
                
            case "figure":
                String classification = view.getClassificationInput();
                toy = new Figure(serialNumber, name, brand, price, availableCount, ageAppropriate, classification);
                break;
                
            case "animal":
                String material = view.getMaterialInput();
                String size = view.getSizeInput();
                toy = new Animal(serialNumber, name, brand, price, availableCount, ageAppropriate, material, size);
                break;
                
            case "puzzle":
                String puzzleType = view.getPuzzleTypeInput();
                toy = new Puzzle(serialNumber, name, brand, price, availableCount, ageAppropriate, puzzleType);
                break;
        }
        // add the new toy to the inventory list
        if (toy != null) {
            toys.add(toy);
            view.showNewToyAdded();
        }
    }

    /**
     * saves all toys to the file using the correct format
     * @param view	displays message
     */
    public void saveToysToFile(View view) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/toys.txt"))) {
            for (Toy toy : toys) {
                writer.write(toy.format());
                writer.newLine();
            }
            view.showToysSaved();
        } catch (IOException e) {
            view.showFileWriteError(e.getMessage());
        }
    }

    /**
     * hands the toy search and purchase
     * @param scanner	scans for user input
     * @param view
     */
    public void searchAndPurchaseToy(Scanner scanner, View view) {
        int searchOption;
        
        do {
        	searchOption = view.showSearchMenu();
        
        	// if user choses to go back it will exist
        if (searchOption == 5) 
            return;
        	// handles the gift suggestion option
        if (searchOption == 4) {
            makeGiftSuggestion(scanner, view);
            continue;
    
        }
        // searches for toys based on the criteria
        List<Toy> matchingToys = new ArrayList<>();
        switch (searchOption) {
            case 1:
                String serialNumber = view.getSerialNumberInput();
                for (Toy toy : toys) {
                    if (toy.getSerialNumber().equals(serialNumber)) matchingToys.add(toy);
                }
                break;
            case 2:
                String name = view.getToyNameInput();
                for (Toy toy : toys) {
                    if (toy.getName().toLowerCase().contains(name.toLowerCase())) matchingToys.add(toy);
                }
                break;
            case 3:
            	// search by toy type
                String type = view.getTypeFilterInput().toLowerCase();
                for (Toy toy : toys) {
                    if ((type.equals("figure") && toy instanceof Figure) ||
                        (type.equals("animal") && toy instanceof Animal) ||
                        (type.equals("puzzle") && toy instanceof Puzzle) ||
                        (type.equals("boardgame") && toy instanceof BoardGame)) {
                        matchingToys.add(toy);
                    }
                }
                break;
        }

        // handles the search results
        if (matchingToys.isEmpty()) {
            view.showNoToysFound();
        } else {
            view.showSearchResults(matchingToys);
            int choice = view.getPurchaseChoice(matchingToys.size());
            
            // if user selects toy it will process the purchase 
            if (choice > 0 && choice <= matchingToys.size()) {
                Toy selectedToy = matchingToys.get(choice - 1);
                if (selectedToy.getAvailableCount() > 0) {
                    selectedToy.setAvailableCount(selectedToy.getAvailableCount() - 1);
                    view.showTransactionSuccess();
                } else {
                    view.showOutOfStock();}
            } else {
                view.showReturningToSearchMenu();
        }
               
    }
        if (searchOption !=5) {
        	view.showPressEnterToContinue();
        	scanner.nextLine();
        }
        }
        while (searchOption !=5);
        
    }

    /**
     * removes toys from the inventory
     * @param scanner	scans for user input
     * @param view
     */
    public void removeToy(Scanner scanner, View view) {
        String serialNumber = view.getSerialNumberInput();

        // finds the toy to remove
        Toy toyToRemove = null;
        for (Toy toy : toys) {
            if (toy.getSerialNumber().equals(serialNumber)) {
                toyToRemove = toy;
                break;
            }
        }

        // handles the remove process
        if (toyToRemove != null) {
            view.showToyDetails(toyToRemove);
            
            // gets confirmation from the user
            String confirmation;
            while (true) {
                confirmation = view.getRemoveConfirmation();

                if (confirmation.equals("y")) {
                    toys.remove(toyToRemove);
                    view.showItemRemoved();
                    break;
                } else if (confirmation.equals("n")) {
                    view.showRemovalCanceled();
                    break;
                } else {
                    view.showMessage("Invalid input. Please enter 'Y' or 'N'.");
                }
            }
        } else {
            view.showToyNotFound();
        }
    }
}