package github.zayn.fsm.collectionstatemode;

/**
 * @ClassName CloseEvent
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/21 下午3:09
 **/
public class CloseEvent implements Event {
    @Override
    public String execute() {
        return "close";
    }
}
