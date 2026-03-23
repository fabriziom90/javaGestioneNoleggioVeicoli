package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.Storage;
import models.User;

public class UserDao implements Storage<User> {
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
	
}
