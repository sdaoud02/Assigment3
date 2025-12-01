package mru.tsc.model;

 
public class Puzzle extends Toy {
 private String puzzleType;   


 /**
  * Constructor for Puzzle
  * @param serialNumber
  * @param name
  * @param brand
  * @param price
  * @param availableCount
  * @param ageAppropriate
  * @param puzzleType
  */
 
  public Puzzle(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, String puzzleType) {
     super(serialNumber, name, brand, price, availableCount, ageAppropriate);
     this.puzzleType = puzzleType;
 }

  // Getters and Setters
 public String getPuzzleType() { 
	 return puzzleType; }
 
 public void setPuzzleType(String puzzleType) {
	 this.puzzleType = puzzleType; }

 
 /**
  * Get category of the toy
  * @return category as String
  */
 @Override
 public String getCategory() {
     return "Puzzle";
 }
 
 /**
  * String representation of Puzzle
  * @return String
  */
 
  @Override
 public String toString() {
     return "Category:Puzzle, " +  super.toString() + ", Puzzle Type: " + puzzleType;
 }

  /**
   * Format Puzzle for storage
   * @return formatted String
   */
  @Override
 public String format() {
     return serialNumber + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";+" + ageAppropriate + ";" + puzzleType;
 }
}