package test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.*;

import dao.UserDao;
import models.User;

class UserTest {
	
	private UserDao userDao;
	@BeforeEach
	void setUp() {
		userDao = new UserDao();
		try {
			Files.deleteIfExists(Path.of("users_list.csv"));
		}
		catch(Exception e) {
			System.out.println("Errore nell'esecuzione del test: "+e.getMessage());
		}
	}
	
	@Test
	void testAddUser() {
		User user = new User("Mario", "Rossi", "mariorossi@mail.com");
		assertTrue(userDao.add(user), "Utente aggiunto");
		assertEquals(1, userDao.getAll().size(), "Lista utenti aggiornata");
	}

}
