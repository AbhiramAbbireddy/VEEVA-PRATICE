# Observer Pattern — Push Model (Weather Station System)

## 1. Overview
In the **Push Model**, the Subject bundles all modified state into a data object (`WeatherData`) and **pushes** it directly as an argument to the observer's `update(WeatherData data)` method.

---

## 2. UML Diagram

```text
                           +-------------------------------+
                           |            Subject            | <<interface>>
                           +-------------------------------+
                           | + register(observer)          |
                           | + remove(observer)            |
                           | + notifyObservers()           |
                           +-------------------------------+
                                          ▲
                                          | implements
                                          |
                    +------------------------------------+
                    |           WeatherStation           | (Concrete Subject)
                    +------------------------------------+
                    | - observers : List<Observer>       |
                    | - weatherData : WeatherData        |
                    +------------------------------------+
                    | + setWeatherData(weatherData)      |
                    | + register(observer)               |
                    | + remove(observer)                 |
                    | + notifyObservers()                |
                    +------------------------------------+
                                          │
                                          │ pushes WeatherData
                                          ▼
                           +-------------------------------+
                           |           Observer            | <<interface>>
                           +-------------------------------+
                           | + update(WeatherData)         |
                           +-------------------------------+
                                          ▲
                         ┌────────────────┼────────────────┐
                         │ implements     │ implements     │ implements
          +-----------------------+ +------------------+ +--------------------+
          |      PhoneDisplay     | |     TVDisplay    | |    WeatherLogger   |
          +-----------------------+ +------------------+ +--------------------+
          | + update(WeatherData) | | + update(Data)   | | + update(Data)     |
          +-----------------------+ +------------------+ +--------------------+
```

---

## 3. Key Characteristics of the Push Model

| Trait | Description |
|:---|:---|
| **Simplicity** | Observers do not need to maintain a reference to the Subject. |
| **Coupling** | Observers depend only on the `WeatherData` payload class, not on the `WeatherStation` class. |
| **Trade-off** | If an observer only cares about temperature, it still receives humidity and pressure in the payload. |

---

## 4. Summary of Code Components
- **`WeatherData`**: Immutable value object holding `temperature`, `humidity`, and `pressure`.
- **`Subject` (`WeatherStation`)**: Collects sensor data and pushes it during `notifyObservers()`.
- **`Observer` (`PhoneDisplay`, `TVDisplay`, `WeatherLogger`)**: Specialized displays rendering the pushed metrics independently.
