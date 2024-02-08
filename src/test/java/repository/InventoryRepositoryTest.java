

package repository;

import model.Builder;
import model.Type;
import model.Wood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Guitar;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




class InventoryRepositoryTest {

    private InventoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InventoryRepository();
    }

    @Test
    void addGuitar() {
        Guitar guitarToAdd = new Guitar(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER, Wood.MAPLE, "SN123", 999.99);
        repository.addGuitar(guitarToAdd);
        Guitar retrievedGuitar = findGuitarInFile(guitarToAdd.getSerialNumber());
        assertNotNull(retrievedGuitar);
        assertEquals(guitarToAdd.getBuilder(), retrievedGuitar.getBuilder());
    }

    @Test
    void addMultipleGuitars() {

        Guitar guitar1 = new Guitar(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER, Wood.MAPLE, "SN123", 999.99);
        Guitar guitar2 = new Guitar(Builder.GIBSON, "Les Paul", Type.ELECTRIC, Wood.MAHOGANY, Wood.MAPLE, "SN456", 1499.99);


        repository.addGuitar(guitar1);
        repository.addGuitar(guitar2);

        Guitar retrievedGuitar1 = findGuitarInFile(guitar1.getSerialNumber());
        assertNotNull(retrievedGuitar1);
        assertEquals(guitar1.getSerialNumber(), retrievedGuitar1.getSerialNumber());

        Guitar retrievedGuitar2 = findGuitarInFile(guitar2.getSerialNumber());
        assertNotNull(retrievedGuitar2);
        assertEquals(guitar2.getSerialNumber(), retrievedGuitar2.getSerialNumber());
    }

    @Test
    void getGuitar() {
        Guitar guitarToAdd = new Guitar(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER, Wood.MAPLE, "SN123", 999.99);
        repository.addGuitar(guitarToAdd);
        Guitar retrievedGuitar = repository.getGuitar("SN123");
        assertNotNull(retrievedGuitar);
        assertEquals(guitarToAdd.getSerialNumber(), retrievedGuitar.getSerialNumber());

    }

    @Test
    void search() {
        clearDatabase();
        Guitar guitar1 = new Guitar(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER, Wood.MAPLE, "SN123", 999.99);
        Guitar guitar2 = new Guitar(Builder.GIBSON, "Les Paul", Type.ELECTRIC, Wood.MAHOGANY, Wood.MAPLE, "SN456", 1499.99);
        repository.addGuitar(guitar1);
        repository.addGuitar(guitar2);
        Guitar searchCriteria = new Guitar(Builder.FENDER, null, null, Wood.ALDER, null, null, -1);
        List<Guitar> matchingGuitars = repository.search(searchCriteria);


        assertNotNull(matchingGuitars);
        assertEquals(1, matchingGuitars.size());
        assertEquals(guitar1.getSerialNumber(), matchingGuitars.get(0).getSerialNumber());
    }

    // Helper method to find a guitar in the file by serial number
    private Guitar findGuitarInFile(String serialNumber)
    {
        try {
            File file = new File("guitars_database.txt");
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Guitar guitar = new InventoryRepository().stringToGuitar(line);
                if (guitar.getSerialNumber().equals(serialNumber)) {
                    return guitar;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearDatabase(){
        String filePath = "C:\\c322-spring2024-homework-2\\guitars_database.txt";

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}