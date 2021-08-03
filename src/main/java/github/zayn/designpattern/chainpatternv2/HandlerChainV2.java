package github.zayn.designpattern.chainpatternv2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import github.zayn.designpattern.chainpattern.BaseHandler;
import github.zayn.designpattern.chainpattern.InputDTO;

/**
 * @ClassName HandlerChain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:28 下午
 **/
@Component
public class HandlerChainV2 {
    @Autowired
    private final List<BaseHandler> baseHandlers;

    public HandlerChainV2(List<BaseHandler> baseHandlers) {
        baseHandlers.sort(Comparator.comparing(BaseHandler::getOrder));
        this.baseHandlers = baseHandlers;
    }

    @PostConstruct
    private void initHandlerChain() {
        Collections.sort(baseHandlers, AnnotationAwareOrderComparator.INSTANCE);
    }

    public final long handle(InputDTO inputDto) {
        long price = inputDto.getCount();
        for (BaseHandler baseHandler : baseHandlers) {
            if (baseHandler != null && baseHandler.check(inputDto)) {
                InputDTO input = new InputDTO();
                input.setCount(price);
                price = baseHandler.calcPrice(input);
            }
        }
        return price;
    }
}
