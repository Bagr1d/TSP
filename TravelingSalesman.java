import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TravelingSalesman {
    public static void main(String[] args) {
        String inputFile = "berlin52.tsp";
        double optimum = 7544;

        try {
            ArrayList<City> cities = loadCities(inputFile);

            ArrayList<City> tour = nearestNeighborTSP(cities);
            double tourLength = calculateTourLength(tour);

            double d = (1 - (optimum / tourLength)) * 100;

            System.out.println("Znaleziona trasa: " + tour);
            System.out.println("Długość trasy: " + tourLength);
            System.out.println("Różnica od optimum: " + d + "%");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<City> loadCities(String filename) throws IOException {
        ArrayList<City> cities = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = br.readLine()) != null) {
            if (line.matches("\\d+.*")) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                cities.add(new City(id, x, y));
            }
        }
        br.close();

        return cities;
    }

    static ArrayList<City> nearestNeighborTSP(ArrayList<City> cities) {
        ArrayList<City> tour = new ArrayList<>();
        City startCity = cities.get(0);
        City currentCity = startCity;
        cities.remove(startCity);
        tour.add(startCity);

            while (!cities.isEmpty()) {
                City nearestCity = null;
                double minDistance = Double.MAX_VALUE;

            for (City city : cities) {
                double distance = currentCity.distanceTo(city);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCity = city;
                }
            }

            if (nearestCity != null) {
                tour.add(nearestCity);
                cities.remove(nearestCity);
                currentCity = nearestCity;
            }
        }

        tour.add(startCity);
        return tour;
    }

    static double calculateTourLength(ArrayList<City> tour) {
        double length = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            length += tour.get(i).distanceTo(tour.get(i + 1));
        }
        return length;
    }
}


