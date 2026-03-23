package models;

public class Bike extends Vehicol{
	private boolean sidecar;
	private EngineType engineType;
	
	public Bike(String plate, int year, Brand brand, String name, boolean available, boolean sidecar, EngineType engineType) {
		super(plate, year, brand, name, available);
		this.sidecar = sidecar;
		this.engineType = engineType;
	}
	public boolean isSidecar() {
		return sidecar;
	}
	public void setSidecar(boolean sidecar) {
		this.sidecar = sidecar;
	}
	public EngineType getEngineType() {
		return engineType;
	}
	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}
	
	@Override
	public String toString() {
		return super.toString()+" Bike=[sidecar=" + (sidecar ? "Disponibile" : "Non disponibile") + ", engineTyèe=" + engineType +  "]";
	}
	
	public String description() {
		return "Auto. "+super.getBrand().getName()+"  "+super.getName()+" "+super.getYear()+" "+super.getPlate()+" sidecar: "+(sidecar ? "Disponibile" : "Non disponibile")+" "+engineType+" "+(super.isAvailable() ? "Disponibile" : "Non disponibile");
	}
	
	
	
}
