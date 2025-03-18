package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A record that analyzes weather data.
 *
 * **Example:**
 * ```
 * WeatherDataAnalyzer analyzer = new WeatherDataAnalyzer(weatherDataList);
 * double avgTemp = analyzer.averageTemperature(8);
 * ```
 *
 * @param data The list of weather data entries.
 */
public record WeatherDataAnalyzer(List<WeatherData> data) {
    /**
     * Computes the average temperature for the given month.
     *
     * **Example:**
     * ```
     * double avg = analyzer.averageTemperature(8);
     * ```
     *
     * @param month The month (1-12) for which to compute the average temperature.
     * @return The average temperature, or NaN if no data exists.
     */
    public double averageTemperature(int month) {
        return data.stream()
                .filter(wd -> wd.date().getMonthValue() == month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Returns the dates with temperatures above the given threshold.
     *
     * **Example:**
     * ```
     * List<LocalDate> hotDays = analyzer.daysAboveTemperature(33.0);
     * ```
     *
     * @param threshold The temperature threshold.
     * @return A list of dates.
     */
    public List<LocalDate> daysAboveTemperature(double threshold) {
        return data.stream()
                .filter(wd -> wd.temperature() > threshold)
                .map(WeatherData::date)
                .collect(Collectors.toList());
    }

    /**
     * Counts the number of rainy days (days with precipitation greater than 0).
     *
     * **Example:**
     * ```
     * long rainyCount = analyzer.countRainyDays();
     * ```
     *
     * @return The count of rainy days.
     */
    public long countRainyDays() {
        return data.stream()
                .filter(wd -> wd.precipitation() > 0.0)
                .count();
    }

    /**
     * Generates a formatted weather report using a text block.
     *
     * **Example:**
     * ```
     * System.out.println(analyzer.generateReport());
     * ```
     *
     * @return A multi-line weather report.
     */
    public String generateReport() {
        String report = """
                        # Weather Data Report
                        
                        Average Temperature (August): %.2f°C
                        Days above threshold (33°C): %s
                        Count of Rainy Days: %d
                        """.formatted(
                averageTemperature(8),
                daysAboveTemperature(33.0),
                countRainyDays()
        );
        return report.stripIndent();
    }
}

