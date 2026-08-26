# Observer Pattern — Pull Model (YouTube Notification System)

## 1. Overview
In the **Pull Model**, when the Subject's state changes, it invokes the Observer's `update()` method **without passing any data payload**. The Observer holds a reference to the Subject (`Observable`) and explicitly **pulls** only the specific information it requires via getter methods.

---

## 2. UML & Architecture

```text
                           +---------------------------+
                           |        Observable         | <<interface>>
                           +---------------------------+
                           | + subscribe(observer)     |
                           | + unsubscribe(observer)   |
                           | + notifySubscribers()     |
                           +---------------------------+
                                       ▲
                                       |
                        implements     |
                                       |
                    +--------------------------------+
                    |        YouTubeChannel          |
                    +--------------------------------+
                    | - channelName : String         |
                    | - latestVideo : String         |
                    | - subscribers : List<Observer> |
                    +--------------------------------+
                    | + uploadVideo(title)           |
                    | + subscribe(observer)          |
                    | + unsubscribe(observer)        |
                    | + notifySubscribers()          |
                    | + getLatestVideo()             |
                    | + getChannelName()             |
                    +--------------------------------+

                                       ▲
                                       |
                         uses          |
                                       |
                    +---------------------------+
                    |         Observer          | <<interface>>
                    +---------------------------+
                    | + update()                |
                    +---------------------------+
                               ▲
                    -------------------------
                    |                       |
                    |                       |
     +---------------------------+   +----------------------------+
     |      FreeSubscriber       |   |     PremiumSubscriber      |
     +---------------------------+   +----------------------------+
     | - name : String           |   | - name : String            |
     | - channel : Observable    |   | - channel : Observable     |
     +---------------------------+   +----------------------------+
     | + update()                |   | + update()                 |
     | + unsubscribe()           |   | + unsubscribe()            |
     +---------------------------+   +----------------------------+
```

---

## 3. Step-by-Step Implementation Walkthrough

### Step 1: `Observer` Interface
Defines the parameter-free `update()` callback:
```java
public interface Observer {
    void update();
}
```

### Step 2: `Observable` Interface
Defines the subscription management contract:
```java
public interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifySubscribers();
}
```

### Step 3: Concrete Subject (`YouTubeChannel`)
Maintains the subscriber list and triggers notifications on video uploads:
```java
public class YouTubeChannel implements Observable {
    private String channelName;
    private String latestVideo;
    private List<Observer> subscribers = new ArrayList<>();

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;
        System.out.println(channelName + " uploaded: " + videoTitle);
        notifySubscribers();
    }
    public String getLatestVideo() { return latestVideo; }
}
```

### Step 4 & 5: Concrete Observers (`FreeSubscriber` & `PremiumSubscriber`)
Demonstrates **polymorphism** where different tiers format the pulled notification uniquely:
```java
public class FreeSubscriber implements Observer {
    private String name;
    private YouTubeChannel channel;

    @Override
    public void update() {
        System.out.println("Free User : " + name + " -> Watch with Ads -> " + channel.getLatestVideo());
    }
}

public class PremiumSubscriber implements Observer {
    private String name;
    private YouTubeChannel channel;

    @Override
    public void update() {
        System.out.println("Premium User : " + name + " -> Watch Ad-Free in 4K -> " + channel.getLatestVideo());
    }
}
```

---

## 4. Key Takeaways of the Pull Model
1. **Selective Data Fetching:** Observers are not burdened with unnecessary data; they query only what is relevant to their role.
2. **Encapsulated Unsubscription:** By holding a reference to the `YouTubeChannel`, each subscriber can self-unsubscribe cleanly (`subscriber.unsubscribe()`).
3. **High Flexibility:** If the Subject adds 20 new fields in the future, the `Observer` interface signature (`update()`) never breaks.
