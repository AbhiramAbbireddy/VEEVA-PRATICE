import java.util.ArrayList;
import java.util.List;

// 1. Data payload object pushed to observers
class WeatherData {
    private final int temperature;
    private final int humidity;
    private final int pressure;

    public WeatherData(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public int getTemperature() { return temperature; }
    public int getHumidity()    { return humidity; }
    public int getPressure()    { return pressure; }

    @Override
    public String toString() {
        return "[Temp=" + temperature + "°C, Humidity=" + humidity + "%, Pressure=" + pressure + " hPa]";
    }
}

// 2. Observer Interface (Push model: update receives data payload)
interface Observer {
    void update(WeatherData weatherData);
}

// 3. Subject (Observable) Interface
interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}

// 4. Concrete Subject: WeatherStation
class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private WeatherData weatherData;

    @Override
    public void register(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(weatherData); // Pushes the weatherData object directly
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
        System.out.println("\n>>> Weather Station updated measurements: " + weatherData);
        notifyObservers();
    }
}

// 5. Concrete Observer: PhoneDisplay
class PhoneDisplay implements Observer {
    private final String user;

    public PhoneDisplay(String user) {
        this.user = user;
    }

    @Override
    public void update(WeatherData data) {
        System.out.println("Phone App [" + user + "] -> Alert: Current Temp: " + data.getTemperature() + "°C | Humidity: " + data.getHumidity() + "%");
    }
}

// 6. Concrete Observer: TVDisplay
class TVDisplay implements Observer {
    @Override
    public void update(WeatherData data) {
        System.out.println("TV Broadcast Screen -> Breaking Weather: " + data.getTemperature() + "°C, Pressure: " + data.getPressure() + " hPa");
    }
}

// 7. Concrete Observer: Logger
class WeatherLogger implements Observer {
    @Override
    public void update(WeatherData data) {
        System.out.println("Cloud Storage Logger -> Appending log: " + data);
    }
}

// 8. Client Demo
public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer phone1 = new PhoneDisplay("Alice");
        Observer phone2 = new PhoneDisplay("Bob");
        Observer tv = new TVDisplay();
        Observer logger = new WeatherLogger();

        station.register(phone1);
        station.register(phone2);
        station.register(tv);
        station.register(logger);

        // State update 1
        station.setWeatherData(new WeatherData(28, 65, 1013));

        System.out.println("\nBob unsubscribes from phone alerts.\n");
        station.remove(phone2);

        // State update 2
        station.setWeatherData(new WeatherData(32, 80, 1008));
    }
}
