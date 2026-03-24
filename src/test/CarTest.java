package test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.*;

import dao.CarDao;
import models.Brand;
import models.Car;
import models.FuelType;
import models.Gear;

class CarTest {
	CarDao carDao;
	Brand bmw; 
	Car car1;
	
	@BeforeEach
	void setUp() {
		carDao = new CarDao();
		try {
			Files.deleteIfExists(Path.of("cars_list.csv"));
		}
		catch(Exception e) {
			System.out.println("Errore nell'esecuzione del test: "+e.getMessage());
		}
	}
	
	@Test
	void testAddUser() {
		bmw = new Brand("BMW", "Germany");
        car1 = new Car(2020, "AA123BB", bmw, "Serie 3", true, 4, FuelType.GAS, Gear.AUTO);
        boolean added = carDao.add(car1);
        assertTrue(added);
        assertEquals(1, carDao.getAll().size());
	}

}
