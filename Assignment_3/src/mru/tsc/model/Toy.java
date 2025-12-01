package mru.tsc.model;

public abstract class Toy {
	protected String serialNumber;
	protected String name;
	protected String brand;
	protected double price;
	protected int availableCount;
	protected int ageAppropriate;

	public Toy(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.availableCount = availableCount;
		this.ageAppropriate = ageAppropriate;
	}

	// Getters
	public String getSerialNumber() { 
		return serialNumber; }
	public String getName() { 
		return name; }
	public String getBrand() { 
		return brand; }
	public double getPrice() { 
		return price; }
	public int getAvailableCount() { 
		return availableCount; }
	public int getAgeAppropriate() {
		return ageAppropriate; }

	//setters
	public void setSerialNumber(String serialNumber) { 
		this.serialNumber = serialNumber; }
	
	public void setName(String name) { 
		this.name = name; }
	
	public void setBrand(String brand) { 
		this.brand = brand; }
	
	public void setPrice(double price) {
		this.price = price; }
	
	public void setAvailableCount(int availableCount) { 
		this.availableCount = availableCount; }
	
	public void setAgeAppropriate(int ageAppropriate) {
		this.ageAppropriate = ageAppropriate; }
	

/**
* Format for 
* @return formatted String
*/
	@Override
	public String toString() {
		return String.format("Serial Number: %s, Name: %s, Brand: %s, Price: $%.2f, Available: %d, Age: %d+", 
				serialNumber, name, brand, price, availableCount, ageAppropriate);
	}
 
public abstract String getCategory();

	public abstract String format();
}