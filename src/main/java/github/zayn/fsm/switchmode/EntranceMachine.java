package github.zayn.fsm.switchmode;

import github.zayn.fsm.InvalidActionException;

import java.util.Objects;

/**
 * @ClassName EntranceMachine
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午8:38
 **/
public class EntranceMachine {
    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        this.state = state;
    }

    public String execute(Action action) {
        if (Objects.isNull(action)) {
            throw new InvalidActionException();
        }
        if (EntranceMachineState.LOCK.equals(state)) {
            switch (action) {
                case INSERT_COIN:
                    setState(EntranceMachineState.UNLOCK);
                    return open();
                case PASS:
                    return alarm();
                default:
                    return null;
            }
        }
        if (EntranceMachineState.UNLOCK.equals(state)) {
            switch (action) {
                case INSERT_COIN:
                    return refund();
                case PASS:
                    setState(EntranceMachineState.UNLOCK);
                    return close();
                default:
                    return null;
            }
        }
        return null;
    }

    public String open() {
        return "opened";
    }

    public String alarm() {
        return "alarm";
    }

    public String refund() {
        return "refund";
    }

    public String close() {
        return "closed";
    }

    private void setState(EntranceMachineState state) {
        this.state = state;
    }


}
