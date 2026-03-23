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
import models.Brand;

public class BrandDao implements Storage<Brand>, Salvable<Brand> {

	List<Brand> brandsList = new ArrayList<Brand>();
	
	@Override
	public List<Brand> getAll() {
		List<Brand> brands = new ArrayList<Brand>(brandsList);
		return brands;
	}

	@Override
	public Brand findOneById(int id) {
		Brand brand = brandsList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
		return brand;
	}

	@Override
	public boolean add(Brand elem) {
		if(!brandsList.contains(elem)) {
			brandsList.add(elem);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Brand elem) {
		Brand brand = brandsList.stream().filter(b -> b.getId() == elem.getId()).findFirst().orElse(null);
		if(brand != null) {			
			brand.setName(elem.getName());
			brand.setCountry(elem.getCountry());
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Brand elem) {
		Brand brand = brandsList.stream().filter(b -> b.getId() == elem.getId()).findFirst().orElse(null);
		
		if(brand != null) {
			brandsList.remove(brand);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void writeToFile(List<Brand> elem) {
		List<String> data = brandsList.stream().map(b -> b.getName()+","+b.getCountry()).collect(Collectors.toList());
		
		Path filePath = Path.of("brands_list.csv");
		
		try {
			Files.write(filePath, data);
		}
		catch(IOException e) {
			System.out.println("Errore nella scrittura del file: "+e.getMessage());
		}
		
	}

	@Override
	public void readFromFile() {
		Path filePath = Path.of("brands_list.csv");
		
		try(Stream<String> lines = Files.lines(filePath)){
			lines.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Errore nella lettura del file: "+e.getMessage());
		}
		
	}

}
