package models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private static int nextId;
	private int id;
	private String name;
	private String surname;
	private String email;
	private List<Vehicol> vehicolList = new ArrayList<Vehicol>();
	
	public User(String name, String surname, String email) {
		this.id = nextId++;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public static int getNextId() {
		return nextId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Vehicol> getVehicolList() {
		return vehicolList;
	}

	public void setVehicolList(List<Vehicol> vehicolList) {
		this.vehicolList = vehicolList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((vehicolList == null) ? 0 : vehicolList.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (vehicolList == null) {
			if (other.vehicolList != null)
				return false;
		} else if (!vehicolList.equals(other.vehicolList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", vehicolList="
				+ vehicolList + "]";
	}
	
	public String descrizione() {
		return "Utente. "+name+" "+surname+" "+email+"\nElenco Veicoli noleggiati: "+vehicolList.toString();
	}
	
	
}
