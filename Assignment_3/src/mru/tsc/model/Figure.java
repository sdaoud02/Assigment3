package mru.tsc.model;

public class Figure extends Toy {
    private String classification;   

     public Figure(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, String classification) {
        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
        this.classification = classification;
    }

     /**
      * Constructor for Figure
      * @param serialNumber
      * @param name
      * @param brand
      * @param price
      * @param availableCount
      * @param ageAppropriate
      * @param classification
      */
     
     // Getters and Setters

 public String getClassification()
 { return classification; }
 
 public void setClassification(String classification) 
 { this.classification = classification; }
 
 
 /**
  * Get category of the toy
  * @return category as String
  */
 @Override
 public String getCategory() {
     return "Figure";
 }
 
 /**
  * String representation of Figure
  * @return String
  */
 
  @Override
 public String toString() {
     return "Category:Figure, " +  super.toString() + ", Classification: " + classification;
 }

  /**
   * Format Figure for storage
   * @return formatted String
   */
  @Override
 public String format() {
     return serialNumber + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";+" + ageAppropriate + ";" + classification;
 }
}