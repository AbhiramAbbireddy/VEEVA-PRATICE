
interface Discount {
    void discount(int amount);
}

class NoDiscount implements Discount {
    @Override
    public void discount(int amount) {
        System.out.println("No Discount, Original price: "+amount);
    }
}

class FlatDiscount implements Discount {
    @Override
    public void discount(int amount) {
        System.out.println("Original price: "+amount);
        amount-=500;
        System.out.println("Flat 500rs dicount, Original price: "+amount);
    }
}

class Percentage implements Discount {
    @Override
    public void discount(int amount) {
        System.out.println("Original price: "+amount);
        int dis=(int) ((int) amount*0.2);
        amount-=dis;
        System.out.println("After 20% percentage of discount, Price is : "+amount);
    }
}
class FestivalDiscount implements Discount {
    @Override
    public void discount(int amount) {
        System.out.println("Original price: "+amount);
        //Assume festival discount is 50%
        int dis=(int) ((int) amount*0.5);
        amount-=dis;
        System.out.println("After festival discount, the price is : "+amount)   ;
    }
}

class CheckoutService {
    private Discount discount;

    public CheckoutService(Discount discount) {
        this.discount=discount;
    }

    public void setStrategy(Discount discount) {
        this.discount=discount;
    }

    public void getDiscount(int amount) {
        discount.discount(amount);
    }
}

public class DiscountEngine {
    public static void main(String[] args) {
        
        CheckoutService service;

        int amount=3000;

        service=new CheckoutService(new NoDiscount());
        service.getDiscount(amount);

        service.setStrategy(new FlatDiscount());
        service.getDiscount(amount);

        service.setStrategy(new FestivalDiscount());
        service.getDiscount(amount);

        service.setStrategy(new Percentage());
        service.getDiscount(amount);


    }
}