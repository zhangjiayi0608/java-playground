package github.zayn.fsm.collectionstatemode;

/**
 * @ClassName OpenEvent
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/21 下午3:09
 **/
public class OpenEvent implements Event {
    @Override
    public String execute() {
        return "open";
    }
}
