package github.zayn.designpattern.decoratormode;

public class HouseBlend implements Beverage {
    @Override
    public String getDescription() {
        return getClass().getName();
    }

    @Override
    public double cost() {
        return 2.00;
    }
}
