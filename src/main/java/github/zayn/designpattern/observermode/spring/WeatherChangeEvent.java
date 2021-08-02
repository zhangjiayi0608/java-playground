package github.zayn.designpattern.observermode.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName WeatherChangeEvent
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/28 11:51 上午
 **/
public class WeatherChangeEvent extends ApplicationEvent {
    public WeatherChangeEvent(Object source) {
        super(source);
    }


}
