package github.zayn.designpattern.chainpattern;

import org.springframework.stereotype.Component;

/**
 * @ClassName SecondHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:22 下午
 **/
@Component
public class SecondHandler implements BaseHandler {
    @Override
    public long calcPrice(InputDTO inputDTO) {
        System.out.println("do something in secondHandler");
        long count = inputDTO.getCount() + 100;
        return count;
    }

    @Override
    public boolean check(InputDTO inputDTO) {
        return inputDTO.getPromotionTypes().contains("SECOND");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
