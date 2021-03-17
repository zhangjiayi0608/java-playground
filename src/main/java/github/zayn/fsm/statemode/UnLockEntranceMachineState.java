package github.zayn.fsm.statemode;

/**
 * @ClassName UnLockEntranceMachineState
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午3:11
 **/
public class UnLockEntranceMachineState implements EntranceMachineState {
    @Override
    public String inertCoin(EntranceMachine entranceMachine) {
        return entranceMachine.refund();
    }

    @Override
    public String pass(EntranceMachine entranceMachine) {
        return entranceMachine.close();
    }
}
