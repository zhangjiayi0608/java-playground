package github.zayn.designpattern.chainpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName Service
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:33 下午
 **/
@Service
public class ChainEngine {
    @Autowired
    HandlerChain handlerChain;



    public long execute(InputDTO inputDTO) {
        long price = handlerChain.handle(inputDTO);
        return price;
    }
}
