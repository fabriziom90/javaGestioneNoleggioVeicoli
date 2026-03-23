package models;

public class Car extends Vehicol{
	private int doorsNumber;
	private FuelType fuelType;
	private Gear gear;
	
	public Car(int id, int year, String plate, Brand brand, String name, boolean available, int doorsNumber, FuelType fuelType, Gear gear) {
		super(plate, year, brand, name, available);
		this.fuelType = fuelType;
		this.gear = gear;
	}

	public int getDoorsNumber() {
		return doorsNumber;
	}

	public void setDoorsNumber(int doorsNumber) {
		this.doorsNumber = doorsNumber;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	@Override
	public String toString() {
		return super.toString()+" Car=[doorsNumber=" + doorsNumber + ", fuelType=" + fuelType + ", gear=" + gear + "]";
	}
	
	public String description() {
		return "Auto. "+super.getBrand().getName()+"  "+super.getName()+" "+super.getYear()+" "+super.getPlate()+" numero porte: "+doorsNumber+" "+gear+" "+fuelType+(super.isAvailable() ? "Disponibile" : "Non disponibile");
	}

	
	
	
}
