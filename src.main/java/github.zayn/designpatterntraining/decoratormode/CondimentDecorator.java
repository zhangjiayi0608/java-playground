package github.zayn.designpatterntraining.decoratormode;


/**
 * 调料类
 *
 * @author zhangjiayi
 */
public interface CondimentDecorator extends Beverage {
    @Override
    String getDescription();

}
