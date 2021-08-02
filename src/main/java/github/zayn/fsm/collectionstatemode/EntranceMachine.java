package github.zayn.fsm.collectionstatemode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import github.zayn.fsm.InvalidActionException;
import lombok.Data;


/**
 * @ClassName EntranceMachine
 * @DESCRIPTION 状态集合状态机
 * @Author zhangjiayi07
 * @Date 2021/3/16 下午8:38
 **/
@Data
public class EntranceMachine {
    private List<EntranceMachineTransaction> entranceMachineTransactionList = Arrays.asList(
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.LOCKED)
                    .action(Action.INSERT_COIN)
                    .nextState(EntranceMachineState.UNLOCKED)
                    .event(new OpenEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.LOCKED)
                    .action(Action.PASS)
                    .nextState(EntranceMachineState.LOCKED)
                    .event(new AlarmEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.UNLOCKED)
                    .action(Action.PASS)
                    .nextState(EntranceMachineState.LOCKED)
                    .event(new CloseEvent())
                    .build(),
            EntranceMachineTransaction.builder()
                    .currentState(EntranceMachineState.UNLOCKED)
                    .action(Action.INSERT_COIN)
                    .nextState(EntranceMachineState.UNLOCKED)
                    .event(new RefundEvent())
                    .build()
    );


    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        this.state = state;
    }

    public String execute(Action action) {
        Optional<EntranceMachineTransaction> transactionOptional = entranceMachineTransactionList
                .stream()
                .filter(transaction ->
                        transaction.getAction().equals(action) && transaction.getCurrentState().equals(state))
                .findFirst();
        if (!transactionOptional.isPresent()) {
            throw new InvalidActionException();
        }
        EntranceMachineTransaction transaction = transactionOptional.get();
        setState(transaction.getNextState());
        return transaction.getEvent().execute();
    }


    private void setState(EntranceMachineState state) {
        this.state = state;
    }


}
