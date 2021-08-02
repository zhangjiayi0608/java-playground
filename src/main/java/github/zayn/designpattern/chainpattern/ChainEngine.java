package github.zayn.designpattern.chainpattern;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @ClassName Service
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:33 下午
 **/
@Service
public class ChainEngine {

    public long execute(InputDTO inputDTO) {
        List<BaseHandler> baseHandlerList = new ArrayList<>();
        baseHandlerList.add(new FirstHandler());
        baseHandlerList.add(new SecondHandler());
        HandlerChain handlerChain = new HandlerChain(baseHandlerList);
        long price = handlerChain.handle(inputDTO);
        return price;
    }
}
