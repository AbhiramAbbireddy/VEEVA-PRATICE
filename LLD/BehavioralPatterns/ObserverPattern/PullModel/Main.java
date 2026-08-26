import java.util.ArrayList;
import java.util.List;

// 1. Observer Interface (Pull model: update() takes no payload)
interface Observer {
    void update();
}

// 2. Observable Interface (Subject)
interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifySubscribers();
}

// 3. Concrete Subject: YouTubeChannel
class YouTubeChannel implements Observable {
    private final String channelName;
    private String latestVideo;
    private final List<Observer> subscribers = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println("Subscriber added.");
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.println("Subscriber removed.");
    }

    @Override
    public void notifySubscribers() {
        for (Observer observer : subscribers) {
            observer.update();
        }
    }

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;

        System.out.println("\n===============================");
        System.out.println(channelName + " uploaded:");
        System.out.println(videoTitle);
        System.out.println("===============================\n");

        notifySubscribers();
    }

    public String getLatestVideo() {
        return latestVideo;
    }

    public String getChannelName() {
        return channelName;
    }
}

// 4. Concrete Observer: FreeSubscriber (Pulls latest video with Ad-supported message)
class FreeSubscriber implements Observer {
    private final String name;
    private final YouTubeChannel channel;

    public FreeSubscriber(String name, YouTubeChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update() {
        // Pulls data from the channel reference
        System.out.println(
            "Free User : " + name +
            "\nNotification : New video uploaded!" +
            "\nWatch with Ads -> " + channel.getLatestVideo()
        );
        System.out.println();
    }

    public void unsubscribe() {
        channel.unsubscribe(this);
    }
}

// 5. Concrete Observer: PremiumSubscriber (Pulls latest video with 4K Ad-Free message)
class PremiumSubscriber implements Observer {
    private final String name;
    private final YouTubeChannel channel;

    public PremiumSubscriber(String name, YouTubeChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update() {
        // Pulls data from the channel reference
        System.out.println(
            "Premium User : " + name +
            "\nNotification : New Premium Video!" +
            "\nWatch Ad-Free in 4K -> " + channel.getLatestVideo()
        );
        System.out.println();
    }

    public void unsubscribe() {
        channel.unsubscribe(this);
    }
}

// 6. Client Demo Runner
public class Main {
    public static void main(String[] args) {
        YouTubeChannel telusko = new YouTubeChannel("Telusko");

        FreeSubscriber abhi = new FreeSubscriber("Abhi", telusko);
        FreeSubscriber john = new FreeSubscriber("John", telusko);

        PremiumSubscriber alice = new PremiumSubscriber("Alice", telusko);
        PremiumSubscriber david = new PremiumSubscriber("David", telusko);

        telusko.subscribe(abhi);
        telusko.subscribe(john);
        telusko.subscribe(alice);
        telusko.subscribe(david);

        telusko.uploadVideo("Observer Pattern in Java");

        System.out.println("\nJohn unsubscribed.\n");
        john.unsubscribe();

        telusko.uploadVideo("Factory Pattern in Java");
    }
}
