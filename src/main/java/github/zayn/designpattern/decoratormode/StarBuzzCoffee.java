package github.zayn.designpattern.decoratormode;

public class StarBuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription() + " " + beverage.cost());
    }
}
