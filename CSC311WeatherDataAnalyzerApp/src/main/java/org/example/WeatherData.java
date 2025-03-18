package org.example;

import java.time.LocalDate;

/**
 * A record representing a single weather data entry.
 *
 * **Example:**
 * ```
 * WeatherData wd = WeatherData.fromCSV("2023-08-01,32.5,65,0.0");
 * ```
 *
 * @param date          The date of the weather data.
 * @param temperature   The temperature in Celsius.
 * @param humidity      The humidity percentage.
 * @param precipitation The precipitation in mm.
 */
public record WeatherData(LocalDate date, double temperature, int humidity, double precipitation) {
    public static WeatherData fromCSV(String csvLine) {
        var parts = csvLine.split(",");
        return new WeatherData(
                LocalDate.parse(parts[0].trim()),
                Double.parseDouble(parts[1].trim()),
                Integer.parseInt(parts[2].trim()),
                Double.parseDouble(parts[3].trim())
        );
    }
}
