package mru.tsc.controller;

import mru.tsc.model.*;
import mru.tsc.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GiftSuggestion {
    private List<Toy> toys;

    /**
     * constructor that will initialize the toy lists
     * @param toys
     */
    public GiftSuggestion(List<Toy> toys) {
        this.toys = toys;
    }

    
    /**
     *  will suggest gifts based on the filters
     * @param scanner
     * @param view
     */
    public void suggestGift(Scanner scanner, View view) {
    	// validates the age filter
    	Integer age = null; 	 
        String ageInput = view.getAgeFilterInput();
        if (!ageInput.isEmpty()) {
            try {
                age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                view.showInvalidAge();
            }
        }
        // will get the type filter
        String type = view.getTypeFilterInput().toLowerCase();

        // validates the price range 
        Double minPrice = null, maxPrice = null;
        String priceRange = view.getPriceRangeInput();
        if (!priceRange.isEmpty()) {
            String[] prices = priceRange.split("-");
            if (prices.length == 2) {
                try {
                    minPrice = Double.parseDouble(prices[0].trim());
                    maxPrice = Double.parseDouble(prices[1].trim());
                    if (minPrice > maxPrice) {
                        view.showMinPriceGreaterThanMax();
                        minPrice = null;
                        maxPrice = null;
                    }
                } catch (NumberFormatException e) {
                    view.showInvalidPriceRangeFormat();
                }
            } else {
                view.showInvalidPriceRangeFormat();
            }
        }

        // filters the toys based on the criteraia 
        List<Toy> suggestions = new ArrayList<>();
        for (Toy toy : toys) {
            boolean matches = true;
            
            // checks for AgeAppropriate
            if (age != null && toy.getAgeAppropriate() > age) {
                matches = false;
            }
            // checks for the type match
            if (!type.isEmpty() && !typeMatchesToy(type, toy)) {
                matches = false;
            }
            // checks the price range
            if (minPrice != null && maxPrice != null && (toy.getPrice() < minPrice || toy.getPrice() > maxPrice)) {
                matches = false;
            }
            // if in stocks and matches criteria will add to suggests 
            if (matches && toy.getAvailableCount() > 0) {
                suggestions.add(toy);
            }
        }
        // will display results 
        if (suggestions.isEmpty()) {
            view.showNoToysFound();
        } else {
            view.showGiftSuggestions(suggestions);
            int choice = view.getGiftPurchaseChoice(suggestions.size());
            
            // if user picks a toy it will process the purchase
            if (choice > 0) {
                Toy selectedToy = suggestions.get(choice - 1);
                if (selectedToy.getAvailableCount() > 0) {
                    selectedToy.setAvailableCount(selectedToy.getAvailableCount() - 1);
                    view.showTransactionSuccess();
                } else {
                    view.showOutOfStock();
                }
            }
        }
    }
    
    /**
     * will checks if a toy does matches the specified type
     * @param type to check against
     * @param toy to check
     * @return	if toy matches the type then it is true otherwise will be false
     */

    private boolean typeMatchesToy(String type, Toy toy) {
        switch (type) {
            case "figure": 
                return toy instanceof Figure;
            case "animal": 
                return toy instanceof Animal;
            case "puzzle":
                return toy instanceof Puzzle;
            case "boardgame":
                return toy instanceof BoardGame;
            default: return false;
        }
    }
}