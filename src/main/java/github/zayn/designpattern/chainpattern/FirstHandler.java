package github.zayn.designpattern.chainpattern;

import org.springframework.stereotype.Component;

/**
 * @ClassName FirstHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:22 下午
 **/
@Component
public class FirstHandler implements BaseHandler {
    @Override
    public long calcPrice(InputDTO inputDTO) {
        System.out.println("do something in firstHandler");
        long count = inputDTO.getCount() + 10;
        return count;
    }

    @Override
    public boolean check(InputDTO inputDTO) {
        return inputDTO.getPromotionTypes().contains("FIRST");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
