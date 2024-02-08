package repository;

import model.Builder;
import model.Guitar;
import model.Type;
import model.Wood;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class InventoryRepository {

        private static final String DATABASE_FILE = "guitars_database.txt";

        public static void addGuitar(Guitar guitar) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_FILE, true))) {
                writer.println(guitarToString(guitar));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Guitar getGuitar(String serialNumber) {
            try (Scanner scanner = new Scanner(new File(DATABASE_FILE))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Guitar guitar = stringToGuitar(line);
                    if (guitar.getSerialNumber().equals(serialNumber)) {
                        return guitar;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Guitar> search(Guitar searchCriteria) {
            ArrayList<Guitar> matchingGuitars = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(DATABASE_FILE))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Guitar guitar = stringToGuitar(line);
                    if (matchesSearchCriteria(guitar, searchCriteria)) {
                        matchingGuitars.add(guitar);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return matchingGuitars;
        }

        //Helper method for search
        private boolean matchesSearchCriteria(Guitar guitar, Guitar searchCriteria) {
            return (searchCriteria.getBuilder() == null || searchCriteria.getBuilder().equals(guitar.getBuilder())) &&
                    (searchCriteria.getBackWood() == null || searchCriteria.getBackWood().equals(guitar.getBackWood()));
        }

        //Helper method that converts a guitar to a String
        private static String guitarToString(Guitar guitar) {
            return String.format("%s,%s,%s,%s,%s,%s,%s",
                    guitar.getBuilder().toString(), guitar.getModel(), guitar.getType().toString(),
                    guitar.getBackWood().toString(), guitar.getTopWood().toString(),
                    guitar.getSerialNumber(), guitar.getPrice());
        }

        //Helper method that converts a string to a guitar
        Guitar stringToGuitar(String line) {
            String[] parts = line.split(",");
            Guitar guitar = new Guitar();
            guitar.setBuilder(Builder.valueOf(parts[0]));
            guitar.setModel(parts[1]);
            guitar.setType(Type.valueOf(parts[2]));
            guitar.setBackWood(Wood.valueOf(parts[3]));
            guitar.setTopWood(Wood.valueOf(parts[4]));
            guitar.setSerialNumber(parts[5]);
            guitar.setPrice(Float.parseFloat(parts[6]));
            return guitar;
        }
}