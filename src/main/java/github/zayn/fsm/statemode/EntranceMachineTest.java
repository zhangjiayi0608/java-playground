package github.zayn.fsm.statemode;

/**
 * @ClassName EntranceMachineTest
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午3:43
 **/
public class EntranceMachineTest {
    public static void main(String[] args) {
        EntranceMachine entranceMachine = new EntranceMachine(new LockEntranceMachineState());
        String result = entranceMachine.execute(Action.INSERT_COIN);
        boolean locked = entranceMachine.isLocked();
        boolean unlocked = entranceMachine.isUnlocked();
        String result2 = entranceMachine.execute(Action.INSERT_COIN);
        System.out.println(entranceMachine);
    }
}
