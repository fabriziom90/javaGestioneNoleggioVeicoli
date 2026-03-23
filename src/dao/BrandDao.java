package dao;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		
	}

}
