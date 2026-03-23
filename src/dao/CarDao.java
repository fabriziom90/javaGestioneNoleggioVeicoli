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
import models.Car;

public class CarDao implements Storage<Car>, Salvable<Car>{
	List<Car> carsList = new ArrayList<Car>();
	
	@Override
	public List<Car> getAll() {
		List<Car> cars = new ArrayList<Car>(carsList);
		return cars;
	}

	@Override
	public Car findOneById(int id) {
		Car car = carsList.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
		return car;
	}

	@Override
	public boolean add(Car elem) {
		if(!carsList.contains(elem)) {
			carsList.add(elem);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Car elem) {
		Car car = carsList.stream().filter(c -> c.getId() == elem.getId()).findFirst().orElse(null);
		if(car != null) {
			car.setAvailable(elem.isAvailable());
			car.setBrand(elem.getBrand());
			car.setDoorsNumber(elem.getDoorsNumber());
			car.setFuelType(elem.getFuelType());
			car.setGear(elem.getGear());
			car.setName(elem.getName());
			car.setPlate(elem.getPlate());
			car.setYear(elem.getYear());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean remove(Car elem) {
		Car car = carsList.stream().filter(c -> c.getId() == elem.getId()).findFirst().orElse(null);
		if(car != null) {
			carsList.remove(car);
			return true;
		}
		return false;
	}
	
	@Override
	public void writeToFile(List<Car> elem) {
		List<String> data = carsList.stream().map(c -> c.getBrand().getName()+","+c.getName()+","+c.getPlate()+","+c.getFuelType()+","+c.isAvailable()).collect(Collectors.toList());
		
		Path filePath = Path.of("cars_list.csv");
		
		try {
			Files.write(filePath, data);
		}
		catch(IOException e) {
			System.out.println("Errore nella scrittura dei file: "+e.getMessage());
		}
	}

	@Override
	public void readFromFile() {
		Path filePath = Path.of("cars_list.csv");
		
		try(Stream<String> lines = Files.lines(filePath)){
			lines.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Errore nella lettura del file: "+e.getMessage());
		}
		
	}


}
