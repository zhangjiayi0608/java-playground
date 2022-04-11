package github.zayn.designpattern.chainpatternv2;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import github.zayn.designpattern.chainpattern.BaseHandler;
import github.zayn.designpattern.chainpattern.FirstHandler;
import github.zayn.designpattern.chainpattern.InputDTO;
import github.zayn.designpattern.chainpattern.SecondHandler;

/**
 * @ClassName Service
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:33 下午
 **/
@Service
public class ChainEngineV2 {
    public long execute(InputDTO inputDTO) {
        List<BaseHandler> handlerList = Lists.newArrayList();
        handlerList.add(new FirstHandler());
        handlerList.add(new SecondHandler());
        HandlerChainV2 handlerChainV2 = new HandlerChainV2(handlerList);
        return handlerChainV2.handle(inputDTO);
    }
}
