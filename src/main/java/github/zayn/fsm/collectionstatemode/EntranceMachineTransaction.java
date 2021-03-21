package github.zayn.fsm.collectionstatemode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName EntranceMachineTransaction
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/21 下午2:57
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntranceMachineTransaction {
    private EntranceMachineState currentState;
    private Action action;
    private EntranceMachineState nextState;
    private Event event;
}
