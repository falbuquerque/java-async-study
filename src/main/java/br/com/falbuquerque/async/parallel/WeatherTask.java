package br.com.falbuquerque.async.parallel;

class WeatherTask implements Runnable {

    @Override
    public void run() {
        new WeatherReader().read10Cities();
    }

}
