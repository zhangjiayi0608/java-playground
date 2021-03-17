package github.zayn.fsm.statemode;

/**
 * @ClassName EntranceMachineState
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午3:04
 **/
public interface EntranceMachineState {
    /**
     * 投入硬币的动作
     *
     * @param entranceMachine
     * @return
     */
    String inertCoin(EntranceMachine entranceMachine);

    /**
     * 通过闸机
     * @param entranceMachine
     * @return
     */
    String pass(EntranceMachine entranceMachine);
}
