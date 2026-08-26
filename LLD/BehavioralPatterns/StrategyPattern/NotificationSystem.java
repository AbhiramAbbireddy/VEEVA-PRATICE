
interface NotificationStrategy {
    void sendNotification(String message);
}

class Email implements NotificationStrategy {
    @Override
    public void sendNotification(String msg) {
        System.out.println("This notification is from email, Message: "+msg);
    }
}
class Sms implements NotificationStrategy {
    @Override
    public void sendNotification(String msg) {
        System.out.println("This notification is from sms, Message: "+msg);
    }
}
class PushNotify implements NotificationStrategy {
    @Override
    public void sendNotification(String msg) {
        System.out.println("This notification is from Notify, Message: "+msg);
    }
}

class NotificationService {
    private NotificationStrategy notificationStrategy;
    public NotificationService(NotificationStrategy notificationStrategy) {
        this.notificationStrategy=notificationStrategy;
    }

    public void setStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy=notificationStrategy;
    }
    public void notify(String msg) {
        notificationStrategy.sendNotification(msg);
    }
}
public class NotificationSystem {
    public static void main(String[] args) {
        
        String message="Hello, This is Abhiram";

        NotificationService notificationService=new NotificationService(new Email());

        notificationService.notify(message);

        notificationService.setStrategy(new Sms());

        notificationService.notify(message);



    }   
}
