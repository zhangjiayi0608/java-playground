package github.zayn.designpatterntraining.decoratormode;

public class Espresso implements Beverage {
    @Override
    public String getDescription() {
        return getClass().getName();
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
