package github.zayn.designpattern.chainpattern;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

/**
 * @ClassName HandlerChain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 3:28 下午
 **/
@Component
public class HandlerChain {
    @Autowired
    private List<BaseHandler> baseHandlers;

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
