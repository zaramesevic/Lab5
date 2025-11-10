import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Cities...");
        File f = new File("cities.csv");
        ArrayList<City> cities = loadAll(false);

        System.out.println("Cities: " + cities.size());
        ArrayList<City> capitols = new ArrayList<>();
        ArrayList<City> nonCapitols = new ArrayList<>();
        for(City c: cities) {
            if(c.isCapitol()) {
                capitols.add(c);
            } else {
                nonCapitols.add(c);
            }
        }
        System.out.println("Cities count: " + cities.size());
        System.out.println("Capitols: " + capitols.size());
        System.out.println("Non capitols: " + nonCapitols.size());
        Boolean r = addCity("Sarajevo1,Bosnia and Herzegovina,large,true", "cities.csv");
        if(r) System.out.println("Succeded");
        else System.out.println("Failed");


        /*File fCapitols = new File("capitols.csv");
        File fNonCapitols = new File("non_capitols.csv");

       Boolean okC = writeToFile("capitols.csv", capitols);
        if(okC) {
            System.out.println("File w Capitols written!");
        }
        Boolean okNc = writeToFile("non_capitols.csv", nonCapitols);
        if(okNc) {
            System.out.println("File w non-Capitols written!");
        }*/


    }

    private static Boolean addCity(String line, String path) {
        String[] parts = line.split(",");
        if (parts.length != 4 ) System.out.println("ERROR: " + line);
        else {
            City c = new City(
                    parts[0], // City name -> Tokyo
                    parts[1], // Country -> Japan
                    parts[2], // Size -> large
                    Boolean.parseBoolean(parts[3])  // Capitol -> False
            );
            if(writeToFile("cities.csv", c)) System.out.println("true");
            return true;
        }
        return false;
    }

    public static Boolean writeToFile(String fPath, ArrayList<City> cities) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fPath));
            bw.write(fPath.split("\\.")[0] + ": ");
            for (City c : cities) {
                bw.write(c.toString());
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static Boolean writeToFile(String fPath, City city) {
        try (FileWriter fw = new FileWriter("cities.csv", true)) {
            fw.append("\n");
            fw.write(city.toString());
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static ArrayList<City> loadAll(
            Boolean includeHeaders
    )
            throws FileNotFoundException {
        File f = new File("cities.csv");
        ArrayList<City> cities = new ArrayList<>();
        Set<String> citySizes = new HashSet<>();
        Scanner sc = new Scanner(f);
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");
            if (parts.length != 4) System.out.println("ERROR: " + line);
            else {
                City c = new City(
                        parts[0], // City name -> Tokyo
                        parts[1], // Country -> Japan
                        parts[2], // Size -> large
                        Boolean.parseBoolean(parts[3])  // Capitol -> False
                );
                cities.add(c);
                citySizes.add(c.getSize());
            }
        }
        return cities;

    }
}