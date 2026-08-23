interface EngineComponent {
    void start();
    void stop();
}

interface MediaComponent {
    void start();
    void stop();
}

class PetrolEngine implements EngineComponent {
    public void start() { System.out.println("Petrol Engine roaring."); }
    public void stop()  { System.out.println("Petrol Engine shut down."); }
}

class StereoPlayer implements MediaComponent {
    public void start() { System.out.println("Stereo playing track."); }
    public void stop()  { System.out.println("Stereo stopped track."); }
}

// Composition resolves the start/stop naming clash
public class NiceCar {
    private final EngineComponent engine;
    private final MediaComponent media;

    public NiceCar() {
        this.engine = new PetrolEngine();
        this.media = new StereoPlayer();
    }

    public void startCar()   { engine.start(); }
    public void stopCar()    { engine.stop(); }
    public void startMusic() { media.start(); }
    public void stopMusic()  { media.stop(); }

    public static void main(String[] args) {
        NiceCar car = new NiceCar();
        car.startCar();
        car.startMusic();
        car.stopMusic();
        car.stopCar();
    }
}
