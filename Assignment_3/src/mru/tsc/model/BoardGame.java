package mru.tsc.model;

public class BoardGame extends Toy {
 private int minPlayers;
 private int maxPlayers;
 private String designers;

 /**
  * Constructor for BoardGame
  * @param serialNumber
  * @param name
  * @param brand
  * @param price
  * @param availableCount
  * @param ageAppropriate
  * @param minPlayers
  * @param maxPlayers
  * @param designers
  */
 public BoardGame(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, int minPlayers, int maxPlayers, String designers)
 	{
	 super(serialNumber, name, brand, price, availableCount, ageAppropriate);
     this.minPlayers = minPlayers;
     this.maxPlayers = maxPlayers;
     this.designers = designers;
 }

 // Getters and Setters

 public int getMinPlayers() 
 { return minPlayers; }
 
 public void setMinPlayers(int minPlayers) 
 { this.minPlayers = minPlayers; }

 public int getMaxPlayers()
 { return maxPlayers; }
 
 public void setMaxPlayers(int maxPlayers)
 { this.maxPlayers = maxPlayers; }

 public String getDesigners() 
 { return designers; }
 
 public void setDesigners(String designers)
 { this.designers = designers; }

 /**
  * Get category of the toy
  * @return category as String
  */
 
 @Override
 public String getCategory() {
     return "BoardGame";
 }
 @Override
 
 /**
  * String representation of BoardGame
  * @return String
  */

 public String toString() {
     return "Category:BoardGame, " + super.toString() + ", Players: " + minPlayers + "-" + maxPlayers + ", Designers:" + designers;
 }
 
 /**
  * Format BoardGame for storage
  * @return formatted String
  */
 @Override
 public String format() {
     return serialNumber + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";+" + ageAppropriate + ";" + minPlayers + "-" + maxPlayers + ";" + designers;
 }
}
