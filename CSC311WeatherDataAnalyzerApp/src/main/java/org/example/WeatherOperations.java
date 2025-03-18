package org.example;

/**
 * An interface for weather operations.
 */
public interface WeatherOperations {
    /**
     * Determines the weather category based on temperature using an enhanced switch with pattern matching.
     *
     * **Example:**
     * ```
     * String category = WeatherOperations.weatherCategory(32.5);
     * ```
     *
     * @param temperature The temperature to categorize.
     * @return "Hot" if temperature ≥ 35°C, "Warm" if ≥ 25°C, or "Cold" otherwise.
     */
    static String weatherCategory(double temperature) {
        if (temperature >= 35.0) {
            return "Hot";
        } else if (temperature >= 25.0) {
            return "Warm";
        } else {
            return "Cold";
        }
    }
}

