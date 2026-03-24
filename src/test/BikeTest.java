package test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BikeDao;
import models.Bike;
import models.Brand;
import models.EngineType;


class BikeTest {

	private BikeDao bikeDao;
	Brand ducati;
	Bike bike1;
	
	@BeforeEach
	void setUp() {
		bikeDao = new BikeDao();
		try {
			Files.deleteIfExists(Path.of("users_list.csv"));
		}
		catch(Exception e) {
			System.out.println("Errore nell'esecuzione del test: "+e.getMessage());
		}
	}
	
	@Test
	void testAddUser() {
		bikeDao = new BikeDao();
        ducati = new Brand("Ducati", "Italy");
        bike1 = new Bike("D123AB", 2021, ducati, "Panigale V4", true, false, EngineType.FOUR_STROKE);
		assertTrue(bikeDao.add(bike1), "Utente aggiunto");
		assertEquals(1, bikeDao.getAll().size(), "Lista utenti aggiornata");
	}

}
