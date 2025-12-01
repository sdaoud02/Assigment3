package mru.tsc.model;

public class Animal extends Toy {
 private String material;
 private String size;  

 /**
  * Constructor for Animal class
  * @param serialNumber
  * @param name
  * @param brand
  * @param price
  * @param availableCount
  * @param ageAppropriate
  * @param material
  * @param size
  */
 public Animal(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, String material, String size) 
	    {super(serialNumber, name, brand, price, availableCount, ageAppropriate);
	    this.material = material;
	    this.size = size;
 }

 // Getters and Setters
 public String getMaterial() { 
	 return material; }
 
 public void setMaterial(String material) {
	 this.material = material; }

 public String getSize() { 
	 return size; }
 
 public void setSize(String size) { 
	 this.size = size; }
 
 /**
  * Get category of the toy
  * @return category as String
  */
 
 @Override
 public String getCategory() {
     return "Animal";
 }
 
 /**
  * String representation of the Animal object
  * @return String representation
  */
 @Override
 public String toString() {
     return	"Category:Animal, " +  super.toString() + ", Material: " + material + ", Size: " + size;
 }

 /**
  * Format the Animal object for storage
  * @return formatted String
  */
 @Override
 public String format() {
     return serialNumber + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";+" + ageAppropriate + ";" + material + ";" + size;
 }
}

