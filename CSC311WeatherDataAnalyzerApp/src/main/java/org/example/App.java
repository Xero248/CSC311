package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The application entry point.
 *
 * **Example:**
 * ```
 * java App
 * ```
 */
public interface App {
    /**
     * Main method to run the Weather Data Analyzer Application.
     *
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        try {
            // Read CSV file, skipping the header line, and parse each line into a WeatherData record
            List<WeatherData> weatherData = Files.lines(Path.of("weatherdata.csv"))
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(WeatherData::fromCSV)
                    .toList();

            WeatherDataAnalyzer analyzer = new WeatherDataAnalyzer(weatherData);

            // Print the generated weather report
            System.out.println(analyzer.generateReport());

            // For each weather data entry, print the date and its corresponding weather category
            weatherData.forEach(wd -> System.out.println(
                    wd.date() + ": " + WeatherOperations.weatherCategory(wd.temperature())
            ));
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}

