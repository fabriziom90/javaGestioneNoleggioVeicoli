package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import interfaces.Salvable;
import interfaces.Storage;
import models.Bike;
import models.Car;

public class BikeDao implements Salvable<Bike>, Storage<Bike> {
	List<Bike> bikesList = new ArrayList<Bike>();

	@Override
	public List<Bike> getAll() {
		List<Bike> bikes = new ArrayList<Bike>(bikesList);
		return bikes;
	}

	@Override
	public Bike findOneById(int id) {
		Bike bike = bikesList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
		return bike;
	}
	
	@Override
	public Bike findOneBy(String string) {
		Bike bike = bikesList.stream().filter(b -> b.getPlate().toLowerCase().equals(string.toLowerCase())).findFirst().orElse(null);
		return bike;
	}

	@Override
	public boolean add(Bike elem) {
		if(!bikesList.contains(elem)) {
			bikesList.add(elem);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Bike elem) {
		Bike bike = bikesList.stream().filter(b -> b.getId() == elem.getId()).findFirst().orElse(null);
		if(bike != null) {
			bike.setBrand(elem.getBrand());
			bike.setName(elem.getName());
			bike.setAvailable(elem.isAvailable());
			bike.setPlate(elem.getPlate());
			bike.setSidecar(elem.isSidecar());
			bike.setYear(elem.getYear());
			bike.setEngineType(elem.getEngineType());
			
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Bike elem) {
		Bike bike = bikesList.stream().filter(b -> b.getId() == elem.getId()).findFirst().orElse(null);
		
		if(bike != null) {
			bikesList.remove(bike);
			return true;
		}
		
		return false;
	}

	@Override
	public void writeToFile(List<Bike> elem) {
		List<String> data = bikesList.stream().map(b -> b.getId()+","+b.getBrand().getName()+","+b.getName()+","+b.getPlate()+","+b.getYear()+","+b.getEngineType()+","+(b.isAvailable()? "Disponibile" : "Non disponibile")).collect(Collectors.toList());
		
		Path filePath = Path.of("bikes_list.csv");
		
		try {
			Files.write(filePath, data);
		}
		catch(IOException e) {
			System.out.println("Errore nella scrittura del file: "+e.getMessage());
		}
		
	}

	@Override
	public void readFromFile() {
		Path filePath = Path.of("bikes_list.csv");
		
		try(Stream<String> lines = Files.lines(filePath)){
			lines.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Errore nella lettura del file: "+e.getMessage());
		}
		
	}
	
	
}
