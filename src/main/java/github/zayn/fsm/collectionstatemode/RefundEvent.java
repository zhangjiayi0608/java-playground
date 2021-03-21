package github.zayn.fsm.collectionstatemode;

/**
 * @ClassName RefundEvent
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/21 下午3:09
 **/
public class RefundEvent implements Event {
    @Override
    public String execute() {
        return "refund";
    }
}
