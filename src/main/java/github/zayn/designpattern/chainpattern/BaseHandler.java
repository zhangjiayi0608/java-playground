package github.zayn.designpattern.chainpattern;

import org.springframework.core.Ordered;

/**
 * @ClassName BaseHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:15 下午
 **/
public interface BaseHandler extends Ordered {
    long calcPrice(InputDTO inputDTO);

    boolean check(InputDTO inputDTO);
}
