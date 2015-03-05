package br.com.falbuquerque.async.parallel;

class WeatherResource {

    private final Weather[] weather;

    WeatherResource(final Weather[] weather) {
        this.weather = weather;
    }

    Weather[] getWeather() {
        return weather;
    }

}
