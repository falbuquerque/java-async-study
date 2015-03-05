package br.com.falbuquerque.async.parallel;

class Weather {

    private final String main;
    private final String description;

    Weather(final String main, final String description) {
        this.main = main;
        this.description = description;
    }

    String getMain() {
        return main;
    }

    String getDescription() {
        return description;
    }

}
