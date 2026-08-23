class Box {
    double l, h, w;

    public Box(double l, double h, double w) {
        this.l = l;
        this.h = h;
        this.w = w;
    }

    public double volume() {
        return l * h * w;
    }
}

class BoxWeight extends Box {
    double weight;

    public BoxWeight(double l, double h, double w, double weight) {
        super(l, h, w); // Parent initialization must occur first
        this.weight = weight;
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        BoxWeight parcel = new BoxWeight(2.0, 3.0, 4.0, 10.5);
        System.out.println("Volume: " + parcel.volume());
        System.out.println("Weight: " + parcel.weight);
    }
}
