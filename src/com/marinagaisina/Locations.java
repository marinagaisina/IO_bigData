package com.marinagaisina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    /* try block with resources!!

    public static void main(String[] args) throws IOException {

        try(FileWriter locFile = new FileWriter("locations.txt");
            FileWriter dirFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationID() + ","+location.getDescription()+"\n");
                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + ","+direction + "," +location.getExits().get(direction) +"\n");
                }
            }
        }
    }
    */
    // the static initialization block will be executed once, and that's when the locations class is loaded,
    // we do not need to create many instances of locations. Also, another option to make sure there will be
    // only one instance created - using a singleton design pattern
    static {
        /* --------Regular try/catch block!---------------

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations_big.txt")); // no need to close FileReader stream because there is a check if the source is an object of Closable interface, if it is, the scanner closes stream at line 63
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: "+loc+": "+description);
                locations.put(loc, new Location(loc, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
        ------------try/catch block with resources!----------------------
        */
        try(Scanner scanner = new Scanner(new FileReader("locations_big.txt"))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: "+loc+": "+description);
                locations.put(loc, new Location(loc, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /* Buffered Reader!! (Now read the exits:) READ BY ONE CHARACTER USING DELIMITER!

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();            //read by one character
                scanner.skip(scanner.delimiter());      //using delimiter
                String direction = scanner.next();      //next character
                scanner.skip(scanner.delimiter());
                int destination = Integer.parseInt(scanner.nextLine());
                System.out.println(loc+": "+direction+": "+destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        */
        //Buffered Reader!! (Now read the exits:) READ BY ENTIRE LINE!
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
            //scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println(loc+": "+direction+": "+destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
