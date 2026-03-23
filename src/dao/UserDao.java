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
import models.User;

public class UserDao implements Storage<User>, Salvable<User> {
	List<User> usersList = new ArrayList<User>();
	
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>(usersList);
		return users;
	}

	@Override
	public User findOneById(int id) {
		User user = usersList.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
		return user;
	}

	@Override
	public boolean add(User elem) {
		if(!usersList.contains(elem)) {
			usersList.add(elem);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean update(User elem) {
		User user = usersList.stream().filter(u -> u.getId() == elem.getId()).findFirst().orElse(null);
		if(user != null) {
			user.setEmail(elem.getEmail());
			user.setName(elem.getEmail());
			user.setSurname(elem.getEmail());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean remove(User elem) {
		User user = usersList.stream().filter(u -> u.getId() == elem.getId()).findFirst().orElse(null);
		if(user != null) {
			usersList.remove(user);
			return true;
		}
		
		return false;
	}

	@Override
	public void writeToFile(List<User> usersList) {
		List<String> data = usersList.stream().map(u -> u.getName()+","+u.getSurname()+","+u.getEmail()).collect(Collectors.toList());
		
		Path filePath = Path.of("users_list.csv");
		try {
			Files.write(filePath, data);
		}
		catch(IOException e) {
			System.out.println("Errore nella scrittura dei file degli utenti: "+e.getMessage());
		}
		
	}

	@Override
	public void readFromFile() {
		Path filePath = Path.of("users_list.csv");
		
		try (Stream<String> lines = Files.lines(filePath)){
			lines.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Errore nella lettura del file degli utenti: "+e.getMessage());
		}
		
	}
	
	
	
}
