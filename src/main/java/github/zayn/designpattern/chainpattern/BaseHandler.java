package github.zayn.designpattern.chainpattern;

import org.springframework.core.Ordered;

/**
 * @ClassName BaseHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:15 下午
 **/
public abstract class BaseHandler implements Ordered {
    public long calcPrice(InputDTO inputDTO) {
        return 0;
    }

    public boolean check(InputDTO inputDTO) {
        return false;
    }
}
