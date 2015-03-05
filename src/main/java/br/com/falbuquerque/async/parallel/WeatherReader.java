package br.com.falbuquerque.async.parallel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

class WeatherReader {

    private final RestTemplate restClient = new RestTemplate();
    private final Gson gson = new Gson();
    
    void read10Cities() {
        read("Sao_Paulo,br");
        read("Rio,rj,br");
        read("Ubatuba,sp,br");
        read("Paraty,rj,br");
        read("London,en");
        read("Seattle,usa");
        read("Paris,fr");
        read("Beijing,china");
        read("Calgary,ca");
        read("Montreal,ca");
    }

    void read(final String city) {
        final String json = restClient.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + city, String.class);
        final WeatherResource weatherResource = gson.fromJson(json, WeatherResource.class);

        try (final PrintWriter printWriter = new PrintWriter(new File("/dev/tmp/parallel.txt"));
                final BufferedWriter writer = new BufferedWriter(printWriter);) {
            writer.write(String.format("%s=%s - %s", city, weatherResource.getWeather()[0].getMain(),
                    weatherResource.getWeather()[0].getDescription()));
            writer.flush();
        } catch (final IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
