package github.zayn.fsm.statemode;

/**
 * @ClassName LockEnteanceMachineState
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午3:11
 **/
public class LockEntranceMachineState implements EntranceMachineState {
    @Override
    public String inertCoin(EntranceMachine entranceMachine) {
        return entranceMachine.open();
    }

    @Override
    public String pass(EntranceMachine entranceMachine) {
        return entranceMachine.alarm();
    }
}
