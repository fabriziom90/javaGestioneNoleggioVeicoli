package models;

public abstract class Vehicol {
	private static int nextId = 1;
	private int id;
	private String plate;
	private int year;
	private Brand brand;
	private boolean available;
	
	public Vehicol(String plate, int year, Brand brand, boolean available) {
		this.id = nextId++;
		this.plate = plate;
		this.year = year;
		this.brand = brand;
		this.available = available;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public static int getNextId() {
		return nextId;
	}

	public int getId() {
		return id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plate == null) ? 0 : plate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicol other = (Vehicol) obj;
		if (plate == null) {
			if (other.plate != null)
				return false;
		} else if (!plate.equals(other.plate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicol [id=" + id + ", plate=" + plate + ", year=" + year + ", available=" + available + "]";
	}
	
	public String description() {
		return "Anno: "+this.year+", disponibile: " +(this.available ? "Sì" : "No")+ " targa: "+this.plate; 
	}
}
