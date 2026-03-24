package manager;

import java.util.List;
import java.util.Scanner;

import dao.BikeDao;
import dao.BrandDao;
import dao.CarDao;
import dao.UserDao;
import models.Bike;
import models.Brand;
import models.Car;
import models.EngineType;
import models.FuelType;
import models.Gear;
import models.User;

public class GarageService {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        CarDao carDao = new CarDao();
        BrandDao brandDao = new BrandDao();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== GESTIONE GARAGE ===");
            System.out.println("1. Mostra utenti");
            System.out.println("2. Mostra auto");
            System.out.println("3. Mostra moto");
            System.out.println("4. Aggiungi utente");
            System.out.println("5. Aggiungi auto");
            System.out.println("6. Aggiungi moto");
            System.out.println("7. Assegna veicolo ad un utente");
            System.out.println("8. Esci");
            System.out.print("Scegli un'opzione: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("--- Utenti ---");
                    List<User> users = userDao.getAll();
                    users.forEach(u -> System.out.println(u.getName() + " " + u.getSurname() + " | " + u.getEmail()));
                    break;

                case 2:
                    System.out.println("--- Auto ---");
                    List<Car> cars = carDao.getAll();
                    cars.forEach(c -> System.out.println(c.getBrand().getName() + " " + c.getName() + " | " + c.getPlate()));
                    break;

                case 3:
                    System.out.println("--- Moto ---");
                    List<Bike> bikes = bikeDao.getAll();
                    bikes.forEach(b -> System.out.println(b.getBrand().getName() + " " + b.getName() + " | " + b.getPlate()));
                    break;

                case 4:
                    System.out.println("Inserisci nome utente:");
                    String name = scanner.nextLine();
                    System.out.println("Inserisci cognome utente:");
                    String surname = scanner.nextLine();
                    System.out.println("Inserisci email utente:");
                    String email = scanner.nextLine();
                    userDao.add(new User(name, surname, email));
                    userDao.writeToFile(userDao.getAll());
                    System.out.println("Utente aggiunto e salvato su file!");
                    break;

                case 5:
                    System.out.println("Inserisci targa auto:");
                    String plate = scanner.nextLine();
                    System.out.println("Inserisci marca (BMW/Honda/...):");
                    String brandName = scanner.nextLine();
                    Brand brand = brandDao.getAll().stream()
                            .filter(b -> b.getName().equalsIgnoreCase(brandName))
                            .findFirst()
                            .orElse(null);
                    if (brand == null) {
                        System.out.println("Marca non trovata!");
                        break;
                    }
                    System.out.println("Inserisci modello auto:");
                    String model = scanner.nextLine();
                    System.out.println("Inserisci anno:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    Car car = new Car(year, plate, brand, model, true, 4, FuelType.GAS, Gear.AUTO); // esempio FuelType/Gear
                    carDao.add(car);
                    carDao.writeToFile(carDao.getAll());
                    System.out.println("Auto aggiunta!");
                    break;

                case 6:
                    System.out.println("Inserisci targa moto:");
                    String bikePlate = scanner.nextLine();
                    System.out.println("Inserisci marca moto:");
                    String bikeBrandName = scanner.nextLine();
                    Brand bikeBrand = brandDao.getAll().stream()
                            .filter(b -> b.getName().equalsIgnoreCase(bikeBrandName))
                            .findFirst()
                            .orElse(null);
                    if (bikeBrand == null) {
                        System.out.println("Marca non trovata!");
                        break;
                    }
                    System.out.println("Inserisci modello moto:");
                    String bikeModel = scanner.nextLine();
                    System.out.println("Inserisci anno:");
                    int bikeYear = scanner.nextInt();
                    scanner.nextLine();
                    Bike bike = new Bike(bikePlate, bikeYear, bikeBrand, bikeModel, true, false, EngineType.FOUR_STROKE);
                    bikeDao.add(bike);
                    brandDao.writeToFile(brandDao.getAll());
                    System.out.println("Moto aggiunta!");
                    break;
                    
                case 7:
                	System.out.println("Inserisci il numero di targa");
                	String userPlate = scanner.next();
                	System.out.println("Inserisci la mail dell'utente");
                	String userEmail = scanner.next();
                	
                	User user = userDao.findOneBy(userEmail);
                	
                	Car findCar = carDao.findOneBy(userPlate);
                	if(findCar != null) {
                		userDao.rentVehicol(user, findCar);
                		System.out.println("Veicolo aggiunto all'utente");
                	}
                	else if(bikeDao.findOneBy(userPlate) != null) {
                		userDao.rentVehicol(user, bikeDao.findOneBy(userPlate));
                		System.out.println("Veicolo aggiunto all'utente");
                	}
                	else {
                		System.out.println("Nessun veicolo trovato con questa targa");
                	}
                case 8:
                    exit = true;
                    break;

                default:
                    System.out.println("Opzione non valida!");
            }
        }

        scanner.close();
        System.out.println("Chiusura programma.");
    }
}
