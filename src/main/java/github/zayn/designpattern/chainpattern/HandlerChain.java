package github.zayn.designpattern.chainpattern;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    private final List<BaseHandler> baseHandlers;

    public HandlerChain(List<BaseHandler> baseHandlers) {
        baseHandlers.sort(Comparator.comparing(BaseHandler::getOrder));
        this.baseHandlers = Collections.unmodifiableList(baseHandlers);
    }

    private void initHandlerChain(){
        Collections.sort(baseHandlers, AnnotationAwareOrderComparator.INSTANCE);
    }

    public final long handle(InputDTO inputDto) {
        long price = inputDto.getCount();
        for (BaseHandler baseHandler : baseHandlers) {
            if (baseHandler != null) {
                InputDTO input = new InputDTO();
                input.setCount(price);
                price = baseHandler.calcPrice(input);
            }
        }
        return price;
    }
}
