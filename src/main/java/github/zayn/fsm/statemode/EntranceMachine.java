package github.zayn.fsm.statemode;

import java.util.Objects;

import github.zayn.fsm.InvalidActionException;

/**
 * @ClassName EntranceMachine
 * @DESCRIPTION 状态模式状态机
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午3:05
 **/
public class EntranceMachine {
    private EntranceMachineState locked = new LockEntranceMachineState();

    private EntranceMachineState unlocked = new UnLockEntranceMachineState();

    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        this.state = state;
    }

    public String execute(Action action) {
        if (Objects.isNull(action)) {
            throw new InvalidActionException();
        }
        if (Action.PASS.equals(action)) {
            return state.pass(this);
        }
        return state.inertCoin(this);
    }

    public boolean isUnlocked() {
        return state == unlocked;
    }

    public boolean isLocked() {
        return state == locked;
    }

    public String open() {
        setState(unlocked);
        return "opened";
    }

    public String alarm() {
        setState(locked);
        return "alarm";
    }

    public String refund() {
        setState(unlocked);
        return "refund";
    }

    public String close() {
        setState(locked);
        return "closed";
    }

    private void setState(EntranceMachineState state) {
        this.state = state;
    }


}
